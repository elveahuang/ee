package cn.elvea.platform.commons.core.extensions.mail;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
public class MailBody implements Serializable {
    private String from;
    private String to;
    private String subject;
    private String content;
}
