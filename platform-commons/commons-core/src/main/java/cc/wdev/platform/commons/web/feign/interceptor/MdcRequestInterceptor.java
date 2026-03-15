package cc.wdev.platform.commons.web.feign.interceptor;

import cc.wdev.platform.commons.utils.StringUtils;
import cc.wdev.platform.commons.utils.mdc.MdcContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import static cc.wdev.platform.commons.constants.GlobalConstants.REQUEST_ID_KEY;

/**
 * @author elvea
 */
@Slf4j
public class MdcRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String requestId = MdcContext.getRequestId();
        if (StringUtils.isNotEmpty(requestId)) {
            template.header(REQUEST_ID_KEY, requestId);
        }
    }

}
