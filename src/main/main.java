package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class main
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(5);
		stack.push(7);
		stack.push(9);
		stack.push(11);
		stack.pop(); // fuck the world i hate my self and want to die and drive to hell and never ever come back again because it's nice and warm down there und i'll never have to freeze of my ass again
		System.out.println(stack.toString());
		String fuck = null;
		/*try
		{
			fuck = in.readLine();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("ist mir egal ftw - uga aga: " + fuck);
	}

}
