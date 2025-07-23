/*
Function maximumGain(s, x, y):
    If x > y:
        # Remove "ab" first, then "ba"
        result1 = removePattern(s, 'a', 'b', x)
        remainingString = result1.string
        score1 = result1.score

        result2 = removePattern(remainingString, 'b', 'a', y)
        score2 = result2.score
    Else:
        # Remove "ba" first, then "ab"
        result1 = removePattern(s, 'b', 'a', y)
        remainingString = result1.string
        score1 = result1.score

        result2 = removePattern(remainingString, 'a', 'b', x)
        score2 = result2.score

    Return score1 + score2
*/

class Solution {
    public int maximumGain(String s, int x, int y) {
        if (x > y) {
            Result first = removePattern(s, 'a', 'b', x);
            Result second = removePattern(first.remaining, 'b', 'a', y);
            return first.score + second.score;
        } else {
            Result first = removePattern(s, 'b', 'a', y);
            Result second = removePattern(first.remaining, 'a', 'b', x);
            return first.score + second.score;
        }
    }

    private static class Result {
        String remaining;
        int score;

        Result(String remaining, int score) {
            this.remaining = remaining;
            this.score = score;
        }
    }

    private Result removePattern(String s, char first, char second, int points) {
        StringBuilder stack = new StringBuilder();
        int score = 0;

        for (char c : s.toCharArray()) {
            int len = stack.length();
            if (len > 0 && stack.charAt(len - 1) == first && c == second) {
                stack.deleteCharAt(len - 1);
                score += points;
            } else {
                stack.append(c);
            }
        }

        return new Result(stack.toString(), score);
    }
}
