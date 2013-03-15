package com.release.vo;

import java.util.List;

/**
 * @FileName : DataVO.java
 * @Project : TEST_PROJECT
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class DataVO {

	/** 실행 명령어 */
	private String command;

	/** 패키징 number */
	private String releaseNum;

	/** package 파일 리스트 */
	private List<String> packageFilePathList;

	/** cvs형식의 install 파일 리스트 */
	private List<String> csvInstallFilePathList;

	/** csv형식의 rollback 파일 리스트 */
	private List<String> csvRollbackFilePathList;

	public List<String> getCsvInstallFilePathList() {
		return csvInstallFilePathList;
	}

	public void setCsvInstallFilePathList(List<String> csvInstallFilePathList) {
		this.csvInstallFilePathList = csvInstallFilePathList;
	}

	public List<String> getCsvRollbackFilePathList() {
		return csvRollbackFilePathList;
	}

	public void setCsvRollbackFilePathList(List<String> csvRollbackFilePathList) {
		this.csvRollbackFilePathList = csvRollbackFilePathList;
	}

	public List<String> getPackageFilePathList() {
		return packageFilePathList;
	}

	public void setPackageFilePathList(List<String> packageFilePathList) {
		this.packageFilePathList = packageFilePathList;
	}

	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * @param command the command to set
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	public String getReleaseNum() {
		return releaseNum;
	}

	public void setReleaseNum(String releaseNum) {
		this.releaseNum = releaseNum;
	}

	@Override
	public String toString() {
		return "DataVO [command=" + command + ", releaseNum=" + releaseNum + ", packageFilePathList="
				+ packageFilePathList + ", csvInstallFilePathList=" + csvInstallFilePathList
				+ ", csvRollbackFilePathList=" + csvRollbackFilePathList + "]";
	}
}
