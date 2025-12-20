package cc.wdev.dev.console.shells;

import org.bytedeco.javacpp.Loader;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Properties;

@ShellComponent
public class JavaCvShell {

    @ShellMethod(key = "javavc")
    public void check() {
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
