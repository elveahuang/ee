package cc.elvea.platform.commons.core.http;

import lombok.Getter;

/**
 * @author elvea
 * @since 24.1.0
 */
public class HttpManger {

    @Getter
    private static volatile Http http = Http.builder().type(HttpType.OKHTTP).build();

    public static void setHttp(Http http) {
        HttpManger.http = http;
    }

}
