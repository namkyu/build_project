package com.release.handler;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;



/**
 * @FileName : PackageBuilderTest.java
 * @Project : build_project
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class PackageBuilderTest {

	@Test
	public void listAddAllTest() {
		List<String> a = new ArrayList<String>();
		a.add("a");

		List<String> b = new ArrayList<String>();
		b.add("b");

		a.addAll(b);
		assertThat(2, is(a.size()));
	}

	@Test
	public void indexofTest() {
		String installPath = "/NCID/trunk/WebRoot/common";
		if (installPath.indexOf("NCID") > -1) {
			String appendPath = installPath.replaceFirst("NCID", "NCID-UI");
			System.out.println(appendPath);
		}

	}
}
