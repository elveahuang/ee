package cc.wdev.platform.commons.core.http;

import cc.wdev.platform.commons.core.http.enums.HttpClientTypeEnum;
import lombok.Getter;

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
