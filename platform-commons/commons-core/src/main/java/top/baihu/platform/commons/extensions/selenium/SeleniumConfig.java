package top.baihu.platform.commons.extensions.selenium;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.baihu.platform.commons.core.http.HttpProxy;
import top.baihu.platform.commons.enums.SeleniumDriverTypeEnum;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeleniumConfig implements Serializable {

    @Builder.Default
    private SeleniumDriverTypeEnum driverType = SeleniumDriverTypeEnum.CHROME;

    private String driverVersion;

    @Builder.Default
    private HttpProxy proxy = HttpProxy.builder().build();

}
