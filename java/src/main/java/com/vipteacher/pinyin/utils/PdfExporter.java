package com.vipteacher.pinyin.utils;



import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by qingjun on 3/15/18.
 */
public class PdfExporter {


    public static void exportToPdf(String pdfFile, List<String> content) throws DocumentException, IOException {
        Document doc = new Document();
        //BaseFont baseFont = BaseFont.createFont("/Users/qingjun/Downloads/youyuan/chinese.simyou.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        BaseFont baseFont = BaseFont.createFont("/Users/qingjun/Downloads/SIMYOU.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);

        Font font = new Font(baseFont, 18, Font.BOLD);
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream (pdfFile));

        Rectangle rect = new Rectangle(36, 54, 559, 788);
        rect.setBorderColor(BaseColor.BLACK);

        writer.setBoxSize("art", rect);
        HeaderFooter header=new HeaderFooter(new Font(baseFont, 10));
        writer.setPageEvent(header);

        doc.open();
        Paragraph paragraph = new Paragraph("小学五年级第二学期第一单元看拼音写汉字", font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        doc.add(paragraph);
        doc.add(createFirstTable(content, new Font(baseFont, 12)));
        doc.close();

    }

    public static PdfPTable createFirstTable(List<String> content, Font font) throws IOException, DocumentException {
        // a table with 4 columns

        int rowPinyinCount = 3;

        PdfPTable table = new PdfPTable(rowPinyinCount * 2);
        table.setSpacingBefore(30);
        table.setSpacingAfter(30);


        int count = 0;
        for(String pinyin : content) {
            if(pinyin != null && pinyin.trim().length() >= 2) {
                count++;
                Phrase phrase = new Phrase(pinyin, font);
                table.addCell(phrase);
                table.addCell(" ");
            }
        }
        int needAppend = (rowPinyinCount - count % rowPinyinCount) % rowPinyinCount;
        for( int i = 0; i < needAppend; i++) {
            table.addCell(" ");table.addCell(" ");
        }

        return table;
    }
}
