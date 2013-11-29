/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.handler;

import com.release.core.AbstractBuilder;
import com.release.util.Conf;
import com.release.util.ftp.FTPHandler;
import com.release.util.ftp.FTPType;
import com.release.util.ftp.FTPVO;
import com.release.vo.DataVO;

/**
 * The Class FtpPutBuilder.
 */
public class FtpPutBuilder extends AbstractBuilder {

	/** vo */
	private DataVO data;

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
		FTPVO ftpvo = new FTPVO();
		ftpvo.setUploadRemoteDirectory(Conf.getValue("ftp.remote.upload.directory"));
		ftpvo.setUploadLocalDirectory(Conf.getValue("ftp.local.upload.directory"));
		ftpvo.setUploadFileNameList(data.getTarFileName());
		ftpvo.setType(FTPType.PUT);

		// 원격지 FTP 서버로 업로드 처리
		String[] ftpServers = Conf.getValue("ftp.remote.ip.list").split(",");
		for (String ftpServerIp : ftpServers) {
			new FTPHandler(ftpServerIp).job(ftpvo);
		}
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

	/**
	 * <pre>
	 * valid
	 *
	 * <pre>
	 * @param dataVO
	 */
	@Override
	protected void valid(DataVO dataVO) {
		this.data = dataVO;
	}


}
