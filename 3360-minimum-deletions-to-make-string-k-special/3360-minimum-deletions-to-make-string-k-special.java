// 1st, brute force solution

/*
class Solution {
    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        int minDeletions = Integer.MAX_VALUE;

        for (int t = 1; t <= maxFreq; t++) {
            int deletions = 0;
            for (int f : freq) {
                if (f == 0) continue;

                if (f < t) {
                    deletions += f; // delete all occurrences (too small)
                } else if (f > t + k) {
                    deletions += f - (t + k); // delete extras (too large)
                }
                // else within range — no deletion needed
            }
            minDeletions = Math.min(minDeletions, deletions);
        }

        return minDeletions;
    }
}
*/


// 2nd, optimized solution

class Solution {
    public int minimumDeletions(String word, int k) {
        Map<Character, Integer> freqMap = new HashMap<>();
        
        // Count character frequencies
        for (char c : word.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Extract frequencies into a list
        List<Integer> freqs = new ArrayList<>(freqMap.values());

        int minDeletions = Integer.MAX_VALUE;

        for (int target : freqs) {
            int deletions = 0;

            for (int f : freqs) {
                if (f > target + k) {
                    deletions += f - (target + k); // too high → trim down
                } else if (f < target) {
                    deletions += f; // too low → delete all
                }
                // in [target, target + k] → keep
            }

            minDeletions = Math.min(minDeletions, deletions);
        }

        return minDeletions;
    }
}

