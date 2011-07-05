package main;

import java.util.ArrayList;

public class Calc_Stack 
{
		//Array das alle Elemente des Stacks beinhaltet
        private ArrayList<String> exp;

        //Letztes Element gepush war eine Ziffer/Zahl
		public boolean lastWasNumber;
		
		//Letztes Commando war ein "a"
		public boolean lastIsCommand;

		//Gibt die Stackgr��e zur�ck
		public int getStackPointer()
		{
			return exp.size();
		}
		
		//Initialisiert den Stack
        public Calc_Stack()
        {
        	//String aufsplitten
        	this.lastWasNumber = false;
        	this.lastIsCommand = false;
        	exp = new ArrayList<String>();
        	exp.clear();
        }
        
        //F�gt ein Element dem Stack hinzu
        public void Push(String s)
        {
        	 exp.add(s);
        }
        
        //F�gt ein Element von Typ Long dem Stack hinzu
        public void Push(Long s)
        {
            exp.add(s.toString());
        }

        //Holt ein Element vom Stack
        public String Pop() throws Exception
        {
            if (exp.size() < 1) //Falls Stack leer ist => Exception
                throw new Exception("no element in stack");

            String s = exp.get(exp.size()-1); //Element holen
            exp.remove(exp.size()-1); //Element l�schen

            return s; //Element zur�ckgeben
        }
        
        //Holt ein Element vom Stack und pr�ft ob es eine Zahl ist
        public long PopLong() throws Exception
        {
            long val;

            try
            {
                val = Long.parseLong(Pop());
            }
            catch (Exception e)
            {
                throw new Exception("cannot pop string as number");
            }
            return val;
        }

        //Pr�ft ob das n�chste Element eine Exp. ist
        public boolean NextIsExpression()
        {
            String value = exp.get(exp.size()-1);

            return main.IsBraced(value);
        }

        //Holt eine Exp. vom Stack und gibt sie in
        public String PopExpression() throws Exception
        {
            if (!NextIsExpression())
            {
                throw new Exception("cannot pop - not an expression");
            }

            return (main.getInnerExpression(Pop())); //L�scht die Klammern weg
        }
        
        //Gibt den Stackinhalt als String zur�ck
        public String toString()
        {
        	String trace = new String();
        	
        	for(String s: exp)
        	{
        		trace = trace.concat(s + " ");
        	}
			return trace;
        }

        //L�scht ein Element an der n-ten Stelle
		public void deleteElementAt(long n)
		{
			try
			{
				this.exp.remove(exp.size()-((int)n-1));
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}

		//Holt das unterste Element aus dem Stack
		public String getFirstElement()
		{
			return this.exp.get(0);
		}

		//gibt das n-te Element aus dem Stack zur�ck
		public String getElementAt(long popLong)
		{
			return exp.get(exp.size() - ((int)popLong-1));
		}
}
