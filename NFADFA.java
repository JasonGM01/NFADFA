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
2. create data structures that will store dfa and nfa
3. create NFA file for NFA call
4. create DFA file for DFA call/conversion
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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NFADFA {
    public static void main(String[] args) throws IOException {
        String NFAState = "";
        String NFASymbol = "";
        String DFAOutputState = "";
        String[] NFAString;
        String[] NFATemp;
        String[] NFATemp2;
        String[] NFATemp3;
        int delta;
        // [state][symbol]
        String[][] nfa = new String[1][1];
        String[][] dfa = new String[1][1];
        List<List<String>> AcceptorList = new ArrayList<>();
        Scanner keyboard = new Scanner(System.in);

        // accept number deltas
        System.out.printf("Input number of deltas:\n");
        delta = keyboard.nextInt();

        // accept string input
        for (int i = 0; i < delta; i++) {
            System.out.printf("Input NFA to convert\nForm: '{ q0 , a } = { q1 , q2 }'\n");

            // grabs delta
            NFAString = keyboard.nextLine().split(" , ");

            // splits delta
            NFATemp = NFAString[0].split(" ");
            NFAState = NFATemp[1];

            NFATemp2 = NFAString[1].split(" ");
            NFASymbol = NFATemp2[0];

            NFATemp3 = NFAString[2].split(" ");
            DFAOutputState = NFATemp2[4] + NFATemp3[0];

            // create delta

            // confirm state and input
            // System.out.printf("state: %s\nInput: %s\nOutput: %s\n", NFA.getState(),
            // NFA.getInput(), NFA.getOutput());

            // put into arraylist
        }

        keyboard.close();
    }
}