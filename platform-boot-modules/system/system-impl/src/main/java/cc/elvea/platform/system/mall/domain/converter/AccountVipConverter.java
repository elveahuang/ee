package cc.elvea.platform.system.mall.domain.converter;

import cc.elvea.platform.system.mall.domain.entity.AccountVipEntity;
import cc.elvea.platform.system.mall.domain.vo.AccountVipVo;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author elvea
 */
@Mapper(componentModel = SPRING)
public interface AccountVipConverter {

    AccountVipVo entity2Vo(AccountVipEntity entity);

    List<AccountVipVo> entityList2VoList(List<AccountVipEntity> entityList);

}
