package cc.wdev.platform.commons.message.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMessage<M extends Serializable> implements Serializable {
    /**
     * 指定会话标识
     */
    private List<String> keys;
    /**
     * 指定接收者
     */
    private List<Long> receivers;
    /**
     * 消息类型
     */
    private String type;
    /**
     * 消息内容
     */
    private M content;
}
