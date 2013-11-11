package com.release.vo;

import java.util.List;

import com.release.common.ReleaseType;

/**
 * @FileName : DataVO.java
 * @Project : TEST_PROJECT
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class DataVO {

	/** 실행 type */
	private ReleaseType type;

	/** 패키징 number */
	private String releaseNum;

	/** FTP 업로드할 tar */
	private String tarFileName;

	private int revisionNum;

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
	 * <pre>
	 * toString
	 *
	 * <pre>
	 * @return
	 */
	@Override
	public String toString() {
		return "DataVO [type=" + type + ", releaseNum=" + releaseNum + ", tarFileName=" + tarFileName + ", packageFilePathList="
				+ packageFilePathList + ", csvInstallFilePathList=" + csvInstallFilePathList + ", csvRollbackFilePathList=" + csvRollbackFilePathList
				+ "]";
	}
}
