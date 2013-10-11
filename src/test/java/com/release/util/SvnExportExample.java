package com.release.util;

import java.io.File;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * @FileName : SvnExportExample.java
 * @Project : build_project
 * @Date : 2013. 10. 10.
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class SvnExportExample {

	private static final String SVN_URL = "https://dev.naver.com/svn/namkyu/trunk/JenkinsTest";
	private static final String destPath = "E:\\test\\svn_client";
	private static final String username = "lng1982";
	private static final String password = "rbdn2001";
	private SVNRepository repository;

	/**
	 * <pre>
	 * main
	 *
	 * <pre>
	 * @param args
	 */
	public static void main(String[] args) {
		SvnExportExample app = new SvnExportExample();
		app.doSvnKit(username, password);
	}

	/**
	 * <pre>
	 * doSvnKit
	 *
	 * <pre>
	 * @param username
	 * @param password
	 */
	private void doSvnKit(String username, String password) {
		DAVRepositoryFactory.setup();
		try {
			SVNURL url = SVNURL.parseURIDecoded(SVN_URL);
			repository = SVNRepositoryFactory.create(url);
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);
			repository.setAuthenticationManager(authManager);

			long latestRevision = repository.getLatestRevision();
			System.out.println("##doSvnKit## ROOT=" + repository.getRepositoryRoot(true) + ", UUID=" + repository.getRepositoryUUID(true) + ", latestRevision=" + latestRevision);

			SVNClientManager ourClientManager = SVNClientManager.newInstance();
			ourClientManager.setAuthenticationManager(authManager);

			SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
			updateClient.setIgnoreExternals(false);
			updateClient.doExport(repository.getLocation()
					, new File(destPath)
					, SVNRevision.create(latestRevision)
					, SVNRevision.create(latestRevision)
					, null
					, true
					, SVNDepth.INFINITY);

		} catch (SVNException ex) {
			throw new RuntimeException(ex);
		}
	}
}
