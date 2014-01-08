/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.core;

import static org.apache.commons.lang.StringUtils.*;

import java.util.Properties;

import com.release.common.ReleaseType;
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
		dataVO.setType(ReleaseType.valueOf(upperCase(properties.getProperty("c"))));
		dataVO.setPassword(properties.getProperty("pw"));
		dataVO.setReleaseNum(upperCase(properties.getProperty("p")));
		dataVO.setTest(upperCase(properties.getProperty("test")));
		dataVO.setReleaseAll(upperCase(properties.getProperty("ra")));
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
			String[] keyAndValue = split(pair, '=');
			props.put(keyAndValue[0], keyAndValue[1]);
		}
		return props;
	}
}
