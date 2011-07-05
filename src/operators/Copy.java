package operators;

import main.Calc_Stack;

public class Copy implements IOperator {

	@Override
	public void doAction(Calc_Stack stack) throws Exception  
	{	
		long n = stack.PopLong();
		if(n < 0)
			throw new Exception("cannot copy element from index lower than zero");
		
		stack.Push(stack.getElementAt(n));
	}

}
