package cc.elvea.platform.commons.core.mail.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
public class MailBody implements Serializable {
    private String from;
    private String to;
    private String subject;
    private String content;
}
