package cc.elvea.platform.commons.core.storage.utils;

import cc.elvea.platform.commons.constants.DateTimeConstants;
import cc.elvea.platform.commons.core.storage.model.FileParameter;
import cc.elvea.platform.commons.utils.DateTimeUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author elvea
 */
@Slf4j
public abstract class StorageUtils {

    public static String generateFilename(String filename) {
        return StringUtils.simpleUuid() + "." + FilenameUtils.getExtension(filename);
    }

    public static String generateFilename(FileParameter parameter) {
        String filename = parameter.getFilename();
        if (StringUtils.isEmpty(filename)) {
            filename = generateFilename(parameter.getOriginalFilename());
        }
        return filename;
    }

    public static String generatePath(FileParameter parameter) {
        String path = parameter.getPath();
        if (StringUtils.isEmpty(path)) {
            path = DateTimeUtils.format(LocalDateTime.now(), DateTimeConstants.Pattern.SIMPLE_DATE);
        }
        return path;
    }

    public static String generateKey(FileParameter parameter, String name, String path) {
        String key = parameter.getKey();
        if (StringUtils.isEmpty(key)) {
            return path + "/" + name;
        }
        return key;
    }

    public static String generateFileKey(FileParameter parameter) {
        String suffix = FilenameUtils.getExtension(parameter.getOriginalFilename());
        String uuid = IdUtil.simpleUUID();
        String path = DateTimeUtils.format(LocalDateTime.now(), DateTimeConstants.Pattern.SIMPLE_DATE);
        return path + "/" + uuid + "." + suffix;
    }

    public static String generateExtFilename(String ext) {
        return StringUtils.simpleUuid() + "." + ext;
    }

    /**
     * 新建本地临时文件夹
     */
    public static File newTempFolder() {
        File tmpFile = new File(FileUtils.getTempDirectoryPath(), StringUtils.simpleUuid());
        if (tmpFile.exists()) {
            try {
                FileUtils.forceDelete(tmpFile);
            } catch (IOException e) {
                log.error("file already exists, but delete failed!", e);
            }
        }
        if (tmpFile.mkdirs()) {
            return tmpFile;
        }
        return null;
    }

    /**
     * 新建本地临时文件
     */
    public static File newTempFile(String filename) throws Exception {
        File tmpFile = new File(FileUtils.getTempDirectoryPath(), filename);
        // 强制建立目录，避免目录不存在报错
        FileUtils.forceMkdirParent(tmpFile);
        // 临时文件如果已经存在，强制删除，重新创建文件
        if (tmpFile.exists()) {
            try {
                FileUtils.forceDelete(tmpFile);
            } catch (IOException e) {
                log.error("Fail to create temp file [{}].", filename, e);
            }
        }
        if (tmpFile.createNewFile()) {
            log.info("create new file {}.", tmpFile.getAbsolutePath());
        }
        return tmpFile;
    }

}
