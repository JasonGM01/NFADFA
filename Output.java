import java.io.*;
import java.util.Set;

public class Output {
    public static void outputWrite(String[] states, String[] alphabet, String[][] transitions, String initialState,
            Set<String> finalStates) throws FileNotFoundException {
        try (PrintWriter output = new PrintWriter("dfa-output.txt");) {

            // print dfa
            output.printf("DFA Transition Table:\n");

            // Print header
            output.printf("%-15s", "State");
            for (String symbol : alphabet) {
                output.printf("%-15s", symbol);
            }
            output.printf("\n");

            // Print each state and its transitions
            for (int i = 0; i < states.length; i++) {
                output.printf("%-15s", states[i]);

                for (int j = 0; j < alphabet.length; j++) {
                    output.printf("%-15s", transitions[i][j]);
                }
                output.printf("\n");
            }

            // Print initial/final states
            output.printf("Initial State: %s\n", initialState);
            output.printf("Final States reached: %s\n", finalStates);

            output.close();
        }
    }
}