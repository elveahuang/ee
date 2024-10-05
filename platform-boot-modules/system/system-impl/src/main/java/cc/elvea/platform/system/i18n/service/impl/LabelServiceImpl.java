package cc.elvea.platform.system.i18n.service.impl;

import cc.elvea.platform.commons.constants.GlobalConstants;
import cc.elvea.platform.commons.core.sequence.Sequence;
import cc.elvea.platform.commons.core.storage.Storage;
import cc.elvea.platform.commons.core.storage.StorageService;
import cc.elvea.platform.commons.core.storage.StorageUtils;
import cc.elvea.platform.commons.data.jpa.service.BaseCachingEntityService;
import cc.elvea.platform.commons.enums.LangTypeEnum;
import cc.elvea.platform.commons.oapis.translator.Translator;
import cc.elvea.platform.commons.oapis.translator.TranslatorManager;
import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.commons.utils.StringUtils;
import cc.elvea.platform.system.i18n.enums.LabelTypeEnum;
import cc.elvea.platform.system.i18n.model.entity.LabelEntity;
import cc.elvea.platform.system.i18n.repository.LabelRepository;
import cc.elvea.platform.system.i18n.service.LabelService;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author elvea
 * @see LabelService
 * @see BaseCachingEntityService
 * @since 24.1.0
 */
@Slf4j
@AllArgsConstructor
@Service
public class LabelServiceImpl extends BaseCachingEntityService<LabelEntity, Long, LabelRepository> implements LabelService {

    private final Sequence sequence;

    private final TranslatorManager translatorFactory;

    private final Storage storage;

    /**
     * @see LabelService#translate()
     */
    @Override
    public void translate() {
        Translator translator = this.translatorFactory.getTranslator();
        List<LabelEntity> labelList = this.findAll();
        if (CollectionUtils.isNotEmpty(labelList)) {
            labelList.forEach((label) -> {
                if (label.getZhTwLabelStaticInd() != null && !label.getZhTwLabelStaticInd()) {
                    label.setZhTwLabel(translator.translate(LangTypeEnum.ZH_CN, LangTypeEnum.ZH_TW, label.getZhLabel()));
                }
                if (StringUtils.isEmpty(label.getEnLabel())) {
                    label.setEnLabel(translator.translate(LangTypeEnum.ZH_CN, LangTypeEnum.EN, label.getZhLabel()));
                }
                if (label.getFrLabelStaticInd() != null && !label.getFrLabelStaticInd()) {
                    label.setFrLabel(translator.translate(LangTypeEnum.EN, LangTypeEnum.FR, label.getEnLabel()));
                }
                if (label.getJaLabelStaticInd() != null && !label.getJaLabelStaticInd()) {
                    label.setJaLabel(translator.translate(LangTypeEnum.EN, LangTypeEnum.JA, label.getEnLabel()));
                }
            });
            this.saveBatch(labelList);
        }

    }

    /**
     * @see LabelService#generate(LabelTypeEnum)
     */
    @Override
    public void generate(LabelTypeEnum labelType) throws Exception {
        List<LabelEntity> labelEntityList = this.findAll();
        Map<LangTypeEnum, Map<String, String>> labelMap = Maps.newHashMap();

        Arrays.stream(LangTypeEnum.values()).toList().forEach(langTypeEnum -> labelEntityList.forEach(labelEntity -> {
            labelMap.put(langTypeEnum, Maps.newHashMap());
            switch (langTypeEnum) {
                case ZH_CN -> labelMap.get(langTypeEnum).put(labelEntity.getCode(), labelEntity.getZhLabel());
                case ZH_TW -> labelMap.get(langTypeEnum).put(labelEntity.getCode(), labelEntity.getZhTwLabel());
                case EN -> labelMap.get(langTypeEnum).put(labelEntity.getCode(), labelEntity.getEnLabel());
                case FR -> labelMap.get(langTypeEnum).put(labelEntity.getCode(), labelEntity.getFrLabel());
                case JA -> labelMap.get(langTypeEnum).put(labelEntity.getCode(), labelEntity.getJaLabel());
            }
        }));

        if (LabelTypeEnum.PROPERTIES.equals(labelType)) {
            for (LangTypeEnum langTypeEnum : LangTypeEnum.values()) {

                Properties properties = new Properties();
                properties.putAll(labelMap.get(langTypeEnum));

                StorageService storageService = this.storage.getStorageService();

                String tempFileName = StorageUtils.generateExtFilename("properties");
                File tempFile = StorageUtils.newTempFile(tempFileName);
                try (OutputStream output = new FileOutputStream(tempFile)) {
                    properties.store(output, langTypeEnum.getCode());
                } catch (Exception e) {
                    log.error("Fail to generate properties file.", e);
                }
                storageService.uploadFile(tempFile);
            }
        } else if (LabelTypeEnum.JSON.equals(labelType)) {
            for (LangTypeEnum langTypeEnum : LangTypeEnum.values()) {

                JsonObject json = new JsonObject();
                labelMap.get(langTypeEnum).forEach(json::addProperty);

                StorageService storageService = this.storage.getMinStorageService();

                String tempFileName = StorageUtils.generateExtFilename("properties");
                File tempFile = StorageUtils.newTempFile(tempFileName);
                FileUtils.writeStringToFile(tempFile, json.toString(), GlobalConstants.UTF8);
                storageService.uploadFile(tempFile);
            }
        }

    }

}
