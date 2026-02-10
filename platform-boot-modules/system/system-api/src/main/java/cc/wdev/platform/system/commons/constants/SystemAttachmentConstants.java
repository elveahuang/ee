package cc.wdev.platform.system.commons.constants;

import cc.wdev.platform.system.commons.biz.AttachmentTypeConfig;
import com.google.common.collect.Lists;

import static java.util.Collections.emptyList;

/**
 * @author elvea
 */
public interface SystemAttachmentConstants {

    AttachmentTypeConfig DEFAULT_CONFIG = AttachmentTypeConfig.builder()
        .extensions(emptyList())
        .fileTypes(emptyList())
        .multiple(false)
        .build();

    AttachmentTypeConfig DEFAULT_IMAGE_CONFIG = AttachmentTypeConfig.builder()
        .extensions(Lists.newArrayList("jpg", "jpeg", "png", "gif"))
        .fileTypes(Lists.newArrayList("image/jpeg", "image/png", "image/gif"))
        .multiple(false)
        .build();

    AttachmentTypeConfig DEFAULT_VIDEO_CONFIG = AttachmentTypeConfig.builder()
        .extensions(Lists.newArrayList("mp4"))
        .fileTypes(Lists.newArrayList("video/mp4"))
        .multiple(false)
        .build();

    AttachmentTypeConfig DEFAULT_AUDIO_CONFIG = AttachmentTypeConfig.builder()
        .extensions(Lists.newArrayList("mp3"))
        .fileTypes(Lists.newArrayList("audio/mpeg"))
        .multiple(false)
        .build();

    AttachmentTypeConfig DEFAULT_OFFICE_CONFIG = AttachmentTypeConfig.builder()
        .extensions(Lists.newArrayList("docx", "xlsx", "pptx"))
        .fileTypes(Lists.newArrayList())
        .multiple(false)
        .build();

    AttachmentTypeConfig DEFAULT_PDF_CONFIG = AttachmentTypeConfig.builder()
        .extensions(Lists.newArrayList("pdf"))
        .fileTypes(Lists.newArrayList("application/pdf"))
        .multiple(false)
        .build();

    AttachmentTypeConfig DEFAULT_DOC_CONFIG = AttachmentTypeConfig.builder()
        .extensions(Lists.newArrayList("pdf"))
        .fileTypes(Lists.newArrayList("application/pdf"))
        .multiple(false)
        .build();

}
