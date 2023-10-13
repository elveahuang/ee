package cn.elvea.platform.commons.core.scheduling.quartz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuartzJobInfo implements Serializable {
    private String key;
    private String className;
    private QuartzJobScheduleType scheduleType;
    private TimeUnit unit;
    private int period;
    private int hour;
    private int minute;
    private String cron;
}
