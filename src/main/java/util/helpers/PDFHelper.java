package util.helpers;

import lombok.extern.log4j.Log4j;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Log4j
public class PDFHelper {

    public static String getTextFromFile(String filePath, int startPage, int endPage) {

        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        String parsedText = null;

        File file = new File(filePath);
        try {
            PDFParser parser = new PDFParser(new FileInputStream(file));
            parser.parse();

            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();

            pdDoc = new PDDocument(cosDoc);

            pdfStripper.setStartPage(startPage);
            pdfStripper.setEndPage(endPage);

            parsedText = pdfStripper.getText(pdDoc);
            pdDoc.close();
            parser.clearResources();
        } catch (IOException e) {
            log.error("Unable to read text from PDF file: " + filePath, e);
        }

        return parsedText;
    }

    public static String getTextFromFile(String filePath) {

        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        String parsedText = null;

        File file = new File(filePath);
        try {
            PDFParser parser = new PDFParser(new FileInputStream(file));
            parser.parse();

            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();

            pdDoc = new PDDocument(cosDoc);

            parsedText = pdfStripper.getText(pdDoc);

            pdDoc.close();
            parser.clearResources();
        } catch (IOException e) {
            log.error("Unable to read text from PDF file: " + filePath, e);
        }

        return parsedText;
    }
}
