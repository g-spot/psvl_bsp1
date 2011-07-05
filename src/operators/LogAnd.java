package operators;

import main.Calc_Stack;

public class LogAnd implements IOperator 
{

	@Override
	public void doAction(Calc_Stack stack) throws Exception
	{
        long arg1 = stack.PopLong();
        long arg2 = stack.PopLong();

        if((arg2 != 0 && arg2 != 1) || (arg1 != 0 && arg1 != 1))
        	throw new Exception("Operands are not boolean values");
        
        long erg = arg2 & arg1;
        
        stack.Push(erg);
	}

}
