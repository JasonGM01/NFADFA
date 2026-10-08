import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String yesNo = "yes";
        Scanner keyboard = new Scanner(System.in);
        NFADFA converter = new NFADFA();
        DFAinput dfa = converter.NFAtoDFA(keyboard);
        String[] states = dfa.getStates();
        String[] alphabet = dfa.getAlphabet();
        String[][] transitions = dfa.getTransitions();
        
        // print dfa
        System.out.printf("\nDFA Transition Table:\n");

        // Print header
        System.out.printf("%-15s", "State");
        for (String symbol : alphabet) {
            System.out.printf("%-15s", symbol);
        }
        System.out.printf("\n");

        // Print each state and its transitions
        for (int i = 0; i < states.length; i++) {
            System.out.printf("%-15s", states[i]);

            for (int j = 0; j < alphabet.length; j++) {
                System.out.printf("%-15s", transitions[i][j]);
            }
            System.out.printf("\n");
        }

        // Print initial/final states
        System.out.printf("Initial State: %s\n",dfa.getInitialState());
        System.out.printf("Final States reached: %s\n",dfa.getFinalStates());

        //lookup transitions
        System.out.printf("Would you like to see a transition? (y/n)\n");
        yesNo = (keyboard.nextLine().equals("y")) ? "yes" : "no";

        while (yesNo.equalsIgnoreCase("yes")) {
            System.out.printf("\nEnter delta to look at:\nForm: (q0q1, a)");
            String delta = keyboard.nextLine();
            String[] temp = delta.split(",");

            String state = temp[0].replace("(", "").trim();
            String symbol = temp[1].replace(")", "").trim();
            String result = dfa.getTransition(state, symbol);

            if (result != null) {
                System.out.printf("(%s, %s) -> %s\n", state, symbol, result);
            }

            System.out.printf("Check another delta? (Yes/No)\n");
            yesNo = (keyboard.nextLine().equals("y")) ? "yes" : "no";
        }
        keyboard.close();

        Output.outputWrite(states, alphabet, transitions, dfa.getInitialState(), dfa.getFinalStates());
    }
}