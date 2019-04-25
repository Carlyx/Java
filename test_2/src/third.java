import javax.swing.*;
import javax.swing.plaf.synth.SynthEditorPaneUI;

public class third {
    public static void main(String[] args){
        Employee staff1 = new Employee("Carl", "2016014414");
        staff1.output();

        staff1.setName("nike");
        System.out.println("修改名字后:");
        staff1.output();
        System.out.println();
        System.out.println("新建员工信息：");
        SalariedEmplyee staff2 = new SalariedEmplyee("john","2016014415",6000);
        staff2.output();
        Object[] possibleValues = { "姓名", "学号", "月薪" };
        Object selectedValue = JOptionPane.showInputDialog(null,
                        "原始信息 \n 姓名：John \n 学号：2016014415 \n 月薪：6000 \n 请选择要修改的内容", "选择",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
//        System.out.println("***********************************");
//        System.out.println(selectedValue);
        JOptionPane pane = new JOptionPane();
        String input = pane.showInputDialog("请输入修改后的内容:");
        if(selectedValue.equals("姓名")){
            staff2.setName(input);
        }
        else if(selectedValue.equals("学号")){
            staff2.setId(input);
        }
        else{
            staff2.setSalaryMonth(Integer.parseInt(input));
        }
        pane.showMessageDialog(pane,"姓名："+staff2.getName()+"\n学号："+staff2.getId()+"\n月薪: "+staff2.getSalaryMonth());
        staff2.setSalaryMonth(8000);
        System.out.println("修改月工资后：");
        staff2.output();

        HourlyEmployee staff3 = new HourlyEmployee("mike", "2016014416", 50, 100);
        staff3.output();
        staff3.setHour(200);
        System.out.println("修改工作小时后的信息:");
        staff3.output();

        CommissionEmployee staff4 = new CommissionEmployee("alice", "2016014417", 0.2, 3000);
        staff4.output();
        staff4.setCommission(0.25);
        System.out.println("修改提成后的信息：");
        staff4.output();
    }
}

class Employee{
    private String name;
    private String id;

    public Employee(String aname , String aid){
        this.name = aname;
        this.id = aid;
    }
    public void setName(String aname){
        this.name = aname;
    }
    public void setId(String aid){
        this.id = aid;
    }
    public String getName(){
        return this.name;
    }
    public String getId(){
        return this.id;
    }
    public void output(){
        System.out.println("姓名：" + getName() + "     " + "身份证："+getId());
    }
}

class SalariedEmplyee extends Employee{
    private int salaryMonth;

    public SalariedEmplyee(String aname,String aid,int asalaryMonth){
        super(aname,aid);
        this.salaryMonth = asalaryMonth;
    }
    public SalariedEmplyee(String aname, String aid){
        super(aname, aid);
    }
    public void setName(String aname){
        super.setName(aname);
    }
    public void setSalaryMonth(int asalaryMonth){
        this.salaryMonth = asalaryMonth;
    }
    public int getSalaryMonth(){
        return this.salaryMonth;
    }
    public void output(){
        super.output();
        System.out.println("月基本工资："+this.getSalaryMonth());
        System.out.println();
    }
}

class HourlyEmployee extends Employee{
        private int salaryHour;
        private int hour;

        public HourlyEmployee(String aname, String aid, int asalaryHour, int ahour){
            super(aname, aid);
            this.salaryHour = asalaryHour;
            this.hour = ahour;
         }
         public HourlyEmployee(String aname,String aid){
            super(aname,aid);
         }
         public void setSalaryHour(int asalaryHour){
            this.salaryHour = asalaryHour;
         }
         public void setHour(int ahour){
            this.hour = ahour;
         }
         public int getSalaryHour(){
            return this.salaryHour;
         }
         public int getHour(){
            return this.hour;
         }
        public void output(){
            super.output();
            System.out.println("每小时工资：" + getSalaryHour() + " " + "已工作"+getHour()+"小时"+" "+"共获得"+hour*salaryHour+"￥");
            System.out.println();
        }
}

class CommissionEmployee extends Employee{
    private double commission;
    private int amount;
    public CommissionEmployee(String aname, String aid, double acommission, int aamount){
        super(aname, aid);
        this.commission = acommission;
        this.amount = aamount;
    }
    public CommissionEmployee(String aname, String aid){
        super(aname,aid);
    }
    public void setCommission(double acommision){
        this.commission = acommision;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public double getCommission(){
        return this.commission;
    }
    public int getAmount(){
        return this.amount;
    }
    public void output(){
        super.output();
        System.out.println("提成比例："+getCommission() + " " + "销售总额："+getAmount()+"\n"+"共获得："+commission*amount+"￥");
        System.out.println();
    }
}
