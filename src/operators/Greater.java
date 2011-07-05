package operators;

import main.Calc_Stack;

public class Greater implements IOperator {

	@Override
	public void doAction(Calc_Stack stack) throws Exception
	{
        long arg1 = stack.PopLong();
        long arg2 = stack.PopLong();

        if(arg2 > arg1)
        	stack.Push("0");
        else
        	stack.Push("1");
	}

}
