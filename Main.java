import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> symbolMap = new HashMap<>();
        symbolMap.put("a", 0);
        symbolMap.put("b", 1);
        symbolMap.put("lambda", 2);
        
        String yesNo = "yes";

        Scanner keyboard = new Scanner(System.in);
        System.out.printf("\nDFA\n");
        String[][] dfa = NFADFA.NFA(keyboard);
        int rowLength = dfa.length; 
        int colLength = dfa[0].length;

        System.out.printf("\nNew DFA transition matrix:\n");
        String[] symbols = {"a", "b", "lambda"};
        System.out.printf("%10s", "State");
        for (String symbol : symbols) {
            System.out.printf("%10s", symbol);
        }
        System.out.println();

        for (int i = 0; i < rowLength; i++) {
            System.out.printf("%10s", "q" + i);
            for (int j = 0; j < colLength; j++) {
                System.out.printf("%10s", dfa[i][j]);
            }
            System.out.println();
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
        

        keyboard.close();
    }
}
