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

		// validation check
		valid(dataVO);

		// before process
		interceptorPreHandle();
		boolean isSuccess = preHandle();
		if (isSuccess == false) {
			System.out.println("##build## (before process failed) isSucces=" + isSuccess + ", type=" + dataVO.getType() + ", releaseNum=" + dataVO.getReleaseNum());
			return;
		}

		// process
		process();

		// after process
		interceptorPostHandle();
		postHandle();

		// hook 메소드
		hook();
	}

	/**
	 * <pre>
	 * interceptorPreHandle
	 *
	 * <pre>
	 */
	protected void interceptorPreHandle() {
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
	 * interceptorPostHandle
	 *
	 * <pre>
	 */
	protected void interceptorPostHandle() {
	}

	/**ㅇ
	/**
	 * <pre>
	 * valid
	 *
	 * <pre>
	 * @param dataVO
	 */
	protected abstract void valid(DataVO dataVO);

	/**
	 * <pre>
	 * preHandle
	 * 전 처리
	 * <pre>
	 * @return
	 */
	protected abstract boolean preHandle();

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
