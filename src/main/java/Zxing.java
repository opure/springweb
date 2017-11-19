import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Hashtable;

public class Zxing {
    public static void main(String[] args) throws WriterException, IOException {

        int width = 100;
        int height = 100;
        String format = "png";
        Hashtable hints= new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        String text = "sku_12313";
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, width, height, hints);
        File outputFile = new File("zxin1g.png");

        MatrixToImageWriter.writeToPath(bitMatrix, format,outputFile.toPath());
    }
}