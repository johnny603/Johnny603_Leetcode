// 1. Initialize a hashtable (freq) to count frequency of each number in arr
// 2. For each number num in arr:
//       if freq contains num, increment its count by 1
//       else, add num with count 1
// 3. Initialize a variable result = -1 to store the lucky number found
// 4. Iterate through each key-value pair (num, count) in freq:
//       if num == count and num > result:
//           update result = num
// 5. Return result

import java.util.Hashtable;
import java.util.Map;

class Solution {
    public int findLucky(int[] arr) {
        int lucky = -1; // If there is no lucky integer return -1
        // keep track the frequency of each num in arr since contraint is low
        Hashtable<Integer, Integer> freq = new Hashtable<>();
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }
        // if both integers in the hashtable are equal, it is a lucky integer
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (num == count && num > lucky) {
                lucky = num;  // update if this lucky number is larger
            }
        }
        return lucky;  
    }
}