/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.handler;

import com.release.vo.DataVO;


/**
 * The Class AbstractBuilder.
 */
public abstract class AbstractBuilder extends CommonBuilder {

	/**
	 * <pre>
	 * build
	 * 빌드 start
	 * <pre>
	 * @param data
	 */
	public void build(DataVO dataVO) {

		valid();

		// before process
		boolean isSuccess = preHandle(dataVO);
		if (isSuccess == false) {
			System.out.println("##build## (before process failed) isSucces=" + isSuccess + ", type=" + dataVO.getType() + ", releaseNum=" + dataVO.getReleaseNum());
			return;
		}

		// process
		process();

		// after process
		postHandle();

		// hook method
		hook();
	}

	/**
	 * <pre>
	 * hook
	 *
	 * <pre>
	 */
	protected void hook() {
	}

	/**
	 * <pre>
	 * valid
	 *
	 * <pre>
	 */
	protected abstract void valid();

	/**
	 * <pre>
	 * preHandle
	 * 전 처리
	 * <pre>
	 * @param data
	 * @return
	 */
	protected abstract boolean preHandle(DataVO data);

	/**
	 * <pre>
	 * process
	 * 처리 진행
	 * <pre>
	 */
	protected abstract void process();

	/**
	 * <pre>
	 * postHandle
	 * 후 처리
	 * <pre>
	 */
	protected abstract void postHandle();

	/**
	 * <pre>
	 * error
	 * 에러 처리
	 * <pre>
	 */
	protected abstract void error();
}
