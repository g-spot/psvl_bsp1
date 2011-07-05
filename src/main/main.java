package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Stack;



public class main
{
	static boolean DEGUB = false;
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input; //Zu verarbeitende Anweisungen für den Taschenrechner
		Calc_Stack stack; //Der Stack auf dem sich die zukünftigen Operanden befinden		
		Parser parser; //Der parser der sich um die Interpätation der Befehle kümmert
		
		try
		{
			//Einlesen der Befehlszeile
			input = in.readLine();
			
		} catch (IOException e1)
		{
			// Error => Quit
			e1.printStackTrace();
			input = "q";
		}
		
		//Erzeugen der Klassen
		stack = new Calc_Stack();
		parser =  new Parser(stack); //Initialisiert parser mit der Referenz des Stacks
		
		String c = "";
		
		while(true) //Endlos arbeiten bis q oder nur mehr eine Zahl auf dem Stack steht => das Endergebnis
		{
			//Gibt das nächste zu verarbeitende Elemet vom Stack zurück
			
			if(input.isEmpty() || stack.lastIsCommand) 
			//Ist noch etwas auf der Eingabefolge oder wurde gerade etwas zur Verarbeitung gepushed?
			{
				
				if(stack.getStackPointer() != 0)//Prüft ob noch Elemente auf dem Stack verfügbar sind
				{
					c = getNextElement(stack); //Holt das nächste Element
					
					if(!isNumber(c)) //Wenn es keine Nummer ist, verarbeite es
					{
						input = c.concat(" "+input); //Das derzeitige
						c = input.substring(0, 1); //Hole das erste Zeichen bzw. den Operator oder die Klammer
						input = input.substring(1); //Entferne das eben geholte Zeichen aus der Eingabefolge
						stack.lastIsCommand = false; //das zuletzt verwendete Commando ist nun verarbeitet
					}
					else
					    break; //Endergebnis
				}
				else
					break;
			}
			else
			{
				//Es gibt noch Eingaben
				
				c = input.substring(0, 1); //Hole das erste Zeichen bzw. den Operator oder die Klammer
				if(!c.startsWith("[")) //Ist es eine Expression?
				{
					//Wenn es keine Expression ist, entferne den Operator bzw. die Zahl aus der Eingabefolge
					input = input.substring(1); 
				}
			}
			
			if(input.isEmpty() && stack.getStackPointer() == 0) //Nichts mehr zu tun
				break;
			
			if(DEGUB)
				System.out.println("DEBUG: c = " + c);
			if(DEGUB)
				System.out.println("DEBUG: input = " + input);
			
			//Check was das Element ist
	        if (isNumber(c))
	        {
	        	//Eine Ziffer
	            parser.handleNumber(c);
	        }
	        else if (c.toLowerCase() == "q")
	        {
	        	//Ende des Programms
	            break;
	        }
	        else if (c.startsWith("["))
	        {
	        	//Eine Expression
	        	input = parser.handleBrace(input);
	        }
	        else
	        {
	        	//Ein Operator oder etwas ungültiges
	        	try
				{
					parser.handleOperator(c);
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
	        }
	        
	        //Ausgabe des Stacktrace
	        System.out.println(stack.toString()  + " # " + input);
		}
		
		//Ausgabe des Endergebnisses
		System.out.println(stack.toString()  + " # " + c);
	}

	/* 
	 * Holt das nächste Element aus dem Stack
	 * */
	private static String getNextElement(Calc_Stack stack) 
	{
		try 
		{
			String elem = stack.Pop();
			
			if(IsBraced(elem)) //Falls es eine Expression ist
			{
				//Zerlege die Expression in Befehele
				elem = getInnerExpression(elem);
			}
			
			return elem;
		} catch (Exception e) 
		{
			e.printStackTrace();
			return "q";
		}
	}
	
	/*
	 * Prüffunktion ob es eine Expression ist
	 */
    public static Boolean IsBraced(String exp)
    {
		return exp.startsWith("[") && exp.endsWith("]");
    }

    /*
     * Entfernt die Klammern der Expression
     */
    public static String getInnerExpression(String exp) throws Exception
    {
        if (!IsBraced(exp))
        {
            throw new Exception("this is not an expression");
        }

        return exp.substring(1, exp.length() - 1); //Klammern wegschneiden
    }
    
    /*
     * Prüft ob der String eine Ziffer oder Zahl ist
     */
    public static boolean isNumber(String num)
    {
        try
        {
            Long.parseLong(num);
        } catch(NumberFormatException nfe) 
        {
            return false;
        }
        return true;
    }
}


