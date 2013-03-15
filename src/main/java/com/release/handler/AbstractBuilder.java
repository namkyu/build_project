package com.release.handler;

import com.release.vo.DataVO;


/**
 * @FileName : AbstractBuilder.java
 * @Project : TEST_PROJECT
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 : abstract
 */
public abstract class AbstractBuilder extends CommonBuilder {

	public AbstractBuilder() {
	}

	/**
	 * <pre>
	 * build
	 * 빌드 start
	 * <pre>
	 * @param data
	 */
	public void build(DataVO data) {
		try {
			boolean isSuccess = preHandle(data); // before process

			if (isSuccess == false) {
				System.out.println("##build failed## preHandle isSucces=" + isSuccess + ", data=" + data);
				return;
			}

			process(); // process
			postHandle(); // after process

		} catch (Exception ex) {
			System.out.println("##build exception## data=" + data);
			ex.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * preHandle
	 * 전 처리
	 * <pre>
	 * @param data
	 * @return
	 * @throws Exception
	 */
	protected abstract boolean preHandle(DataVO data) throws Exception;

	/**
	 * <pre>
	 * process
	 * 처리 진행
	 * <pre>
	 * @throws Exception
	 */
	protected abstract void process() throws Exception;

	/**
	 * <pre>
	 * postHandle
	 * 후 처리
	 * <pre>
	 * @throws Exception
	 */
	protected abstract void postHandle() throws Exception;

	/**
	 * <pre>
	 * error
	 * 에러 처리
	 * <pre>
	 */
	protected abstract void error();
}
