class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int X = 0;
        for (int i = 0; i < operations.length; i++) {
            // operations
            if (operations[i].equals("--X")) {
                X--;
            }
            if (operations[i].equals("X--")) {
                X--;
            }
            if (operations[i].equals("++X")) {
                X++;
            }
            if (operations[i].equals("X++")) {
                X++;
            }
        }
        return X;
    }
}