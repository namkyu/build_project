package com.release.util.ftp;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.release.util.Conf;

/**
 * @FileName : FTPHandler.java
 * @Project : sample_project
 * @Date : 2012. 4. 16.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class FTPHandler {

	/** FTP 유틸 */
	private final FTPUtil ftp;

	/** FTP 아이피 */
	private final String ftpRemoteIp;
	/** FTP 포트 */
	private final int ftpRemotePort;
	/** user */
	private final String ftpRemoteUser;
	/** password */
	private final String ftpRemotePw;

	/**
	 * 생성자
	 */
	public FTPHandler(String ftpIp) {
		this.ftp = new FTPUtil();
		this.ftpRemoteIp = ftpIp;
		this.ftpRemotePort = Conf.getIntValue("ftp.remote.port");
		this.ftpRemoteUser = Conf.getValue("ftp.remote.user");
		this.ftpRemotePw = Conf.getValue("ftp.remote.password");
		System.out.println("##FTPHandler## ftpRemoteIp=" + ftpRemoteIp + ", ftpRemotePort=" + ftpRemotePort + ", ftpRemoteUser=" + ftpRemoteUser + ", ftpRemotePw=" + ftpRemotePw);
	}

	/**
	 * <pre>
	 * job
	 *
	 * <pre>
	 * @param ftpvo
	 * @return
	 */
	public boolean job(FTPVO ftpvo) {
		boolean isSuccess = false;
		try {
			ftp.connect(ftpRemoteIp, ftpRemotePort);
			ftp.login(ftpRemoteUser, ftpRemotePw);

			// FTP 작업 start
			isSuccess = jobProcess(ftpvo);

			ftp.logout();
			ftp.disconnect();

		} catch (Exception ex) {
			System.out.println("##job## (exception failed) ftpvo=" + ftpvo);
			ex.printStackTrace();
			isSuccess = false;
		}

		return isSuccess;
	}

	/**
	 * <pre>
	 * jobProcess
	 *
	 * <pre>
	 * @param ftpvo
	 * @param type
	 * @return
	 */
	private boolean jobProcess(FTPVO ftpvo) {
		boolean isSuccess = false;
		FTPType type = ftpvo.getType();

		// 파일 다운로드
		if (FTPType.GET == type) {
			isSuccess = downloadProcess(ftpvo);
		}
		// 파일 업로드
		else if(FTPType.PUT == type) {
			isSuccess = uploadProcess(ftpvo);
			System.out.println("##jobProcess## isSuccess=" + isSuccess + ", uploadLocalDirectory=" + ftpvo.getUploadLocalDirectory() + ", uploadLocalFileName=" + ftpvo.getUploadFileNameList() + ", uploadRemoteDirectory=" + ftpvo.getUploadRemoteDirectory());
		}
		// 파일 다운로드, 업로드
		else if(FTPType.BOTH == type) {
			isSuccess = downloadProcess(ftpvo);
			isSuccess = uploadProcess(ftpvo);
		}
		// type error
		else {
			System.out.println("##jobProcess## (invalid type) type=" + type);
		}
		return isSuccess;
	}

	/**
	 * <pre>
	 * uploadProcess
	 * 파일 업로드
	 * <pre>
	 * @param ftpvo
	 * @return
	 */
	public boolean uploadProcess(FTPVO ftpvo) {
		if (ftpvo.getUploadFilePattern() != null) {
			setUploadFileList(ftpvo);
		}

		return ftp.uploadFile(ftpvo.getUploadLocalDirectory()
					, ftpvo.getUploadFileNameList()
					, ftpvo.getUploadRemoteDirectory());
	}

	/**
	 * <pre>
	 * downloadProcess
	 * 파일 다운로드
	 * <pre>
	 * @param ftpvo
	 * @return
	 */
	public boolean downloadProcess(FTPVO ftpvo) {
		return ftp.getRetrieveFile(ftpvo.getDownloadLocalFileDirectory()
				, ftpvo.getDownloadRemoteFileNameList()
				, ftpvo.getDownloadRemoteDirectory()
				, ftpvo.getDownloadFilePattern());
	}

	/**
	 * <pre>
	 * getUploadFileList
	 * 업로드 디렉토리에서 패턴에 일치하는 파일 리스트 추출
	 * <pre>
	 * @param ftpvo
	 * @return
	 */
	public void setUploadFileList(FTPVO ftpvo) {
		String uploadLocalDirectory = ftpvo.getUploadLocalDirectory();
		File directory = new File(uploadLocalDirectory);
		File[] files = directory.listFiles();

		Pattern pattern = Pattern.compile(ftpvo.getUploadFilePattern());
		for (File file : files) {
			String fileName = file.getName();
			Matcher matcher = pattern.matcher(fileName);
			boolean matched = matcher.matches();

			if (matched) { // 패턴 일치
				ftpvo.setUploadFileNameList(fileName);
			}
		}
	}
}
