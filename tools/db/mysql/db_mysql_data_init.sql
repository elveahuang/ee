-- =====================================================================================================================
-- 初始化基础数据
-- =====================================================================================================================

use ee_platform;

truncate sys_entity_authority;

insert into sys_entity_authority (`tenant_id`, `biz_type`, `entity_id`, `authority_id`, `created_at`)
select sr.tenant_id, 'ROLE', sr.id, sa.id, now()
from sys_role sr,
     sys_authority sa
where sr.id = 1004001;

insert into sys_entity_authority (`tenant_id`, `biz_type`, `entity_id`, `authority_id`, `created_at`)
select st.id, 'TENANT', st.id, sa.id, now()
from sys_tenant st,
     sys_authority sa
where st.id = 1000001;
