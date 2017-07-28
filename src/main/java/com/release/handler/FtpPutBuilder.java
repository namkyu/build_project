package com.release.handler;

import com.release.anno.Action;
import com.release.util.Conf;
import com.release.util.ftp.FTPHandler;
import com.release.util.ftp.FTPType;
import com.release.util.ftp.FTPVO;
import com.release.vo.DataVO;

/**
 * @FileName : FTPBuilder.java
 * @Project : my_project_release
 * @Date : 2013. 3. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
@Action(value = "PUTALL")
public class FtpPutBuilder extends AbstractBuilder {

    /**
     * vo
     */
    private DataVO data;

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


}
