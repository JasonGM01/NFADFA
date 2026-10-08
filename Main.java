import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String yesNo = "yes";
        Scanner keyboard = new Scanner(System.in);
        System.out.printf("\nDFA\n");
        String[][] dfa = NFADFA.NFA(keyboard);
        int rowLength = dfa.length; 
        int colLength = dfa[0].length;

        try(PrintWriter output = new PrintWriter("dfa-output.txt")){
            output.printf("\nNew DFA transition matrix:\n");
            String[] symbols = {"a", "b", "lambda"};
            output.printf("%10s", "State");
            for (String symbol : symbols) {
                output.printf("%10s", symbol);
            }
            output.println();

            for (int i = 0; i < rowLength; i++) {
                output.printf("%10s", "q" + i);
                for (int j = 0; j < colLength; j++) {
                    output.printf("%10s", dfa[i][j]);
                }
                output.println();
            }
        }

        System.out.printf("\nEnter delta to look at:\nForm: (q0, a)\n");
        String delta = keyboard.nextLine();
        String[] temp = delta.split(",");
        String temp1 = temp[0].replace("(","");
        String temp2 = temp[1].replace(")","").trim();
        int state = Integer.parseInt(temp1.substring(1));
        String symbol = temp2;
        while(yesNo.equals("yes")){
            System.out.printf("%s\n", dfa[state][symbolMap.get(symbol)]);
            System.out.printf("Check another delta?\n(Yes/No)\n");
            yesNo = keyboard.nextLine().equals("yes") ? "yes" : "no";
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
