import java.util.*;

public class DFAinput {
    private String[][] transitions;
    private String[] states;
    private String[] alphabet;
    private String initialState;
    private Set<String> finalStates;

    public DFAinput(String[] states, String[] alphabet, String initialState, Set<String> finalStates) {
        // Constructor takes in the state and input of the DFA and sets it equal to our
        // current fields
        this.states = states;
        this.alphabet = alphabet;
        this.initialState = initialState;
        this.finalStates = finalStates;
        transitions = new String[states.length][alphabet.length];

        // A new object string called transitions set equal to the row and column

        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < transitions[i].length; j++) {
                transitions[i][j] = ""; // Transitions is set empty for the matrix
            }
        }
    }

    public void addTransition(String state, String symbol, String target) {
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

        for (int j = 0; j < alphabet.length; j++) {
            if (alphabet[j].equals(symbol)) {
                column = j;
                break;
            }

        }

        if (row == -1 || column == -1) {
            System.out.printf("Invalid input");
        } else {
            transitions[row][column] = target;
        }
    }

    public String getTransition(String state, String symbol) {
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

        for (int j = 0; j < alphabet.length; j++) {
            if (alphabet[j].equals(symbol)) {
                column = j;
                break;
            }

        }
        if (row == -1 || column == -1) {
            System.out.printf("Invalid input");
            return null;
        } else {
            return transitions[row][column];
        }
    }

    public String[] getStates() {
        return states;
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

    public String[][] getTransitions(){
        return transitions;
    }
}
