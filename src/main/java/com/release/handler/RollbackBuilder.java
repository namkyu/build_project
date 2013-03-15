package com.release.handler;

import java.io.IOException;
import java.util.List;

import com.release.anno.Action;
import com.release.core.BaseType;
import com.release.util.FileUtil;
import com.release.vo.DataVO;


/**
 * @FileName : RollbackBuilder.java
 * @Project : TEST_PROJECT
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
@Action("rollback")
public class RollbackBuilder extends AbstractBuilder {

	/** data */
	private DataVO data;

	@Override
	protected boolean preHandle(DataVO dataVO) {
		System.out.println("#########################################################");
		System.out.println("## ROLLBACK");
		System.out.println("#########################################################");

		this.data = dataVO;
		return true;
	}

	/**
	 * 롤백
	 */
	@Override
	protected void process() throws IOException {
		String rollbackFile = makePath(BaseType.ROLLBACK_FILE_NAME, data.getReleaseNum());
		List<String> csvRollbackFilePathList = FileUtil.readFile(rollbackFile);

		for (String csvRollbackFilePath : csvRollbackFilePathList) {
			String rollbaFilePath = csvRollbackFilePath.split(BaseType.SEPARATOR)[0];
			String backupFilePath = csvRollbackFilePath.split(BaseType.SEPARATOR)[1];

			String destDir = getDirPath(rollbaFilePath);
			makeDir(destDir);

			System.out.println("##process##(rollback file copy) backupFilePath=" + backupFilePath + ", rollbaFilePath=" + rollbaFilePath);
			FileUtil.nioCopy(backupFilePath, rollbaFilePath);
		}
	}

	@Override
	protected void postHandle() {

	}

	@Override
	protected void error() {

	}

}
