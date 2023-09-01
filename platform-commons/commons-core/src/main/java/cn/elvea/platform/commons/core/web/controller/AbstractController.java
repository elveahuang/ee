package cn.elvea.platform.commons.core.web.controller;

/**
 * 顶层抽象控制器
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class AbstractController {

    public String redirect(String url) {
        return String.format("redirect:%s", url);
    }

    public String forward(String url) {
        return String.format("forward:%s", url);
    }

}
