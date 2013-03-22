package com.release.util.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oroinc.net.ftp.FTP;
import com.oroinc.net.ftp.FTPClient;
import com.oroinc.net.ftp.FTPFile;
import com.oroinc.net.ftp.FTPReply;

/**
 * @FileName : FtpUtil.java
 * @Project : sample_project
 * @Date : 2012. 4. 16.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class FTPUtil {

	/** The client. */
	private FTPClient client = null;

	public FTPUtil() {
		client = new FTPClient();
	}

	/**
	 * <pre>
	 * connect
	 * FTP 접속
	 * <pre>
	 * @param remoteIp
	 * @param port
	 * @return
	 */
	public boolean connect(String remoteIp, int port) {
		boolean isConnected = false;
		try {
			client = new FTPClient();
			client.connect(remoteIp, port);

			// 연결 성공 응답 코드 확인
            int reply = client.getReplyCode();

            isConnected = FTPReply.isPositiveCompletion(reply);
            if (isConnected == false) {
            	client.disconnect();
            	System.out.println("##connect## (ftp connection failed) remoteIp=" + remoteIp + ", port=" + port);
            }

		} catch (IOException ex) {
			System.out.println("##connect## (exception failed) remoteIp=" + remoteIp + ", port=" + port);
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}

		return isConnected;
	}

	/**
	 * <pre>
	 * cd
	 * 디렉토리 이동
	 * <pre>
	 * @param path
	 * @throws IOException
	 */
	public void cd(String path) throws IOException {
		client.changeWorkingDirectory(path);
	}

	/**
	 * <pre>
	 * login
	 * 로그인
	 * <pre>
	 * @param userId
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public boolean login(String userId, String password) throws IOException {
		boolean isFtpLogin = client.login(userId, password);
		client.enterLocalPassiveMode();
		client.setFileType(FTP.BINARY_FILE_TYPE);
		return isFtpLogin;
	}

	/**
	 * <pre>
	 * logOut
	 * 로그 아웃
	 * <pre>
	 * @throws IOException
	 */
	public void logout() throws IOException  {
		client.logout();
	}

	/**
	 * <pre>
	 * disconnect
	 * 접속 끓기
	 * <pre>
	 * @throws IOException
	 */
	public void disconnect() throws IOException {
		client.disconnect();
	}

	/**
	 * <pre>
	 * deleteFile
	 * 파일 삭제
	 * <pre>
	 * @param file
	 * @return
	 */
	public boolean deleteFile(File file) {
		boolean fileDelFlag = false;
		if (file.exists()) {
			fileDelFlag = file.delete();
		}
		return fileDelFlag;
	}

	/**
	 * <pre>
	 * getRetrieveFile
	 * FTP 파일 다운로드
	 * <pre>
	 * @param downloadLocalDirectory
	 * @param downloadFileNameList
	 * @param downloadRemoteDirectory
	 * @param downloadFilePattern
	 * @return
	 */
	public boolean getRetrieveFile(String downloadLocalDirectory, List<String> downloadFileNameList, String downloadRemoteDirectory, String downloadFilePattern) {
		boolean downFlag = false;
		FileOutputStream fos = null;
		try {
			// 디렉토리 이동
			cd(downloadRemoteDirectory);

			// 패턴이 지정되어 있으면 다운로드 받을 파일 리스트를 추출
			if (downloadFilePattern != null) {
				downloadFileNameList = getPatternByDownloadFileList(downloadFilePattern);
			}

			for (String downloadFileName : downloadFileNameList) {
				// 다운로드 받을 로컬 path
				String downloadLocalFilePath = makeFilePath(downloadLocalDirectory, downloadFileName);

				File file = new File(downloadLocalFilePath);
				fos = new FileOutputStream(file);

				downFlag = client.retrieveFile(downloadFileName, fos); // 다운로드
				if (downFlag == false) {
					boolean isDelete = deleteFile(file); // 파일 다운로드 실패 시 로컬에 생성한 파일 삭제
					System.out.println("##getRetrieveFile## (download failed) downloadRemoteDirectory=" + downloadRemoteDirectory + ", downloadFileName=" + downloadFileName + ", downloadLocalDirectory=" + downloadLocalDirectory + ", isDelete=" + isDelete);
				}
			}

		} catch (Exception ex) {
			System.out.println("##getRetrieveFile## (exception failed) downloadRemoteDirectory=" + downloadRemoteDirectory + ", downloadFileNameList=" + downloadFileNameList + ", downloadLocalDirectory=" + downloadLocalDirectory);
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException ex) {}
			}
		}

		return downFlag;
	}

	/**
	 * <pre>
	 * uploadFile
	 * 파일 업로드
	 * <pre>
	 * @param uploadLocalDirectory
	 * @param uploadFileNameList
	 * @param uploadRemoteDirectory
	 * @return
	 */
	public boolean uploadFile(String uploadLocalDirectory, List<String> uploadFileNameList, String uploadRemoteDirectory) {
		InputStream in = null;
		boolean uploaded = false;
		try {
			// 디렉토리 이동
			cd(uploadRemoteDirectory);
			// binary mode
			client.setFileType(FTP.BINARY_FILE_TYPE);

			for (String uploadFileName : uploadFileNameList) {
				String uploadLocalFilePath = makeFilePath(uploadLocalDirectory, uploadFileName);
				in = new FileInputStream(uploadLocalFilePath); // 로컬 파일 read
				uploaded = client.storeFile(uploadFileName, in); // 업로드
			}

		} catch (Exception ex) {
			System.out.println("##uploadFile## (exception failed) remoteDirectory=" + uploadRemoteDirectory + ", uploadLocalDirectory=" + uploadLocalDirectory + ", uploadFileNameList=" + uploadFileNameList);
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {}
			}
		}

		return uploaded;
	}

	/**
	 * <pre>
	 * makeFilePath
	 *
	 * <pre>
	 * @param directory
	 * @param fileName
	 * @return
	 */
	public String makeFilePath(String directory, String fileName) {
		StringBuilder path = new StringBuilder();
		path.append(directory);
		path.append(File.separator);
		path.append(fileName);
		return path.toString();
	}

	/**
	 * <pre>
	 * getPatternByDownloadFileList
	 * 패턴을 이용하여 다운로드 받을 리스트 추출
	 * <pre>
	 * @param downloadFilePattern
	 * @return
	 * @throws IOException
	 */
	public List<String> getPatternByDownloadFileList(String downloadFilePattern) throws IOException {
		List<String> downloadFileNameList = new ArrayList<String>();
		FTPFile[] files = client.listFiles();

		Pattern pattern = Pattern.compile(downloadFilePattern);
		for (FTPFile file : files) {
			String fileName = file.getName();
			Matcher matcher = pattern.matcher(fileName);
			boolean matched = matcher.matches();

			if (matched) { // 패턴 일치
				downloadFileNameList.add(fileName);
			}
		}

		return downloadFileNameList;
	}
}