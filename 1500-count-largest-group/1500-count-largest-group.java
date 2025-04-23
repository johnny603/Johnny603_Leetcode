import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int countLargestGroup(int n) {
        int output = 0;       // Final count of groups that have the largest size
        int maxSize = 0;      // Tracks the maximum size among all groups
        Map<Integer, Integer> count = new HashMap<>();  // Map to count frequency of each digit sum

        // Step through every number from 1 to n
        for (int i = 1; i <= n; i++) {
            int num = i;
            int sum = 0;

            // Calculate the sum of the digits of the current number
            while (num > 0) {
                sum += num % 10;  // Add the last digit
                num /= 10;        // Remove the last digit
            }

            // Increment the count of this digit sum in the map
            count.put(sum, count.getOrDefault(sum, 0) + 1);

            // Update maxSize if the current group size becomes the new max
            maxSize = Math.max(maxSize, count.get(sum));
        }

        // Count how many groups have the size equal to maxSize
        for (int value : count.values()) {
            if (value == maxSize) {
                output++;
            }
        }

        return output;  // Return the number of groups with the largest size
    }
}
