class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {

        // columns: y -> all x in this column
        Map<Integer, TreeSet<Integer>> cols = new HashMap<>();
        // rows: x -> all y in this row
        Map<Integer, TreeSet<Integer>> rows = new HashMap<>();

        for (int[] b : buildings) {
            int x = b[0], y = b[1];

            cols.computeIfAbsent(y, k -> new TreeSet<>()).add(x);
            rows.computeIfAbsent(x, k -> new TreeSet<>()).add(y);
        }

        int count = 0;

        for (int[] b : buildings) {
            int x = b[0], y = b[1];

            TreeSet<Integer> col = cols.get(y);
            TreeSet<Integer> row = rows.get(x);

            boolean hasAbove = col.lower(x) != null;
            boolean hasBelow = col.higher(x) != null;
            boolean hasLeft  = row.lower(y) != null;
            boolean hasRight = row.higher(y) != null;

            if (hasAbove && hasBelow && hasLeft && hasRight) {
                count++;
            }
        }

        return count;
    }
}
