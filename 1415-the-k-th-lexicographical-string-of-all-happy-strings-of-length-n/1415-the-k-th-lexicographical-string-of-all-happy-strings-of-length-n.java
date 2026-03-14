// count how many happy strings start with a certain prefix and skip entire blocks until we reach the k-th string. This avoids generating all strings.

class Solution {
    public String getHappyString(int n, int k) {

        int total = 3 * (1 << (n - 1));
        if (k > total) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        char prev = '#';

        for (int i = 0; i < n; i++) {

            int block = 1 << (n - i - 1);

            for (char c : new char[]{'a','b','c'}) {

                if (c == prev) continue;

                if (k > block) {
                    k -= block;
                } else {
                    sb.append(c);
                    prev = c;
                    break;
                }
            }
        }

        return sb.toString();
    }
}
