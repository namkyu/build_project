/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.core;

import com.release.common.ReleaseType;
import com.release.handler.EncryptHandler;
import com.release.handler.FtpPutBuilder;
import com.release.handler.InstallBuilder;
import com.release.handler.PackageBuilder;
import com.release.handler.RollbackBuilder;
import com.release.handler.SVNHistoryBuilder;


/**
 * A factory for creating Release objects.
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
		} else if (ReleaseType.ENCRYPT == type) {
			return new EncryptHandler();
		}

		return null;
	}
}
