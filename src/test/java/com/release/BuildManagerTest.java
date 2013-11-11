package com.release;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.release.util.Conf;


/**
 * @FileName : BuildManagerTest.java
 * @Project : build_project
 * @Date : 2013. 3. 14.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class BuildManagerTest {

	private BuildManager buildManager;

	@Before
	public void initObj() throws IOException {
		buildManager = new BuildManager();
	}

//	@Test
//	@Ignore
//	public void packageTest() {
//		buildManager.startProcess("PACKAGE", "E:/test/build/R001", "");
//	}
//
//	@Test
//	@Ignore
//	public void installTest() {
//		buildManager.startProcess("INSTALL", "E:/test/build/R001", "");
//	}
//
//	@Test
//	@Ignore
//	public void rollTest() {
//		buildManager.startProcess("ROLLBACK", "E:/test/build/R001", "");
//	}
//
//	@Test
//	@Ignore
//	public void putAllTest() throws IOException {
//		Conf.init();
//		buildManager.startProcess("PUTALL", "E:/test/build/R001", "R001.tar.gz");
//	}
//
	@Test
	public void svnHistoryTest() throws IOException {
		Conf.init();
		buildManager.startProcess("SVN_HISTORY", "E:/test/build/R001", null, 122);
	}

	@Test
	public void enumEqualsTest() {
		String command = "INSTALL";
		boolean check = buildManager.checkType(command);
		assertThat(check, is(true));
	}

	@Test
	public void enumEqualsFailTest() {
		String command = "pppp";
		boolean check = buildManager.checkType(command);
		assertThat(check, is(false));
	}

}
