package com.company.springdemo.common.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * FileUtil
 *
 * @author whs
 * @date 2018/8/16 下午5:26
 **/
public class FileUtil {

    /**
     * 重试最大次数
     */
    public static final int TRY_MAX_COUNT = 3;
    /**
     * 重试最小次数
     */
    public static final int TRY_MIN_COUNT = 1;

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 写文件带重试的
     *
     * @param filePath 文件地址
     * @param content 内容
     * @param tryCount 重试次数
     * @return 是否成功
     */
    public static boolean writeFile(String filePath, String content, int tryCount) {
        return writeFile(filePath, content, tryCount, false);
    }

    /**
     * 写文件带重试的
     *
     * @param filePath 文件地址
     * @param content 内容
     * @param tryCount 重试次数
     * @param append 是否追加
     * @return 是否成功
     */
    public static boolean writeFile(String filePath, String content, int tryCount, boolean append) {
        boolean success = writeFile(filePath, content, append);

        //失败并且可以重试
        boolean failAndCanRetry = !success && tryCount > TRY_MIN_COUNT;
        if (failAndCanRetry) {
            return writeFile(filePath, content, --tryCount);
        } else {
            return success;
        }
    }

    /**
     * 写文件
     *
     * @param filePath 文件地址
     * @param content 内容
     * @return 是否成功
     */
    public static boolean writeFile(String filePath, String content, boolean append) {
        logger.info("start to writeFile, filePath:{}", filePath);

        File f = new File(filePath);
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            //true表示可以追加新内容
            fw = new FileWriter(f.getAbsoluteFile(), append);
            bw = new BufferedWriter(fw);
            bw.write(content);

            return true;
        } catch (Exception e) {
            logger.error("write file error: ", e);
            return false;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception e) {
                //ignore
            }
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                //ignore
            }
        }
    }

    /**
     * 从资源目录里面拷贝文件
     */
    public static boolean copyFileFromResource(String location, String destPath)
        throws IOException {
        logger.info("start to copyFileFromResource for location: {}, destPath: {}", location,
            destPath);

        Resource fileResource = new PathMatchingResourcePatternResolver().getResource(location);
        InputStream inputStream = fileResource.getInputStream();
        FileUtils.copyInputStreamToFile(inputStream, new File(destPath));
        return true;
    }

    /**
     * 复制文件
     *
     * @param currentPath 想要复制的文件(全路径, 含文件名)
     * @param destPath 目标路径(全路径, 含文件名)
     * @return 是否成功
     */
    public static boolean copyFile(String currentPath, String destPath) {
        File current = new File(currentPath);
        File dest = new File(destPath);

        boolean result = true;
        try {
            FileUtils.copyFile(current, dest);
        } catch (IOException e) {
            logger.error("copy file failed", e);
            result = false;
        }
        return result;
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            logger.info("delete file {} failed", fileName);
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            } else {
                return deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                logger.error("delete file {} failed", fileName);
                return false;
            }
        } else {
            logger.info("file {} not exist", fileName);
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            logger.error("directory {} not exist, delete failed", dir);
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtil.deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            logger.error("delete directory failed");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 强制创建文件
     *
     * @param filePath 文件地址
     * @return 是否成功
     */
    public static void forceCreateFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            // 判断父目录是否存在
            if (!file.getParentFile().exists()) {
                // 不存在：先创建父目录文件夹，再创建指定的文件。
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                file.createNewFile();
            }
        }
    }


    /**
     * 读取文件内容
     * @param filePath
     * @return
     */
    public static List<String> getDataFromFilePath(String filePath){
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
            return lines;
        }catch (IOException e){
            logger.error("从文件:{},读取数据异常",filePath);
            return null;
        }
    }
}
