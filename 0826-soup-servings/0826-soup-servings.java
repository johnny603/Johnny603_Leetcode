class Solution {
    // Memoization cache: key = "a,b" stores probability result
    private Map<String, Double> memo = new HashMap<>();
    
    public double soupServings(int n) {
        // Optimization shortcut:
        // For large n, probability approaches 1, so just return 1.0
        if (n > 4800) return 1.0;
        
        // Convert n mL into units of 25 mL
        int units = (n + 24) / 25;  // equivalent to ceil(n / 25.0)
        
        return dfs(units, units);
    }
    
    private double dfs(int a, int b) {
        // Base cases
        if (a <= 0 && b <= 0) return 0.5;  // both empty at same time
        if (a <= 0) return 1.0;             // A empty first
        if (b <= 0) return 0.0;             // B empty first
        
        // Check memo
        String key = a + "," + b;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        // Recursive calls for all 4 serving operations
        double prob = 0.25 * (
            dfs(a - 4, b) +       // 100 mL from A (4 units), 0 from B
            dfs(a - 3, b - 1) +   // 75 mL A (3 units), 25 mL B (1 unit)
            dfs(a - 2, b - 2) +   // 50 mL A, 50 mL B
            dfs(a - 1, b - 3)     // 25 mL A, 75 mL B
        );
        
        // Memoize and return
        memo.put(key, prob);
        return prob;
    }
}