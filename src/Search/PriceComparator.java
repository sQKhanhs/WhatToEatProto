package Search;

import java.util.Comparator;

public class PriceComparator implements Comparator<String[]> {
    @Override
    public int compare(String[] string1, String[] string2) {
        return Integer.parseInt(string1[0]) - Integer.parseInt(string2[0]);
    }
}
