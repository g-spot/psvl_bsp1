package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

import parser.InputParser;

public class main
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Stack<String> stack = new Stack<String>();
		InputParser ip = new InputParser();
		ArrayList<String> splittedString;
		
		String next_action = "0";
		String inputString = "";
		
		while(next_action != "q")
		{
			generate_Prompt(stack);
			
			try 
			{
				inputString = in.readLine();
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			splittedString = ip.parse(inputString);
			
			generate_Prompt(stack);
			
		}
		
	}

	private static void generate_Prompt(Stack<String> stack)
	{
		
		System.out.println("\n " + stack.toString() + " #");
	}
}


