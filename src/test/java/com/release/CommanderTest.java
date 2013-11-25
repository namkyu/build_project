/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.release.core.BuildHandler;
import com.release.util.Conf;


/**
 * The Class CommanderTest.
 */
public class CommanderTest {

	@Before
	public void init() {
		Conf.init();
	}

	@Test
	@Ignore
	public void packageTest() {
		String[] args = {"c=package", "p=E:/test/build/R001"};
		new BuildHandler(args).execute();
	}

	@Test
//	@Ignore
	public void installTest() {
		String[] args = {"c=install", "p=C:/Users/nklee/Downloads/R001"};
		new BuildHandler(args).execute();
	}

	@Test
	@Ignore
	public void rollTest() {
		String[] args = {"c=rollback", "p=E:/test/build/R001"};
		new BuildHandler(args).execute();
	}
}
