import java.util.*;

class Solution {
    public int[] findEvenNumbers(int[] digits) {
        Set<Integer> result = new HashSet<>(); // track uniques

        int n = digits.length;
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) continue; // No leading zero

            for (int j = 0; j < n; j++) {
                if (j == i) continue;

                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) continue;

                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                    if (num % 2 == 0) {
                        result.add(num); // Unique valid number
                    }
                }
            }
        }

        List<Integer> list = new ArrayList<>(result);
        Collections.sort(list);

        // Convert List to array
        int[] resArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            resArr[i] = list.get(i);
        }

        return resArr;
    }
}
