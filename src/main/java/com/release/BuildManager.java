package com.release;

import com.release.core.BuildHandler;

import org.apache.commons.lang.StringUtils;



/**
 * @FileName : BuildManager.java
 * @Project : TEST_PROJECT
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class BuildManager {

	/**
	 * <pre>
	 * main
	 *
	 * <pre>
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("#########################################");
		System.out.println("## package  : 적용할 소스 백업");
		System.out.println("## install  : 소스 적용");
		System.out.println("## rollback : 소스 원복");
		System.out.println("#########################################");

		String command = args[0]; // 실행 옵션
		String packageNum = args[1]; // package number

		BuildManager buildManager = new BuildManager();
		buildManager.check(command, packageNum);
		buildManager.start(command, packageNum);
	}

	/**
	 * <pre>
	 * start
	 *
	 * <pre>
	 * @param command
	 */
	private void start(String command, String releaseNum) {
		try {
			new BuildHandler(command, releaseNum).execute();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(0); // stop application
		}
	}


	/**
	 * <pre>
	 * checkFile
	 *
	 * <pre>
	 * @param packageFilePath
	 * @throws Exception
	 */
	private void check(String command, String releaseNum) throws Exception {
		// command 체크
		if (StringUtils.EMPTY.equals(command)) {
			throw new Exception("##check##(invalid command) command=" + command + ", releaseNum=" + releaseNum);
		}
		// 패키징 작업 시 package number 값 유무 확인
		else if (StringUtils.EMPTY.equals(releaseNum)) {
			throw new Exception("##check##(not be empty releaseNum) releaseNum=" + releaseNum + ", command=" + command);
		}
	}

}
