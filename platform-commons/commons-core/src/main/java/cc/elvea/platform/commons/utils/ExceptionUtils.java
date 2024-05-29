package cc.elvea.platform.commons.utils;


import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author elvea
 * @since 24.1.0
 */
public abstract class ExceptionUtils {

    public static RuntimeException unchecked(Throwable ex) {
        if (ex instanceof RuntimeException) {
            return (RuntimeException) ex;
        } else {
            return new RuntimeException(ex);
        }
    }

    public static String getStackTraceAsString(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

}
