package cc.elvea.platform.system.announcement.service;

import cc.elvea.platform.commons.core.sequence.Sequence;
import cc.elvea.platform.commons.utils.StopWatchUtils;
import cc.elvea.platform.system.BaseTests;
import cc.elvea.platform.system.announcement.domain.entity.AnnouncementEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author elvea
 */
@Slf4j
public class AnnouncementServiceTests extends BaseTests {

    @Autowired
    AnnouncementService announcementService;

    @Autowired
    Sequence sequence;

    @Test
    public void base() {
        Assertions.assertNotNull(announcementService);

        List<AnnouncementEntity> entities = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            AnnouncementEntity entity = new AnnouncementEntity();
            entity.setTitle("Title - " + this.sequence.nextIdAsString());
            entity.setContent("Content - " + this.sequence.nextIdAsString());
            entities.add(entity);
        }

        StopWatch watch = StopWatchUtils.createStopWatch("批量插入公告测试数据");
        watch.start("插入数据");
        this.announcementService.saveBatch(entities, 1000);
        watch.stop();

        log.info(watch.prettyPrint());
    }

}
