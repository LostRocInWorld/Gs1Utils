package com.UnintGs1.service;

import java.io.*;

public class WriteTxt {
	public static String iotype="";
	public static File file =null;
	/**
	 * 字符串写入到指定的文本文件中
	 * @param iotype  写入的文件内容
	 * @param isnsTxt 写入文件的具体位置
	 * @return
	 */
	public static String writeIO(String iotype,String isnsTxt) {
		file=null;	//file对象置空，保证成功第二次转换能正常创建文件
		if (file == null) {
			file = new File(isnsTxt);
			if(!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			FileOutputStream fos = null;
			OutputStreamWriter osw = null;
			BufferedWriter bw = null;

			try {
				fos = new FileOutputStream(file,true);
				osw = new OutputStreamWriter(fos);
				bw = new BufferedWriter(osw);
				bw.write(iotype);
				bw.newLine();
				bw.newLine();
				bw.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("文件未找到");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (osw != null) {
					try {
						osw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		return iotype;
	}
}
