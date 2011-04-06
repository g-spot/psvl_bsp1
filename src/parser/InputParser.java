package parser;

import java.util.ArrayList;

public class InputParser 
{	
	public ArrayList<String> parse(String input)
	{
		ArrayList<String> result = new ArrayList<String>();
		int braceCount = 0;
		int bracePos = 0;
		
		for(int i=0; i<input.length(); i++)
		{
			if(braceCount == 0)
			{
				switch(input.charAt(i))
				{
					case 'a':
							result.add("a");
						break;
					case 'c':
							result.add("e");
						break;
					case 'd':
							result.add("d");
						break;
					case 'q':
							System.exit(0); 
						break;
					case ' ':
							//Do Nothing FTW
						break;
					case '[':
							braceCount = 1;
							bracePos = i;
						break;
					case ']':
							if(braceCount == 0)
								//FAIL
						break;
				}
			}
			else
			{
				if(input.charAt(i)=='[')
					braceCount++;
				else
					if(input.charAt(i)==']')
						braceCount--;
				if(braceCount == 0)
				{
					result.add(input.substring(bracePos, i));
				}
			}
				
		}
		
		return result;
	}
}
