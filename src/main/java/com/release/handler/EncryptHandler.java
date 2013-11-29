package com.release.handler;

import com.release.core.AbstractBuilder;
import com.release.util.CipherAES;
import com.release.vo.DataVO;

/**
 * @FileName : EncryptHandler.java
 * @Project : build_project
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class EncryptHandler extends AbstractBuilder {

	private DataVO dataVO;

	/**
	 * <pre>
	 * valid
	 *
	 * <pre>
	 * @param dataVO
	 */
	@Override
	protected void valid(DataVO dataVO) {
		this.dataVO = dataVO;
	}

	/**
	 * <pre>
	 * preHandle
	 *
	 * <pre>
	 * @return
	 */
	@Override
	protected boolean preHandle() {
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
		CipherAES aes = new CipherAES();
		String encryptPassword = aes.encrypt(dataVO.getPassword());
		System.out.println("=========================================================");
		System.out.println("encrypt password : " + encryptPassword);
		System.out.println("=========================================================");
	}

	/**
	 * <pre>
	 * postHandle
	 *
	 * <pre>
	 */
	@Override
	protected void postHandle() {
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
}
