/**
 * This file is part of pinyin4j (http://sourceforge.net/projects/pinyin4j/)
 * and distributed under GNU GENERAL PUBLIC LICENSE (GPL).
 * <p>
 * pinyin4j is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p>
 * pinyin4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with pinyin4j.
 */

package com.vipteacher.pinyin.gui;

import com.itextpdf.text.DocumentException;
import com.vipteacher.pinyin.gui.component.PinyinProofPanel;
import com.vipteacher.pinyin.gui.component.VtTextArea;
import com.vipteacher.pinyin.utils.PdfExporter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */

/**
 * A demo show the functions of pinyin4j library
 *
 * @author Li Min (xmlerlimin@gmail.com)
 *
 */
public class VipTeacherApplet extends JApplet {

    private static final int APP_WIN_WIDTH = 800;

    private static final int APP_WIN_HEIGHTH = 600;

    private static final Dimension APP_SIZE = new Dimension(APP_WIN_WIDTH, APP_WIN_HEIGHTH);

    private static final long serialVersionUID = -1934962385592030162L;

    private JPanel jContentPane = null;

    private JPanel inputPanel = null;

    private PinyinProofPanel proofPanel = null;

    private JButton convertButton = null;

    private JButton backFirstPageButton = null;

    private JButton exportPdfButton = null;

    private JButton exportWordButton = null;

    private JPanel buttonPanel = null;

    private static String appName = "超级语文老师";

    /**
     * This method initializes charTextField
     *
     * @return javax.swing.JTextField
     */
    private JTextArea getCharTextArea() {
        if (charTextArea == null) {
            charTextArea = new VtTextArea(APP_WIN_WIDTH, 480);
        }
        return charTextArea.getTextArea();
    }


    static public void main(String argv[]) {
        if (false) {
            try {
                PdfExporter.exportToPdf("/Users/qingjun/workspace/vipteacher/vip.pdf", null);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        final VipTeacherApplet appletDemo = new VipTeacherApplet();


        JFrame jFrame = new JFrame(appName);
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                appletDemo.stop();
                appletDemo.destroy();
                System.exit(0);
            }
        });
        jFrame.add("Center", appletDemo);

        appletDemo.init();
        appletDemo.start();

        jFrame.setSize(APP_SIZE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    /**
     * This is the default constructor
     */
    public VipTeacherApplet() {
        super();
        init();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    public void init() {
        this.setSize(APP_SIZE);
        this.getProofPanel();
        this.setContentPane(getJContentPane());
        this.setName(appName);
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getTitlePanel(), BorderLayout.NORTH); // Generated
            jContentPane.add(getInputPanel(), BorderLayout.CENTER); // Generated
            jContentPane.add(getButtonPanel(), BorderLayout.SOUTH); // Generated
            switchPage(0);
        }
        return jContentPane;
    }

    private JLabel titleLabel;

    private JPanel titlePanel;

    private JPanel getTitlePanel() {
        if (titlePanel == null) {
            titleLabel = new JLabel();
            titleLabel.setText("第一步，输入要转换的词语"); // Generated

            titlePanel = new JPanel();
            titlePanel.add(titleLabel);
        }
        return titlePanel;
    }


    /**
     * This method initializes jPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getInputPanel() {
        if (inputPanel == null) {
            inputPanel = new JPanel();
            inputPanel.setPreferredSize(new Dimension(APP_WIN_WIDTH, 480)); // Generated
            inputPanel.add(getCharTextArea(), null); // Generated
            inputPanel.setVisible(true);
        }
        return inputPanel;
    }


    public JPanel getProofPanel() {
        if (proofPanel == null) {
            proofPanel = new PinyinProofPanel();
            proofPanel.setSize(new Dimension(APP_WIN_WIDTH, 480)); // Generated
            proofPanel.setVisible(false);
        }
        return proofPanel;
    }

    /**
     * This method initializes jButton
     *
     * @return javax.swing.JButton
     */
    private JButton getConvertButton() {
        if (convertButton == null) {
            convertButton = new JButton();
            convertButton.setText("下一步"); // Generated
            convertButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (proofPanel.proof(charTextArea.getText())) {
                        switchPage(1);
                    }
                }
            });

        }
        return convertButton;
    }

    private void switchPage(int page) {
        switch (page) {
            case 0:
                convertButton.setVisible(true);
                backFirstPageButton.setVisible(false);
                exportPdfButton.setVisible(false);
                exportWordButton.setVisible(false);
                inputPanel.setVisible(true);
                proofPanel.setVisible(false);
                jContentPane.add(getInputPanel(), BorderLayout.CENTER);
                titleLabel.setText("第一步，输入要转换的词语"); // Generated
                break;
            case 1:
                convertButton.setVisible(false);
                backFirstPageButton.setVisible(true);
                exportPdfButton.setVisible(true);
                exportWordButton.setVisible(true);
                inputPanel.setVisible(false);
                proofPanel.setVisible(true);
                proofPanel.setSize(new Dimension(APP_WIN_WIDTH, 480)); // Generated
                jContentPane.add(getProofPanel(), BorderLayout.CENTER);
                titleLabel.setText("第二步，请您校对"); // Generated

        }
    }

    /**
     * This method initializes jPanel2
     *
     * @return javax.swing.JPanel
     */
    private JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getConvertButton(), BorderLayout.EAST);
            buttonPanel.add(getBackFirstPageButton());
            buttonPanel.add(getExportPdfButton(), BorderLayout.EAST);
            buttonPanel.add(getExportWordButton(), BorderLayout.EAST);
        }
        return buttonPanel;
    }


    private JButton getBackFirstPageButton() {
        if (backFirstPageButton == null) {
            backFirstPageButton = new JButton("返回上一步");
            backFirstPageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switchPage(0);
                }
            });
        }
        return backFirstPageButton;
    }

    private JButton getExportPdfButton() {

        if (exportPdfButton == null) {
            exportPdfButton = new JButton();
            exportPdfButton.setText("导出到Pdf"); // Generated
            exportPdfButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    // String wordInput= getChineseCharText();
                    proofPanel.exportFile("PDF 文件", "pdf");
                }

            });

        }
        return exportPdfButton;
    }


    private JButton getExportWordButton() {

        if (exportWordButton == null) {
            exportWordButton = new JButton();
            exportWordButton.setText("导出到Word"); // Generated
            exportWordButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    // String wordInput= getChineseCharText();
                    proofPanel.exportFile("Word 文件", "docx");


                }

            });

        }
        return exportWordButton;
    }


    private VtTextArea charTextArea = null;



}