class Solution {
  public int maxDifference(String s, int k) {
    // Initialize the answer to the smallest possible integer value.
    int sol = Integer.MIN_VALUE;

    // Try all permutations of two different characters from '0' to '4'.
    for (Pair<Character, Character> pair : getPermutations()) {
      final char a = pair.getKey();  // character a must have an **odd** frequency
      final char b = pair.getValue(); // character b must have an **even** frequency

      // minDiff[parityA][parityB] will track the **minimum difference** of count(a) - count(b)
      // for all valid window starts that have given parities of a and b.
      int[][] minDiff = new int[2][2];
      Arrays.stream(minDiff).forEach(A -> Arrays.fill(A, Integer.MAX_VALUE / 2));

      // prefixA[i] stores the cumulative count of 'a' in s[0..i)
      List<Integer> prefixA = new ArrayList<>(List.of(0));
      // prefixB[i] stores the cumulative count of 'b' in s[0..i)
      List<Integer> prefixB = new ArrayList<>(List.of(0));

      // Two-pointer sliding window technique
      for (int l = 0, r = 0; r < s.length(); ++r) {
        // Update prefix sums
        prefixA.add(prefixA.get(prefixA.size() - 1) + (s.charAt(r) == a ? 1 : 0));
        prefixB.add(prefixB.get(prefixB.size() - 1) + (s.charAt(r) == b ? 1 : 0));

        // While window size is at least k and both a and b are present in the window
        while (r - l + 1 >= k &&
               prefixA.get(l) < prefixA.get(prefixA.size() - 1) &&
               prefixB.get(l) < prefixB.get(prefixB.size() - 1)) {

          // Track the smallest (a - b) for windows starting at index l,
          // grouped by the parity (odd/even) of a and b.
          int parityA = prefixA.get(l) % 2;
          int parityB = prefixB.get(l) % 2;
          minDiff[parityA][parityB] = Math.min(
              minDiff[parityA][parityB],
              prefixA.get(l) - prefixB.get(l)
          );
          ++l; // Move the window's left side forward
        }

        // Now compute the difference between a and b in the current window
        int countA = prefixA.get(prefixA.size() - 1);
        int countB = prefixB.get(prefixB.size() - 1);

        // For a to be odd and b to be even, we need the opposite parity from the minDiff table
        int targetParityA = 1 - (countA % 2);
        int targetParityB = countB % 2;

        // Update the answer with the maximum difference found so far
        sol = Math.max(sol, (countA - countB) - minDiff[targetParityA][targetParityB]);
      }
    }

    return sol;
  }

  // Helper method to generate all permutations of two distinct characters from '0' to '4'
  private List<Pair<Character, Character>> getPermutations() {
    List<Pair<Character, Character>> permutations = new ArrayList<>();
    for (final char a : "01234".toCharArray())
      for (final char b : "01234".toCharArray())
        if (a != b)
          permutations.add(new Pair<>(a, b));
    return permutations;
  }
}
