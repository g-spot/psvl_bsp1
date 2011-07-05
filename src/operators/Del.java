package operators;

import main.Calc_Stack;

public class Del implements IOperator 
{

	@Override
	public void doAction(Calc_Stack stack) throws Exception
	{
		long n = stack.PopLong();
		if(n < 0)
			throw new Exception("cannot delete element from index lower than zero");
		
		stack.deleteElementAt(n);
	}

}
