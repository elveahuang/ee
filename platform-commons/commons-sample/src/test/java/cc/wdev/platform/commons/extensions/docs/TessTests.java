package cc.wdev.platform.commons.extensions.docs;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @author elvea
 */
@Slf4j
public class TessTests {

    @Test
    public void baseTest() {
        File root = new File("." + File.separator + "temp" + File.separator);
        File file = new File(root.getAbsolutePath(), "image.png");
        ITesseract instance = new Tesseract();
        instance.setDatapath("tessdata");
        try {
            String result = instance.doOCR(file);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }

}
