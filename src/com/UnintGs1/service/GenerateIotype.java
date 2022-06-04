package com.UnintGs1.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Classname GenerateIotype
 * @Descrption TODO
 * @Date 2021/7/2上午 04:58
 * @Created By Dustin_Peng
 */
public class GenerateIotype {
    public static String generate(List<String> isns, String srcString, String pattern){
        Date crDate= new Date();
        SimpleDateFormat smp = new SimpleDateFormat("yyyyMMddHHmmss");
        String crDateStr = smp.format(crDate);
        String destxt="isn_"+crDateStr+".txt";
        for (int i = 0; i < isns.size(); i++) {
            String newIotype = srcString.replaceAll("\\$" + pattern + "\\$",  isns.get(i));
            WriteTxt.writeIO(newIotype, destxt);
        }
        return  destxt;
    }
}
