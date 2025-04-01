package cc.elvea.platform.system.attachment.controller.app;

import cc.elvea.platform.commons.annotations.Authenticated;
import cc.elvea.platform.commons.annotations.OperationLog;
import cc.elvea.platform.commons.base.R;
import cc.elvea.platform.commons.core.storage.StorageFactory;
import cc.elvea.platform.commons.core.storage.model.FileObject;
import cc.elvea.platform.commons.enums.ResponseCodeEnum;
import cc.elvea.platform.commons.exception.ServiceException;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.commons.web.controller.AbstractController;
import cc.elvea.platform.system.attachment.api.AttachmentApi;
import cc.elvea.platform.system.attachment.model.AttachmentFile;
import cc.elvea.platform.system.attachment.model.AttachmentParameter;
import cc.elvea.platform.system.attachment.model.request.AttachmentRelationRequest;
import cc.elvea.platform.system.attachment.model.request.AttachmentTypeRequest;
import cc.elvea.platform.system.attachment.model.vo.AttachmentTypeVo;
import cc.elvea.platform.system.attachment.model.vo.AttachmentVo;
import com.google.common.collect.Lists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;
import java.util.List;

import static cc.elvea.platform.commons.constants.AttachmentConstants.DEFAULT_EDITOR_EXT;
import static cc.elvea.platform.system.commons.constants.SystemMappingConstants.*;

/**
 * @author elvea
 */
@RestController
@AllArgsConstructor
@Tag(name = "AttachmentController", description = "附件控制器")
public class AttachmentController extends AbstractController {

    private final StorageFactory storage;

    private final AttachmentApi attachmentApi;

    @Authenticated
    @Operation(summary = "获取附件类型")
    @ApiResponse(description = "获取附件类型")
    @OperationLog("获取附件类型")
    @PostMapping(API_V1__ATTACHMENT__TYPE)
    public R<AttachmentTypeVo> getAttachmentType(AttachmentTypeRequest request) {
        return R.success(attachmentApi.getAttachmentType(request));
    }

    @Authenticated
    @Operation(summary = "获取附件")
    @ApiResponse(description = "获取附件")
    @OperationLog("获取附件")
    @PostMapping(API_V1__ATTACHMENT__GET)
    public R<AttachmentVo> getAttachment(AttachmentRelationRequest request) {
        return R.success(attachmentApi.getAttachment(request));
    }

    @Authenticated
    @OperationLog("附件上传")
    @Operation(summary = "附件上传")
    @ApiResponse(description = "附件上传")
    @PostMapping(API_V1__ATTACHMENT__UPLOAD_FILE)
    public R<?> uploadAttachmentFile(@RequestParam("file") MultipartFile file) throws Exception {
        if (StringUtils.isNotEmpty(file.getOriginalFilename()) && FilenameUtils.isExtension(file.getOriginalFilename().toLowerCase(), DEFAULT_EDITOR_EXT)) {
            FileObject<?> object = storage.getStorageService().uploadFile(file);
            AttachmentVo vo = AttachmentVo.builder().url(object.getUrl()).build();
            return R.success(vo);
        }
        throw new ServiceException(ResponseCodeEnum.ATTACHMENT_LIMIT_ERROR);
    }

    @Authenticated
    @OperationLog("附件上传")
    @Operation(summary = "附件上传")
    @ApiResponse(description = "附件上传")
    @PostMapping(API_V1__ATTACHMENT__UPLOAD)
    public R<?> uploadAttachment(AttachmentParameter parameter, MultipartHttpServletRequest request) throws Exception {
        // 获取待上传文件集合
        List<MultipartFile> files = Lists.newArrayList();
        Iterator<String> it = request.getFileNames();
        while (it.hasNext()) {
            files.add(request.getFile(it.next()));
        }

        // 验证所有文件的文件名
        for (MultipartFile file : Lists.newArrayList(files)) {
            if (StringUtils.isEmpty(file.getOriginalFilename()) || !FilenameUtils.isExtension(file.getOriginalFilename().toLowerCase(), DEFAULT_EDITOR_EXT)) {
                throw new ServiceException(ResponseCodeEnum.ATTACHMENT_LIMIT_ERROR);
            }
        }

        List<AttachmentFile> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(files)) {
            for (MultipartFile file : files) {
                result.add(this.attachmentApi.uploadAttachmentFile(parameter, file));
            }
        }
        return R.success(result);
    }

    @Authenticated
    @OperationLog("编辑器附件上传")
    @Operation(summary = "编辑器附件上传")
    @ApiResponse(description = "编辑器附件上传")
    @PostMapping(API_V1__ATTACHMENT__EDITOR_UPLOAD)
    public R<?> uploadEditorAttachment(@RequestParam("file") MultipartFile file) throws Exception {
        if (StringUtils.isNotEmpty(file.getOriginalFilename()) && FilenameUtils.isExtension(file.getOriginalFilename().toLowerCase(), DEFAULT_EDITOR_EXT)) {
            FileObject<?> object = storage.getStorageService().uploadFile(file);
            AttachmentVo vo = AttachmentVo.builder().url(object.getUrl()).build();
            return R.success(vo);
        }
        throw new ServiceException(ResponseCodeEnum.ATTACHMENT_LIMIT_ERROR);
    }

}
