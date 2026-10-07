import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.printf("\nDFA\n");
        String[][] dfa = NFADFA.NFA(keyboard);

        System.out.printf("Enter delta to look at:\nForm: (q0, a)\n");
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