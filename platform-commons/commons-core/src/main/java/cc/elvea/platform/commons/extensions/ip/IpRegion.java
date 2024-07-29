package cc.elvea.platform.commons.extensions.ip;

import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
public class IpRegion {

    private Searcher searcher = null;

    public void init(Resource resource) {
        log.info("IpRegion [{}] init start.", resource.getFilename());
        try {
            searcher = Searcher.newWithBuffer(resource.getContentAsByteArray());
            log.info("IpRegion [{}] init done .", resource.getFilename());
        } catch (IOException e) {
            log.info("IpRegion [{}] init failed .", resource.getFilename(), e);
            throw new RuntimeException(e);
        }
    }

    public String search(String ip) throws Exception {
        if (searcher == null) {
            return "";
        }
        return this.searcher.search(ip);
    }

}
