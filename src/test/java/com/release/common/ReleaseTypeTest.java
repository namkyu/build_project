package com.release.common;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * @FileName : ReleaseTypeTest.java
 * @Project : my_project_release
 * @Date : 2013. 3. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class ReleaseTypeTest {

    @Test
    public void enumValuesTest() {
        ReleaseType[] types = ReleaseType.values();
        assertThat(ReleaseType.PACKAGE, is(types[0]));
        assertThat(ReleaseType.INSTALL, is(types[1]));
    }

    @Test
    public void enumName() {
        String name = ReleaseType.INSTALL.name();
        assertThat("INSTALL", is(name));
    }

    @Test
    public void enum_valueOf_test() {
        ReleaseType type = ReleaseType.valueOf("INSTALL");
        assertThat(type, is(ReleaseType.INSTALL));
    }
}
