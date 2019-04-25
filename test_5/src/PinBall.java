import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;


public class PinBall {
    public static void main(String[] args){
        new JFrameTest();
    }
}

class JFrameTest extends JFrame{
    private final int WIDTH=600;
    private final int HEIGHT = 400;
    private JPanel jp=null;
    private JButton jb1=null,jb2=null;
    private  ThreadTest threadTest;
    private Queue<ThreadTest> arr = null;
    public JFrameTest(){
        setSize(600,400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrameTest.EXIT_ON_CLOSE);
        setVisible(true);

        jp = new JPanel();
        jp.setLayout(null);
        this.add(jp);
        jp.setBackground(Color.WHITE);

        jb1 = new JButton("Start");
        jb2 = new JButton("End");

        jp.add(jb1);
        jp.add(jb2);

        jb1.setBounds(190,310,100,40);
        jb2.setBounds(320,310,100,40);

        setVisible(true);

        jb1.addActionListener(new ActionThread());
        jb2.addActionListener(new EndThread());
        arr = new LinkedList<ThreadTest>();
    }
    public void DoIt(){
//        System.out.println(jp.getWidth()+" : "+jp.getHeight());
        Ball b = new Ball((int)jp.getWidth()/2,(int)jp.getHeight()-75);
        Graphics g = jp.getGraphics();
        threadTest = new ThreadTest(g,b,jp);
        arr.add(threadTest);
    }
    private class ActionThread implements ActionListener{
        public ActionThread(){

        }
        public void actionPerformed(ActionEvent event){
            DoIt();
            Thread th = new Thread(threadTest);
            th.start();
        }
    }

    private class EndThread implements ActionListener{
        public EndThread(){

        }
        public void actionPerformed(ActionEvent event){
            while(!arr.isEmpty()){
                arr.poll().stopThread();
            }
            JOptionPane jOptionPane = new JOptionPane("线程结束");
            jOptionPane.showMessageDialog(jOptionPane,"线程结束");
        }
    }
}

class ThreadTest implements Runnable{
    private boolean stop = true;
    private Graphics g = null;
    private Ball b = null;
    private JPanel jp = null;
    public ThreadTest(){

    }
    public ThreadTest(Graphics _g, Ball _b,JPanel jp){
        this.g = _g;
        this.b = _b;
        this.jp = jp;
    }
    public void run(){
        synchronized(this) {
            g.setColor(Color.BLUE);
            g.fillOval(b.getX(), b.getY(), 30, 30);
//         g.drawOval(b.getX(),b.getY(),30,30);
            while (stop) {
                try {
                    g.setColor(Color.WHITE);
//                g.
                    g.fillOval(b.getX(), b.getY(), 30, 30);
//                g.cle
                    stop = b.move(20, -10, jp.getWidth() + 10, jp.getHeight());
                    g.setColor(Color.blue);
                    g.fillOval(b.getX(), b.getY(), 30, 30);
                    Thread.sleep(100);
//                System.out.println(b.getX());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            g.setColor(Color.RED);
            g.fillOval(b.getX(), b.getY(), 30, 30);
        }
    }
    public void stopThread(){
        this.stop = false;
    }
}

class Ball{
    private int x = 0;
    private int y = 0;
    private int flag = 0;
    public Ball(int x,int y){
        this.x = x;
        this.y = y;
    }
    public boolean move(int dx, int dy,int W,int H){
        if(flag ==0 && this.x+dx>=W-15){
            flag = 1;
            dx = -dx;
        }
        if(flag == 1 && this.y+dy>15){
            dx = -dx;
        }
        if(flag == 1 && this.y+dy<=15){
            flag = 2;
            dx = -dx;
            dy = -dy;
        }
        if(flag ==2 && this.x+dx>15){
            dx = -dx;
            dy = -dy;
        }
        if(flag == 2 && this.x+dx<=15){
            flag = 3;
            dy = -dy;
        }
        if(flag == 3 && this.y+dy<H-80)
            dy = -dy;
        if(flag == 3 && this.y+dy>=H-80){
            return false;
        }
        this.x += dx;
        this.y += dy;
        return true;
//        System.out.println(W);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}