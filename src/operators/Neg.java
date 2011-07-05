package operators;

import main.Calc_Stack;

public class Neg implements IOperator 
{

	@Override
	public void doAction(Calc_Stack stack) throws Exception
	{
        long arg = stack.PopLong();

        stack.Push(arg*(-1));
	}

}
