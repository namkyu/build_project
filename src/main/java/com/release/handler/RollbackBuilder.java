package com.release.handler;

import com.release.anno.Action;
import com.release.util.FileUtil;
import com.release.vo.DataVO;

import java.util.List;

import static com.release.common.BaseType.ROLLBACK_FILE_NAME;
import static com.release.common.BaseType.SEPARATOR;


/**
 * @FileName : RollbackBuilder.java
 * @Project : my_project_release
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
@Action(value = "ROLLBACK")
public class RollbackBuilder extends AbstractBuilder {

    /**
     * data
     */
    private DataVO data;

    /**
     * <pre>
     * preHandle
     *
     * <pre>
     * @param dataVO
     * @return
     */
    @Override
    protected boolean preHandle(DataVO dataVO) {
        System.out.println("#########################################################");
        System.out.println("## ROLLBACK");
        System.out.println("#########################################################");

        this.data = dataVO;
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
        List<String> csvRollbackFilePathList = FileUtil.readFile(rollbackFile);

        for (String csvRollbackFilePath : csvRollbackFilePathList) {
            String rollbaFilePath = csvRollbackFilePath.split(SEPARATOR)[0];
            String backupFilePath = csvRollbackFilePath.split(SEPARATOR)[1];

            String destDir = getDirPath(rollbaFilePath);
            makeDir(destDir);

            System.out.println("##process##(rollback file copy) backupFilePath=" + backupFilePath + ", rollbaFilePath=" + rollbaFilePath);
            FileUtil.nioCopy(backupFilePath, rollbaFilePath);
        }
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

}
