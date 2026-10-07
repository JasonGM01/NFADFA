/*
Team 1:
Members:
1. Jason Gonzalez Molina
2. Alonso Benoun
3. Ethan Harris
4. Fernando Gomez

Description:
This project will convert an nfa to a dfa.
input nfa can be stored as a matrix where columns correspond to input symbols
and rows correspond to states.
matrix cells are subsets of states representing transitions for 
corresponding states and symbols.
Returns matrix representing dfa. each cell must be a single state

TO DO:
1. Create main file for testing purposes
   > done

2. create data structures that will store dfa and nfa
   > done

3. create NFA
   > done

4. create DFA
   > done

5. fix logic based on parameters and needs
   > done

6. confirm output formatting
   > done

**Parser idea was good but will continue to a different path based on new
understanding**

sample inputs:
1. 
input nfa: 
(q0, a) = {q0, q1}
(q1, b) = {q1, q2}
(q2, a) = {q2}
with initial q0 and final q1

> input essentially:
(q0, a) --> q0q1

2.
input nfa:
(q0, a) = {q0, q1}
(q1, b) = {q1, q2}
(q2, a) = {q2}
(q0, lambda) = {q2}
with initial q0 and final q1
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class NFADFA {
    public static String[][] NFA(Scanner keyboard) {
        HashMap<String, Integer> symbolMap = new HashMap<>();
        int delta;
        String deltaInput;
        String[] NFADelta;
        String[] NFAInput;
        String NFAState;
        String NFASymbol;
        String DFAState;

        // populate hashmap for lookup
        symbolMap.put("a", 0);
        symbolMap.put("b", 1);
        symbolMap.put("lambda", 2);

        // Scanner keyboard = new Scanner(System.in);

        // accept number deltas
        System.out.printf("Input number of deltas:\n");
        delta = keyboard.nextInt();
        keyboard.nextLine();

        // create array using delta as parameter
        // [state][symbol]
        String[][] nfa = new String[delta][3];
        String[][] dfa = new String[delta][3];

        // accept string input
        // 17 char total for string with 2 states output
        // 12 for single state
        for (int i = 0; i < delta; i++) {
            System.out.printf("Input NFA to convert\nForm: '(q0, a) = {q0, q1}'\n");

            // grabs delta
            deltaInput = keyboard.nextLine();

            // split delta to state and symbol
            // nfa side
            NFADelta = deltaInput.split("=");
            NFAInput = NFADelta[0].split(",");

            // dfa side
            if (NFADelta[1].contains(",")) {
                DFAState = NFADelta[1]
                        .replace("{", "")
                        .replace("}", "")
                        .replace(", ", "")
                        .trim();
            } else {
                DFAState = NFADelta[1]
                        .replace("{", "")
                        .replace("}", "")
                        .trim();
            }

            // store state, symbol, destination
            NFAState = NFAInput[0].replace("(", "").trim();
            NFASymbol = NFAInput[1].replace(")", "").trim();
            int row = Integer.parseInt(NFAState.substring(1));
            int col = symbolMap.get(NFASymbol);
            nfa[row][col] = NFADelta[1].trim();
            dfa[row][col] = DFAState;
        }

        // trash state for dfa
        for (int i = 0; i < delta; i++) {
            for (int j = 0; j < 3; j++) {
                if (dfa[i][j] == null) {
                    dfa[i][j] = "trash";
                }
            }
        }

        // close keyboard
        // keyboard.close();

        return dfa;
    }
}
