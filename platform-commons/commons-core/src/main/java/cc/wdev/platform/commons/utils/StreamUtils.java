package cc.wdev.platform.commons.utils;

import cc.wdev.platform.commons.ai.domain.AiContentVo;
import cc.wdev.platform.commons.ai.enums.AiContentType;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class StreamUtils {

    private static final String START_TAG = "```json-render";
    private static final String END_TAG = "```";

    public static Flux<AiContentVo> processStream(Flux<@NotNull String> rawStream) {
//        JsonMapper objectMapper = JacksonUtils.getObjectMapper();
        String startTag = START_TAG;
        String endTag = END_TAG;

        // 每一个订阅者(每个请求)都拥有独立的上下文状态
        return Flux.defer(() -> {
            AtomicBoolean isDataMode = new AtomicBoolean(false);
            StringBuilder buffer = new StringBuilder();

            return rawStream.handle((String data, SynchronousSink<AiContentVo> sink) -> {
                buffer.append(data);
                String current = buffer.toString();

                if (!isDataMode.get()) {
                    // 检测起始标记
                    if (current.contains(startTag)) {
                        int startIndex = current.indexOf(startTag);
                        // 发送标记前的内容
                        String textBefore = current.substring(0, startIndex);
                        if (!textBefore.isEmpty()) {
                            sink.next(AiContentVo.builder().type(AiContentType.TEXT.getValue()).content(textBefore).build());
                        }
                        // 切换模式，清理 buffer，保留标记后的内容
                        isDataMode.set(true);
                        buffer.delete(0, startIndex + startTag.length());
                    } else {
                        // 如果还没匹配到标记，且 buffer 足够长（防止截断标记），就先发出去
                        if (current.length() > startTag.length()) {
                            int safeLimit = current.length() - startTag.length();
                            String safeText = current.substring(0, safeLimit);
                            sink.next(AiContentVo.builder().type(AiContentType.TEXT.getValue()).content(safeText).build());
                            buffer.delete(0, safeLimit);
                        }
                    }
                } else {
                    // 结束标记
                    if (current.contains(endTag)) {
                        int endIndex = current.indexOf(endTag);
                        String jsonData = current.substring(0, endIndex);
                        try {
                            // 解析 JSON
                            sink.next(AiContentVo.builder().type(AiContentType.INTERACTION.getValue()).content(jsonData).build());
                        } catch (Exception e) {
                            sink.error(new RuntimeException("JSON 解析失败: " + jsonData, e));
                            return;
                        }
                        // 切换回文本模式，清理已消耗的 buffer
                        isDataMode.set(false);
                        buffer.delete(0, endIndex + endTag.length());
                    }
                }
            });
        });
    }

}
