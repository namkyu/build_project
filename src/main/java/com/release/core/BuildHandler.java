package com.release.core;

import java.util.ArrayList;
import java.util.List;

import com.release.handler.AbstractBuilder;
import com.release.vo.DataVO;

/**
 * @FileName : BuildHandler.java
 * @Project : TEST_PROJECT
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class BuildHandler {

	/** data */
	public DataVO data;

	/**
	 * @param command
	 */
	public BuildHandler(String command, String packageNum) {
		data = new DataVO();
		data.setCommand(command);
		data.setReleaseNum(packageNum);
	}

	/**
	 * <pre>
	 * execute
	 *
	 * <pre>
	 * @throws Exception
	 */
	public void execute() throws Exception {
		AbstractBuilder abstractBuilder = ReleaseProxy.createInstance(data.getCommand());
		abstractBuilder.build(data);
	}

	/**
	 * <pre>
	 * getFileName
	 *
	 * <pre>
	 * @param fileList
	 * @return
	 */
	@Deprecated
	public List<String> getFileName(List<String> fileList) {
		List<String> fileNameList = new ArrayList<String>();
		for (String str : fileList) {
			fileNameList.add(str.substring(str.lastIndexOf("/") + 1));
		}
		return fileNameList;
	}

}
