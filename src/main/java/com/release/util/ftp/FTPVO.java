package com.release.util.ftp;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName : FTPVO.java
 * @Project : my_project_release
 * @Date : 2012. 4. 16.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class FTPVO {

    /**
     * GET, PUT, BOTH 구분
     */
    private FTPType type;

    /**
     * 다운로드 받을 원격지 디렉토리
     */
    private String downloadRemoteDirectory;
    /**
     * 원격지에서 다운로드 받는 파일의 로컬 저장 디렉토리
     */
    private String downloadLocalFileDirectory;
    /**
     * 다운로드 파일 패턴
     */
    private String downloadFilePattern;
    /**
     * 다운로드 받을 원격지 파일 리스트
     */
    private final List<String> downloadRemoteFileNameList = new ArrayList<String>();

    /**
     * 업로드 할 로컬 저장 디렉토리
     */
    private String uploadLocalDirectory;
    /**
     * 업로드 할 파일들의 원격 저장 디렉토리
     */
    private String uploadRemoteDirectory;
    /**
     * 업로드 파일 패턴
     */
    private String uploadFilePattern;
    /**
     * 업로드 할 파일 리스트
     */
    private final List<String> uploadLocalFileNameList = new ArrayList<String>();

    public String getDownloadFilePattern() {
        return downloadFilePattern;
    }

    public void setDownloadFilePattern(String downloadFilePattern) {
        this.downloadFilePattern = downloadFilePattern;
    }

    public String getUploadFilePattern() {
        return uploadFilePattern;
    }

    public void setUploadFilePattern(String uploadFilePattern) {
        this.uploadFilePattern = uploadFilePattern;
    }

    public String getDownloadRemoteDirectory() {
        return downloadRemoteDirectory;
    }

    public void setDownloadRemoteDirectory(String downloadRemoteDirectory) {
        this.downloadRemoteDirectory = downloadRemoteDirectory;
    }

    public String getDownloadLocalFileDirectory() {
        return downloadLocalFileDirectory;
    }

    public void setDownloadLocalFileDirectory(String downloadLocalFileDirectory) {
        this.downloadLocalFileDirectory = downloadLocalFileDirectory;
    }

    public List<String> getDownloadRemoteFileNameList() {
        return downloadRemoteFileNameList;
    }

    public void setDownloadRemoteFileNameList(String downloadFileName) {
        this.downloadRemoteFileNameList.add(downloadFileName);
    }

    public List<String> getUploadLocalFileNameList() {
        return uploadLocalFileNameList;
    }

    public String getUploadRemoteDirectory() {
        return uploadRemoteDirectory;
    }

    public void setUploadRemoteDirectory(String uploadRemoteDirectory) {
        this.uploadRemoteDirectory = uploadRemoteDirectory;
    }

    public FTPType getType() {
        return type;
    }

    public void setType(FTPType type) {
        this.type = type;
    }

    public String getUploadLocalDirectory() {
        return uploadLocalDirectory;
    }

    public void setUploadLocalDirectory(String uploadLocalDirectory) {
        this.uploadLocalDirectory = uploadLocalDirectory;
    }

    public List<String> getUploadFileNameList() {
        return uploadLocalFileNameList;
    }

    public void setUploadFileNameList(String uploadFileName) {
        this.uploadLocalFileNameList.add(uploadFileName);
    }

    /**
     * <pre>
     * toString
     *
     * <pre>
     * @return
     */
    @Override
    public String toString() {
        return "FTPVO [type=" + type + ", downloadRemoteDirectory=" + downloadRemoteDirectory + ", downloadLocalFileDirectory="
                + downloadLocalFileDirectory + ", downloadFilePattern=" + downloadFilePattern + ", downloadRemoteFileNameList="
                + downloadRemoteFileNameList + ", uploadLocalDirectory=" + uploadLocalDirectory + ", uploadRemoteDirectory=" + uploadRemoteDirectory
                + ", uploadFilePattern=" + uploadFilePattern + ", uploadLocalFileNameList=" + uploadLocalFileNameList + "]";
    }

}
