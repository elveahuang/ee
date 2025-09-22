package cc.wdev.platform.commons.core.ai.aliyun;

import cc.wdev.dev.webapp.BaseTests;
import com.alibaba.dashscope.audio.asr.transcription.*;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author elvea
 */
public class AiAliyunAsrTests extends BaseTests {

    @Autowired
    private AiAliyunFactory aiAliyunFactory;

    @Test
    public void baseTest() throws Exception {
        Assertions.assertNotNull(this.aiAliyunFactory);
    }

    @Test
    public void transcriptionTest() {
        TranscriptionParam param = TranscriptionParam.builder()
            .apiKey(aiAliyunFactory.getConfig().getApiKey())
            .model(aiAliyunFactory.getConfig().getTranscription().getModel())
            .parameter("language_hints", new String[]{"zh", "en"})
            .fileUrls(Arrays.asList(
                "https://dashscope.oss-cn-beijing.aliyuncs.com/samples/audio/paraformer/hello_world_female2.wav",
                "https://dashscope.oss-cn-beijing.aliyuncs.com/samples/audio/paraformer/hello_world_male2.wav"))
            .build();
        Transcription transcription = new Transcription();
        TranscriptionResult result = transcription.asyncCall(param);
        System.out.println("RequestId: " + result.getRequestId());
        result = transcription.wait(TranscriptionQueryParam.FromTranscriptionParam(param, result.getTaskId()));
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(result.getOutput()));
        List<TranscriptionTaskResult> taskResultList = result.getResults();
        Assertions.assertNotNull(taskResultList);
    }

}
