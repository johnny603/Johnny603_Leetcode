public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);  // first element is always 1

        for (int r = 1; r <= rowIndex; r++) {
            row.add(1);  // add a new element at the end
            // update from right to left
            for (int i = r - 1; i > 0; i--) {
                row.set(i, row.get(i) + row.get(i - 1));
            }
        }

        return row;
    }
}
