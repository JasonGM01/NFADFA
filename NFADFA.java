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

TODO:
1. Create main file
2. create data structures that will store dfa and nfa
3. create NFA file for NFA call
4. create DFA file for DFA call/conversion
5. fix logic based on parameters and needs
6. confirm output formatting

sample inputs:
1. 
input nfa: 
{q0, a} = {q0, q1}
{q1, b} = {q1, q2}
{q2, a} = {q2}
with initial q0 and final q1

2.
input nfa:
{q0, a} = {q0, q1}
{q1, b} = {q1, q2}
{q2, a} = {q2}
{q0, <lambda>} = {q2}
with initial q0 and final q1
*/

import java.util.ArrayList;
import java.util.Scanner;

public class NFADFA {
    public static void main(String[] args) {
        String[] NFA = new String[2];
        ArrayList<String[]> NFAList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.printf("Input NFA to convert\nForm: '{q0,a} = {q1,q2}'\n");
        NFA = sc.next().split("=");
        System.out.printf("%s\n", NFA[0]);
        NFAList.add(NFA);

        System.out.printf("%s", NFAList);

        sc.close();
    }
}