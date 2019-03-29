
import javafx.scene.layout.BorderWidths;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Emotion {
    public static void main(String[] args){
            JFrameTest frame = new JFrameTest();
    }
}

class JPanelTest extends JPanel{
    private JButton button1 = new JButton("Cry");
    public JPanelTest(){
        this.setLayout(new FlowLayout(10,20,280));

        JButton button2 = new JButton("Angry");
        JButton button3 = new JButton("Smile");
        JButton button4 = new JButton("Exit");

        Modify(button1,60,36,16);
        Modify(button2,60,36,16);
        Modify(button3,60,36,16);
        Modify(button4,60,36,16);

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
    }
    public void Modify(JButton jButton,int wid, int hei,int s){
        jButton.setPreferredSize(new Dimension(wid,hei));
        Font f = new Font("宋体",Font.BOLD,s);
        jButton.setFont(f);
        jButton.setMargin(new java.awt.Insets(0,0,0,0));
    }
}

class JFrameTest extends JFrame{
    private static final int WIDTH = 360;
    private static final int HEIGHT = 360;
    public JFrameTest(){
        setSize(WIDTH,HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setLocation(screenWidth/4, screenHeight/4);
        setTitle("test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Graphics g=this.getGraphics();
        try{
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
        Event event = new Event(g);
    }
}

class Event{
    private Graphics g;
    public Event(Graphics _g){
        this.g=_g;
        g.drawLine(10,20,100,101);
    }
}

