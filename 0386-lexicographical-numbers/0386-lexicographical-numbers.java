import java.util.*;

class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<String> strList = new ArrayList<>();
        
        // Add all numbers as strings to the list
        for (int i = 1; i <= n; i++) {
            strList.add(String.valueOf(i));
        }

        // Sort the list lexicographically
        Collections.sort(strList);

        // Convert back to integers
        List<Integer> result = new ArrayList<>();
        for (String s : strList) {
            result.add(Integer.parseInt(s));
        }

        return result;
    }
}
