package com.vipteacher.pinyin.gui.component;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.itextpdf.text.DocumentException;
import com.vipteacher.pinyin.entity.YuwenWord;
import com.vipteacher.pinyin.utils.PdfExporter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by qingjun on 3/14/18.
 */
public class PinyinProofPanel extends JPanel {

    Vector<String> columnNames = new Vector<String>(2);

    String chinese;

    JScrollPane scrollPane;

    Vector<Vector<String>> tableValues = new Vector();

    Map<String, YuwenWord> yunwenWordMap = new HashMap<>();

    List<YuwenWord> proovedList = new LinkedList<>();


    public PinyinProofPanel() {
        columnNames.add("词语");
        columnNames.add("拼音");
        columnNames.add("词语");
        columnNames.add("拼音");
    }

    public boolean proof(String text) {
        if (text.length() > 0) {
            text = text.replace('\n', ' ');
            if (scrollPane == null || text.equals(chinese) == false) {
//                Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
//                Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
//
                List<Term> terms = NLPTokenizer.segment(text);
                if (scrollPane != null) {
                    remove(scrollPane);
                }
//                String[] words = terms.stream().map(s->s.word).filter(s->s.trim().length() > 0).collect(Collectors.toList()).toArray(new String[1]);
                String[] words = text.split(" ");
                tableValues = new Vector();
                yunwenWordMap = new HashMap<>();
                proovedList = new LinkedList<>();
                Vector<String> wordAndPinyin = new Vector<String>();

                for (int i = 0; i < words.length; i++) {
                    YuwenWord ywWord = new YuwenWord(words[i]);
                    ywWord.convert(wordAndPinyin);
                    yunwenWordMap.put(ywWord.getChineseWord(), ywWord);
                    if (i > 0 && (i & 1) == 1) {
                        tableValues.add(wordAndPinyin);
                        wordAndPinyin = new Vector<String>();
                    }
                }
                if (wordAndPinyin.size() > 0) {
                    tableValues.add(wordAndPinyin);
                }
                JTable proofTable = new JTable(tableValues, columnNames);
                scrollPane = new JScrollPane(proofTable);
                add(scrollPane, BorderLayout.CENTER);
            }
            chinese = text;
            return true;
        }
        return false;
    }

    public void exportFile(String desc, String extension) {
        JFileChooser exportChooser = new JFileChooser();
        exportChooser.addChoosableFileFilter(new FileNameExtensionFilter(desc, extension));
        int isOpen = exportChooser.showSaveDialog(this);
        if (isOpen == 0) {  //为0,则表示选择了"保存"
            String exportPath = exportChooser.getSelectedFile().toString();


            if (exportPath.endsWith("." + extension) == false) {
                exportPath += "." + extension;
            }
            System.out.println(exportPath);
            if (confirmExport(exportPath)) {
                switch (extension) {
                    case "pdf":
                        exportPdf(exportPath);
                        break;
                    case "docx":
                        exportDocx(exportPath);
                }
            }
        }
    }

    private void exportDocx(String exportPath) {
        // To Be Impl
    }


    private void exportPdf(String exportPath) {
        try {
            List<String> pinyinList = collectPinyin();
            if (confirmProof()) {
                PdfExporter.exportToPdf(exportPath, pinyinList);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean confirmProof() {
        if (proovedList.size() > 0) {
            for (YuwenWord ywWord : proovedList) {
                System.out.println(String.format("%s, autoPinyin: %s, proofPinyin: %s",
                        ywWord.getChineseWord(), ywWord.getConvertedPinyin(),
                        ywWord.getProofPinyin()));
            }
            System.out.println();
        }
        return true;
    }

    private List<String> collectPinyin() {
        List<String> pinyinList = new ArrayList<>();
        boolean isPinyin = false;
        YuwenWord ywWord = null;
        for (Vector<String> row : tableValues) {
            for (String pinyin : row) {
                if (isPinyin) {
                    if (ywWord != null) {
                        pinyinList.add(pinyin);
                        ywWord.checkProof(proovedList, pinyin);
                    }
                } else {
                    ywWord = yunwenWordMap.get(pinyin);
                }
                isPinyin = !isPinyin;
            }
        }
        return pinyinList;
    }

    private boolean confirmExport(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            int sel = JOptionPane.showConfirmDialog(this, String.format("文件\"%s\"已经存在，是否覆盖？", file.toString()));
            return sel == 0;
        }
        return true;
    }
}
