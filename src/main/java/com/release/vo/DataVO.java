/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.vo;

import java.util.List;

import com.release.common.ReleaseType;

/**
 * The Class DataVO.
 */
public class DataVO {

	/** 실행 type */
	private ReleaseType type;

	/** 패키징 number */
	private String releaseNum;

	/** FTP 업로드할 tar */
	private String tarFileName;

	private String password;
	/** revisionNum */
	private int revisionNum;
	private String test;
	/** remote workspace에 등록되어 있는 디렉토리에 모두 배포할지 여부 */
	private String releaseAll;

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

	public String getReleaseNum() {
		return releaseNum;
	}

	public void setReleaseNum(String releaseNum) {
		this.releaseNum = releaseNum;
	}

	/**
	 * @return the type
	 */
	public ReleaseType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ReleaseType type) {
		this.type = type;
	}

	/**
	 * @return the tarFileName
	 */
	public String getTarFileName() {
		return tarFileName;
	}

	/**
	 * @param tarFileName the tarFileName to set
	 */
	public void setTarFileName(String tarFileName) {
		this.tarFileName = tarFileName;
	}

	/**
	 * @return the revisionNum
	 */
	public int getRevisionNum() {
		return revisionNum;
	}

	/**
	 * @param revisionNum the revisionNum to set
	 */
	public void setRevisionNum(int revisionNum) {
		this.revisionNum = revisionNum;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the test
	 */
	public String getTest() {
		return test;
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(String test) {
		this.test = test;
	}

	/**
	 * @return the releaseAll
	 */
	public String getReleaseAll() {
		return releaseAll;
	}

	/**
	 * @param releaseAll the releaseAll to set
	 */
	public void setReleaseAll(String releaseAll) {
		this.releaseAll = releaseAll;
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
		return "DataVO [type=" + type + ", releaseNum=" + releaseNum + ", tarFileName=" + tarFileName + ", password=" + password + ", revisionNum="
				+ revisionNum + ", test=" + test + ", releaseAll=" + releaseAll + ", packageFilePathList=" + packageFilePathList
				+ ", csvInstallFilePathList=" + csvInstallFilePathList + ", csvRollbackFilePathList=" + csvRollbackFilePathList + "]";
	}
}
