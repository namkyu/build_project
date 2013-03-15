package com.release.handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.release.anno.Action;
import com.release.core.BaseType;
import com.release.util.FileUtil;
import com.release.vo.DataVO;


/**
 * @FileName : InstallBuilder.java
 * @Project : TEST_PROJECT
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
@Action("install")
public class InstallBuilder extends AbstractBuilder {

	/** vo */
	private DataVO data;

	/**
	 * 기존 소스 백업
	 */
	@Override
	protected boolean preHandle(DataVO dataVO) throws Exception {
		System.out.println("#########################################################");
		System.out.println("## INSTALL BACKUP");
		System.out.println("#########################################################");

		this.data = dataVO;

		// 백업 소스를 저장할 디렉토리 생성
		String backupDir = makePath(BaseType.BACKUP_DIRECTORY, data.getReleaseNum());
		makeDir(backupDir);

		// cvs형식의 인스톨 파일 리스트 추출
		String installFile = makePath(BaseType.INSTALL_FILE_NAME, data.getReleaseNum());
		List<String> csvInstallFilePathList = FileUtil.readFile(installFile);

		// 백업 파일 리스트 중 중복되는 파일 name 저장 객체
		List<String> fileNameList = new ArrayList<String>();

		// rollback 파일 리스트
		List<String> csvRollbackFilePathList = new ArrayList<String>();

		for (String cvsInstallFilePath : csvInstallFilePathList) {
			String installFilePath = cvsInstallFilePath.split(BaseType.SEPARATOR)[0];
			String installFileName = getFileName(installFilePath);
			String backupFilePath = getDestinationFilePath(fileNameList, installFileName, backupDir);

			boolean existFile = new File(installFilePath).isFile();
			if (existFile) {
				// 백업 디렉토리로 copy
				FileUtil.nioCopy(installFilePath, backupFilePath);
				System.out.println("##preHandle##(install file backup) installFilePath=" + installFilePath + ", backupFilePath=" + backupFilePath);
			}

			fileNameList.add(installFileName);

			// rollback 파일 리스트 저장
			csvRollbackFilePathList.add(installFilePath + BaseType.SEPARATOR + backupFilePath);
		}

		data.setCsvRollbackFilePathList(csvRollbackFilePathList);
		data.setCsvInstallFilePathList(csvInstallFilePathList);

		return true;
	}

	/**
	 * 배포
	 */
	@Override
	protected void process() throws IOException {
		System.out.println("#########################################################");
		System.out.println("## INSTALL");
		System.out.println("#########################################################");

		for (String csvInstallFile : data.getCsvInstallFilePathList()) {
			String installFilePath = csvInstallFile.split(BaseType.SEPARATOR)[0];
			String sourceFilePath = csvInstallFile.split(BaseType.SEPARATOR)[1];

			String destDir = getDirPath(installFilePath);
			makeDir(destDir); // 적용할 소스 디렉토리 생성

			System.out.println("##process##(install file copy) installFilePath=" + installFilePath + ", sourceFilePath=" + sourceFilePath);
			FileUtil.nioCopy(sourceFilePath, installFilePath);
		}
	}

	/**
	 * rollback 파일 생성
	 */
	@Override
	protected void postHandle() throws Exception {
		new File(BaseType.ROLLBACK_FILE_NAME).delete();

		// 소스 적용 완료 후 rollback 파일 생성
		for (String csvRollbackFilePath : data.getCsvRollbackFilePathList()) {
			String rollbackFile = makePath(BaseType.ROLLBACK_FILE_NAME, data.getReleaseNum());
			FileUtil.writeMsg(csvRollbackFilePath, rollbackFile);
		}
	}

	@Override
	protected void error() {

	}
}
