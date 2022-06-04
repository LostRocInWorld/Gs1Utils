package com.UnintGs1.panel;

import com.UnintGs1.service.GenerateIotype;
import com.UnintGs1.service.ReadIoType;
import com.UnintGs1.service.ReadIsnFromTxt;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname FunctionPanel
 * @Descrption 选择文件的面板
 * @Date 2021/7/2上午 12:42
 * @Created By Dustin_Peng
 */
public class FunctionPanel extends JPanel {
    private VariablePanel variablePanel;
    public List<String> varList = null;
    public ArrayList<String> isns = null;
    public String srcString = "";
    private JFileChooser srcChooser = null;   //模板变量的文件选择器
    private JFileChooser isnChooser = null; //需要替换的变量的选择器
    private JButton srcBtn = null;   //模板变量的按钮
    private JButton isnBtn = null; //需要替换的变量的按钮
    private JPanel btnPanel = null;
    private JTextArea varTextArea = null;



    public FunctionPanel() {
        initialize();
    }

    public void initialize() {
        setSize(500, 150);
        setVisible(true);
        JLabel fileLabel = new JLabel("文件选择");
        fileLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        //变量选择
        variablePanel = new VariablePanel();

        btnPanel = new JPanel();
        //点击按钮选择变量模板
        srcBtn = new JButton("选择变量iotype");
        srcBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                srcString="";
                srcChooser = new JFileChooser("D://");
                FileFilter filter = new FileNameExtensionFilter("文本文件(txt)", "txt");//注意使用的是javax.swing.filechooser.FileFilter
                srcChooser.setFileFilter(filter);
                int i = srcChooser.showOpenDialog(getRootPane());
                if (i == JFileChooser.APPROVE_OPTION) {// 判断用户单击的是否为“打开”按钮
                    varTextArea.setText("变量如下：\n"); //重置变量显示框
                    File srcFile = srcChooser.getSelectedFile();
                    srcString = ReadIoType.readIOType(srcFile.getAbsolutePath());
//                    System.out.println(srcString);
                    Pattern pattern = Pattern.compile("\\$[a-zA-Z0-9]+\\$", Pattern.CASE_INSENSITIVE);//忽略大小写匹配
                    Matcher matcher = pattern.matcher(srcString);
//                    System.out.println("----------------");
                    varList = new ArrayList<>();
                    while (matcher.find()) {
//                        System.out.println(matcher.group());
                        if (!varList.contains(matcher.group())) {//去重添加到varList中
                            varList.add(matcher.group());
                            varTextArea.append(matcher.group() + "\n"); //添加到变量显示框中
//                            VariablePanel.varList.add(matcher.group());
                        }
                    }
//                        varList.forEach(System.out::println); //输出
                    validate();
                }
            }
        });

        //選擇ISN文件的按鈕
        isnBtn = new JButton("选择ISN文件");
        isnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String choosePath = this.getClass().getClassLoader().getResource("").getPath();
//                System.out.println(choosePath);
                isnChooser = new JFileChooser("D://");
                FileFilter filter = new FileNameExtensionFilter("文本文件(txt)", "txt");//注意使用的是javax.swing.filechooser.FileFilter
                isnChooser.setFileFilter(filter);
                int i = isnChooser.showOpenDialog(getRootPane());
                if (i == JFileChooser.APPROVE_OPTION) {// 判断用户单击的是否为“打开”按钮
                    File isnFile = isnChooser.getSelectedFile();
                    isns = ReadIsnFromTxt.readISNtoList(isnFile.getAbsolutePath());
                    isns.forEach(System.out::println);
                }
            }
        });
        //变量显示框
        varTextArea = new JTextArea("变量如下：\n");

        //開始轉換按鈕
        //转换按钮
        JButton okBtn = new JButton("开始转换");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = "";
                if (variablePanel.selectedText != null) {
                    //转换并输出到文件
                    path = GenerateIotype.generate(isns, srcString, variablePanel.selectedText);
                }
                File file = new File(path);
                if (file.exists()){
                    varTextArea.setText("变量如下：\n");
                    JOptionPane.showConfirmDialog(null, "轉換成功", "提示", JOptionPane.CLOSED_OPTION);
                }

            }
        });


        btnPanel.setLayout(new FlowLayout());


        btnPanel.add(srcBtn);
        btnPanel.add(varTextArea);
        btnPanel.add(isnBtn);
        btnPanel.add(okBtn);


//        add(fileLabel, BorderLayout.NORTH);
        add(variablePanel, BorderLayout.NORTH);
        add(btnPanel, BorderLayout.CENTER);

    }
}
