#!/usr/bin/python
#coding:utf-8

__author__ = 'qingjun'

import sys
from pypinyin import pinyin, lazy_pinyin, Style

from docx import Document

from docx.shared import Inches

def read_words(file):
    document = Document(file)
    words = []
    for p in document.paragraphs:
        words.extend(p.text.split(' '))
        print words
    return words

def output_words(outfile, words):
    doc = Document(outfile)
    global title
    doc.add_heading(title, 0)
    line_words = 0
    line=''
    for word in words:
        py = pinyin(word)
        line += u' '.join(w[0] for w in py)
        line += u'_' * len(py) * 2
        line += u' ' * 2
        print line
        line_words += 1

        if line_words == 5:
            #print repr(line)
            doc.add_paragraph(line)
            line = ''
            line_words = 0
    doc.add_paragraph(line)
    doc.save(outfile)

title = u"小学五年级第一单元看拼音写汉字"
space_num_per_char=6

if __name__ == "__main__":
    words = read_words(sys.argv[1])
    output_words(sys.argv[2], words)


#
# document = Document()
#
# document.add_heading('Document Title', 0)
#
# p = document.add_paragraph('A plain paragraph having some ')
# p.add_run('bold').bold = True
# p.add_run(' and some ')
# p.add_run('italic.').italic = True
#
# document.add_heading('Heading, level 1', level=1)
# document.add_paragraph('Intense quote', style='IntenseQuote')
#
# document.add_paragraph(
#     'first item in unordered list', style='ListBullet'
# )
# document.add_paragraph(
#     'first item in ordered list', style='ListNumber'
# )
#
# #document.add_picture('monty-truth.png', width=Inches(1.25))
#
# document.add_page_break()

# document.save(u'/Users/qingjun/workspace/vipteacher/new.docx')


