class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();
        int i = 0;
        int j = n - 1;

        // ignore leading 'L'
        while (i < n && directions.charAt(i) == 'L') {
            i++;
        }

        // ignore trailing 'R'
        while (j >= 0 && directions.charAt(j) == 'R') {
            j--;
        }

        int collisions = 0;

        // count all R, L, S inside the remaining range
        for (int k = i; k <= j; k++) {
            if (directions.charAt(k) != 'S') {
                collisions++;
            }
        }

        return collisions;
    }
}
