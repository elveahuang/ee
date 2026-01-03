package cc.wdev.dev.console.components;

import org.bytedeco.javacpp.Loader;
import org.springframework.shell.core.command.annotation.Command;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class JavaCvCommand {

    @Command(name = "javacv")
    public void javacv() {
        System.setProperty("org.bytedeco.javacpp.logger.debug", "true");
        Properties properties = Loader.loadProperties();
        System.out.println(properties.getProperty("platform.extension"));
        System.out.println(properties.getProperty("platform"));
        System.out.println(System.getProperty("java.library.path"));
        Loader.load(org.bytedeco.ffmpeg.global.avutil.class);
        Loader.load(org.bytedeco.tesseract.global.tesseract.class);
        Loader.load(org.bytedeco.leptonica.global.leptonica.class);
    }

}
