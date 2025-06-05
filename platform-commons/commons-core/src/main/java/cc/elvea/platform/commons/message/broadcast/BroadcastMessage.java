package cc.elvea.platform.commons.message.broadcast;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author elvea
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BroadcastMessage<T, E extends BroadcastEvent<T>> implements Serializable {

    private T data;

    public abstract E toEvent();

}
