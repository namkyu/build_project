/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.handler;

import static com.release.common.BaseType.*;

import java.util.List;

import com.release.util.Conf;
import com.release.util.FileUtil;
import com.release.util.SVNUtil;
import com.release.vo.DataVO;

/**
 * The Class SVNHistoryBuilder.
 */
public class SVNHistoryBuilder extends AbstractBuilder {

	List<String> historyList;
	DataVO data;

	public SVNHistoryBuilder() {
		Conf.init();
	}

	/**
	 * <pre>
	 * preHandle
	 *
	 * <pre>
	 * @param data
	 * @return
	 */
	@Override
	protected boolean preHandle(DataVO data) {
		this.data = data;
		return true;
	}

	/**
	 * <pre>
	 * process
	 *
	 * <pre>
	 */
	@Override
	protected void process() {
		String svnUrl = Conf.getValue("svn.repository.url");
		String id = Conf.getValue("svn.id");
		String password = Conf.getValue("svn.password");

		SVNUtil svnUtil = new SVNUtil(svnUrl, id, password);
		historyList = svnUtil.getSVNHistory(data.getRevisionNum());
		System.out.println(historyList);
	}

	/**
	 * <pre>
	 * postHandle
	 *
	 * <pre>
	 */
	@Override
	protected void postHandle() {
		for (String path : historyList) {
			String installFile = makePath(PACKAGE_FILE_NAME, data.getReleaseNum());
			FileUtil.writeMsg(path, installFile);
		}
	}

	/**
	 * <pre>
	 * error
	 *
	 * <pre>
	 */
	@Override
	protected void error() {
	}

	/**
	 * <pre>
	 * valid
	 *
	 * <pre>
	 */
	@Override
	protected void valid() {
	}

}
