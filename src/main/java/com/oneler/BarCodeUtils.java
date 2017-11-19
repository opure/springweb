package com.oneler;

import com.google.zxing.BarcodeFormat;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.krysalis.barcode4j.BarcodeConstants;
import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.svg.SVGCanvasProvider;
import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class BarCodeUtils {

    public static void main(String[] args) throws SAXException, IOException, ConfigurationException, BarcodeException, TransformerException {
        DefaultConfigurationBuilder builder = new DefaultConfigurationBuilder();
        Configuration cfg = builder.buildFromFile(new File(BarCodeUtils.class.getClass().getResource("/").getPath()+"config.xml"));
        BarcodeUtil barcodeUtil = BarcodeUtil.getInstance();
        BarcodeGenerator barcodeGenerator = barcodeUtil.createBarcodeGenerator(cfg);
        String msg = "skuj_012345679";
        SVGCanvasProvider provider = new SVGCanvasProvider(false, 0);
        barcodeGenerator.generateBarcode(provider, msg);

        org.w3c.dom.DocumentFragment frag = provider.getDOMFragment();
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer trans = factory.newTransformer();
        Source src = new DOMSource(frag);
        Result res = new StreamResult(new File("svg_barcode.svg"));
        trans.transform(src, res);
    }
}
