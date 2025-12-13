class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        int n = code.length;

        // Fixed business line order
        List<String> order = Arrays.asList(
            "electronics", "grocery", "pharmacy", "restaurant"
        );

        // Map to store valid coupon codes per business line
        Map<String, List<String>> map = new HashMap<>();
        for (String b : order) {
            map.put(b, new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            // Condition 3: coupon must be active
            if (!isActive[i]) continue;

            // Condition 1: code must be non-empty and alphanumeric + underscore
            if (code[i] == null || code[i].isEmpty() || !code[i].matches("[A-Za-z0-9_]+")) {
                continue;
            }

            // Condition 2: valid business line
            if (!map.containsKey(businessLine[i])) continue;

            // Coupon is valid
            map.get(businessLine[i]).add(code[i]);
        }

        // Build result in required order
        List<String> result = new ArrayList<>();
        for (String b : order) {
            List<String> codes = map.get(b);
            Collections.sort(codes); // lexicographical order
            result.addAll(codes);
        }

        return result;
    }
}
