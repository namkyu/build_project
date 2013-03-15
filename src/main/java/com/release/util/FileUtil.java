package com.release.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import com.release.core.BaseType;

import org.apache.commons.io.FilenameUtils;

// TODO: Auto-generated Javadoc
/**
 * @FileName : FileUtil.java
 * @Project : TEST_PROJECT
 * @Date : 2012. 1. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
public class FileUtil {

    /**
     * Nio copy.
     *
     * @param srcPath the src path
     * @param destPath the dest path
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void nioCopy(String srcPath, String destPath) throws IOException {
        FileChannel inChannel = new FileInputStream(new File(srcPath)).getChannel();
        FileChannel outChannel = new FileOutputStream(new File(destPath)).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException e) {
            throw e;
        } finally {
            FileUtil.close(inChannel);
            FileUtil.close(outChannel);
        }

    }

    /**
     * 특정 디렉토리로 파일 이동, 파일명은 그대로, 이동후 원본은 삭제,.
     *
     * @param srcFilePath 경로와 파일명을 포함한 소스파일명
     * @param destFilePath 경로와 파일명을 포함한 대상 파일명
     * @return true, if nio move
     */
    public static boolean NioMove(String srcFilePath, String destFilePath) {
        File srcFile = new File(srcFilePath);
        if (srcFile.isFile() == false) {
            return false;
        }
        try {
            FileUtil.nioCopy(srcFilePath, destFilePath);
            srcFile.delete();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Close.
     *
     * @param channel the channel
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
     * Media을 저장하기 위한 폴더를 생성한다.
     *
     * @param root the root
     * @param mid the mid
     * @return 새로 생성되는 경로, root 경로는 제외
     */
    public static String createDatePath(String root, String mid) {
        StringBuilder sb = new StringBuilder();
        String newPath = null;
        try {
            sb.append(DateTimeUtil.getNowSimpleDateFormat("yyyy/MM/dd"));
            sb.append('/').append(mid);
            newPath = FilenameUtils.separatorsToSystem(sb.toString());

            sb.setLength(0);
            sb.append(root).append('/').append(newPath);
            String savePath = FilenameUtils.separatorsToSystem(sb.toString());

            new File(savePath).mkdirs();
        } catch (Exception e) {
            return null;
        }
        return newPath;
    }

    /**
     * Separators to system.
     *
     * @param path the path
     *
     * @return the string
     */
    public static String separatorsToSystem(String path) {
        return org.apache.commons.io.FilenameUtils.separatorsToSystem(path);
    }

    /**
     * Separators to url.
     *
     * @param path the path
     *
     * @return the string
     */
    public static String separatorsToUrl(String path) {
        return org.apache.commons.io.FilenameUtils.separatorsToUnix(path);
    }

    /**
     * <pre>
     * readTxtFile
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
				} catch (IOException e) {}
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
     * @throws Exception
     */
    public static void writeMsg(String msg, String path) throws Exception {
		BufferedWriter bf = null;
		try {
			bf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path), true), BaseType.CHARACTER_SET));
			bf.write(msg);
			bf.newLine();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (bf != null) {
				try {
					bf.close();
				} catch (IOException ex) {}
			}
		}
	}
}



