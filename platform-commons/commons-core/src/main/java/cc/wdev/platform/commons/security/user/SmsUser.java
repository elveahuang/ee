package cc.wdev.platform.commons.security.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsUser implements Serializable {
    /**
     * smsType
     */
    private String smsType;

    /**
     * sms
     */
    private String sms;

    /**
     * extra
     */
    private String extra;
}
