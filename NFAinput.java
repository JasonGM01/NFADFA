import java.util.*;

//Will make  more comments soon
public class NFAinput {
	private String[][] transitions;
	private String[] states;
	private String[] alphabet;
	private String initialState;
	private Set<String> finalStates;
	// Field setup for a typical DFA input

	public NFAinput(String[] states, String[] alphabet, String initialState, Set<String> finalStates) {
		// Constructor takes in the state and input of the DFA and sets it equal to our
		// current fields
		this.states = states;
		this.alphabet = alphabet;
		this.initialState = initialState;
		this.finalStates = finalStates;
		transitions = new String[states.length][alphabet.length + 1];

		// A new object string called transitions set equal to the row and column

		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < transitions[i].length; j++) {
				transitions[i][j] = ""; // Transitions is set empty for the matrix
			}
		}
	}

	public void addTransition(String state, String symbol, Set<String> targets) {
		int row = -1;
		int column = -1;
		// Set for failure if it equals -1

		for (int i = 0; i < states.length; i++) {
			if (states[i].equals(state)) {
				row = i;
				break;
			}
		}

		// Compareds the current value of states too the recieved value of state
		// Then sets i equal too row and stops the loop

		if (symbol.equals("<lambda>")) {
			column = alphabet.length;
		} // Lambda will be used for the empty set
		else {
			for (int j = 0; j < alphabet.length; j++) {
				if (alphabet[j].equals(symbol)) {
					column = j;
					break;
				}

			}
		}

		if (row == -1 || column == -1) {
			System.out.printf("Invalid input");
		} else {
			transitions[row][column] = String.join(",", targets);
		}
	}

	public Set<String> move(Set<String> states, String symbol) {
		Set<String> result = new HashSet<>();
		int column = -1;

		for (int i = 0; i < alphabet.length; i++) {
			if (alphabet[i].equals(symbol)) {
				column = i;
				break;
			}

		}
		// Similar too above compares the current alphabet value to that of symbol
		// Sets column equal too that current value then stops

		if (column == -1) {
			return result;
		}

		for (String state : states) {
			int row = -1;

			for (int i = 0; i < this.states.length; i++) {
				if (this.states[i].equals(state)) {
					row = i;
					break;
				}
			}

			if (row != -1 && !transitions[row][column].isEmpty()) {
				String[] targets = transitions[row][column].split(",");

				for (int i = 0; i < targets.length; i++) {

					result.add(targets[i].trim());

				}
			}

		}

		return result;
	}

	public Set<String> lambdaClosure(Set<String> states) {
		Set<String> closure = new HashSet<>(states);
		List<String> worklist = new ArrayList<>(states);

		int lambdaColumn = alphabet.length;

		while (!worklist.isEmpty()) {
			String currentState = worklist.remove(worklist.size() - 1);

			int row = -1;

			for (int i = 0; i < this.states.length; i++) {
				if (this.states[i].equals(currentState)) {
					row = i;
					break;
				}
			}

			if (row != -1 && !transitions[row][lambdaColumn].isEmpty()) {
				String[] targets = transitions[row][lambdaColumn].split(",");
				for (String target : targets) {
					String trimmedTarget = target.trim();

					if (closure.add(trimmedTarget)) {
						worklist.add(trimmedTarget);
					}
				}

			}

		}

		return closure;

	}

	public boolean containsFinal(Set<String> state) {

		for (String s : state) {
			if (finalStates.contains(s)) {
				return true;
			}

		}

		return false;
	}

	public String[] getAlphabet() {
		return alphabet;
	}

	public String getInitialState() {
		return initialState;
	}

	public Set<String> getFinalStates() {
		return finalStates;
	}

}
