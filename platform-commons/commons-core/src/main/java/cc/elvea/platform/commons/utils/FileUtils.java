package cc.elvea.platform.commons.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.FileSystems;

/**
 * @author elvea
 */
public class FileUtils {

    public static final FileNameMap FILE_NAME_MAP = URLConnection.getFileNameMap();

    public static final String SEPARATOR = FileSystems.getDefault().getSeparator();

    public static FileInputStream openInputStream(final File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (!file.canRead()) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        return new FileInputStream(file);
    }

    public static String getExtension(final File file) {
        return FilenameUtils.getExtension(file.getName());
    }

    public static long getFileSize(final File file) {
        return org.apache.commons.io.FileUtils.sizeOf(file);
    }

    public static String getContentType(final File file) {
        return getContentType(file.getName());
    }

    public static String getContentType(final String filename) {
        return FILE_NAME_MAP.getContentTypeFor(filename);
    }

}
