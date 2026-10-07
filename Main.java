import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String yesNo = "no";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("DFA\n");

        NFADFA converter = new NFADFA();

        DFAinput dfa = converter.NFAtoDFA(keyboard);

        // print dfa
        String[] states = dfa.getStates();
        String[] alphabet = dfa.getAlphabet();
        String[][] transitions = dfa.getTransitions();

        System.out.println("DFA Transition Table:");

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
        System.out.printf("Initial State: %s",dfa.getInitialState());
        System.out.printf("Final States: %s",dfa.getFinalStates());


        //lookup trnasitions
        System.out.printf("Would you like to see a transition? (y/n)\n");
        yesNo = (keyboard.nextLine().equals('y')) ? "yes" : "no";

        while (yesNo.equalsIgnoreCase("yes")) {

            System.out.println("\nEnter delta to look at:\nForm: (q0q1, a)");

            String delta = keyboard.nextLine();
            String[] temp = delta.split(",");

            String state = temp[0]
                    .replace("(", "")
                    .trim();

            String symbol = temp[1]
                    .replace(")", "")
                    .trim();

            String result =dfa.getTransition(state, symbol);

            if (result != null) {
                System.out.println("(" + state + ", " + symbol + ") -> " + result);
            }

            System.out.println("\nCheck another delta?\n(Yes/No)");
            yesNo = keyboard.nextLine();
        }
        keyboard.close();
    }
}