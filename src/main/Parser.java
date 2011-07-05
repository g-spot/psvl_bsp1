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
	
	//HashMap die alle gültigen Operatoren beinhaltet mit den jeweiligen Klassen die die Funktionalität beinhalten
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

	/*
	 * Verarbeitet Nummern
	 */
	public void handleNumber(String c)
	{
		if(stack.lastWasNumber) //Falls die letzte Eingabe eine Ziffer war
		{
			long upper,lower, number;
			try
			{
				//Füge die letzte und die neue zu einer Zahl zusammen
				upper = stack.PopLong();
				lower = Long.parseLong(c);
				
				//Berechne die neue Zahl
				upper = upper *10;
				number = upper + lower;
				
				//Gib die neue Zahl auf den Stack
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
		stack.lastWasNumber = true; //Das letzte Element war eine Zahl
	}

	/* 
	 * Leerzeichen ignorieren
	 * */
	public void handleDelimiter(String c) 
	{
		// do nothing?
		stack.lastWasNumber = false;
	}

	/*
	 * Führt für einen Operator die entsprechende Aktion aus
	 */
	public void handleOperator(String c) throws Exception 
	{
		if(!operators.containsKey(c)) //Prüfung ob der Operator gültig ist
		{
			if(!c.startsWith(" ")) //Prüfung ob es ein Leerzeichen ist, wenn nicht => Error
				throw new Exception("Unkown operator");
			else
				stack.lastWasNumber = false;
		}
		else
		{
			//Holt für den Operator die entsprechende Klasse
			IOperator op;
			op = operators.get(c);
			op.doAction(stack); //Führt die Aktion auf den Stack aus
		}
		stack.lastWasNumber = false;
	}

	/*
	 * Kümmert sich um die Behandlung von Klammern in Klammern
	 */
	public String handleBrace(String input)
	{	
		int braceCount = 0; //Zählt die offenen Klammern
		int endIndex = 0; //enthält nach der Schleiche den Index der schließenden Klammer
		
		for(int i = 0; i < input.length(); i++ )
		{
			if(input.charAt(i) == '[')
				braceCount = braceCount + 1;
			if(input.charAt(i) == ']')
				braceCount = braceCount - 1;
			
			if(braceCount == 0)
			{
				endIndex = i;
				break;
			}
		}
		
		//Gibt die Expression auf den Stack
		stack.Push(input.subSequence(0, endIndex+1 ).toString() );
		
		if(main.DEGUB)
		{
			System.out.println("DEBUG: braces pushed: " + input.subSequence(0, endIndex+1 ).toString());
			System.out.println("DEBUG: input returned: " + input.substring(endIndex).toString());
		}
		stack.lastWasNumber = false;
		
		//Gibt die Eingabefolge ohne die Expression zurück
		return input.substring(endIndex+1).toString() ;
	}
}