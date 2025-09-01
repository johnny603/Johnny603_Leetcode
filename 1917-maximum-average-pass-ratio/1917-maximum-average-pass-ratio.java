class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // PriorityQueue with custom comparator to maximize gain
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        
        // Push initial gains for each class
        for (int[] c : classes) {
            int p = c[0], t = c[1];
            double gain = gain(p, t);
            pq.offer(new double[]{gain, p, t});
        }
        
        // Assign extra students
        for (int i = 0; i < extraStudents; i++) {
            double[] top = pq.poll();
            int p = (int) top[1], t = (int) top[2];
            p++; t++;
            pq.offer(new double[]{gain(p, t), p, t});
        }
        
        // Compute final average
        double total = 0.0;
        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            int p = (int) cur[1], t = (int) cur[2];
            total += (double) p / t;
        }
        
        return total / classes.length;
    }
    
    // Gain function
    private double gain(int p, int t) {
        return ((double)(p + 1) / (t + 1)) - ((double) p / t);
    }
}
