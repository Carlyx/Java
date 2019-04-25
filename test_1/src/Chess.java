
import javax.swing.*;
import java.lang.*;

public class Chess {
	public static void main(String[] args) throws InterruptedException
	{
		JOptionPane pane = new JOptionPane();
		Chip chip = new Chip(3,3);
		String input = pane.showInputDialog(
				"电脑执 ' * '\n"+
				"----------------------------------------\n"+
				"|    "+"   "+chip.getLoc(1)+"   "+"    |    "+"   "+chip.getLoc(2)+"   "+"    |    "+"   "+chip.getLoc(3)+"   "+"    |\n"+
				"----------------------------------------\n"+
				"|    "+"   "+chip.getLoc(4)+"   "+"    |    "+"   "+chip.getLoc(5)+"   "+"    |    "+"   "+chip.getLoc(6)+"   "+"    |\n"+
				"----------------------------------------\n"+
				"|    "+"   "+chip.getLoc(7)+"   "+"    |    "+"   "+chip.getLoc(8)+"   "+"    |    "+"   "+chip.getLoc(9)+"   "+"    |\n"+
				"----------------------------------------\n"+
				"请输入位置:\n"
				);
//		chip.output1();
//		System.out.println(input);
		while(true)
		{
			if(input.equals("1") || input.equals("2") || 
			   input.equals("3") || input.equals("4") || 
			   input.equals("5") || input.equals("6") || 
			   input.equals("7") || input.equals("8") || 
			   input.equals("9"))
			{
				int loc = Integer.parseInt(input); 
//						System.out.println(loc);
				if(chip.getLoc(loc)=="O" || chip.getLoc(loc)=="*")
				{
					pane.showMessageDialog(pane, "此位置已有棋子");
				}
				else
				{
					chip.setLoc(loc,"O");
//					Thread.sleep(500);
					// 判断胜负
					int res = chip.judgeResult("O");
					if(res==1)
					{
						pane.showMessageDialog(pane,
							"电脑执 ' * '\n"+
							"----------------------------------------\n"+
							"|    "+"   "+chip.getLoc(1)+"   "+"    |    "+"   "+chip.getLoc(2)+"   "+"    |    "+"   "+chip.getLoc(3)+"   "+"    |\n"+
							"----------------------------------------\n"+
							"|    "+"   "+chip.getLoc(4)+"   "+"    |    "+"   "+chip.getLoc(5)+"   "+"    |    "+"   "+chip.getLoc(6)+"   "+"    |\n"+
							"----------------------------------------\n"+
							"|    "+"   "+chip.getLoc(7)+"   "+"    |    "+"   "+chip.getLoc(8)+"   "+"    |    "+"   "+chip.getLoc(9)+"   "+"    |\n"+
							"----------------------------------------\n"+
							"恭喜你赢了！\n"
							);
						break;
					}
					else
					{
						if(chip.judgeDraw()==1)
						{
							pane.showMessageDialog(pane,
								"电脑执 ' * '\n"+
								"----------------------------------------\n"+
								"|    "+"   "+chip.getLoc(1)+"   "+"    |    "+"   "+chip.getLoc(2)+"   "+"    |    "+"   "+chip.getLoc(3)+"   "+"    |\n"+
								"----------------------------------------\n"+
								"|    "+"   "+chip.getLoc(4)+"   "+"    |    "+"   "+chip.getLoc(5)+"   "+"    |    "+"   "+chip.getLoc(6)+"   "+"    |\n"+
								"----------------------------------------\n"+
								"|    "+"   "+chip.getLoc(7)+"   "+"    |    "+"   "+chip.getLoc(8)+"   "+"    |    "+"   "+chip.getLoc(9)+"   "+"    |\n"+
								"----------------------------------------\n"+
								"加油，平局！\n"
								);
							break;
						}
						while(true)
						{
							int computerLoc = (int) (1+Math.random()*(9));
							if(chip.getLoc(computerLoc)=="O" || chip.getLoc(computerLoc)=="*" || computerLoc<1 || computerLoc>9)
							{
								continue;
							}
							else
							{
								chip.setLoc(computerLoc, "*");
								break;
							}
						}
						int res1 = chip.judgeResult("*");
						if(res1 == 1)
						{
							pane.showMessageDialog(pane,
								"电脑执 ' * '\n"+
								"----------------------------------------\n"+
								"|    "+"   "+chip.getLoc(1)+"   "+"    |    "+"   "+chip.getLoc(2)+"   "+"    |    "+"   "+chip.getLoc(3)+"   "+"    |\n"+
								"----------------------------------------\n"+
								"|    "+"   "+chip.getLoc(4)+"   "+"    |    "+"   "+chip.getLoc(5)+"   "+"    |    "+"   "+chip.getLoc(6)+"   "+"    |\n"+
								"----------------------------------------\n"+
								"|    "+"   "+chip.getLoc(7)+"   "+"    |    "+"   "+chip.getLoc(8)+"   "+"    |    "+"   "+chip.getLoc(9)+"   "+"    |\n"+
								"----------------------------------------\n"+
								"很遗憾你输了！\n"
								);
							break;
						}
					}
				}
			}
			else
			{
				pane.showMessageDialog(pane, "输入有误!");
			}
			input = pane.showInputDialog(
				"电脑执 ' * '\n"+
				"----------------------------------------\n"+
				"|    "+"   "+chip.getLoc(1)+"   "+"    |    "+"   "+chip.getLoc(2)+"   "+"    |    "+"   "+chip.getLoc(3)+"   "+"    |\n"+
				"----------------------------------------\n"+
				"|    "+"   "+chip.getLoc(4)+"   "+"    |    "+"   "+chip.getLoc(5)+"   "+"    |    "+"   "+chip.getLoc(6)+"   "+"    |\n"+
				"----------------------------------------\n"+
				"|    "+"   "+chip.getLoc(7)+"   "+"    |    "+"   "+chip.getLoc(8)+"   "+"    |    "+"   "+chip.getLoc(9)+"   "+"    |\n"+
				"----------------------------------------\n"+
				"请输入位置:\n"
				);
		}
	}
}

class Chip
{
	private static String[][] arr;
	public Chip(int a, int b)
	{
		arr = new String[a][b];
		int ans=0;
		for(int i=0;i<a;i++)
		{
			for(int j=0;j<b;j++)
			{
				arr[i][j]=String.valueOf(++ans);
			}
		}
	}
	public static void output1()
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				System.out.println(arr[i][j]);
			}
		}
	}
	public static String getLoc(int loc)
	{
		return arr[(loc-1)/3][(loc+2)%3];
	}
	public static void setLoc(int loc , String str)
	{
		arr[(loc-1)/3][(loc+2)%3] = str;
	}
	public static int judgeResult(String str)
	{
		if(arr[0][0]==str && arr[0][1]==str && arr[0][2]==str ||
		   arr[1][0]==str && arr[1][1]==str && arr[1][2]==str ||
		   arr[2][0]==str && arr[2][1]==str && arr[2][2]==str ||
		   arr[0][0]==str && arr[1][0]==str && arr[2][0]==str ||
		   arr[0][1]==str && arr[1][1]==str && arr[2][1]==str ||
		   arr[0][2]==str && arr[1][2]==str && arr[2][2]==str ||
		   arr[0][0]==str && arr[1][1]==str && arr[2][2]==str ||
		   arr[0][2]==str && arr[1][1]==str && arr[2][0]==str)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	public static int judgeDraw()
	{
		if((arr[0][0]=="O" || arr[0][0]=="*") && (arr[0][1]=="O" || arr[0][1]=="*") &&
		   (arr[0][2]=="O" || arr[0][2]=="*") && (arr[1][0]=="O" || arr[1][0]=="*") &&
		   (arr[1][1]=="O" || arr[1][1]=="*") && (arr[1][2]=="O" || arr[1][2]=="*") &&
		   (arr[2][0]=="O" || arr[2][0]=="*") && (arr[2][1]=="O" || arr[2][1]=="*") &&
		   (arr[2][2]=="O" || arr[2][2]=="*"))
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}