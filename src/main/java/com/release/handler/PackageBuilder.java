package com.release.handler;

import static com.release.common.BaseType.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.release.util.FileUtil;
import com.release.vo.DataVO;


/**
 * @FileName : BackupBuilder.java
 * @Project : TEST_PROJECT
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 : 패키징
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
	 * @param dataVO
	 * @return
	 */
	@Override
	protected boolean preHandle(DataVO dataVO) {
		System.out.println("#########################################################");
		System.out.println("## PACKAGE");
		System.out.println("#########################################################");

		this.data = dataVO;

		// 적용 파일 리스트 추출 후 set
		String packageFile = makePath(PACKAGE_FILE_NAME, data.getReleaseNum());
		data.setPackageFilePathList(FileUtil.readFile(packageFile));

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
			String packageFileName = getFileName(packageFilePath);
			String destinationFilePath = getDestinationFilePath(fileNameList, packageFileName, packageDir);

			// 적용되는 파일의 소스들을 source 디렉토리에 copy
			FileUtil.nioCopy(packageFilePath, destinationFilePath);
			System.out.println("##process##(package file copy) packageFilePath=" + packageFilePath + ", destinationFilePath=" + destinationFilePath);

			fileNameList.add(packageFileName);
			csvInstallFilePathList.add(packageFilePath + SEPARATOR + destinationFilePath);
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
		new File(INSTALL_FILE_NAME).delete();

		// csv 형식으로 된 install.txt 파일 생성
		for (String installPath : data.getCsvInstallFilePathList()) {
			String installFile = makePath(INSTALL_FILE_NAME, data.getReleaseNum());
			FileUtil.writeMsg(installPath, installFile);
		}
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
				System.out.println("##existFile## (file not found) check=" + check + ", filePath=" + filePath);
				return false;
			}
		}
		return true;
	}
}
