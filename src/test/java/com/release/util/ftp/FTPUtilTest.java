package com.release.util.ftp;

import com.release.util.Conf;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.SocketException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * @FileName : FTPUtilTest.java
 * @Project : build_project
 * @Date : 2013. 3. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class FTPUtilTest {

    private FTPClient client;

    @Before
    public void init() {
        client = new FTPClient();
    }

    @Test
    public void ftpLogin() throws SocketException, IOException {
        Conf.init();
        client.connect("localhost", 21);
        boolean isFtpLogin = client.login("testuser", "1111");

        int reply = client.getReplyCode();
        assertThat(230, is(reply));
        assertThat(isFtpLogin, is(true));
    }
}
