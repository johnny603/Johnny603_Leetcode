class TrieNode {
    Map<String, TrieNode> children;
    boolean isEnd;

    public TrieNode() {
        children = new HashMap<>();
        isEnd = false;
    }
}

public class Solution {
    public List<String> removeSubfolders(String[] folder) {
        TrieNode root = new TrieNode();

        // Sort and insert folders into Trie
        Arrays.sort(folder);
        for (String path : folder) {
            String[] parts = path.split("/");
            TrieNode node = root;
            boolean isSubfolder = false;

            for (int i = 1; i < parts.length; i++) {
                if (node.isEnd) {
                    isSubfolder = true;
                    break;
                }
                node.children.putIfAbsent(parts[i], new TrieNode());
                node = node.children.get(parts[i]);
            }

            if (!isSubfolder) {
                node.isEnd = true; // mark the current folder as a valid root folder
            }
        }

        // DFS to collect top-level folders
        List<String> result = new ArrayList<>();

        dfs(root, new ArrayList<>(), result);

        return result;
    }

    private void dfs(TrieNode node, List<String> path, List<String> result) {
        if (node.isEnd) {
            result.add("/" + String.join("/", path));
            return; // don't go deeper into subfolders
        }

        for (Map.Entry<String, TrieNode> entry : node.children.entrySet()) {
            path.add(entry.getKey());
            dfs(entry.getValue(), path, result);
            path.remove(path.size() - 1); // backtrack
        }
    }
}
