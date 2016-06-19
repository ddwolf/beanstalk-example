package com.qiyuan;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ColorEditor{
public ColorEditor(){
     JFrame f=new JFrame();
     Object[][] playerInfo={
                 {"阿呆",new Integer(66),new Integer(32),new Integer(98),new Boolean(false)},
                {"阿呆",new Integer(82),new Integer(69),new Integer(128),new Boolean(true)},
    };
    String[] Names={"姓名","语文","数学","总分","及格"};
    JTable table=new JTable(playerInfo,Names);
    table.setPreferredScrollableViewportSize(new Dimension(550,30));
    JScrollPane scrollPane=new JScrollPane(table);
    f.getContentPane().add(scrollPane,BorderLayout.CENTER);
    f.setTitle("Simple Table");
    f.pack();
    f.show();

    f.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                          System.exit(0);
                        }
                      });
   }
   public static void main(String[] args){
     ColorEditor b=new ColorEditor();
   }
  }
