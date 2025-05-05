package cc.elvea.platform.system.catalog.mapper;

import cc.elvea.platform.commons.data.mybatis.mapper.BaseEntityMapper;
import cc.elvea.platform.system.catalog.model.entity.CatalogRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author elvea
 */
@Mapper
@Repository
public interface CatalogRelationMapper extends BaseEntityMapper<CatalogRelationEntity, Long> {

    /**
     * @param relationType 关联类型
     * @param entityId     实体ID
     * @return List
     */
//    @Query("select * from sys_catalog_relation r where r.relation_type = :relationType and r.entity_id = :entityId")
    List<CatalogRelationEntity> getParents(String relationType, long entityId);

    /**
     * @param relationType 关联类型
     * @param entityId     实体ID
     * @return List
     */
//    @Query("select * from sys_catalog_relation r where r.relation_type = :relationType and r.entity_id = :entityId and r.parent_ind = 1")
    List<CatalogRelationEntity> getDirectParents(String relationType, long entityId);

    /**
     * @param relationType 关联类型
     * @param ancestorId   实体ID
     * @return List
     */
//    @Query("select * from sys_catalog_relation r where r.relation_type = :relationType and r.entity_id = :entityId")
    List<CatalogRelationEntity> getChildren(String relationType, long ancestorId);

    /**
     * @param relationType 关联类型
     * @param ancestorId   实体ID
     * @return List
     */
//    @Query("select * from sys_catalog_relation r where r.relation_type = :relationType and r.entity_id = :entityId and r.parent_ind = 1")
    List<CatalogRelationEntity> getDirectChildren(String relationType, long ancestorId);

    /**
     * @param relationType 关联类型
     * @param ancestorId   实体ID
     */
//    @Modifying
//    @Query("delete from sys_catalog_relation r where r.relation_type = :relationType and r.ancestor_id = :ancestorId")
    void deleteAsAncestor(String relationType, long ancestorId);

    /**
     * @param relationType 关联类型
     * @param entityId     实体ID
     */
//    @Modifying
//    @Query("delete from sys_catalog_relation r where r.relation_type = :relationType and r.entity_id = :entityId")
    void deleteAsChild(String relationType, long entityId);

}
