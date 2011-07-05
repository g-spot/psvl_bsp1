package operators;

import main.Calc_Stack;

public class Equals implements IOperator {

	@Override
	public void doAction(Calc_Stack stack) throws Exception
	{
		long arg1 = 0;
		long arg2 = 0;
		
		try
		{
			arg1 = stack.PopLong();
			arg2 = stack.PopLong();
			
		}catch(Exception ex)
		{
			String str1;
			String str2;
			
			try
			{
				str1 = stack.Pop();
				str2 = stack.Pop();
				
			}catch(Exception ex2)
			{
				throw new Exception("Operands don't have the same type");
			}
			
			if(str1.compareTo(str2) == 0)
				stack.Push("0");
			else
				stack.Push("1");
		}
		
        if(arg2 == arg1)
        	stack.Push("0");
        else
        	stack.Push("1");
	}

}
