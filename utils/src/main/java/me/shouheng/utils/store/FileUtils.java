package me.shouheng.utils.store;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import me.shouheng.utils.data.StringUtils;

/**
 * 用来处理文件的拷贝、增加和删除等逻辑
 *
 * @author WngShhng 2019-05-08 21:30
 */
public final class FileUtils {

    private static final String LINE_SEP = System.getProperty("line.separator");

    /*------------------------------------- info methods ----------------------------------------*/

    public static File getFileByPath(final String filePath) {
        return StringUtils.isSpace(filePath) ? null : new File(filePath);
    }

    public static boolean isFileExists(final String filePath) {
        return isFileExists(getFileByPath(filePath));
    }

    public static boolean isFileExists(final File file) {
        return file != null && file.exists();
    }

    public static boolean isFile(final String filePath) {
        return isFile(getFileByPath(filePath));
    }

    public static boolean isFile(final File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static boolean isDir(final String dirPath) {
        return isDir(getFileByPath(dirPath));
    }

    public static boolean isDir(final File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    /*------------------------------------- curd methods ----------------------------------------*/

    public static boolean createOrExistsDir(final String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }

    /**
     * 判断指定的文件是否存在，如果不存在就创建它
     *
     * @param file 指定的文件
     * @return 该文件是否存在，true 表示存在
     */
    public static boolean createOrExistsFile(final File file) {
        if (file == null) return false;
        if (file.exists()) return file.isFile();
        // 判断父目录是否是文件夹，如果不存在就创建它
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            // 创建文件
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createFileByDeleteOldFile(final String filePath) {
        return createFileByDeleteOldFile(getFileByPath(filePath));
    }

    /**
     * Create a file if it doesn't exist, otherwise delete old file before creating.
     *
     * @param file The file.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean createFileByDeleteOldFile(final File file) {
        if (file == null) return false;
        // file exists and unsuccessfully delete then return false
        if (file.exists() && !file.delete()) return false;
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean rename(final File file, final String newName) {
        // file is null then return false
        if (file == null) return false;
        // file doesn't exist then return false
        if (!file.exists()) return false;
        // the new name is space then return false
        if (StringUtils.isSpace(newName)) return false;
        // the new name equals old name then return true
        if (newName.equals(file.getName())) return true;
        File newFile = new File(file.getParent() + File.separator + newName);
        // the new name of file exists then return false
        return !newFile.exists() && file.renameTo(newFile);
    }

    public static boolean copyFile(final String srcFilePath, final String destFilePath) {
        return copyFile(getFileByPath(srcFilePath), getFileByPath(destFilePath));
    }

    public static boolean copyFile(final String srcFilePath, final String destFilePath,
                                   final OnReplaceListener listener) {
        return copyFile(getFileByPath(srcFilePath), getFileByPath(destFilePath), listener);
    }

    public static boolean copyFile(final File srcFile, final File destFile) {
        return copyOrMoveFile(srcFile, destFile, false);
    }

    public static boolean copyFile(final File srcFile, final File destFile,
                                   final OnReplaceListener listener) {
        return copyOrMoveFile(srcFile, destFile, listener, false);
    }

    public static boolean moveFile(final String srcFilePath, final String destFilePath) {
        return moveFile(getFileByPath(srcFilePath), getFileByPath(destFilePath));
    }

    public static boolean moveFile(final String srcFilePath, final String destFilePath,
                                   final OnReplaceListener listener) {
        return moveFile(getFileByPath(srcFilePath), getFileByPath(destFilePath), listener);
    }

    public static boolean moveFile(final File srcFile, final File destFile) {
        return copyOrMoveFile(srcFile, destFile, true);
    }

    public static boolean moveFile(final File srcFile, final File destFile,
                                   final OnReplaceListener listener) {
        return copyOrMoveFile(srcFile, destFile, listener, true);
    }

    public static boolean copyDir(final String srcDirPath, final String destDirPath) {
        return copyDir(getFileByPath(srcDirPath), getFileByPath(destDirPath));
    }

    public static boolean copyDir(final String srcDirPath, final String destDirPath,
                                  final OnReplaceListener listener) {
        return copyDir(getFileByPath(srcDirPath), getFileByPath(destDirPath), listener);
    }

    public static boolean copyDir(final File srcDir, final File destDir) {
        return copyOrMoveDir(srcDir, destDir, false);
    }

    public static boolean copyDir(final File srcDir, final File destDir,
                                  final OnReplaceListener listener) {
        return copyOrMoveDir(srcDir, destDir, listener, false);
    }

    public static boolean moveDir(final String srcDirPath, final String destDirPath) {
        return moveDir(getFileByPath(srcDirPath), getFileByPath(destDirPath));
    }

    public static boolean moveDir(final String srcDirPath, final String destDirPath,
                                  final OnReplaceListener listener) {
        return moveDir(getFileByPath(srcDirPath), getFileByPath(destDirPath), listener);
    }

    public static boolean moveDir(final File srcDir, final File destDir) {
        return copyOrMoveDir(srcDir, destDir, true);
    }

    public static boolean moveDir(final File srcDir, final File destDir,
                                  final OnReplaceListener listener) {
        return copyOrMoveDir(srcDir, destDir, listener, true);
    }

    public static boolean delete(final String filePath) {
        return delete(getFileByPath(filePath));
    }

    public static boolean delete(final File file) {
        if (file == null) return false;
        if (file.isDirectory()) {
            return deleteDir(file);
        }
        return deleteFile(file);
    }

    public static boolean deleteDir(final String dirPath) {
        return deleteDir(getFileByPath(dirPath));
    }

    public static boolean deleteDir(final File dir) {
        if (dir == null) return false;
        // dir doesn't exist then return true
        if (!dir.exists()) return true;
        // dir isn't a directory then return false
        if (!dir.isDirectory()) return false;
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete()) return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir(file)) return false;
                }
            }
        }
        return dir.delete();
    }

    public static boolean deleteFile(final String srcFilePath) {
        return deleteFile(getFileByPath(srcFilePath));
    }

    public static boolean deleteFile(final File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());
    }

    public static boolean deleteAllInDir(final String dirPath) {
        return deleteAllInDir(getFileByPath(dirPath));
    }

    public static boolean deleteAllInDir(final File dir) {
        return deleteFilesInDirWithFilter(dir, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        });
    }

    public static boolean deleteFilesInDir(final String dirPath) {
        return deleteFilesInDir(getFileByPath(dirPath));
    }

    public static boolean deleteFilesInDir(final File dir) {
        return deleteFilesInDirWithFilter(dir, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
    }

    public static boolean deleteFilesInDirWithFilter(final String dirPath, final FileFilter filter) {
        return deleteFilesInDirWithFilter(getFileByPath(dirPath), filter);
    }

    public static boolean deleteFilesInDirWithFilter(final File dir, final FileFilter filter) {
        if (dir == null) return false;
        // dir doesn't exist then return true
        if (!dir.exists()) return true;
        // dir isn't a directory then return false
        if (!dir.isDirectory()) return false;
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (filter.accept(file)) {
                    if (file.isFile()) {
                        if (!file.delete()) return false;
                    } else if (file.isDirectory()) {
                        if (!deleteDir(file)) return false;
                    }
                }
            }
        }
        return true;
    }

    public interface OnReplaceListener {
        boolean onReplace();
    }

    /*------------------------------------- inner methods ---------------------------------------*/

    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static boolean copyOrMoveFile(final File srcFile, final File destFile, final boolean isMove) {
        return copyOrMoveFile(srcFile, destFile, new OnReplaceListener() {
            @Override
            public boolean onReplace() {
                return true;
            }
        }, isMove);
    }

    private static boolean copyOrMoveFile(final File srcFile, final File destFile,
                                          final OnReplaceListener listener, final boolean isMove) {
        if (srcFile == null || destFile == null) return false;
        // srcFile equals destFile then return false
        if (srcFile.equals(destFile)) return false;
        // srcFile doesn't exist or isn't a file then return false
        if (!srcFile.exists() || !srcFile.isFile()) return false;
        if (destFile.exists()) {
            if (listener == null || listener.onReplace()) {// require delete the old file
                if (!destFile.delete()) {// unsuccessfully delete then return false
                    return false;
                }
            } else {
                return true;
            }
        }
        if (!createOrExistsDir(destFile.getParentFile())) return false;
        try {
            return writeFileFromIS(destFile, new FileInputStream(srcFile))
                    && !(isMove && !deleteFile(srcFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean copyOrMoveDir(final File srcDir, final File destDir, final boolean isMove) {
        return copyOrMoveDir(srcDir, destDir, new OnReplaceListener() {
            @Override
            public boolean onReplace() {
                return true;
            }
        }, isMove);
    }

    private static boolean copyOrMoveDir(final File srcDir, final File destDir,
                                         final OnReplaceListener listener, final boolean isMove) {
        if (srcDir == null || destDir == null) return false;
        // destDir's path locate in srcDir's path then return false
        String srcPath = srcDir.getPath() + File.separator;
        String destPath = destDir.getPath() + File.separator;
        if (destPath.contains(srcPath)) return false;
        if (!srcDir.exists() || !srcDir.isDirectory()) return false;
        if (destDir.exists()) {
            if (listener == null || listener.onReplace()) {// require delete the old directory
                if (!deleteAllInDir(destDir)) {// unsuccessfully delete then return false
                    return false;
                }
            } else {
                return true;
            }
        }
        if (!createOrExistsDir(destDir)) return false;
        File[] files = srcDir.listFiles();
        for (File file : files) {
            File oneDestFile = new File(destPath + file.getName());
            if (file.isFile()) {
                if (!copyOrMoveFile(file, oneDestFile, listener, isMove)) return false;
            } else if (file.isDirectory()) {
                if (!copyOrMoveDir(file, oneDestFile, listener, isMove)) return false;
            }
        }
        return !isMove || deleteDir(srcDir);
    }

    private static boolean writeFileFromIS(final File file,
                                           final InputStream is) {
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte data[] = new byte[8192];
            int len;
            while ((len = is.read(data, 0, 8192)) != -1) {
                os.write(data, 0, len);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private FileUtils() {
        throw new UnsupportedOperationException("u can't initialize me!");
    }
}
