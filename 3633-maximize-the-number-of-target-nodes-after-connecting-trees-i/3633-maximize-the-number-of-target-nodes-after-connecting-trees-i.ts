// Calculate the maximum number of target nodes for each node in the first graph
function maxTargetNodes(edges1: number[][], edges2: number[][], k: number): number[] {
    // Build the adjacency list for the second graph
    const graph2 = build(edges2);

    // Determine the number of nodes in graph2
    const numNodesGraph2 = edges2.length + 1;

    // Variable to keep track of the maximum nodes found
    let maxNodesGraph2 = 0;

    // Explore graph2 from each node and keep track of the maximum nodes reachable at depth k-1
    for (let i = 0; i < numNodesGraph2; i++) {
        maxNodesGraph2 = Math.max(maxNodesGraph2, dfs(graph2, i, -1, k - 1));
    }

    // Build the adjacency list for the first graph
    const graph1 = build(edges1);

    // Determine the number of nodes in graph1
    const numNodesGraph1 = edges1.length + 1;

    // Initialize the result array with baseline values from graph2
    const results = Array(numNodesGraph1).fill(maxNodesGraph2);

    // Explore graph1 from each node to adjust the maximum target nodes
    for (let i = 0; i < numNodesGraph1; i++) {
        results[i] += dfs(graph1, i, -1, k);
    }

    return results;
}

// Build the adjacency list representation of the graph
function build(edges: number[][]): number[][] {
    const numNodes = edges.length + 1;
    const graph: number[][] = Array.from({ length: numNodes }, () => []);
    for (const [a, b] of edges) {
        graph[a].push(b);
        graph[b].push(a);
    }
    return graph;
}

// Depth-first search to compute the number of nodes reachable within a given depth
function dfs(graph: number[][], currentNode: number, parentNode: number, depth: number): number {
    if (depth < 0) {
        return 0;  // Base case: depth is negative, stop searching
    }

    let count = 1;  // Start with counting the current node
    for (const neighbor of graph[currentNode]) {
        if (neighbor !== parentNode) {  // Avoid revisiting the parent node
            count += dfs(graph, neighbor, currentNode, depth - 1);
        }
    }
    return count;
}