package cn.elvea.platform.commons.core.constants;

/**
 * @author elvea
 * @since 0.0.1
 */
public interface DataSourceConstants {

    /**
     * 主数据源
     */
    String DS_MASTER = "masterDataSource";

    String DS_CONFIG_MASTER = "masterDataSourceConfig";

    /**
     * 从数据源
     */
    String DS_SLAVE = "slaveDataSource";

    String DS_CONFIG_SLAVE = "slaveDataSourceConfig";

    /**
     * 定时任务数据源
     */
    String DS_JOB = "jobDataSource";

    String DS_CONFIG_JOB = "jobDataSourceConfig";

}
