import java.util.*;

public class ContainsState {
    public static boolean containsStateSet(ArrayList<Set<String>> states, Set<String> target) {
        for (Set<String> state : states) {
            if (state.equals(target)) {
                return true;
            }
        }
        return false;
    }
}