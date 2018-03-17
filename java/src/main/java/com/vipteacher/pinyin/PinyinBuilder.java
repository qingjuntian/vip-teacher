package com.vipteacher.pinyin;

import java.util.List;
import java.util.Scanner;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;

/**
 * Created by qingjun on 1/20/18.
 */
public class PinyinBuilder {
    public static void main(String... args) throws PinyinException {
        Scanner sc = new Scanner(System.in);
        String input;
        while ( (input = sc.nextLine()).equals("bye") == false) {
            List<Pinyin> pinyinList = HanLP.convertToPinyinList(input);
            for (Pinyin py : pinyinList) {
                System.out.println(py.getPinyinWithToneMark());
            }
//            System.out.println(PinyinHelper.convertToPinyinString(input, ",", PinyinFormat.WITH_TONE_MARK));
//            System.out.println(HanLP.convertToPinyinList(input));
        }
    }
}
