/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.common;


/**
 * The Class BaseType.
 */
public class BaseType {

	/** scan package */
	public static String ANNOTATION_SCAN_PACKAGE = "com.release.handler";
	/** 구분자 */
	public static String SEPARATOR = ",";
	/** 인코딩 */
	public static String CHARACTER_SET = "UTF-8";

	/** 적용 소스 디렉토리 */
	public static String SOURCE_DIRECTORY = "source";
	/** 백업 소스 디렉토리 */
	public static String BACKUP_DIRECTORY = "backup";
	/** 중복 파일 디렉토리 prefix */
	public static String DUPL_DIRECTORY_PREFIX = "/dupl_";

	/** 패키징 리스트 파일 */
	public static String PACKAGE_FILE_NAME = "package.txt";
	/** 적용 리스트 파일 */
	public static String INSTALL_FILE_NAME = "install.txt";
	/** 롤백 리스트 파일 */
	public static String ROLLBACK_FILE_NAME = "rollback.txt";
}
