package com.release.core;

import com.release.common.ReleaseType;
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
	private DataVO data;

	/**
	 * @param command
	 * @param releaseNum
	 */
	public BuildHandler(String command, String releaseNum, String tarFileName, int revisionNum) {
		data = new DataVO();
		data.setType(ReleaseType.valueOf(command));
		data.setReleaseNum(releaseNum);
		data.setTarFileName(tarFileName);
		data.setRevisionNum(revisionNum);
	}

	/**
	 * <pre>
	 * execute
	 *
	 * <pre>
	 */
	public void execute() {
		AbstractBuilder abstractBuilder = ReleaseFactory.createInstance(data.getType());
		abstractBuilder.build(data);
	}
}
