/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.handler;

import static com.release.common.BaseType.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import com.release.core.AbstractBuilder;
import com.release.core.BufferedReaderCallback;
import com.release.util.Conf;
import com.release.util.FileUtil;
import com.release.util.SeleniumUtil;
import com.release.util.ZipUtil;
import com.release.vo.DataVO;


/**
 * The Class PackageBuilder.
 */
public class PackageBuilder extends AbstractBuilder {

	/** data */
	private DataVO data;

	/**
	 * <pre>
	 * preHandle
	 * 적용 파일 리스트 추출
	 * 적용 파일 존재 유무 확인
	 * <pre>
	 * @return
	 */
	@Override
	protected boolean preHandle() {
		System.out.println("#########################################################");
		System.out.println("## PACKAGE");
		System.out.println("#########################################################");

		// 적용 파일 리스트 추출 후 set
		String packageFile = makePath(PACKAGE_FILE_NAME, data.getReleaseNum());
		List<String> packageFilePathList = makePackageFilePathList(packageFile);
		data.setPackageFilePathList(getUniqueList(packageFilePathList));

		// 적용 파일 존재 유무 확인
		if (existFile(data.getPackageFilePathList()) == false) {
			return false;
		}

		return true;
	}

	/**
	 * <pre>
	 * 패키징 진행
	 *
	 * <pre>
	 */
	@Override
	protected void process() {
		// 적용 소스를 저장할 디렉토리 생성
		String packageDir = makePath(SOURCE_DIRECTORY, data.getReleaseNum());
		makeDir(packageDir);

		// 원본 적용 파일 리스트
		List<String> packageFilePathList = data.getPackageFilePathList();

		// 원본 적용 파일 리스트 중 중복되는 파일 name 저장 객체
		List<String> fileNameList = new ArrayList<String>();

		// cvs형식의 install 파일 리스트
		List<String> csvInstallFilePathList = new ArrayList<String>();

		for (String packageFilePath : packageFilePathList) {
			String packageFileName = new File(packageFilePath).getName();
			String destinationFilePath = getDestinationFilePath(fileNameList, packageFileName, packageDir);

			// 적용되는 파일의 소스들을 source 디렉토리에 copy
			FileUtil.nioCopy(packageFilePath, destinationFilePath);
			System.out.println("##process##(package file copy) packageFilePath=" + packageFilePath + ", destinationFilePath=" + destinationFilePath);

			fileNameList.add(packageFileName);
			csvInstallFilePathList.add(replacePath(packageFilePath) + SEPARATOR + destinationFilePath);
		}

		data.setCsvInstallFilePathList(csvInstallFilePathList);
	}

	/**
	 * <pre>
	 * postHandle
	 * install 파일 생성
	 * <pre>
	 */
	@Override
	protected void postHandle() {
		String installFile = makePath(INSTALL_FILE_NAME, data.getReleaseNum());
		new File(installFile).delete();

		// csv 형식으로 된 install.txt 파일 생성
		for (String installPath : data.getCsvInstallFilePathList()) {
			FileUtil.writeMsg(installPath, installFile);
		}

		// 디렉토리 압축
		String zipTargetDirectory = data.getReleaseNum();
		ZipUtil.zip(zipTargetDirectory, zipTargetDirectory + ZIP_FILE_SUFFIX);
	}

	/**
	 * <pre>
	 * interceptorPostHandle
	 * postHandle 메소드 실행 전 처리
	 * <pre>
	 */
	@Override
	protected void interceptorPostHandle() {
		List<String> csvInstallFilePathList = data.getCsvInstallFilePathList();
		List<String> addFilePathList = new ArrayList<String>();
		List<String> addRemoteWorkspaceFilePathList = new ArrayList<String>();

		// NCID-UI에 배포할 파일 추가
		for (String installPath : csvInstallFilePathList) {
			if (installPath.indexOf("NCID") > -1) {
				String appendPath = installPath.replaceFirst("NCID", "NCID-UI");
				addFilePathList.add(appendPath);
			}
		}

		// remote workspace에 등록되어 있는 디렉토리에 모두 배포할지 여부
		if ("Y".equals(data.getReleaseAll())) {
			String[] remoteWorkspaces = Conf.getValue("remote.workspace").split(",");
			for (int i = 1; i < remoteWorkspaces.length; i++) { // i 값을 1로 한 이유는 첫 번째 workspace 디렉토리의 path는 이미 생성되어 있기 때문
				for (String installPath : csvInstallFilePathList) {
					String appendPath = installPath.replaceFirst(remoteWorkspaces[0], remoteWorkspaces[i]);
					addRemoteWorkspaceFilePathList.add(appendPath);
				}
			}
		}

		// add path
		csvInstallFilePathList.addAll(addFilePathList);
		csvInstallFilePathList.addAll(addRemoteWorkspaceFilePathList);
	}

	/**
	 * <pre>
	 * error
	 *
	 * <pre>
	 */
	@Override
	protected void error() {
	}

	/**
	 * <pre>
	 * existFile
	 * 적용할 소스 파일이 존재하는지 체크
	 * <pre>
	 * @param fileList
	 * @return
	 */
	private boolean existFile(List<String> fileList) {
		for (String filePath : fileList) {
			boolean check = new File(filePath).isFile();
			if (check == false) {
				System.out.println("##existFile## (file not found) check=" + check + ", filePath=" + filePath + ", file count=" + fileList.size());
				return false;
			}
		}
		return true;
	}

	/**
	 * <pre>
	 * makePackageFilePathList
	 * 로컬 full path 생성
	 * <pre>
	 * @param packageFile
	 * @return
	 */
	private List<String> makePackageFilePathList(String packageFile) {
		BufferedReaderCallback callback = new BufferedReaderCallback() {
			public String doSomethingWithReader(String line) {
				String cutPath = replaceDeleteTxtToEmpty(line, Conf.getValue("history.path.delete.text"));
				String path = Conf.getValue("local.workspace") + cutPath;

				// 디렉토리는 적용 항목에서 제외
				boolean isDirectory = new File(path).isDirectory();
				if (isDirectory) {
					return null;
				}

				// 특정 확장자는 적용 항목에서 제외
				String[] excludeFileExtensionArr = Conf.getValue("exclude.file.extension.list").split(",");
				for (String excludeFileExtension : excludeFileExtensionArr) {
					String fileExtension = FilenameUtils.getExtension(path);
					if (excludeFileExtension.equals(fileExtension)) {
						return null;
					}
				}

				return path;
			}
		};
		return FileUtil.readFile(packageFile, callback);
	}

	/**
	 * <pre>
	 * chageDeleteTxt
	 *
	 * <pre>
	 * @param str
	 * @return
	 */
	private String replaceDeleteTxtToEmpty(String str, String replaceTxt) {
		String[] deleteTxt = replaceTxt.split(",");
		for (String txt : deleteTxt) {
			str = str.replaceFirst(txt, StringUtils.EMPTY);
		}
		return str;
	}

	/**
	 * <pre>
	 * replacePath
	 *
	 * <pre>
	 * @param packageFilePath
	 * @return
	 */
	private String replacePath(String packageFilePath) {
		String removeWorkspace = replaceDeleteTxtToEmpty(packageFilePath, Conf.getValue("local.workspace"));
		String resultPath = replaceDeleteTxtToEmpty(removeWorkspace, Conf.getValue("local.file.path.delete.txt"));
		String[] remoteWorkspaces = Conf.getValue("remote.workspace").split(",");
		return remoteWorkspaces[0] + resultPath;
	}


	/**
	 * <pre>
	 * hook
	 * 브라우저 띄우기
	 * <pre>
	 */
	@Override
	protected void hook() {
		String zip = data.getReleaseNum() + ZIP_FILE_SUFFIX;
		String uploadTargetNetwork = Conf.getValue("selenium.webhard.upload.target.network");
		SeleniumUtil seleniumUtil = new SeleniumUtil(zip, uploadTargetNetwork);
		seleniumUtil.loadBrowserToUpload();
		seleniumUtil.stop();

		if ("Y".equals(data.getTest()) == false) {
			new File(zip).delete(); // zip 파일 삭제
		}
	}

	/**
	 * <pre>
	 * valid
	 *
	 * <pre>
	 * @param dataVO
	 */
	@Override
	protected void valid(DataVO dataVO) {
		this.data = dataVO;
	}
}
