package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;
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
		Calc_Stack stack = new Calc_Stack(args);  
		Parser parser = new Parser(stack);
		
		while(true)
		{
			//Gibt das nächste zu verarbeitende Elemet vom Stack zurück
			String c = getNextElement(stack); 
			
			//Check was das Element ist
	        if (isNumber(c))
	        {
	            parser.handleNumber(c);
	        }
	        else if (c.toLowerCase() == "q")
	        {
	            break;
	        }
	        else if (c.toLowerCase() == " ")
	        {
	            parser.handleDelimiter(c);
	        }
	        //else if (c.toLowerCase() == "")
	        //{
	        //    context.HandleQuote();
	        //}
	        else
	        {
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
		}
		
	}

	private static String getNextElement(Calc_Stack stack) 
	{
		// TODO Auto-generated method stub
		
		try 
		{
			return stack.Pop();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			return "q";
		}
	}

	private static void generate_Prompt(Calc_Stack stack)
	{
		
		System.out.println("\n " + stack.toString() + " #");
	}
	
    public static Boolean IsBraced(String exp)
    {
		return exp.startsWith("[") && exp.endsWith("]");
    }

    public static String getInnerExpression(String exp) throws Exception
    {
        if (!IsBraced(exp))
        {
            throw new Exception("this is not an expression");
        }

        return exp.substring(1, exp.length() - 2); //Klammern wegschneiden
    }
    
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


