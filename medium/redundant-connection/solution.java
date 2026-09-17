/*
684. Redundant Connection   [Medium]
https://leetcode.com/problems/redundant-connection/

Runtime: 6 ms   Memory: 45.3 MB

In this problem, a tree is an **undirected graph** that is connected and has no cycles.

You are given a graph that started as a tree with `n` nodes labeled from `1` to `n`, with one additional edge added. The added edge has two **different** vertices chosen from `1` to `n`, and was not an edge that already existed. The graph is represented as an array `edges` of length `n` where `edges[i] = [ai, bi]` indicates that there is an edge between nodes `ai` and `bi` in the graph.

Return _an edge that can be removed so that the resulting graph is a tree of_ `n` _nodes_. If there are multiple answers, return the answer that occurs last in the input.

**Example 1:**

![](https://assets.leetcode.com/uploads/2021/05/02/reduntant1-1-graph.jpg)

**Input:** edges = \[\[1,2\],\[1,3\],\[2,3\]\]
**Output:** \[2,3\]

**Example 2:**

![](https://assets.leetcode.com/uploads/2021/05/02/reduntant1-2-graph.jpg)

**Input:** edges = \[\[1,2\],\[2,3\],\[3,4\],\[1,4\],\[1,5\]\]
**Output:** \[1,4\]

**Constraints:**

*   `n == edges.length`
*   `3 <= n <= 1000`
*   `edges[i].length == 2`
*   `1 <= ai < bi <= edges.length`
*   `ai != bi`
*   There are no repeated edges.
*   The given graph is connected.
*/

public class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            boolean[] visit = new boolean[n + 1];

            if (dfs(u, -1, adj, visit)) {
                return edge;
            }
        }
        return new int[0];
    }

    private boolean dfs(int node, int parent,
                        List<List<Integer>> adj, boolean[] visit) {
        if (visit[node]) {
            return true;
        }

        visit[node] = true;
        for (int nei : adj.get(node)) {
            if (nei == parent) {
                continue;
            }
            if (dfs(nei, node, adj, visit)) {
                return true;
            }
        }
        return false;
    }
}
