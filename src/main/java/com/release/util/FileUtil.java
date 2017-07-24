package com.release.util;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import static com.release.common.BaseType.CHARACTER_SET;

/**
 * @FileName : FileUtil.java
 * @Project : my_project_release
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class FileUtil {

    /**
     * <pre>
     * nioCopy
     *
     * <pre>
     * @param srcPath
     * @param destPath
     */
    public static void nioCopy(String srcPath, String destPath) {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = new FileInputStream(new File(srcPath)).getChannel();
            outChannel = new FileOutputStream(new File(destPath)).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            FileUtil.close(inChannel);
            FileUtil.close(outChannel);
        }

    }

    /**
     * <pre>
     * close
     *
     * <pre>
     * @param channel
     */
    public static void close(FileChannel channel) {
        try {
            if (channel != null) {
                channel.close();
            }
        } catch (IOException e) {
        }
    }

    /**
     * <pre>
     * readFile
     *
     * <pre>
     * @param readFile
     * @return
     */
    public static List<String> readFile(String readFile) {
        BufferedReader br = null;
        List<String> fileList = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(readFile));
            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                fileList.add(readLine);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return fileList;
    }

    /**
     * <pre>
     * writeMsg
     *
     * <pre>
     * @param msg
     * @param path
     */
    public static void writeMsg(String msg, String path) {
        BufferedWriter bf = null;
        try {
            bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path), true), CHARACTER_SET));
            bf.write(msg);
            bf.newLine();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}