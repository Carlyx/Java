
import javax.swing.*;
import java.lang.*;

public class Palindrome {
	public static void main(String[] args)
	{
		JOptionPane pane = new JOptionPane();
		String input = pane.showInputDialog("请输入字符串:");
//		System.out.println(input);
		checkPanlidrome check = new checkPanlidrome();
		String inputReverse = check.reverse(input);
//		System.out.println(inputReverse);
		if(input.equals(inputReverse))
		{
			pane.showMessageDialog(pane, input+"是回文");
		}
		else
		{
			pane.showMessageDialog(pane, input+"不是回文");
		}
	}
}


class checkPanlidrome
{
	public static String reverse(String str)
	{
		char[] chars = str.toCharArray();
		String reverse = "";
		for(int i = chars.length-1 ; i>=0 ;i--)
		{
			reverse += chars[i];
		}
		return reverse;
	}
	public checkPanlidrome()
	{
		
	}
}
