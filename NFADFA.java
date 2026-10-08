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

import java.util.*;

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
      ArrayList<String> dfaSources = new ArrayList<>();
      ArrayList<String> dfaSymbols = new ArrayList<>();
      ArrayList<String> dfaTargets = new ArrayList<>();    
      ArrayList<Set<String>> dfaStates = new ArrayList<>();
      ArrayList<Set<String>> unprocessed = new ArrayList<>();
      ArrayList<String> dfaStateNames = new ArrayList<>();

      System.out.printf("Enter number of transitions:\n");
      delta = keyboard.nextInt();
      keyboard.nextLine();

      System.out.printf("Enter initial state:\n");
      initialState = keyboard.nextLine().trim();

      System.out.printf("Enter final state:\n");
      finalStates.add(keyboard.nextLine().trim());

      // Initial/final states should also count as discovered states
      discoveredStates.add(initialState);
      discoveredStates.addAll(finalStates);

      // read input
      for (int i = 0; i < delta; i++) {
         System.out.printf("Enter transition:\nForm: (q0, a) = {q0, q1}\n");
         String transition = keyboard.nextLine();

         // Split:
         // (q0, a) = {q0, q1}
         //
         // left = (q0, a)
         // right = {q0, q1}

         String[] tempTransition = transition.split("=");
         String left = tempTransition[0].trim();
         String right = tempTransition[1].trim();

         // Remove parentheses from left side
         left = left.replace("(", "").replace(")", "").trim();

         // Split source state and symbol
         String[] leftParts = left.split(",");
         String sourceState = leftParts[0].trim();
         String sourceSymbol = leftParts[1].trim();

         // Remove braces from destination side
         right = right.replace("{", "").replace("}", "").trim();

         // Split targets
         String[] targets = right.split(",");
         Set<String> targetSet = new HashSet<>();

         for (int j = 0; j < targets.length; j++) {
            targets[j] = targets[j].trim();
            if (!targets[j].isEmpty()) {
               targetSet.add(targets[j]);
            }
         }

         discoveredStates.add(sourceState);
         discoveredStates.addAll(targetSet);

         // lambda is NOT part of DFA alphabet
         if (!sourceSymbol.equals("lambda") && !sourceSymbol.equals("<lambda>")) {
            discoveredAlphabet.add(sourceSymbol);
         }

         // save transitions
         parsedSources.add(sourceState);
         parsedSymbols.add(sourceSymbol);
         parsedTargets.add(targetSet);
      }

      // build nfa
      String[] states = discoveredStates.toArray(new String[0]);
      String[] alphabet = discoveredAlphabet.toArray(new String[0]);

      NFAinput nfa = new NFAinput(states, alphabet, initialState, finalStates);

      // Add parsed transitions to NFA matrix
      for (int i = 0; i < parsedSources.size(); i++) {
         String symbol = parsedSymbols.get(i);

         // Match NFAinput's lambda notation
         if (symbol.equals("lambda")) {
            symbol = "<lambda>";
         }
         nfa.addTransition(parsedSources.get(i), symbol, parsedTargets.get(i));
      }

      // DFA initial state starts with lambda closure
      Set<String> startSet = new HashSet<>();
      startSet.add(initialState);
      startSet = nfa.lambdaClosure(startSet);
      dfaStates.add(startSet);
      unprocessed.add(startSet);
      boolean trashUsed = false;

      // process dfa states
      while (!unprocessed.isEmpty()) {
         Set<String> current = unprocessed.remove(0);
         String currentName = SetName.stateSetToName(current);

         for (String symbol : alphabet) {
            // Move using the symbol
            Set<String> destination = nfa.move(current, symbol);

            // Then apply lambda closure
            destination = nfa.lambdaClosure(destination);
            String destinationName;

            if (destination.isEmpty()) {
               destinationName = "trash";
               trashUsed = true;
            } else {
               destinationName = SetName.stateSetToName(destination);

               // Check if DFA state already exists
               if (!ContainsState.containsStateSet(dfaStates, destination)) {
                  Set<String> newState = new HashSet<>(destination);
                  dfaStates.add(newState);
                  unprocessed.add(newState);
               }
            }

            // Save DFA transition
            dfaSources.add(currentName);
            dfaSymbols.add(symbol);
            dfaTargets.add(destinationName);
         }
      }

      // build dfa state names
      for (Set<String> state : dfaStates) {
         dfaStateNames.add(SetName.stateSetToName(state));
      }

      if (trashUsed) {
         dfaStateNames.add("trash");
      }

      String[] dfaStateArray = dfaStateNames.toArray(new String[0]);

      // Determine dfa final states
      Set<String> dfaFinalStates = new HashSet<>();
      for (Set<String> state : dfaStates) {
         if (nfa.containsFinal(state)) {
            dfaFinalStates.add(SetName.stateSetToName(state));
         }
      }
      String dfaInitialState = SetName.stateSetToName(startSet);

      // Create dfa object
      DFAinput dfa = new DFAinput(dfaStateArray, alphabet, dfaInitialState, dfaFinalStates);

      // Add all discovered DFA transitions
      for (int i = 0; i < dfaSources.size(); i++) {
         dfa.addTransition(dfaSources.get(i), dfaSymbols.get(i), dfaTargets.get(i));
      }

      // Trash loops to itself for every symbol
      if (trashUsed) {
         for (String symbol : alphabet) {
            dfa.addTransition("trash", symbol, "trash");
         }
      }
      return dfa;
   }
}
