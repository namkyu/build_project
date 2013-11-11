package com.release.core;

import com.release.common.ReleaseType;
import com.release.handler.AbstractBuilder;
import com.release.handler.FtpPutBuilder;
import com.release.handler.InstallBuilder;
import com.release.handler.PackageBuilder;
import com.release.handler.RollbackBuilder;
import com.release.handler.SVNHistoryBuilder;


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
	 * createInstance
	 *
	 * <pre>
	 * @param type
	 * @return
	 */
	public static AbstractBuilder createInstance(ReleaseType type) {
		if (ReleaseType.PACKAGE == type) {
			return new PackageBuilder();
		} else if (ReleaseType.INSTALL == type) {
			return new InstallBuilder();
		} else if (ReleaseType.ROLLBACK == type) {
			return new RollbackBuilder();
		} else if (ReleaseType.PUTALL == type) {
			return new FtpPutBuilder();
		} else if (ReleaseType.SVN_HISTORY == type) {
			return new SVNHistoryBuilder();
		}

		else {
			return null;
		}
	}
}
