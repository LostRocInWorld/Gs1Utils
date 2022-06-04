package com.UnintGs1.main;

import com.UnintGs1.panel.FunctionPanel;
import com.UnintGs1.panel.VariablePanel;

import java.awt.*;

import javax.swing.*;

public class Main extends JFrame {
	private FunctionPanel functionPanel;
	private VariablePanel  variablePanel;
		public  Main () {
			setTitle("转换单包指令中的变量");
			setSize(600,500);
			setLayout(new BorderLayout());
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			initialize();	//注意此方法的调用顺序
		}

	private void initialize() {
		Container c = this.getContentPane();
		c.removeAll();
		functionPanel =new FunctionPanel();
		c.add(functionPanel,BorderLayout.CENTER);
		c.validate();
	}



	public static void main(String[] args) {
		Main mainFrame = new Main();
		try {
			mainFrame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//		String iotypetxt="UNIT.txt";	//變量iotype的位置
//		String isntxt="ISN.txt";	//isn的位置
//		String destxt="isnss.txt";	//寫出文件的地址
//
//
//			ReadIOType.readIOType(iotypetxt);
//			String iotype = ReadIOType.iotype;
////		System.out.println(iotype);
//			ArrayList<String> isns = ReadISN.readISNtoList(isntxt);
//			for (int i = 0; i < isns.size(); i++) {
//				String newIotype = iotype.replaceAll("\\$ISN\\$", isns.get(i));
//				WriteTxt.writeIO(newIotype,destxt);
//			}
//
//		File file=new File("isnss.txt");
//		if(file.exists())
//		JOptionPane.showConfirmDialog(null, "轉換成功", "提示",  JOptionPane.CLOSED_OPTION);
//
}



