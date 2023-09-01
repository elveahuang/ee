package cn.elvea.platform.commons.core.storage.local;

import cn.elvea.platform.commons.core.exception.ServiceException;
import cn.elvea.platform.commons.core.storage.AbstractStorageService;
import cn.elvea.platform.commons.core.storage.StorageService;
import cn.elvea.platform.commons.core.storage.StorageUtils;
import cn.elvea.platform.commons.core.storage.domain.FileObject;
import cn.elvea.platform.commons.core.storage.domain.FileParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @author elvea
 * @see LocalStorageService
 * @see StorageService
 * @since 0.0.1
 */
@Slf4j
public class LocalStorageServiceImpl extends AbstractStorageService implements LocalStorageService {

    private final LocalStorageConfig config;

    public LocalStorageServiceImpl(LocalStorageConfig config) {
        this.config = config;
    }

    /**
     * @see StorageService#getFile(String)
     */
    @Override
    public FileObject<?> getFile(String path, boolean withLocalTempFile) {
        File file = new File(this.config.getPath(), path);
        return LocalFileObject.builder().object(file).build();
    }

    /**
     * @see StorageService#uploadFile(InputStream, FileParameter, String)
     */
    @Override
    public FileObject<?> uploadFile(InputStream is, FileParameter params, String path) throws Exception {
        try {
            File file = new File(this.config.getPath() + StorageUtils.SEPARATOR + path);

            File parent = file.getParentFile();
            if (parent != null && !parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException("目录 '" + parent + "' 创建失败");
            }
            FileCopyUtils.copy(is, Files.newOutputStream(file.toPath()));

            return LocalFileObject.builder().key(path).object(file).build();
        } catch (Exception e) {
            throw new ServiceException("上传文件失败：", e);
        }
    }

}
