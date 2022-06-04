package com.UnintGs1.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @Classname VariablePanel
 * @Descrption 选择变量的面板
 * @Date 2021/7/2上午 12:37
 * @Created By Dustin_Peng
 */
public class VariablePanel extends JPanel {
    public String selectedText;
    public static List<String> varList = null;
    public List<JRadioButton> varRadioBtnList = null;
    private String[] var= new String[]{"ISN","UPCCODE","CUSTITEM","MEID","IMEIS","MODEL"};

    public VariablePanel() {
        initialize();
    }

    public void initialize() {
        setSize(500, 150);
        setVisible(true);
        JLabel varLabel = new JLabel("变量选择");
        varLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        add(varLabel, BorderLayout.NORTH);

        JRootPane rootPane = this.getRootPane();
        ButtonGroup group = new ButtonGroup();
        for(String str:var){
            JRadioButton tempRadioBtn = new JRadioButton(str);
            tempRadioBtn.addActionListener(new btnListener());
            group.add(tempRadioBtn);
            add(tempRadioBtn);
        }


    }

     class btnListener implements ActionListener{
         @Override
         public void actionPerformed(ActionEvent e) {
             JRadioButton radioBtn = (JRadioButton) e.getSource();
             selectedText = radioBtn.getText();
             System.out.println(selectedText);
         }
    }


}
