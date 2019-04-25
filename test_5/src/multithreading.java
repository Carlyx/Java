package test;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class multithreading {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Mypaint();
    }
}
class Mypaint extends JFrame{
    private int x[]=new int[105];
    private int y[]=new int[105];
    private int r;
    private int sum=0;
    public void init() {
        for(int i=0;i<105;i++) {
            x[i]=200;
            y[i]=400;
            r=20;
        }
    }
    public Mypaint(){
        init();
        this.setSize(500,500);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton j1=new JButton("开始");
        Font font=new Font("黑体",Font.PLAIN,18);
        j1.setFont(font);
        j1.setBounds(100, 400,100,50);
        this.add(j1);
        JButton j2=new JButton("结束");
        j2.setFont(font);
        j2.setBounds(250,400,100,50);
        this.add(j2);
        this.setVisible(true);
        Graphics g=this.getGraphics();
        DrawEvent listener=new DrawEvent();
        listener.set(g);
        j1.addActionListener(listener);
        j2.addActionListener(listener);
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        for(int i=0;i<sum;i++) {
            g.fillOval(x[i],y[i],r,r);
        }
    }
    class drawrun implements Runnable{
        private int pos;
        public drawrun(int p) {
            pos=p;
        }
        public void run() {
            synchronized (this) {
                //System.out.println(x+" "+y);
                for (int i = 0; i < 281; i++) {
                    x[pos] += 1;
                    y[pos] -= 1;
                    //System.out.println(x+" "+y);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
                for (int i = 0; i < 281; i++) {
                    x[pos] -= 1;
                    y[pos] += 1;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        }
    }
    class DrawEvent implements ActionListener{
        private Graphics g;
        public void set(Graphics g1) {
            g=g1;
        }
        public void actionPerformed(ActionEvent e) {
            String action=e.getActionCommand();
            if(action.equals("开始")) {
                sum++;
                g.fillOval(200,400,20,20);
                Thread thread1=new Thread(new drawrun(sum-1));
                thread1.start();
            }
            else {
                System.exit(0);
            }
        }
    }
}
