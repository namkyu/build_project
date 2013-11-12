/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release;

import org.apache.commons.lang.StringUtils;

import com.release.core.BuildHandler;
import com.release.util.Conf;



/**
 * The Class Commander.
 */
public class Commander {

	/**
	 * <pre>
	 * main
	 *
	 * <pre>
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		try {
			Conf.init();
			new BuildHandler(args).execute();
		} catch (Exception ex) {
			help();
			println("######################################");
			println("## error");
			println("######################################");
			ex.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * help
	 *
	 * <pre>
	 */
	private static void help() {
		println("######################################");
		println("## 사용법");
		println("######################################");
		println("PACKAGE  : 적용할 소스 패키징");
		println("INSTALL  : 소스 적용");
		println("ROLLBACK : 소스 원복");
		println(StringUtils.EMPTY);
		println("java -jar build_project.jar 명령옵션 패키지번호");
		println(" - PACKAGE example : java -jar build_project.jar c=PACKAGE p=R001");
		println(" - INSTALL example : java -jar build_project.jar c=INSTALL p=R001");
		println(" - ROLLBACK example : java -jar build_project.jar c=ROLLBACK p=R001");
		println(StringUtils.EMPTY);
		println("build_project.jar 파일이 있는 경로에 패키지번호 디렉토리가 존재해야 함");
		println("패키지번호 디렉토리 안에는 적용할 소스 리스트가 기록되어 있는 package.txt 파일이 존재해야 함");
		println("build_project.jar 파일이 있는 경로에 conf.xml 파일이 존재해야 함");
		println(StringUtils.EMPTY);
	}

	/**
	 * <pre>
	 * println
	 *
	 * <pre>
	 * @param str
	 */
	private static void println(String str) {
		System.out.println(str);
	}
}
