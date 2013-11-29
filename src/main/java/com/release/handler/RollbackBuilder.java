/*
 * Copyright (c) 2013 namkyu.
 * All right reserved.
 *
 */
package com.release.handler;

import static com.release.common.BaseType.*;

import java.io.File;
import java.util.List;

import com.release.core.AbstractBuilder;
import com.release.core.BufferedReaderCallback;
import com.release.util.FileUtil;
import com.release.vo.DataVO;


/**
 * The Class RollbackBuilder.
 */
public class RollbackBuilder extends AbstractBuilder {

	/** data */
	private DataVO data;

	/**
	 * <pre>
	 * preHandle
	 *
	 * <pre>
	 * @return
	 */
	@Override
	protected boolean preHandle() {
		System.out.println("#########################################################");
		System.out.println("## ROLLBACK");
		System.out.println("#########################################################");
		return true;
	}

	/**
	 * <pre>
	 * process
	 * 롤백
	 * <pre>
	 */
	@Override
	protected void process() {
		String rollbackFile = makePath(ROLLBACK_FILE_NAME, data.getReleaseNum());
		List<String> csvRollbackFilePathList = makePackageFilePathList(rollbackFile);

		for (String csvRollbackFilePath : csvRollbackFilePathList) {
			String rollbaFilePath = csvRollbackFilePath.split(SEPARATOR)[0];
			String backupFilePath = csvRollbackFilePath.split(SEPARATOR)[1];

			String destDir = new File(rollbaFilePath).getParent();
			makeDir(destDir);

			System.out.println("##process##(rollback file copy) backupFilePath=" + backupFilePath + ", rollbaFilePath=" + rollbaFilePath);
			FileUtil.nioCopy(backupFilePath, rollbaFilePath);
		}
	}

	/**
	 * <pre>
	 * makePackageFilePathList
	 *
	 * <pre>
	 * @param packageFile
	 * @return
	 */
	private List<String> makePackageFilePathList(String packageFile) {
		BufferedReaderCallback callback = new BufferedReaderCallback() {
			public String doSomethingWithReader(String line) {
				return line;
			}
		};

		return FileUtil.readFile(packageFile, callback);
	}

	/**
	 * <pre>
	 * postHandle
	 *
	 * <pre>
	 */
	@Override
	protected void postHandle() {

	}

	/**
	 * <pre>
	 * error
	 *
	 * <pre>
	 */
	@Override
	protected void error() {

	}

	/**
	 * <pre>
	 * valid
	 *
	 * <pre>
	 * @param dataVO
	 */
	@Override
	protected void valid(DataVO dataVO) {
		this.data = dataVO;
	}
}
