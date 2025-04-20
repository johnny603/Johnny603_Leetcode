import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numRabbits(int[] answers) {
        int minRabbits = 0;
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int answer : answers) {
            frequency.put(answer, frequency.getOrDefault(answer, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            int answer = entry.getKey();
            int count = entry.getValue();
            // The number of rabbits needed for this answer group
            minRabbits += ((count + answer) / (answer + 1)) * (answer + 1);
        }

        return minRabbits;
    }
}
