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
   > nfa side done

3. create NFA
   > done

4. create DFA
5. fix logic based on parameters and needs
6. confirm output formatting

sample inputs:
1. 
input nfa: 
(q0, a) = {q0, q1}
(q1, b) = {q1, q2}
(q2, a) = {q2}
with initial q0 and final q1

2.
input nfa:
(q0, a) = {q0, q1}
(q1, b) = {q1, q2}
(q2, a) = {q2}
(q0, <lambda>) = {q2}
with initial q0 and final q1
*/

import java.io.IOException;
import java.util.Scanner;

public class NFADFA {
    public static void NFA() {
        int delta;
        char[] NFAString;
        String NFAInput;
        String NFAState = "";
        String NFASymbol = "";
        
        try(Scanner keyboard = new Scanner(System.in);){

        // accept number deltas
        System.out.printf("Input number of deltas:\n");
        delta = keyboard.nextInt();
        keyboard.nextLine();

        //create array using delta as parameter
        // [state][symbol]
        String[][] nfa = new String[delta][2];

        // accept string input
        for (int i = 0; i < delta; i++) {
            System.out.printf("Input NFA to convert\nForm: '(q0, a) = {q0, q1}'\n");

            // grabs delta
            NFAInput = keyboard.nextLine();

            //split delta to state and symbol
            NFAString = NFAInput.toCharArray();
            NFAState += NFAString[1];
            NFAState += NFAString[2];
            NFASymbol += NFAString[5];

            //store state and symbol
            nfa[i][0] = NFAState; 
            nfa[i][1] = NFASymbol;

            //reset
            NFAState = "";
            NFASymbol = "";
        }

        //print nfa states and symbols
        for(int i = 0; i < delta; i++){
            System.out.printf("NFA Delta #%d\nState: %s\nSymbol: %s\n\n", i+1, nfa[i][0], nfa[i][1]);
        }

        keyboard.close();
    }
}

    public static void main(String[] args) throws IOException {
        NFA();
    }
}