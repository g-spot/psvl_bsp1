package main;

public class Calc_Stack 
{
        private String[] exp;

        public int StackPointer;

		public boolean lastWasNumber;

        public Calc_Stack(String[] exp)
        {
            this.exp = exp;
        }

        public void Push(String s)
        {
            StackPointer++;
            exp[StackPointer] = s;
        }

        public void Push(Long s)
        {
            StackPointer++;
            exp[StackPointer] = s.toString();
        }

        public String Pop() throws Exception
        {
            if (StackPointer < 1)
                throw new Exception("no element in stack");

            String s = exp[StackPointer];

            StackPointer--;

            return s;
        }

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

        public boolean NextIsExpression()
        {
            String value = exp[StackPointer];

            return main.IsBraced(value);
        }

        public String PopExpression() throws Exception
        {
            if (!NextIsExpression())
            {
                throw new Exception("cannot pop number as expression");
            }

            return (main.getInnerExpression(Pop()));
        }
        
        public String toString()
        {
        	String trace = new String();
        	
        	for(String s: exp)
        	{
        		trace = trace.concat(s + " ");
        	}
			return trace + "# ";
        }
}
