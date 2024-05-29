package cc.elvea.platform.commons.oapis.sms;

import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
public class SmsBody implements Serializable {
    private String mobileCountryCode;
    private String mobileNumber;
    private String template;
    @Builder.Default
    private Map<String, Object> params = Maps.newHashMap();
}
