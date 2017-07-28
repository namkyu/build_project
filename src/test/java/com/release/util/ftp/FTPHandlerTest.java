package com.release.util.ftp;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


/**
 * @FileName : FTPHandlerTest.java
 * @Project : my_project_release
 * @Date : 2013. 3. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class FTPHandlerTest {

    private FTPHandler handler;

    @Before
    public void init() {
        handler = new FTPHandler("124.136.171.126");
    }

    @Test
    @Ignore
    public void download() {
        FTPVO ftpvo = new FTPVO();
        ftpvo.setDownloadRemoteDirectory("/svc/tad/ftp");
        ftpvo.setDownloadLocalFileDirectory("E:\\test\\ftp\\download");
        ftpvo.setDownloadFilePattern("tad_daily.+20120425.csv");
        ftpvo.setType(FTPType.GET);
        handler.job(ftpvo);
    }

    @Test
    @Ignore
    public void upload() {
        FTPVO ftpvo = new FTPVO();
        ftpvo.setUploadRemoteDirectory("/svc/tad/ftp");
        ftpvo.setUploadLocalDirectory("E:\\test\\ftp");
        ftpvo.setUploadFilePattern("tad_daily.+20120425.csv");
        ftpvo.setType(FTPType.PUT);
        handler.job(ftpvo);
    }

    @Test
    @Ignore
    public void both() {
        FTPVO ftpvo = new FTPVO();
        ftpvo.setDownloadRemoteDirectory("/svc/tad/ftp");
        ftpvo.setDownloadLocalFileDirectory("E:\\test\\ftp\\download");
        ftpvo.setDownloadFilePattern("tad_daily.+20120425.csv");

        ftpvo.setUploadRemoteDirectory("/svc/tad/ftp");
        ftpvo.setUploadLocalDirectory("E:\\test\\ftp");
        ftpvo.setUploadFilePattern("tad_daily.+20120425.csv");

        ftpvo.setType(FTPType.BOTH);
        handler.job(ftpvo);
    }
}
