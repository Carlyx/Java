import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class GuessNumber {
    public static void main(String[] args){
        new JFrameTest();
    }
}

class JFrameTest extends JFrame{
    private final static int WIDTH = 350;
    private final static int HEIGHT = 200;
    private int count=0;
    private int num = 0;
    private int flag = 0;
    private JLabel label=null, label1=null,label2=null;
    private JButton button=null,button1=null,button2=null;
    private JTextField text=null;
    private Container con=null;
    private JPanel jp=null,jp1=null;
    private int[] vis = null;
    public JFrameTest(){
        super("GuessNumber");
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        con=getContentPane();
        jp=new JPanel();
        jp.setLayout(null);

        label=new JLabel(" 你已经猜了 "+count+" 次 ");
        jp.add(label);

        label1 = new JLabel("输入猜测的数:");
        jp.add(label1);

        text = new JTextField("");
        text.setPreferredSize(new Dimension(50,5));
        text.setSize(10,5);
        jp.add(text);

        label2 = new JLabel("输入1-1000的整数");
        jp.add(label2);

        button=new JButton("OK");
        jp.add(button);

        button1 = new JButton("Restart");
        jp.add(button1);

        button2 = new JButton("Exit");
        jp.add(button2);

        con.add(jp);

        label.setBounds(2,5,120,20);
        label2.setBounds(210,50,120,30);
        label1.setBounds(10,50,110,30);
        text.setBounds(100,50,100,30);
        button.setBounds(20,120,80,30);
        button1.setBounds(125,120,80,30);
        button2.setBounds(230,120,80,30);

//        jp.setBackground(Color.RED);
        setVisible(true);

        num = (int)(1+Math.random()*(1000-1+1));
        vis = new int[1000];
        Arrays.fill(vis,0);
        System.out.println(num);

        button.addActionListener( new  ActionListener( ) {
            @Override
              public void actionPerformed(ActionEvent ev) {
                if(JFrameTest.this.flag == 1){
                    JOptionPane jOptionPane = new JOptionPane();
                    jOptionPane.showMessageDialog(jOptionPane,"本次猜数已成功，请重新开始！");
                }
                else{
                    count++;
                    String str = JFrameTest.this.text.getText();
                    if(str.equals("")){
                        JOptionPane jOptionPane = new JOptionPane();
                        jOptionPane.showMessageDialog(jOptionPane,"输入有误");
                    }
                    else{
                        int inum=0;
                        try{
                            inum = Integer.parseInt(str);
                            label.setText(" 你已经猜了 " + count + " 次");
                            if(JFrameTest.this.vis[inum]==1){
                                JOptionPane jOptionPane = new JOptionPane();
                                jOptionPane.showMessageDialog(jOptionPane,"本次已经猜过该数字");
                            }
                            else if(inum==num){
                                JFrameTest.this.jp.setBackground(Color.YELLOW);
                                JFrameTest.this.text.setEditable(false);
                                JFrameTest.this.label1.setText("");
                                JFrameTest.this.flag = 1;
                                JFrameTest.this.label2.setText("恭喜猜对了！");
                            }
                            else if(inum>num){
                                JFrameTest.this.jp.setBackground(Color.RED);
                                JFrameTest.this.label2.setText("太大了");
                                JFrameTest.this.vis[inum] = 1;
                            }
                            else if(inum<num){
                                JFrameTest.this.jp.setBackground(Color.BLUE);
                                JFrameTest.this.label2.setText("太小了");
                                JFrameTest.this.vis[inum] = 1;
                            }
                        }catch (Exception e){
                            JOptionPane jOptionPane = new JOptionPane();
                            jOptionPane.showMessageDialog(jOptionPane,"输入有误");
                        }
                    }
                }
              }
        });
        button1.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
                count=0;
                label.setText(" 你已经猜了 " + count + " 次");
                JFrameTest.this.jp.setBackground(Color.WHITE);
                JFrameTest.this.flag = 0;
                JFrameTest.this.text.setEditable(true);
                JFrameTest.this.label2.setText("输入1-1000的整数");
                JFrameTest.this.num = (int)(1+Math.random()*(1000-1+1));
                Arrays.fill(JFrameTest.this.vis,0);
                System.out.println(num);
            }
        });
        ActionDis actionDis = new ActionDis();
        button2.addActionListener(actionDis);
    }
    private class ActionDis implements ActionListener{
        public void actionPerformed(ActionEvent event){
            dispose();
        }
    }
}