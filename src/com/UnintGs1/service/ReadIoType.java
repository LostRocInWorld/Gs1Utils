package com.UnintGs1.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadIoType {
    public static String iotype ;
    public static File file = null;

    /**
     * 读取ISN
     *
     * @param isnTxt IOTYPE的位置
     * @return
     */
    public static String readIOType(String isnTxt) {

        iotype="";  //此处要先置空，否则会有值导致之前的数据也留存其中
        file = new File(isnTxt);
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            char chars[] = new char[128];
            int end = -1;
            while ((end = br.read(chars)) != -1) {
                if (chars != null)
                    iotype += new String(chars);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件未找到");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return iotype;
    }

//	@Test
//	public void testReadIO() {
//		readIOType("D:\\Users\\Dustin_Peng\\Desktop\\UNIT(LL).txt");
//		System.out.println(iotype);
//	}
}
