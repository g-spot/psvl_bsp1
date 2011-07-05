package main;

import java.util.HashMap;

import operators.Copy;
import operators.Del;
import operators.Divi;
import operators.Do;
import operators.Equals;
import operators.Greater;
import operators.IOperator;
import operators.LogAnd;
import operators.LogOr;
import operators.Lower;
import operators.Minus;
import operators.Mod;
import operators.Multi;
import operators.Neg;
import operators.Plus;

public class Parser 
{
	Calc_Stack stack;
	HashMap<String,IOperator> operators = new HashMap<String,IOperator>();
	
	public Parser(Calc_Stack stack)
	{
		this.stack = stack;
		
		operators.put("+", new Plus());
		operators.put("-", new Minus());
		operators.put("*", new Multi());
		operators.put("/", new Divi());
		operators.put("%", new Mod());
		operators.put("&", new LogAnd());
		operators.put("|", new LogOr());
		operators.put("=", new Equals());
		operators.put("<", new Lower());
		operators.put(">", new Greater());
		operators.put("~", new Neg());
		operators.put("c", new Copy());
		operators.put("d", new Del());
		operators.put("a", new Do());
	}

	public void handleNumber(String c)
	{
		if(stack.lastWasNumber)
		{
			long upper,lower, number;
			try
			{
				upper = stack.PopLong();
				lower = Long.parseLong(c);
				
				upper = upper *10;
				number = upper + lower;
				stack.Push(number);
				
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			stack.Push(c); //Zahl wird auf den Stack gepushed
		}
		stack.lastWasNumber = true;
	}

	public void handleDelimiter(String c) 
	{
		// do nothing?
		stack.lastWasNumber = false;
	}

	public void handleOperator(String c) throws Exception 
	{
		if(!operators.containsKey(c))
		{
			throw new Exception("Unkown operator");
		}
		else
		{
			IOperator op;
			op = operators.get("c");
			op.doAction(stack);
		}
		stack.lastWasNumber = false;
	}
}
