package com.release.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @FileName : Conf.java
 * @Project : build_project
 * @Date : 2013. 3. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class Conf {

    private static Properties mPro = null;
    public static String confFileName = "conf.xml";

    /**
     * <pre>
     * init
     *
     * <pre>
     * @throws IOException
     */
    public static void init() throws IOException {
        InputStream in = new FileInputStream(confFileName);
        Conf conf = new Conf();
        mPro = conf.loadProperties(in);
    }

    /**
     * <pre>
     * loadProperties
     *
     * <pre>
     * @param in
     * @return
     * @throws IOException
     */
    private Properties loadProperties(InputStream in) throws IOException {
        Properties properties = new Properties();
        properties.loadFromXML(in);
        return properties;
    }

    /**
     * <pre>
     * getValue
     *
     * <pre>
     * @param key
     * @return
     */
    public static String getValue(String key) {
        return mPro.getProperty(key);
    }

    /**
     * <pre>
     * getIntValue
     *
     * <pre>
     * @param string
     * @return
     */
    public static int getIntValue(String string) {
        String value = mPro.getProperty(string);
        if (value != null) {
            return Integer.parseInt(value);
        }
        return 0;
    }
}
