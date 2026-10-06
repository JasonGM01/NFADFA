import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
public class NFAinput
{
	private String[][] transitions;
	private String[]states;
	private String[] alphabet;
	private String initialState;
	private Set<String> finalStates;


	public NFAinput(String[]states, String []alphabet, String initialState,Set<String> finalStates)
	{
		this.states = states;
		this.alphabet = alphabet;
		this.initialState = initialState;
		this.finalStates = finalStates;
		transitions = new String [states.length][alphabet.length + 1];

		for(int i = 0; i < states.length; i++)
		{
			for (int j = 0; j < transitions[i].length; j++)
			{
				transitions[i][j] = "";
			}
		}
	}

	public void addTransition(String state, String symbol, Set<String> targets)
	{
		int row = -1;
		int column = -1;
	
		for(int i = 0; i < states.length; i++)
		{
			if(states[i].equals(state))
			{
				row = i;
				break;
			}
		}

		if(symbol.equals("<lambda>"))
		{
			column = alphabet.length;
		}
		else
		{
			for(int j = 0; j < alphabet.length; j++)
			{
				if(alphabet[j].equals(symbol))
					{
						column = j;
						break;
					}		

			}
		}	
	
			if(row ==-1 || column == -1)
			{
				System.out.printf("Invalid input");
			}
			else
			{
				transitions[row][column] = String.join(",", targets);
			}	
	}

	public Set<String> move(Set<String> states, String symbol)
	{
		Set<String> result = new HashSet<>();
		int column =-1;

		for(int i = 0; i < alphabet.length; i++)
		{
			if(alphabet[i].equals(symbol))
			{
				column = i;
				break;
			}

		}
		

		if(column ==-1)
		{
			return result;
		}

		for(String state : states)
		{
			int row = -1;

			for(int i = 0; i< this.states.length; i++)
			{
				if(this.states[i].equals(state))
				{
					row = i;
					break;
				}
			}
		

			if(row!=-1 && !transitions[row][column].isEmpty())
			{
				String[] targets = transitions[row][column].split(",");

				for(int i = 0; i < targets.length;i++)
				{

					result.add(targets[i].trim());

				}
			}

		}


		return result;
	}

	public Set<String> lambdaClosure(Set<String> states)
	{
		Set<String> closure = new HashSet<>(states);
		List<String> worklist = new ArrayList<>(states);
		
		int lambdaColumn = alphabet.length;

		while(!worklist.isEmpty())
		{
			String currentState = worklist.remove(worklist.size() - 1);

			int row = -1;
			
			for(int i  = 0; i < this.states.length; i++)
			{
				if(this.states[i].equals(currentState))
				{
					row = i;
					break;
				}
			}
		

			if(row!= - 1 && !transitions[row][lambdaColumn].isEmpty())
			{
				String[] targets = transitions[row][lambdaColumn].split(",");
				for(String target : targets)
				{
					String trimmedTarget = target.trim();

					if(closure.add(trimmedTarget))
					{
						worklist.add(trimmedTarget);
					}
				}	
		
			}	

		}

		return closure;
		
	}

	public boolean containsFinal(Set<String> state)
	{
		
		for(String s : state)
		{
			if(finalStates.contains(s))
			{
				return true;
			}
			
		
		}
		
		return false;
	}


	public String[] getAlphabet()
	{return alphabet;}

	public String getInitialState()
	{return initialState;}

	public Set<String> getFinalStates()
	{return finalStates;}
	


}
