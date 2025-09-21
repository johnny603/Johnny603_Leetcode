class MovieRentingSystem {
    Map<Pair<Integer, Integer>, Integer> priceMap = new HashMap<>();
    Map<Integer, TreeSet<MovieEntry>> unrented = new HashMap<>();
    TreeSet<MovieEntry> rented = new TreeSet<>();

    public MovieRentingSystem(int n, int[][] entries) {
        for (int[] entry : entries) {
            int shop = entry[0], movie = entry[1], price = entry[2];
            priceMap.put(new Pair<>(shop, movie), price);
            unrented.computeIfAbsent(movie, k -> new TreeSet<>())
                    .add(new MovieEntry(shop, movie, price));
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if (unrented.containsKey(movie)) {
            int count = 0;
            for (MovieEntry e : unrented.get(movie)) {
                res.add(e.shop);
                if (++count == 5) break;
            }
        }
        return res;
    }

    public void rent(int shop, int movie) {
        int price = priceMap.get(new Pair<>(shop, movie));
        MovieEntry entry = new MovieEntry(shop, movie, price);
        unrented.get(movie).remove(entry);
        rented.add(entry);
    }

    public void drop(int shop, int movie) {
        int price = priceMap.get(new Pair<>(shop, movie));
        MovieEntry entry = new MovieEntry(shop, movie, price);
        rented.remove(entry);
        unrented.computeIfAbsent(movie, k -> new TreeSet<>()).add(entry);
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        int count = 0;
        for (MovieEntry e : rented) {
            res.add(Arrays.asList(e.shop, e.movie));
            if (++count == 5) break;
        }
        return res;
    }

    class MovieEntry implements Comparable<MovieEntry> {
        int shop, movie, price;

        MovieEntry(int shop, int movie, int price) {
            this.shop = shop;
            this.movie = movie;
            this.price = price;
        }

        public int compareTo(MovieEntry other) {
            if (this.price != other.price)
                return Integer.compare(this.price, other.price);
            if (this.shop != other.shop)
                return Integer.compare(this.shop, other.shop);
            return Integer.compare(this.movie, other.movie);
        }

        public boolean equals(Object o) {
            if (!(o instanceof MovieEntry)) return false;
            MovieEntry other = (MovieEntry) o;
            return this.shop == other.shop && this.movie == other.movie;
        }

        public int hashCode() {
            return Objects.hash(shop, movie);
        }
    }

    class Pair<U, V> {
        U first;
        V second;

        Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair<?, ?> p = (Pair<?, ?>) o;
            return Objects.equals(first, p.first) && Objects.equals(second, p.second);
        }

        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}
