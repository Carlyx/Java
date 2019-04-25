import java.awt.*;
import javax.swing.*;
import java.math.*;

public class GraphMy{
    public static void main(String[] args){
        EventQueue.invokeLater(() ->
        {
            JFrameTest frame = new JFrameTest();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class JPanelTest extends JPanel{
    public void paintComponent(Graphics g){
        int f=0;
        double a,b,c,d;
        for(int i=0;i<20;i++) {
            int op = (int)(1+Math.random()*(3-1+1));
            if(op==1){
                a = (10+Math.random()*600);
                b = (10+Math.random()*400);
                c = (10+Math.random()*600);
                d = (10+Math.random()*400);
                MyLine line = new MyLine(a, b, c, d);
                line.draw(g);
            }
            else if(op==2){
                a = (10+Math.random()*600);
                b = (10+Math.random()*400);
                c = 10+Math.random()*(600-(int)a);
                d = 10+Math.random()*(400-(int)b);
                MyRectangle rectangle = new MyRectangle(a, b, c, d);
                rectangle.draw(g);
            }
            else{
                while(true){
                    a = Math.random()*600;
                    b = Math.random()*400;
                    c = 20+Math.random()*(600-(int)(a));
                    d = 20+Math.random()*(400-(int)(b));
                    if((int)(a+1+c)>=600 || (int)(b+1+d)>=400){
                        continue;
                    }
                    break;
                }
                MyOval oval = new MyOval(a, b, c, d);
                oval.draw(g);
            }
        }
    }
}

class JFrameTest extends JFrame{
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    public JFrameTest(){
        super.setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        super.setLocation(screenWidth/4, screenHeight/4);
        super.setTitle("Photo");

        add(new JPanelTest());
//        pack();
    }
}

abstract class MyShape{
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    public MyShape(){
        this.x1 = 0.0;
        this.y1 = 0.0;
        this.x2 = 0.0;
        this.y2 = 0.0;
    }
    public MyShape(double ax1, double ay1, double ax2, double ay2){
        this.x1 = ax1;
        this.y1 = ay1;
        this.x2 = ax2;
        this.y2 = ay2;
    }
    public void setX1(double x1){
        this.x1 = x1;
    }
    public void setY1(double y1){
        this.y1 = y1;
    }
    public void setX2(double x2){
        this.x2 = x2;
    }
    public void setY2(double y2){
        this.y2 = y2;
    }
    public double getX1(){
        return this.x1;
    }
    public double getY1(){
        return this.y1;
    }
    public double getX2(){
        return this.x2;
    }
    public double getY2(){
        return this.y2;
    }
    public abstract void draw(Graphics g);
}

class MyLine extends MyShape{
    public MyLine(double x1, double y1, double x2, double y2){
        super(x1, y1, x2, y2);
    }
    public void draw(Graphics g){
        g.drawLine((int)super.getX1(), (int)super.getY1(), (int)super.getX2(), (int)super.getY2());
    }
}

class MyRectangle extends MyShape{
    public MyRectangle(double x1, double y1, double x2, double y2){
        super(x1, y1, x2, y2);
    }
    public void draw(Graphics g){
        g.drawRect((int)super.getX1(), (int)super.getY1(), (int)super.getX2(), (int)super.getY2());
    }
}

class MyOval extends MyShape{
    public MyOval(double x, double y, double width, double height){
        super(x,y,height,width);
    }
    public void draw(Graphics g){
        g.drawOval((int)super.getX1(), (int)super.getY1(), (int)super.getX2(), (int)super.getY2());
    }
}