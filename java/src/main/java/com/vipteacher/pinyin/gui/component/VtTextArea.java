package com.vipteacher.pinyin.gui.component;


import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.stream.Collectors;

/**
 * Created by qingjun on 3/14/18.
 */
public class VtTextArea {
    JTextArea textArea;
    boolean focused = false;


    public VtTextArea(int width, int height) {
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Dialog", Font.PLAIN, 12)); // Generated
        textArea.setText("请输入词语，词语之间用空格分隔"); // Generated
        textArea.setPreferredSize(new Dimension(width, height)); // Generated
        textArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                checkInitStatus();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                checkInitStatus();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            void checkInitStatus() {
                if (focused == false) {
                    focused = true;
                    textArea.setText("");
                }
            }

        });
    }


    public JTextArea getTextArea() {
        return textArea;
    }

    public String getText() {
        return textArea.getText();
    }


}
