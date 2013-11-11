/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.common;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


/**
 * The Class ReleaseTypeTest.
 */
public class ReleaseTypeTest {

	@Test
	public void enumValuesTest() {
		ReleaseType[] types = ReleaseType.values();
		System.out.println(Arrays.toString(types));
	}

	@Test
	public void enumName() {
		String name = ReleaseType.INSTALL.name();
		assertThat("INSTALL", is(name));
	}

	@Test
	public void enumTest2() {
		ReleaseType type = ReleaseType.valueOf("INSTALL");
		assertThat(type, is(ReleaseType.INSTALL));
	}
}
