package com.vipteacher.pinyin.entity;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Created by qingjun on 3/17/18.
 */
public class YuwenWord {
    String chineseWord;
    String convertedPinyin;
    String proofPinyin;

    public YuwenWord(String chineseWord) {
        this.chineseWord = chineseWord.trim();
    }

    public String getConvertedPinyin() {
        return convertedPinyin;
    }

    public void setConvertedPinyin(String convertedPinyin) {
        this.convertedPinyin = convertedPinyin;
    }

    public String getProofPinyin() {
        return proofPinyin;
    }

    public void setProofPinyin(String proofPinyin) {
        this.proofPinyin = proofPinyin;
    }

    public String getChineseWord() {
        return this.chineseWord;
    }

    public void convert(Vector<String> wordAndPinyin) {
        wordAndPinyin.add(chineseWord.trim());
        java.util.List<Pinyin> pinyinList = HanLP.convertToPinyinList(chineseWord.trim());
        this.convertedPinyin = String.join(" ", pinyinList.stream().map(s -> s.getPinyinWithToneMark()).collect(Collectors.toList()).toArray(new String[pinyinList.size()]));
        this.setProofPinyin(convertedPinyin);
        wordAndPinyin.add(convertedPinyin);

    }

    public void checkProof(List<YuwenWord> proovedList, String pinyin) {
        if (pinyin.equals(convertedPinyin) == false) {
            proofPinyin = pinyin;
            proovedList.add(this);
        }
    }
}
