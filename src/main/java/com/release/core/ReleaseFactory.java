package com.release.core;

import com.release.handler.AbstractBuilder;
import com.release.handler.InstallBuilder;
import com.release.handler.PackageBuilder;
import com.release.handler.RollbackBuilder;


/**
 * @FileName : ReleaseFactory.java
 * @Project : TEST_PROJECT
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class ReleaseFactory {

	/**
	 * <pre>
	 * getInstance
	 * 어노테이션으로 변경
	 * <pre>
	 * @param instanceCode
	 * @return
	 */
	@Deprecated
	public static AbstractBuilder createInstance(String instanceCode) {
		if (BaseType.PACKAGE.equals(instanceCode)) {
			return new PackageBuilder();
		} else if (BaseType.INSTALL.equals(instanceCode)) {
			return new InstallBuilder();
		} else if (BaseType.ROLLBACK.equals(instanceCode)) {
			return new RollbackBuilder();
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		AbstractBuilder builder = ReleaseFactory.createInstance("backup");
		System.out.println(builder);
	}
}
