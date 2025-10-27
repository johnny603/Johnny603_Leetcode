class Solution {
    public int numberOfBeams(String[] bank) {
        int numLasers = 0;
        int prevDevices = 0;

        for (String row : bank) {
            // Count number of '1's in the current row
            int currDevices = 0;
            for (char c : row.toCharArray()) {
                if (c == '1') currDevices++;
            }

            // Only calculate beams if this row has devices
            if (currDevices > 0) {
                numLasers += prevDevices * currDevices;
                prevDevices = currDevices;  // Update for next non-empty row
            }
        }

        return numLasers;
    }
}
