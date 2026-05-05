-- =====================================================================================================================
-- 常用数据库脚本整理
-- =====================================================================================================================

/**
* 清理所有定时任务表
*/
SET foreign_key_checks = 0;
TRUNCATE TABLE qrtz_blob_triggers;
TRUNCATE TABLE qrtz_calendars;
TRUNCATE TABLE qrtz_cron_triggers;
TRUNCATE TABLE qrtz_fired_triggers;
TRUNCATE TABLE qrtz_job_details;
TRUNCATE TABLE qrtz_locks;
TRUNCATE TABLE qrtz_paused_trigger_grps;
TRUNCATE TABLE qrtz_scheduler_state;
TRUNCATE TABLE qrtz_simple_triggers;
TRUNCATE TABLE qrtz_simprop_triggers;
TRUNCATE TABLE qrtz_triggers;
SET foreign_key_checks = 1;

/**
* 删除所有定时任务表
*/
SET foreign_key_checks = 0;
DROP TABLE qrtz_blob_triggers cascade;
DROP TABLE qrtz_calendars cascade;
DROP TABLE qrtz_cron_triggers cascade;
DROP TABLE qrtz_fired_triggers cascade;
DROP TABLE qrtz_job_details cascade;
DROP TABLE qrtz_locks cascade;
DROP TABLE qrtz_paused_trigger_grps cascade;
DROP TABLE qrtz_scheduler_state cascade;
DROP TABLE qrtz_simple_triggers cascade;
DROP TABLE qrtz_simprop_triggers cascade;
DROP TABLE qrtz_triggers cascade;
SET foreign_key_checks = 1;

--
-- 清除消息所有记录
--

truncate sys_message_type cascade;
truncate sys_message_channel cascade;
truncate sys_message cascade;
truncate sys_message_template cascade;
truncate sys_message_content cascade;
truncate sys_message_user cascade;
truncate sys_message_history cascade;
truncate sys_notice cascade;
