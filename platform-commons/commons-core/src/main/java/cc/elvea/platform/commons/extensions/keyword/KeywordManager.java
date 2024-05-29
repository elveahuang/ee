package cc.elvea.platform.commons.extensions.keyword;

import java.util.List;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface KeywordManager {

    void initialize(List<String> list);

    boolean check(String text);

}
