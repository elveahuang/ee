package cc.elvea.platform.commons.utils.jdbc.dialect;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface DateTimeClause {

    String currentDateTime();

    String currentDate();

    String currentTime();

}
