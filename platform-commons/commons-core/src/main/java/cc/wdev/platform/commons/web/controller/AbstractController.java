package cc.wdev.platform.commons.web.controller;

import cc.wdev.platform.commons.core.Context;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 顶层抽象控制器
 *
 * @author elvea
 */
public abstract class AbstractController {

    protected Context context;

    protected String redirect(String url) {
        return String.format("redirect:%s", url);
    }

    protected String forward(String url) {
        return String.format("forward:%s", url);
    }

    @Autowired
    public void setContext(Context context) {
        this.context = context;
    }

}
