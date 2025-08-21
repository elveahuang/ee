package cc.wdev.platform.sample.ai;

import cc.wdev.platform.sample.BaseTests;
import com.alibaba.cloud.ai.dashscope.api.DashScopeAudioTranscriptionApi;
import com.alibaba.cloud.ai.dashscope.audio.DashScopeAudioTranscriptionOptions;
import com.alibaba.cloud.ai.dashscope.audio.transcription.AudioTranscriptionModel;
import com.alibaba.cloud.ai.dashscope.common.DashScopeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author elvea
 */
@Slf4j
public class AiAudioTests extends BaseTests {

    private final String DEFAULT_MODEL = DashScopeAudioTranscriptionApi.AudioTranscriptionModel.PARAFORMER_REALTIME_V2.getValue();

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Autowired
    private AudioTranscriptionModel transcriptionModel;

    @Test
    public void baseTest() {
        Assertions.assertNotNull(transcriptionModel);
    }

    @Test
    public void sttTest() throws Exception {
        File root = new File("temp" + File.separator);
        File audio = new File(root.getAbsolutePath(), "audio.mp3");
        if (!audio.exists()) {
            log.info("Audio not exists");
        }

        AudioTranscriptionResponse response = transcriptionModel.call(
            new AudioTranscriptionPrompt(
//                new FileSystemResource(audio),
                new UrlResource("https://lf3-static.bytednsdoc.com/obj/eden-cn/nupenuvpxnuvo/xgplayer_doc/xgplayer-demo-720p.mp4"),
                DashScopeAudioTranscriptionOptions.builder()
                    .withModel(DEFAULT_MODEL)
                    .withFormat(DashScopeAudioTranscriptionOptions.AudioFormat.MP3)
                    .build()
            )
        );
        String text = response.getResult().getOutput();
        Assertions.assertNotNull(text);
    }

    @Test
    public void transcriptionTest() throws MalformedURLException {
        File root = new File("temp" + File.separator);
        File audio = new File(root.getAbsolutePath(), "audio.mp3");
        if (!audio.exists()) {
            log.info("Audio not exists");
        }

        StringBuilder sb = new StringBuilder();
        CountDownLatch latch = new CountDownLatch(1);
        try {
            AudioTranscriptionResponse submitResponse = transcriptionModel.asyncCall(
                new AudioTranscriptionPrompt(
                    new UrlResource("https://lf3-static.bytednsdoc.com/obj/eden-cn/nupenuvpxnuvo/xgplayer_doc/xgplayer-demo-720p.mp4"),
                    DashScopeAudioTranscriptionOptions.builder().withModel(DEFAULT_MODEL).build()
                )
            );

            DashScopeAudioTranscriptionApi.Response.Output submitOutput = Objects.requireNonNull(submitResponse.getMetadata().get("output"));
            String taskId = submitOutput.taskId();
            scheduler.scheduleAtFixedRate(() -> checkTaskStatus(taskId, sb, latch), 0, 1, TimeUnit.SECONDS);
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DashScopeException("Thread was interrupted: " + e.getMessage());
        } finally {
            scheduler.shutdown();
        }

        String text = sb.toString();
        Assertions.assertNotNull(text);
    }

    private void checkTaskStatus(String taskId, StringBuilder stringBuilder, CountDownLatch latch) {
        try {
            AudioTranscriptionResponse fetchResponse = transcriptionModel.fetch(taskId);
            DashScopeAudioTranscriptionApi.Response.Output fetchOutput =
                Objects.requireNonNull(fetchResponse.getMetadata().get("output"));
            DashScopeAudioTranscriptionApi.TaskStatus taskStatus = fetchOutput.taskStatus();
            if (taskStatus.equals(DashScopeAudioTranscriptionApi.TaskStatus.SUCCEEDED)) {
                stringBuilder.append(fetchResponse.getResult().getOutput());
                latch.countDown();
            } else if (taskStatus.equals(DashScopeAudioTranscriptionApi.TaskStatus.FAILED)) {
                log.warn("Transcription failed.");
                latch.countDown();
            }
        } catch (Exception e) {
            latch.countDown();
            throw new RuntimeException("Error occurred while checking task status: " + e.getMessage());
        }
    }

}
