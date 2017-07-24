package com.release.handler;

import com.release.util.FileUtil;
import com.release.vo.DataVO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.release.common.BaseType.*;


/**
 * @FileName : InstallBuilder.java
 * @Project : my_project_release
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class InstallBuilder extends AbstractBuilder {

    /**
     * vo
     */
    private DataVO data;

    /**
     * <pre>
     * preHandle
     * 기존 소스 백업
     * <pre>
     * @param dataVO
     * @return
     */
    @Override
    protected boolean preHandle(DataVO dataVO) {
        System.out.println("#########################################################");
        System.out.println("## INSTALL BACKUP");
        System.out.println("#########################################################");

        this.data = dataVO;

        // 백업 소스를 저장할 디렉토리 생성
        String backupDir = makePath(BACKUP_DIRECTORY, data.getReleaseNum());
        makeDir(backupDir);

        // cvs형식의 인스톨 파일 리스트 추출
        String installFile = makePath(INSTALL_FILE_NAME, data.getReleaseNum());
        List<String> csvInstallFilePathList = FileUtil.readFile(installFile);

        // 백업 파일 리스트 중 중복되는 파일 name 저장 객체
        List<String> fileNameList = new ArrayList<String>();

        // rollback 파일 리스트
        List<String> csvRollbackFilePathList = new ArrayList<String>();

        for (String cvsInstallFilePath : csvInstallFilePathList) {
            String installFilePath = cvsInstallFilePath.split(SEPARATOR)[0];
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
            csvRollbackFilePathList.add(installFilePath + SEPARATOR + backupFilePath);
        }

        data.setCsvRollbackFilePathList(csvRollbackFilePathList);
        data.setCsvInstallFilePathList(csvInstallFilePathList);

        return true;
    }

    /**
     * <pre>
     * process
     * 배포
     * <pre>
     */
    @Override
    protected void process() {
        System.out.println("#########################################################");
        System.out.println("## INSTALL");
        System.out.println("#########################################################");

        for (String csvInstallFile : data.getCsvInstallFilePathList()) {
            String installFilePath = csvInstallFile.split(SEPARATOR)[0];
            String sourceFilePath = csvInstallFile.split(SEPARATOR)[1];

            String destDir = getDirPath(installFilePath);
            makeDir(destDir); // 적용할 소스 디렉토리 생성

            System.out.println("##process##(install file copy) installFilePath=" + installFilePath + ", sourceFilePath=" + sourceFilePath);
            FileUtil.nioCopy(sourceFilePath, installFilePath);
        }
    }

    /**
     * <pre>
     * postHandle
     * rollback 파일 생성
     * <pre>
     */
    @Override
    protected void postHandle() {
        new File(ROLLBACK_FILE_NAME).delete();

        // 소스 적용 완료 후 rollback 파일 생성
        for (String csvRollbackFilePath : data.getCsvRollbackFilePathList()) {
            String rollbackFile = makePath(ROLLBACK_FILE_NAME, data.getReleaseNum());
            FileUtil.writeMsg(csvRollbackFilePath, rollbackFile);
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
}
