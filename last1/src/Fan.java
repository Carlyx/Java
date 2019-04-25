import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fan{
    public static void main(String[] args){
        new JFrameTest();
    }
}

class JFrameTest extends JFrame{
    private final int WIDTH = 960;
    private final int HEIGHT = 400;
    private JPanel[] jp = new JPanel[3];
    private JPanel bot = null, center = null;
    private JButton[] start = new JButton[3];
    private JButton[] stop = new JButton[3];
    private JButton[] reverse = new JButton[3];
    private JButton start_all = null, stop_all = null;
    private JPanelTest[] jpt = new JPanelTest[3];
    private Thread th[] = new Thread[3];
    private int i = 0;
    public JFrameTest(){
        setSize(WIDTH, HEIGHT);
        for(int i = 0; i < 3; i++){
            jp[i] = new JPanel();
            jp[i].setLayout(new FlowLayout());
            jp[i].setPreferredSize(new Dimension(300, 360));
            start[i] = new JButton();
            start[i].setText("Start");
            jp[i].add(start[i]);
            stop[i] = new JButton();
            stop[i].setText("Stop");
            jp[i].add(stop[i]);
            reverse[i] = new JButton();
            reverse[i].setText("Reverse");
            jp[i].add(reverse[i]);
            jpt[i] = new JPanelTest();
            jp[i].add(jpt[i]);

            th[i] = new Thread(jpt[i]);
            th[i].start();
        }
        center = new JPanel();
        center.setLayout(new FlowLayout());
        center.add(jp[0]);
        center.add(jp[1]);
        center.add(jp[2]);
        start_all = new JButton();
        start_all.setText("Start All");
        stop_all = new JButton();
        stop_all.setText("Stop All");
        bot = new JPanel();
        bot.setLayout(new FlowLayout());
        bot.add(start_all);
        bot.add(stop_all);
        setLayout(new BorderLayout());
        this.add(center);
        this.add(bot, BorderLayout.SOUTH);
        i=0;
        for(i = 0; i < 3; i++){
            start[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i = 0;i<3;i++){
                        if(start[i] == e.getSource()){
                            jpt[i].getspeed();
                            break;
                        }
                    }
                }
            });
            stop[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i =0;i<3;i++){
                        if(stop[i] == e.getSource()){
                            jpt[i].losespeed();
                            break;
                        }
                    }
                }
            });
            reverse[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0;i<3;i++){
                        if(reverse[i] == e.getSource()){
                            jpt[i].reverse();
                            break;
                        }
                    }
                }
            });
        }
        start_all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<3;i++){
                    jpt[i].getspeed();
                }
            }
        });
        stop_all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<3;i++){
                    jpt[i].losespeed();
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

class JPanelTest extends JPanel implements Runnable{
    private int angle = 0;
    private boolean state = false;
    private JSlider js = null;
    private boolean direction = false;
    public JPanelTest(){
//        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 250));
        js = new JSlider(0, 360, 0);
        synchronized (this){
            js.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    angle = js.getValue();
                    repaint();
                }
            });
        }
        this.add(js, BorderLayout.SOUTH);
    }
    public void paint(Graphics g){
        super.paint(g);     // 加上这一句就可以防止图像不显示
        g.drawOval(0, 0, 200, 200);
        g.setColor(Color.red);
        for(int i = 0; i < 360; i+=90){
            g.fillArc(10,10, 180, 180, i+angle, 30);
        }
    }
    public void run(){
        while (true){
            try{
                Thread.sleep(200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            synchronized (this){
                while (!state){
                    try {
                        wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            if(direction==false){
                angle = (angle+10)%360;
            }else{
                angle = (angle-10+360)%360;
            }
            js.setValue(angle);
            repaint();
        }
    }
    public synchronized void getspeed(){
        if(state==false){
            this.state = true;
            notify();
        }
    }
    public synchronized void losespeed(){
        if(state==true){
            this.state = false;
        }
    }
    public synchronized void reverse(){
        this.direction = !this.direction;
    }
}