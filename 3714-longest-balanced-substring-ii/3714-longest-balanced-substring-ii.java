class Solution {

    public int longestBalanced(String s) {
        char[] arr = s.toCharArray();

        // Case 1: substring uses only ONE character
        int oneChar = longestSingleChar(arr);

        // Case 2: substring uses exactly TWO characters equally
        int twoChar = Math.max(
            longestTwoChars(arr, 'a', 'b'),
            Math.max(
                longestTwoChars(arr, 'b', 'c'),
                longestTwoChars(arr, 'a', 'c')
            )
        );

        // Case 3: substring uses all THREE characters equally
        int threeChar = longestThreeChars(arr);

        return Math.max(oneChar, Math.max(twoChar, threeChar));
    }

    /*
     ---------------------------------------------------------
     CASE 1: Longest run of a single character

     A substring with only one distinct character is always
     balanced, because all distinct characters appear equally
     (there's only one of them).
     ---------------------------------------------------------
    */
    private int longestSingleChar(char[] arr) {
        int best = 0;
        int i = 0;
        int n = arr.length;

        while (i < n) {
            int j = i + 1;

            // extend while characters are identical
            while (j < n && arr[j] == arr[i]) {
                j++;
            }

            best = Math.max(best, j - i);

            // jump to next block
            i = j;
        }

        return best;
    }

    /*
     ---------------------------------------------------------
     CASE 2: Exactly two characters appear equally

     We treat charA as +1 and charB as -1.
     If prefix difference repeats, the substring between them
     has equal counts.

     This is a classic prefix sum trick.
     ---------------------------------------------------------
    */
    private int longestTwoChars(char[] arr, char charA, char charB) {
        int best = 0;
        int i = 0;
        int n = arr.length;

        while (i < n) {

            // skip characters not part of this pair
            while (i < n && arr[i] != charA && arr[i] != charB) {
                i++;
            }

            // map: prefix difference -> first index seen
            Map<Integer, Integer> firstSeen = new HashMap<>();
            firstSeen.put(0, i - 1);

            int diff = 0; // (#A - #B)

            // process contiguous block containing only A or B
            while (i < n && (arr[i] == charA || arr[i] == charB)) {

                diff += (arr[i] == charA) ? 1 : -1;

                Integer prevIndex = firstSeen.get(diff);

                if (prevIndex != null) {
                    best = Math.max(best, i - prevIndex);
                } else {
                    firstSeen.put(diff, i);
                }

                i++;
            }
        }

        return best;
    }

    /*
     ---------------------------------------------------------
     CASE 3: All three characters appear equally

     We track two differences:

        x = count(a) - count(b)
        y = count(b) - count(c)

     If (x, y) repeats, then the substring in between added
     equal amounts of a, b, c.
     ---------------------------------------------------------
    */
    private int longestThreeChars(char[] arr) {

        // map: encoded (x,y) -> first index
        Map<Long, Integer> firstSeen = new HashMap<>();
        firstSeen.put(encode(0, 0), -1);

        int[] count = new int[3]; // counts of a,b,c
        int best = 0;

        for (int i = 0; i < arr.length; i++) {

            count[arr[i] - 'a']++;

            int x = count[0] - count[1];
            int y = count[1] - count[2];

            long key = encode(x, y);

            Integer prevIndex = firstSeen.get(key);

            if (prevIndex != null) {
                best = Math.max(best, i - prevIndex);
            } else {
                firstSeen.put(key, i);
            }
        }

        return best;
    }

    /*
     Encode two integers into one long key.
     We shift to avoid collisions.
    */
    private long encode(int x, int y) {
        return ((long)(x + 100000) << 20) | (y + 100000);
    }
}
