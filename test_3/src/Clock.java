//import java.awt.*;
//import javax.swing.*;
//
//public class Clock {
//    public static void main(String[] args){
//        EventQueue.invokeLater(() ->
//        {
//            JFrameTest frame = new JFrameTest();
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setVisible(true);
//        });
//    }
//}
//
//class JPanelTest extends JPanel{
//    public JPanelTest(){
//    }
//    public void paintComponent(Graphics g){
//        g.drawString("12",270,78);
//        g.drawString("3",382,180);
//        g.drawString("6",275,292);
//        g.drawString("9",173,180);
//        g.drawLine(280,80,280,90);
//
//        double P = Math.tan(3.14/2);
//        double y = Math.sqrt(100*100/(1+P))+180;
//        double x = (y-180)/P+280;
//        System.out.println(x+"  "+y);
//        g.drawLine(280,180,(int)x,(int)y);
//        g.drawString("Hour:0 Minute:0 Second:0",200,320);
////        System.out.println(g.getFont());
//        g.drawOval(180,80,200,200);
//    }
//}
//
//class JFrameTest extends JFrame{
//    private static final int WIDTH = 600;
//    private static final int HEIGHT = 400;
//    public JFrameTest(){
//        super.setSize(WIDTH, HEIGHT);
//        Toolkit kit = Toolkit.getDefaultToolkit();
//        Dimension screenSize = kit.getScreenSize();
//        int screenHeight = screenSize.height;
//        int screenWidth = screenSize.width;
//        super.setLocation(screenWidth/4, screenHeight/4);
//        super.setTitle("Photo");
//
//        add(new JPanelTest());
////        pack();
//    }
//}
