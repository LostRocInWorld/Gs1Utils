package com.UnintGs1.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * @Classname ReadFromExcel
 * @Descrption 从txt中读取isn
 * @Date 2021/7/2上午 07:42
 * @Created By Dustin_Peng
 */
public class ReadIsnFromTxt {
	public static ArrayList<String> isns=new ArrayList<>();	//需要初始化，否则add报错空指针异常
	public static File file = null;

	public static ArrayList<String> readISNtoList(String isnTxt) {
		isns.clear();	//也需要清除之前读取的ISN
		if (file == null) {
			file = new File(isnTxt);
		}
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String isn = null;
//			int i = 0;
			while ((isn = br.readLine()) != null) {
//				i++;
//				System.out.println("第" + i + "次:" + isn);
				if(isn!=null&&!("".equals(isn.trim())))
				isns.add(isn.trim());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("文件未找到");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("读取错误");
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

		return isns;
	}

//	@Test
//	public void test() {
//		ReadISN.readISNtoList("D:\\Users\\Dustin_Peng\\Desktop\\ISN.txt");
//	}
}
