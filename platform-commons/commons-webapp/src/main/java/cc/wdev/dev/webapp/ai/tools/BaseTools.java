package cc.wdev.dev.webapp.ai.tools;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.annotation.RegisterReflection;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author elvea
 */
@Component("baseTools")
@RequiredArgsConstructor
@RegisterReflection(memberCategories = MemberCategory.INVOKE_DECLARED_METHODS)
public class BaseTools {

    @Tool(name = "getCurrentDateTime", description = "获取当前时间")
    String getCurrentDateTime() {
        return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }

    @Tool(name = "getBooks", description = "获取推荐书籍")
    List<String> getBooks() {
        List<String> books = new ArrayList<>();
        books.add("三国演义");
        books.add("高等数学");
        books.add("语言榆树");
        return books;
    }

}
