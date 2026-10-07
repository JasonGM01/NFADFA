import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class SetName {
    public static String stateSetToName(Set<String> stateSet) {
        ArrayList<String> sorted = new ArrayList<>(stateSet);
        Collections.sort(sorted);

        String result = "";

        for (String state : sorted) {
            result += state;
        }

        return result;
    }
}