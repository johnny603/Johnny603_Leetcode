class Solution {
    public int numberOfWays(String corridor) {
        int n = corridor.length();
        long mod = 1_000_000_007L;
        
        // Count total seats
        int totalSeats = 0;
        for (char c : corridor.toCharArray()) {
            if (c == 'S') totalSeats++;
        }
        if (totalSeats % 2 != 0 || totalSeats == 0) return 0;

        long ways = 1;
        int seatsCount = 0;
        int plantsBetweenSections = 0;
        boolean firstSection = false;

        for (char c : corridor.toCharArray()) {
            if (c == 'S') {
                seatsCount++;
                if (seatsCount % 2 == 0) { // end of a valid section
                    if (firstSection) {
                        ways = (ways * (plantsBetweenSections + 1)) % mod;
                    } else {
                        firstSection = true; // first section found
                    }
                    plantsBetweenSections = 0; // reset for next section
                }
            } else { // c == 'P'
                if (seatsCount % 2 == 0 && seatsCount != 0) {
                    plantsBetweenSections++;
                }
            }
        }

        return (int) ways;
    }
}
