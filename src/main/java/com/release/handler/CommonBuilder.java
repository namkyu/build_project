/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.handler;

import static com.release.common.BaseType.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * The Class CommonBuilder.
 */
public class CommonBuilder {

	/**
	 * <pre>
	 * getDuplIdx
	 * 중복되는 파일을 저장할 디렉토리 suffix 추출
	 * <pre>
	 * @param completeCopyFile
	 * @param fileName
	 * @return
	 */
	public int getDuplIdx(List<String> completeCopyFile, String fileName) {
		int duplIdx = 0;
		for (String copyFileName : completeCopyFile) {
			if (copyFileName.equals(fileName)) {
				duplIdx++;
			}
		}
		return duplIdx;
	}

	/**
	 * <pre>
	 * makeDir
	 * 디렉토리 생성
	 * <pre>
	 * @param directory
	 */
	public void makeDir(String directory) {
		File dir = new File(directory);
		dir.setReadable(true);
		dir.setExecutable(true);
		dir.setWritable(true);
		dir.mkdir();
	}

	/**
	 * <pre>
	 * getFileName
	 * 파일 name 추출
	 * <pre>
	 * @param filePath
	 * @return
	 */
	public String getFileName(String filePath) {
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
		return fileName;
	}

	/**
	 * <pre>
	 * getDirectoryPath
	 * 디렉토리 path 추출
	 * <pre>
	 * @param path
	 * @return
	 */
	public String getDirPath(String path) {
		String dirPath = path.substring(0, path.lastIndexOf("/"));
		return dirPath;
	}

	/**
	 * <pre>
	 * makeDuplDirectory
	 * 중복 파일을 저장할 디렉토리 생성
	 * <pre>
	 * @param completeCopyFile
	 * @param fileName
	 * @param directory
	 * @return
	 */
	public String makeDuplDirectory(List<String> completeCopyFile, String fileName, String directory) {
		int duplIdx = getDuplIdx(completeCopyFile, fileName);
		String duplDirectoryPath = directory + DUPL_DIRECTORY_PREFIX + duplIdx;

		// 중복 파일 저장을 위한 디렉토리 생성
		new File(duplDirectoryPath).mkdirs();

		String duplPath = getPath(fileName, duplDirectoryPath);
		return duplPath;
	}

	/**
	 * <pre>
	 * getPath
	 * destination path 생성
	 * <pre>
	 * @param fileName
	 * @return
	 */
	public String getPath(String fileName, String directory) {
		StringBuilder path = new StringBuilder();
		path.append(directory);
		path.append(File.separator);
		path.append(fileName);

		return path.toString();
	}

	/**
	 * <pre>
	 * makePath
	 *
	 * <pre>
	 * @param value
	 * @return
	 */
	public String makePath(String value, String releaseNum) {
		StringBuilder path = new StringBuilder();
		path.append(releaseNum);
		path.append(File.separator);
		path.append(value);
		return path.toString();
	}


	/**
	 * <pre>
	 * getDestinationFilePath
	 * destination path 생성
	 * <pre>
	 * @param fileNameList
	 * @param fileName
	 * @return
	 */
	public String getDestinationFilePath(List<String> fileNameList, String fileName, String directory) {
		String destinationFilePath = null;

		// 적용 파일 중 중복이 있는지 체크
		if (fileNameList.contains(fileName)) {
			destinationFilePath = makeDuplDirectory(fileNameList, fileName, directory);
		} else {
			destinationFilePath = getPath(fileName, directory);
		}

		return destinationFilePath;
	}

	/**
	 * <pre>
	 * getUniqueList
	 *
	 * <pre>
	 * @param list
	 * @return
	 */
	public <E> List<E> getUniqueList(List<E> list) {
		List<E> uniqueItems = new ArrayList<E>(new HashSet<E>(list));
        return uniqueItems;
	}
}
