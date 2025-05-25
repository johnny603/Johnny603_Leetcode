class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        
        while (columnNumber > 0) {
            columnNumber--; // adjust for 1-based indexing
            int remainder = columnNumber % 26;
            char ch = (char) ('A' + remainder);
            sb.insert(0, ch); // insert at the front
            columnNumber /= 26;
        }
        
        return sb.toString();
    }
}
