import java.util.Random;

public class ProCom{
    public static void main(String[] args){
        Bag bag = new Bag();
        Pro p = new Pro(bag);
        Com c = new Com(bag);
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);
        t1 = new Thread(p);
        t2 = new Thread(c);
        t1.start();
        t2.start();
    }
}


class Bag{
    private static int id;
    private static int ip;
    private StringBuilder[] arr;
    public Bag(){
        id = 0;
        ip=-1;
        arr = new StringBuilder[10];
    }
    public synchronized void push(String a){
        try {
            if(id==10){
                System.out.println("缓冲池已满");
                wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        notify();
        System.out.println("生产者加入"+a);
        arr[id++] = new StringBuilder(a);
    }
    public synchronized void pop(){
        try {
            if(ip==id || ip==10){
                System.out.println("缓冲池为空");
                wait();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        notify();
        System.out.println("消费者消费"+arr[++ip].reverse());
    }
}

class Pro implements Runnable{
    private Bag b;
    public Pro(Bag b){
        this.b = b;
    }
    public void run(){
        for(int i=0;i<10;i++){
            try{
                Thread.sleep(1000);
                String a = getRandomString2(3);
                b.push(a);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public static String getRandomString2(int length){
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(3);
            long result=0;
            switch(number){
                case 0:
                    result=Math.round(Math.random()*25+65);
                    sb.append(String.valueOf((char)result));
                    break;
                case 1:
                    result=Math.round(Math.random()*25+97);
                    sb.append(String.valueOf((char)result));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }


        }
        return sb.toString();
    }
}

class Com implements Runnable{
    private Bag b;
    public Com(Bag b){
        this.b = b;
    }
    public void run(){
        for(int i=0;i<10;i++){
            try {
                Thread.sleep(1500);
                b.pop();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}