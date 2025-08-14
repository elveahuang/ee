package top.baihu.platform.commons.core.http;

import lombok.Getter;
import top.baihu.platform.commons.core.http.enums.HttpClientTypeEnum;

/**
 * @author elvea
 */
public class HttpManger {

    @Getter
    private static volatile HttpFactory http = HttpFactory.builder().type(HttpClientTypeEnum.OKHTTP).build();

    public static void setHttp(HttpFactory http) {
        HttpManger.http = http;
    }

}
