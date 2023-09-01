package cn.elvea.platform.commons.core.utils;

import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author elvea
 * @since 0.0.1
 */
public class WebUtilsTests {

    @Test
    public void test() {
        String url = "http://192.168.0.5:4200/activity-learn?activityId=963759829585891330%26activityType=CLASSROOM";
        System.out.println(URLEncodedUtils.parse(url, StandardCharsets.UTF_8));
        System.out.println(UriUtils.decode(url, StandardCharsets.UTF_8));
    }

}
