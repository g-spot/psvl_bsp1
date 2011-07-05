package operators;

import main.Calc_Stack;

public class Do implements IOperator {

	@Override
	public void doAction(Calc_Stack stack) throws Exception 
	{
		stack.Push(stack.PopExpression());
		stack.lastIsCommand = true;
	}

}
