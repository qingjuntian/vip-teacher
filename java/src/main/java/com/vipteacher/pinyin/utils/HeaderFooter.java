package com.vipteacher.pinyin.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.util.Random;

/**
 * Created by qingjun on 3/16/18.
 */
public class HeaderFooter extends PdfPageEventHelper {

    Font font;
    Random random = new Random();

    String[] footStringList = {"今天作业不太多哦",
            "不积跬步，无以至千里",
            "加油，很快就写完啦",
            "今天作业有点多",
            "千里之行，始于足下",
            "当你看到这里的时候，你的作业应该已经写完了",
            "知识就是力量——Francis Bacon",
            "仰望星空，脚踏大地",
            ""};

    public HeaderFooter(Font font) {
        this.font = font;
    }

    public void onEndPage(PdfWriter writer, Document document) {

        Rectangle rect = writer.getBoxSize("art");
//        switch (writer.getPageNumber() % 2) {
//            case 0:
//                ColumnText.showTextAligned(writer.getDirectContent(),
//                        Element.ALIGN_RIGHT, new Phrase("even header"),
//                        rect.getRight(), rect.getTop(), 0);
//                break;
//            case 1:
//                ColumnText.showTextAligned(writer.getDirectContent(),
//                        Element.ALIGN_LEFT, new Phrase("odd header"),
//                        rect.getLeft(), rect.getTop(), 0);
//                break;
//        }

//        ColumnText.showTextAligned(writer.getDirectContent(),
//                Element.ALIGN_CENTER, new Phrase(String.format("page %d", writer.getPageNumber())),
//                (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);


        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_CENTER, new Phrase(footStringList[random.nextInt(footStringList.length)], font),
                (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
    }
}