package cc.elvea.platform.commons.logging.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlStatLogDto implements Serializable {
    /**
     * 路径
     */
    private String path;
    /**
     * 平均执行时长
     */
    private Double avgTime;
    /**
     * 最大执行时长
     */
    private Long maxTime;
    /**
     * 最小执行时长
     */
    private Long minTime;
    /**
     * 总执行时长
     */
    private Long totalTime;
    /**
     * 总执行次数
     */
    private Long totalNum;
    /**
     * 总执行成功次数
     */
    private Long totalSuccessNum;
    /**
     * 总执行失败次数
     */
    private Long totalErrorNum;

    public UrlStatLogDto(String path, Double avgTime, Long maxTime, Long minTime, Long totalTime, Long totalNum) {
        this.path = path;
        this.avgTime = avgTime;
        this.maxTime = maxTime;
        this.minTime = minTime;
        this.totalTime = totalTime;
        this.totalNum = totalNum;
    }

}
