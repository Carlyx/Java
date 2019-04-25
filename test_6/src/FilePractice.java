import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.*;

public class FilePractice {
    public static void main(String[] args){
        JFrameTest jft = new JFrameTest();
        jft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jft.setVisible(true);
    }
}

class JFrameTest extends JFrame{
    private final int W = 400;
    private final int H = 400;
    private JPanel jp = null;
    private JLabel jl1 =null,jl2 = null, jl3 = null;
    private JButton jb1 = null, jb2 = null, jb3 = null, jb4 = null,jb5 = null;
    private JTextArea jt1 = null, jt2 = null;
    private String path = "";
    private StringBuilder sb = null, res=null,res1=null;
    public JFrameTest(){
        setSize(W,H);
        setLocation(500,200);
//        setLocationByPlatform(true);

        jp = new JPanel();
        jp.setLayout(null);
        this.add(jp);

        jl1 = new JLabel();
        jp.add(jl1);
        jl1.setText("读写文件地址:");
        jl1.setBounds(40,20,120,40);

        jt1 = new JTextArea();
        jp.add(jt1);
        jt1.setBounds(150,30,200,20);

        jl2 = new JLabel();
        jl2.setText("从本地选取文件");
        jp.add(jl2);
        jl2.setBounds(40,70,120,40);

        jb2 = new JButton();
        jb2.setText("选取文件");
        jp.add(jb2);
        jb2.setBounds(180,70,150,40);
        jb2.addActionListener(new JB2());

        jb1 = new JButton();
        jp.add(jb1);
        jb1.setText("开始统计");
        jb1.setBounds(40,140,120,40);
        jb1.addActionListener(new JB1());

        jb3 = new JButton();
        jp.add(jb3);
        jb3.setText("退出");
        jb3.setBounds(200,140,120,40);
        jb3.addActionListener(new JB3());

        jl3 = new JLabel();
        jp.add(jl3);
        jl3.setText("输入写入内容");
        jl3.setBounds(40,200,80,40);

        jt2 = new JTextArea();
        jp.add(jt2);
        jt2.setBounds(150,200,160,40);

        jb4 = new JButton();
        jp.add(jb4);
        jb4.setText("写入文件");
        jb4.setBounds(100,260,120,40);

        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String a = jt2.getText();
                    String[] arr = a.split("\n|\t|\r");
                    System.out.println(a);
                    System.out.println(jt1.getText());
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jt1.getText()),"GBK"));
                    for(String p:arr){
                        out.write(p);
                        out.newLine();
                    }
                    out.flush();
                    out.close();
                }catch (IOException ee){
                    ee.printStackTrace();
                }
                JOptionPane jo = new JOptionPane();
                jo.showMessageDialog(jo,"保存成功");
            }
        });
    }

    private class JB1 implements ActionListener{
        public JB1(){

        }
        public void actionPerformed(ActionEvent event){
            path = jt1.getText();
//            System.out.println(path);
            sb = readFileByChars(path);
            System.out.println(sb);
            int p=0,q = 0;
            for(int i = 0;i<sb.length();i++){
                if(sb.charAt(i)==' '|| sb.charAt(i)=='\n'){
                    p++;
                }
            }
            int blank=0;
            String txt=sb.toString();
            String[] count1 =txt.split("[ \\t\\n\\x0B\\f\\r]");
            int len=count1.length;
            for (int i = 0; i < count1.length; i++) {
                if (count1[i].equals("")) {
                    blank++;
                }
            }
            res = new StringBuilder();
            res1 = new StringBuilder();
            res.append("字符个数: ");
            res.append((sb.length()-p));
            res.append("\n");
            res.append("单词个数：");
            res.append((len-blank));
            System.out.println("字符个数: " + (sb.length()-p));
            System.out.println("单词个数："+(len-blank));
            jt2.setText(String.valueOf(res));
            JOptionPane jj = new JOptionPane();
            jj.showMessageDialog(jj,res);
        }
    }

    private class JB2 implements ActionListener{
        public JB2(){

        }
        public void actionPerformed(ActionEvent event){
            JFileChooser jfc=new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
            jfc.showDialog(new JLabel(), "选择");
            File file=jfc.getSelectedFile();
//            if(file.isDirectory()){
//                System.out.println("文件夹:"+file.getAbsolutePath());
//            }else if(file.isFile()){
//                System.out.println("文件:"+file.getAbsolutePath());
//            }
            jt1.setText(file.getAbsolutePath());
//            System.out.println(jfc.getSelectedFile().getName());
        }
    }
    public static StringBuilder readFileByChars(String fileName){
        StringBuilder ss = new StringBuilder();
//        File file = new File(fileName);
        Reader reader = null;
        try {
//            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    ss.append(tempchars);
//                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            ss.append(tempchars[i]);
//                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (IOException e1) {
            e1.printStackTrace();
            JOptionPane joo = new JOptionPane();
            joo.showMessageDialog(joo,"地址错误");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return ss;
    }
    private class JB3 implements ActionListener{
        public JB3(){

        }
        public void actionPerformed(ActionEvent event){
            dispose();
        }
    }

}

/*
FileInputStream、FileOutputStream、DataInputSteam、DataOutputSteam、RandomAccessFile处理二进制文件；
        (2)使用FileReader、FileWriter、BufferedReader、BufferedWriter 、PrintWriter处理文本文件
        */
/*
编写程序，统计英文文本文件中的字符数目和单词数目。程序运行时，输入要统计的文件的名称，程序处理后输出字符数目和单词数目。
 */