package cc.wdev.platform.commons.web.feign.interceptor;

import cc.wdev.platform.commons.core.tenant.TenantContext;
import cc.wdev.platform.commons.utils.NumberUtils;
import cc.wdev.platform.commons.utils.StringUtils;
import cc.wdev.platform.commons.utils.mdc.MdcContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

import static cc.wdev.platform.commons.constants.GlobalConstants.*;

/**
 * @author elvea
 */
@Slf4j
public class MdcRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String tenantId = TenantContext.getTenantIdAsString();
        String tenantRootInd = NumberUtils.toString(TenantContext.getTenantRootInd());
        String requestId = MdcContext.getRequestId();
        if (StringUtils.isNotEmpty(requestId)) {
            template.header(REQUEST_ID_KEY, requestId);
        }
        if (StringUtils.isNotEmpty(tenantId) || StringUtils.isNotEmpty(tenantRootInd)) {
            template.header(TENANT_ID_KEY, tenantId);
            template.header(TENANT_ROOT_IND_KEY, tenantRootInd);
        }
    }

}
