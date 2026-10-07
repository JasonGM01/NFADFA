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

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class NFADFA {
   public static Accepter<Set<String>> NFA(Scanner keyboard) {
        int delta;
        String deltaInput;
        String[] NFADelta;
        String[] transition;
        String state;
        String symbol;
        String[] destination;
        String initialState;
        String finalState;
        String[] finalStates;
        String yesNo = "y";
        Accepter<Set<String>> nfa;
        Accepter<Set<String>> dfa;
        Set<String> states;
        Set<String> symbols;

        // accept number deltas, inital, and final(s)
        System.out.printf("Enter number of deltas:\n");
        delta = keyboard.nextInt();

        //new final states array
        finalStates = new String[delta];

        System.out.printf("Enter initial state:\nForm: '{q0}'\n");
        initialState = keyboard.nextLine();
        
        while(yesNo.equals("y")){
         int i = 0;   
         System.out.printf("Enter final state(s):\nForm: '{q0}' or '{q0, q1}'");
            finalState = keyboard.nextLine();
            finalStates[i] += finalState;
            i++;
            System.out.printf("more?(y/n)");
            yesNo = keyboard.nextLine();
            if(!yesNo.equals("y")) break;
        }
        //buffer to next line
        keyboard.nextLine();

        // accept string input
        // 17 char total for string with 2 states output
        // 13 for single state
        for (int i = 0; i < delta; i++) {
            System.out.printf("Input NFA to convert\nForm: '(q0, a) = {q0, q1}'\n");

            // grabs delta
            deltaInput = keyboard.nextLine();
            
            //in the event that input includes delta
            if(deltaInput.contains("delta")) {String[] temp = deltaInput.split("("); deltaInput = temp[1];}

            // split delta to state and symbol
            // nfa side
            // left is (state, symbol)
            // right is {states}
            NFADelta = deltaInput.split("=");
            transition = NFADelta[0].split(",");
            state = transition[0].trim();
            symbol = transition[1].trim();
            
            //store
            states.add(state);
            symbols.add(symbol);

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
            // nfa[row][col] = NFADelta[1].trim();
            // dfa[row][col] = DFAState;
        }

        // trash state for dfa
        // for (int i = 0; i < delta; i++) {
        //     for (int j = 0; j < 2; j++) {
        //         if (dfa[i][j] == null) {
        //             dfa[i][j] = "trash";
        //         }
        //     }
        // }

        return dfa;
    }
}
