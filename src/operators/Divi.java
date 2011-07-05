package operators;

import main.Calc_Stack;

public class Divi implements IOperator 
{

	@Override
	public void doAction(Calc_Stack stack) throws Exception
	{
        long arg1 = stack.PopLong();
        long arg2 = stack.PopLong();

        if(arg1 == 0)
        	throw new Exception("divide by zero");
        
        stack.Push(arg2 / arg1);
	}

} 
