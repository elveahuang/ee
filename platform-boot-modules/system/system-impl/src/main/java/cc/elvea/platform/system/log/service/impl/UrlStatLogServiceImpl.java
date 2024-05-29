package cc.elvea.platform.system.log.service.impl;

import cc.elvea.platform.commons.cache.CacheKeyGenerator;
import cc.elvea.platform.commons.data.domain.IdEntity;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.logging.domain.UrlLogDto;
import cc.elvea.platform.commons.logging.domain.UrlStatLogDto;
import cc.elvea.platform.commons.utils.ObjectUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.log.cache.UrlStatLogCacheKeyGenerator;
import cc.elvea.platform.system.log.model.entity.UrlStatLogEntity;
import cc.elvea.platform.system.log.repository.UrlStatLogRepository;
import cc.elvea.platform.system.log.service.UrlStatLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @author elvea
 * @see UrlStatLogService
 * @see BaseCachingEntityService
 * @since 24.1.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class UrlStatLogServiceImpl
        extends BaseCachingEntityService<UrlStatLogEntity, Long, UrlStatLogRepository>
        implements UrlStatLogService {

    private final UrlStatLogCacheKeyGenerator cacheKeyGenerator = new UrlStatLogCacheKeyGenerator();

    @Override
    public CacheKeyGenerator getCacheKeyGenerator() {
        return cacheKeyGenerator;
    }

    /**
     * @see UrlStatLogService#findUrlStatLogByPath(String)
     */
    @Override
    public UrlStatLogEntity findUrlStatLogByPath(String path) {
        return this.findByCacheKey(cacheKeyGenerator.keyByCode(path), key -> {
            UrlStatLogEntity condition = UrlStatLogEntity.builder().path(path).build();
            Example<UrlStatLogEntity> example = Example.of(condition);
            UrlStatLogEntity entity = this.repository.findOne(example).orElse(null);
            this.setCache(entity);
            return entity;
        });
    }

    /**
     * @see UrlStatLogService#saveUrlStatLog(UrlLogDto)
     */
    @Override
    public void saveUrlStatLog(UrlLogDto urlLog) {
        UrlStatLogEntity entity = this.findUrlStatLogByPath(urlLog.getPath());
        if (entity == null) {
            entity = UrlStatLogEntity.builder().path(urlLog.getPath()).build();
        }
        UrlStatLogDto stat = this.repository.getStatData(urlLog.getPath());
        if (stat == null) {
            entity.setMaxTime(urlLog.getExecTime());
            entity.setMinTime(urlLog.getExecTime());
            entity.setAvgTime(urlLog.getExecTime());
            entity.setTotalTime(urlLog.getExecTime());
            entity.setTotalNum(1L);
            entity.setTotalSuccessNum(1L);
            entity.setTotalErrorNum(0L);
        } else {
            entity.setMaxTime(stat.getMaxTime());
            entity.setMinTime(stat.getMinTime());
            entity.setAvgTime(stat.getAvgTime().longValue());
            entity.setTotalTime(stat.getTotalTime());
            entity.setTotalNum((stat.getTotalNum() != null) ? stat.getTotalNum() + 1 : 1L);
            entity.setTotalSuccessNum((stat.getTotalNum() != null) ? stat.getTotalNum() + 1 : 1L);
            entity.setTotalErrorNum(0L);
        }
        this.save(entity);
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void setCache(UrlStatLogEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().set(this.cacheKeyGenerator.keyById(model.getId()), model);
            }
            if (StringUtils.isNotEmpty(model.getPath())) {
                getCacheService().set(this.cacheKeyGenerator.keyByCode(model.getPath()), model);
            }
        }
    }

    /**
     * @see BaseCachingEntityService#setCache(IdEntity)
     */
    @Override
    public void deleteCache(UrlStatLogEntity model) {
        if (!ObjectUtils.isEmpty(model)) {
            if (!ObjectUtils.isEmpty(model.getId())) {
                getCacheService().delete(this.cacheKeyGenerator.keyById(model.getId()));
            }
            if (StringUtils.isNotEmpty(model.getPath())) {
                getCacheService().delete(this.cacheKeyGenerator.keyByCode(model.getPath()));
            }
        }
    }

}
