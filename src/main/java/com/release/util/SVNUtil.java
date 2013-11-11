/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
 * The Class SVNUtil.
 */
public class SVNUtil {

	/** repository */
	private String repositoryUrl;
	/** id */
	private String id;
	/** password */
	private String password;

	/**
	 * @param repositoryUrl
	 * @param id
	 * @param password
	 */
	public SVNUtil(String repositoryUrl, String id, String password) {
		this.repositoryUrl = repositoryUrl;
		this.id = id;
		this.password = password;
	}

	/**
	 * <pre>
	 * getSVNHistory
	 *
	 * <pre>
	 * @param revision
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getSVNHistory(int revision) {
		long startRevision = revision;
		long endRevision = startRevision;
		List<String> historyList = new ArrayList<String>();

		// library setup
		setupLibrary();

		try {
			// Repository
			SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(repositoryUrl));

			// 인증
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(id, password);
			repository.setAuthenticationManager(authManager);

			// Repository history
			Collection<SVNLogEntry> logEntries = repository.log(new String[] { "" }, null, startRevision, endRevision, true, true);
			for (Iterator<SVNLogEntry> entries = logEntries.iterator(); entries.hasNext();) {
				SVNLogEntry logEntry = entries.next();

				if (logEntry.getChangedPaths().size() > 0) {
					Set<String> changedPathsSet = logEntry.getChangedPaths().keySet();

					for (Iterator<String> changedPaths = changedPathsSet.iterator(); changedPaths.hasNext();) {
						SVNLogEntryPath entryPath = (SVNLogEntryPath) logEntry.getChangedPaths().get(changedPaths.next());
						String filePath = entryPath.getPath();
						historyList.add(filePath);
					}
				}
			}

		} catch (SVNException ex) {
			new RuntimeException(ex);
		}

		return historyList;
	}

	/**
	 * <pre>
	 * setupLibrary
	 *
	 * <pre>
	 */
	private void setupLibrary() {
		DAVRepositoryFactory.setup();
		SVNRepositoryFactoryImpl.setup();
		FSRepositoryFactory.setup();
	}
}
