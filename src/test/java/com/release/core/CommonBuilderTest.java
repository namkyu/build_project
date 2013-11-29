package com.release.core;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;



/**
 * @FileName : CommonBuilderTest.java
 * @Project : build_project
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class CommonBuilderTest {


	@Test
	public void getDirectoryPath() {
		String filePath = "c:\\test\\test1\\a.txt";
		File file = new File(filePath);
		assertThat("c:\\test\\test1", is(file.getParent()));
	}

	@Test
	public void getFileName() {
		String filePath = "c:\\test\\test1\\a.txt";
		File file = new File(filePath);
		assertThat("a.txt", is(file.getName()));
	}
}
