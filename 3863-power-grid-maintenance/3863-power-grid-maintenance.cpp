class Solution {
public:
    // Depth-First Search (DFS) to label all computers in the same connected component
    void dfs(int node, vector<vector<int>>& graph, int groupId,
             vector<int>& component, vector<bool>& visited) {
        visited[node] = true;
        component[node] = groupId;
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, groupId, component, visited);
            }
        }
    }

    vector<int> processQueries(int numComputers, vector<vector<int>>& connections,
                               vector<vector<int>>& queries) {
        // Step 1: Build adjacency list representation of the computer network
        vector<vector<int>> graph(numComputers + 1);
        for (auto& edge : connections) {
            int u = edge[0], v = edge[1];
            graph[u].push_back(v);
            graph[v].push_back(u);
        }

        // Step 2: Use DFS to find connected components (i.e., groups of connected computers)
        vector<bool> visited(numComputers + 1, false);
        vector<int> component(numComputers + 1, -1);
        int groupCount = 0;

        for (int i = 1; i <= numComputers; ++i) {
            if (!visited[i]) {
                dfs(i, graph, groupCount, component, visited);
                ++groupCount;
            }
        }

        // Step 3: For each connected component, store its active (online) computers
        vector<set<int>> onlineNodesInGroup(groupCount);
        for (int i = 1; i <= numComputers; ++i) {
            onlineNodesInGroup[component[i]].insert(i);
        }

        // Step 4: Track the online/offline status of each computer
        vector<bool> isOnline(numComputers + 1, true);
        vector<int> results;

        // Step 5: Process each query
        for (auto& query : queries) {
            int queryType = query[0];
            int computer = query[1];
            int groupId = component[computer];

            if (queryType == 2) {
                // Query type 2 → Turn off (deactivate) a computer
                if (isOnline[computer]) {
                    isOnline[computer] = false;
                    onlineNodesInGroup[groupId].erase(computer);
                }
            } else {
                // Query type 1 → Request an active computer in the same group
                if (isOnline[computer]) {
                    // If the queried computer is online, return itself
                    results.push_back(computer);
                } else {
                    // Otherwise, return the smallest active computer in the same group
                    if (onlineNodesInGroup[groupId].empty()) {
                        results.push_back(-1); // No online computers left in this group
                    } else {
                        results.push_back(*onlineNodesInGroup[groupId].begin());
                    }
                }
            }
        }

        return results;
    }
};
