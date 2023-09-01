package cn.elvea.platform.commons.core.extensions.sms;

import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
public class SmsBody implements Serializable {
    private String mobileCountryCode;
    private String mobileNumber;
    @Builder.Default
    private Map<String, Object> params = Maps.newHashMap();
}
