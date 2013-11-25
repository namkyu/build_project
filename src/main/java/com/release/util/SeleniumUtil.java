package com.release.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.chrome.ChromeDriver;

import com.thoughtworks.selenium.Selenium;

/**
 * @FileName : SeleniumUtil.java
 * @Project : build_project
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class SeleniumUtil {

	private Selenium selenium;
	private String baseUrl;
	private String loginPath;
	private String userId;
	private String password;
	private String targetNetwork;
	private String uploadDirectory;
	private String zipFileName;

	/**
	 * @param driverPath
	 * @param loadUrl
	 * @param userId
	 * @param password
	 */
	public SeleniumUtil(String zipFileName, String uploadTargetNetwork) {
		System.setProperty("webdriver.chrome.driver", Conf.getValue("selenium.driver.path"));
		this.baseUrl = Conf.getValue("selenium.webhard.url");
		this.loginPath = Conf.getValue("selenium.webhard.login.path");
		this.userId = Conf.getValue("selenium.webhard.login.userId");
		this.password = Conf.getValue("selenium.webhard.login.password");
		this.uploadDirectory = Conf.getValue("selenium.webhard.upload.directory");
		this.zipFileName = zipFileName;
		this.targetNetwork = uploadTargetNetwork;

		WebDriver driver = new ChromeDriver();
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	/**
	 * <pre>
	 * loadBrowser
	 * 업로드
	 * <pre>
	 * @throws Exception
	 */
	public void loadBrowserToUpload() {
		login();
		selenium.click("css=p > img");
		selenium.click("document.Form2.http_upload_yn[1]");
		selenium.type("id=http_file", uploadDirectory + zipFileName);
		selenium.click("css=img.input_image");
		selenium.waitForPageToLoad("30000");
	}

	/**
	 * <pre>
	 * loadBrowserToDownload
	 * 다운로드
	 * <pre>
	 */
	public void loadBrowserToDownload() {
		login();
		selenium.click("css=td.lineMain > a > img");
		selenium.waitForPageToLoad("30000");
	}

	/**
	 * <pre>
	 * login
	 *
	 * <pre>
	 */
	private void login() {
		selenium.open(loginPath);
		selenium.click("id=" + targetNetwork);
		selenium.type("name=userid", userId);
		selenium.type("name=password", password);
		selenium.click("css=img[alt=\"로그인버튼\"]");
		selenium.waitForPageToLoad("30000");
	}

	/**
	 * <pre>
	 * quit
	 *
	 * <pre>
	 */
	public void stop() {
		selenium.stop();
	}
}
