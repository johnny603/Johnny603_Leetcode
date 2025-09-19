class Spreadsheet {
    private Map<String, Integer> cells;

    public Spreadsheet(int rows) {
        cells = new HashMap<>();
    }
    
    public void setCell(String cell, int value) {
        cells.put(cell, value);
    }
    
    public void resetCell(String cell) {
        cells.remove(cell); // Reset means back to 0 (default)
    }
    
    public int getValue(String formula) {
        // Remove the '=' at the start
        formula = formula.substring(1); // "X+Y"
        String[] parts = formula.split("\\+"); // ["X", "Y"]
        
        return getOperandValue(parts[0]) + getOperandValue(parts[1]);
    }
    
    private int getOperandValue(String operand) {
        // If it's a cell reference like "A1", "B2", etc.
        if (Character.isLetter(operand.charAt(0))) {
            return cells.getOrDefault(operand, 0);
        }
        // Otherwise, it's just an integer
        return Integer.parseInt(operand);
    }
}
