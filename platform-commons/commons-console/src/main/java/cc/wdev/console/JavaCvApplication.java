package cc.wdev.console;

import org.bytedeco.javacpp.Loader;

import java.util.Properties;

public class JavaCvApplication {

    public static void main(String[] args) {
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
