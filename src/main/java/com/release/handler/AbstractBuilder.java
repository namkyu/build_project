package com.release.handler;

import com.release.vo.DataVO;


/**
 * @FileName : AbstractBuilder.java
 * @Project : my_project_release
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 : abstract
 */
public abstract class AbstractBuilder extends CommonBuilder {

    /**
     * <pre>
     * build
     * 빌드 start
     * <pre>
     * @param data
     */
    public void build(DataVO data) {
        // before process
        boolean isSuccess = preHandle(data);
        if (isSuccess == false) {
            System.out.println("##build## (before process failed) isSucces=" + isSuccess + ", type=" + data.getType() + ", releaseNum=" + data.getReleaseNum());
            return;
        }

        // process
        process();
        // after process
        postHandle();
    }

    /**
     * <pre>
     * preHandle
     * 전 처리
     * <pre>
     * @param data
     * @return
     */
    protected abstract boolean preHandle(DataVO data);

    /**
     * <pre>
     * process
     * 처리 진행
     * <pre>
     */
    protected abstract void process();

    /**
     * <pre>
     * postHandle
     * 후 처리
     * <pre>
     */
    protected abstract void postHandle();

    /**
     * <pre>
     * error
     * 에러 처리
     * <pre>
     */
    protected abstract void error();
}
