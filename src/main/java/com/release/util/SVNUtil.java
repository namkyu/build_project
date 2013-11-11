package com.release.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
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
 * @FileName : SVNUtil.java
 * @Project : build_project
 * @Date : 2013. 11. 6.
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class SVNUtil {

	private String repositoryUrl;
	private String id;
	private String password;

	public SVNUtil(String repositoryUrl, String id, String password) {
		this.repositoryUrl = repositoryUrl;
		this.id = id;
		this.password = password;
	}

	public List<String> getSVNHistory(int revision) {
		long startRevision = revision;
		long endRevision = startRevision;
		List<String> historyList = new ArrayList<String>();

		setupLibrary();

		try {
			SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(repositoryUrl));

			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(id, password);
			repository.setAuthenticationManager(authManager);

			Collection<SVNLogEntry> logEntries = repository.log(new String[] { "" }, null, startRevision, endRevision, true, true);
			for (Iterator<SVNLogEntry> entries = logEntries.iterator(); entries.hasNext();) {
				SVNLogEntry logEntry = entries.next();

				if (logEntry.getChangedPaths().size() > 0) {
					Set changedPathsSet = logEntry.getChangedPaths().keySet();

					for (Iterator changedPaths = changedPathsSet.iterator(); changedPaths.hasNext();) {
						SVNLogEntryPath entryPath = (SVNLogEntryPath) logEntry.getChangedPaths().get(changedPaths.next());
						String filePath = entryPath.getPath();
						if (filePath.lastIndexOf(".java") == -1) {
							historyList.add(Conf.getValue("local.workspace") + filePath.replaceFirst("/trunk", StringUtils.EMPTY));
						}
					}
				}
			}

		} catch (SVNException e) {
			e.printStackTrace();
		}

		return historyList;
	}

	private static void setupLibrary() {
		DAVRepositoryFactory.setup();
		SVNRepositoryFactoryImpl.setup();
		FSRepositoryFactory.setup();
	}
}
