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
   public DFAinput NFAtoDFA(Scanner keyboard) {
      int delta;
      String initialState;
      Set<String> finalStates = new HashSet<>();
      Set<String> discoveredStates = new HashSet<>();
      Set<String> discoveredAlphabet = new HashSet<>();
      ArrayList<String> parsedSources = new ArrayList<>();
      ArrayList<String> parsedSymbols = new ArrayList<>();
      ArrayList<Set<String>> parsedTargets = new ArrayList<>();

      //input delta
      System.out.println("Enter number of transitions:");
      delta = keyboard.nextInt();
      keyboard.nextLine();

      System.out.println("Enter initial state:");
      initialState = keyboard.nextLine().trim();
      System.out.println("Enter final state(s), separated by commas:");
      String[] finals = keyboard.nextLine().split(",");

      for (String state : finals) {
         finalStates.add(state.trim());
      }
      discoveredStates.add(initialState);
      discoveredStates.addAll(finalStates);

      //read nfa inputs
      for (int i = 0; i < delta; i++) {
         System.out.println("Enter transition:");
         System.out.println("Form: (q0, a) = {q0, q1}");
         String transition = keyboard.nextLine();

         String[] tempTransition = transition.split("=");
         String left = tempTransition[0].trim();
         String right = tempTransition[1].trim();

         // Allow either:
         // (q0, a)
         // delta(q0, a)
         left = left.replace("delta", "").replace("(", "").replace(")", "").trim();

         String[] leftParts = left.split(",");
         String sourceState = leftParts[0].trim();
         String sourceSymbol = leftParts[1].trim();

         right = right.replace("{", "").replace("}", "").trim();

         String[] targets = right.split(",");
         Set<String> targetSet = new HashSet<>();

         for (String target : targets) {
            target = target.trim();

            if (!target.isEmpty()) {
               targetSet.add(target);
            }
         }
         discoveredStates.add(sourceState);
         discoveredStates.addAll(targetSet);

         // lambda is not part of DFA alphabet
         if (!sourceSymbol.equals("lambda") && !sourceSymbol.equals("<lambda>")) {
            discoveredAlphabet.add(sourceSymbol);
         }
         parsedSources.add(sourceState);
         parsedSymbols.add(sourceSymbol);
         parsedTargets.add(targetSet);
      }

      //build nfa
      String[] states = discoveredStates.toArray(new String[0]);
      String[] alphabet = discoveredAlphabet.toArray(new String[0]);
      NFAinput nfa = new NFAinput(states, alphabet, initialState, finalStates);

      for (int i = 0; i < parsedSources.size(); i++) {
         String symbol = parsedSymbols.get(i);

         if (symbol.equals("lambda")) {
            symbol = "<lambda>";
         }
         nfa.addTransition(parsedSources.get(i), symbol, parsedTargets.get(i));
      }

      //subset construction
      ArrayList<Set<String>> dfaStates = new ArrayList<>();
      ArrayList<Set<String>> unprocessed = new ArrayList<>();
      ArrayList<String> dfaSources = new ArrayList<>();
      ArrayList<String> dfaSymbols = new ArrayList<>();
      ArrayList<String> dfaTargets = new ArrayList<>();
      Set<String> startSet = new HashSet<>();

      startSet.add(initialState);
      startSet = nfa.lambdaClosure(startSet);
      dfaStates.add(startSet);
      unprocessed.add(startSet);
      boolean trashUsed = false;

      while (!unprocessed.isEmpty()) {
         Set<String> current = unprocessed.remove(0);
         String currentName = stateSetToName(current);

         for (String symbol : alphabet) {
            Set<String> destination = nfa.move(current, symbol);
            destination = nfa.lambdaClosure(destination);
            String destinationName;

            if (destination.isEmpty()) {
               destinationName = "trash";
               trashUsed = true;
            } else {
               destinationName = stateSetToName(destination);

               if (!dfaStates.contains(destination)) {
                  Set<String> newState = new HashSet<>(destination);
                  dfaStates.add(newState);
                  unprocessed.add(newState);
               }
            }
            dfaSources.add(currentName);
            dfaSymbols.add(symbol);
            dfaTargets.add(destinationName);
         }
      }

      //create dfa state names
      ArrayList<String> dfaStateNames = new ArrayList<>();

      for (Set<String> state : dfaStates) {
         dfaStateNames.add(stateSetToName(state));
      }

      if (trashUsed) {
         dfaStateNames.add("trash");
      }
      String[] dfaStateArray = dfaStateNames.toArray(new String[0]);

      //determine dfa final states
      Set<String> dfaFinalStates = new HashSet<>();

      for (Set<String> state : dfaStates) {
         if (nfa.containsFinal(state)) {
            dfaFinalStates.add(stateSetToName(state));
         }
      }
      String dfaInitialState = stateSetToName(startSet);

      //build dfa
      DFAinput dfa = new DFAinput(dfaStateArray, alphabet, dfaInitialState, dfaFinalStates);

      for (int i = 0; i < dfaSources.size(); i++) {
         dfa.addTransition(dfaSources.get(i), dfaSymbols.get(i), dfaTargets.get(i));
      }

      // Trash state loops to itself
      if (trashUsed) {
         for (String symbol : alphabet) {
            dfa.addTransition("trash", symbol, "trash");
         }
      }
      return dfa;
   }

   // Convert:
   // {q0, q1} -> q0q1
   private String stateSetToName(Set<String> stateSet) {
      ArrayList<String> sorted = new ArrayList<>(stateSet);
      Collections.sort(sorted);
      String result = "";

      for (String state : sorted) {
         result += state;
      }
      return result;
   }
}