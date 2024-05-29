package cc.elvea.platform.system.mall.model.converter;

import cc.elvea.platform.system.mall.model.entity.AccountVipEntity;
import cc.elvea.platform.system.mall.model.vo.AccountVipVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
@Mapper
public interface AccountVipConverter {

    AccountVipConverter INSTANCE = Mappers.getMapper(AccountVipConverter.class);

    AccountVipVo entity2Vo(AccountVipEntity entity);

    List<AccountVipVo> entityList2VoList(List<AccountVipEntity> entityList);

}
