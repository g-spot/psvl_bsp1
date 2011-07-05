package operators;

import main.Calc_Stack;

public class Minus implements IOperator 
{

	@Override
	public void doAction(Calc_Stack stack) throws Exception
	{
        long arg1 = stack.PopLong();
        long arg2 = stack.PopLong();

        stack.Push(arg2 - arg1);
	}

}
