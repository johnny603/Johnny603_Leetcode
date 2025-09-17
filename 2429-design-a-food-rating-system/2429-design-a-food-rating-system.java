class FoodRatings {
    private Map<String, String> foodToCuisine;
    private Map<String, Integer> foodToRating;
    private Map<String, PriorityQueue<FoodEntry>> cuisineToFoods;

    private static class FoodEntry {
        String food;
        int rating;
        FoodEntry(String f, int r) {
            food = f;
            rating = r;
        }
    }

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        foodToCuisine = new HashMap<>();
        foodToRating = new HashMap<>();
        cuisineToFoods = new HashMap<>();

        for (int i = 0; i < foods.length; i++) {
            String food = foods[i];
            String cuisine = cuisines[i];
            int rating = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRating.put(food, rating);

            cuisineToFoods.putIfAbsent(cuisine, new PriorityQueue<>(
                (a, b) -> {
                    if (a.rating != b.rating) return b.rating - a.rating; // higher rating first
                    return a.food.compareTo(b.food); // lexicographically smaller first
                }
            ));
            cuisineToFoods.get(cuisine).add(new FoodEntry(food, rating));
        }
    }
    
    public void changeRating(String food, int newRating) {
        foodToRating.put(food, newRating);
        String cuisine = foodToCuisine.get(food);
        cuisineToFoods.get(cuisine).add(new FoodEntry(food, newRating));
    }
    
    public String highestRated(String cuisine) {
        PriorityQueue<FoodEntry> pq = cuisineToFoods.get(cuisine);
        while (true) {
            FoodEntry top = pq.peek();
            if (foodToRating.get(top.food) == top.rating) {
                return top.food;
            }
            pq.poll(); // remove outdated entry
        }
    }
}
