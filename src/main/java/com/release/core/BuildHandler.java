/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.core;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.release.common.ReleaseType;
import com.release.handler.AbstractBuilder;
import com.release.vo.DataVO;

/**
 * The Class BuildHandler.
 */
public class BuildHandler {

	private DataVO dataVO;

	/**
	 * @param args
	 */
	public BuildHandler(String[] args) {
		Properties properties = parseConfigParams(args);

		dataVO = new DataVO();
		dataVO.setType(ReleaseType.valueOf(properties.getProperty("c").toUpperCase()));
		dataVO.setReleaseNum(properties.getProperty("p"));
	}

	/**
	 * <pre>
	 * execute
	 *
	 * <pre>
	 */
	public void execute() {
		AbstractBuilder abstractBuilder = ReleaseFactory.createInstance(dataVO.getType());
		abstractBuilder.build(dataVO);
	}

	/**
	 * <pre>
	 * parseConfigParams
	 *
	 * <pre>
	 * @param args
	 * @return
	 */
	public Properties parseConfigParams(String[] args) {
		Properties props = new Properties();
		for (String pair : args) {
			String[] keyAndValue = StringUtils.split(pair, '=');
			props.put(keyAndValue[0], keyAndValue[1]);
		}
		return props;
	}
}
