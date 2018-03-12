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
    return words

def output_words_with_paragraph(outfile, words):
    doc = Document()
    global title
    #doc.add_heading(title, 0)
    line_words = 0
    line_pinyin=''
    line_to_input=''
    char_num = 0
    first = True
    for word in words:
        char_num += len(word)
        py = pinyin(word)
        word_pinyin = u' '.join(w[0] for w in py)
        word_pinyin_number = len(word_pinyin)

        append_whitespace_number = (len(word) * space_num_per_char - word_pinyin_number * 3 / 2 + 8 / len(word)) / 2

        line_pinyin += u' ' * append_whitespace_number + word_pinyin + u' ' * append_whitespace_number

        line_to_input += u'' if first else u'  '
        line_to_input += u'(' + (u' '* len(py) * space_num_per_char) + ')'

        line_words += 1
        if first: first = False

        if char_num >= 14:
            #print repr(line)
            doc.add_paragraph(line_pinyin)
            doc
            doc.add_paragraph(line_to_input)
            line_pinyin = ''
            line_to_input = ''
            char_num = 0
            first = True
    doc.add_paragraph(line_pinyin)
    doc.add_paragraph(line_to_input)
    doc.save(outfile)

title = u"小学五年级第一单元看拼音写汉字"
space_num_per_char=8

if __name__ == "__main__":
    words = read_words(sys.argv[1])
    output_words_with_paragraph(sys.argv[2], words)


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


