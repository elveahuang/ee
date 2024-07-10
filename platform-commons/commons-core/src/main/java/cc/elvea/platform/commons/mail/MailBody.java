package cc.elvea.platform.commons.mail;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
public class MailBody implements Serializable {
    private String from;
    private String to;
    private String subject;
    private String content;
}
