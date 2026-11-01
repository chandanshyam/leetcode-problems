/*
3970. Shortest Path With At Most K Consecutive Identical Characters   [Medium]
https://leetcode.com/problems/shortest-path-with-at-most-k-consecutive-identical-characters/

Runtime: 107 ms   Memory: 240.6 MB

You are given an integer `n` representing the number of nodes in a **directed weighted** graph, numbered from 0 to `n - 1`. This is represented by a 2D integer array `edges`, where `edges[i] = [ui, vi, wi]` represents a directed edge from node `ui` to node `vi` with weight `wi`.

You are also given a string `labels` of length `n`, where `labels[i]` is the character assigned to node `i`, and an integer `k`.

Return the **minimum** **total** edge weight of a path from node 0 to node `n - 1` such that the concatenation of the labels of the nodes along the path contains **at most** `k` **consecutive** **identical** characters. If no valid path exists, return -1.

**Example 1:**

**Input:** n = 3, edges = \[\[0,1,1\],\[1,2,1\],\[0,2,3\]\], labels = "aab", k = 1

**Output:** 3

**Explanation:**

The optimal valid path from node 0 to node 2 is as follows:

*   Use `edges[2] = [0, 2, 3]` to reach node 2 with a weight `wi = 3`.

The corresponding concatenation of labels is `"ab"`, which satisfies at most `k = 1` consecutive identical characters. Thus, the answer is 3.

**Example 2:**

**Input:** n = 3, edges = \[\[0,1,1\],\[1,2,1\],\[0,2,3\]\], labels = "aab", k = 2

**Output:** 2

**Explanation:**

The optimal valid path from node 0 to node 2 is as follows:

*   Use `edges[0] = [0, 1, 1]` to reach node 1 with weight `wi = 1`.
*   Use `edges[1] = [1, 2, 1]` to reach node 2 with weight `wi = 1`.

The corresponding concatenation of labels is `"aab"`, which satisfies at most `k = 2` consecutive identical characters. Thus, the answer is 2.

**Example 3:**

**Input:** n = 3, edges = \[\[0,1,1\],\[1,2,1\]\], labels = "aaa", k = 2

**Output:** \-1

**Explanation:**

There is no valid path from node 0 to node 2 that satisfies at most `k = 2` consecutive identical characters. Thus, the answer is -1.

**Constraints:**

*   `1 <= n == labels.length <= 5 * 104`
*   `0 <= edges.length <= 5 * 104`
*   `edges[i] == [ui, vi, wi]`
*   `0 <= ui, vi <= n - 1`
*   `ui != vi`
*   `1 <= wi <= 104`
*   `labels` consists of lowercase English letters
*   `1 <= k <= 50`
*/

class Solution {
    int dik[][];
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        dik=new int[n][k+1];
        List<List<int[]>>lst=new ArrayList<>();
        for(int i=0;i<n;i++)lst.add(new ArrayList<>());
        for(int e[]:edges){
             lst.get(e[0]).add(new int[]{e[1],e[2]});
        }
        for(int u[]:dik)Arrays.fill(u,Integer.MAX_VALUE);
        PriorityQueue<int[]>que=new PriorityQueue<>((a,b)->a[2]-b[2]);
        que.offer(new int[]{0,1,0});
        dik[0][1]=0;
        while(!que.isEmpty()){
            int p[]=que.poll();
            int nd=p[0];
            int con=p[1];
            int w=p[2];
            for(int kk[]:lst.get(nd)){
                if(labels.charAt(nd)==labels.charAt(kk[0]) && con==k)continue;
                if(labels.charAt(nd)==labels.charAt(kk[0])){
                    if(dik[kk[0]][con+1]>w+kk[1]){
                        dik[kk[0]][con+1]=w+kk[1];
                        que.offer(new int[]{kk[0],con+1,dik[kk[0]][con+1]});
                    }
                }else if(dik[kk[0]][1]>w+kk[1]){
                        dik[kk[0]][1]=w+kk[1];
                         que.offer(new int[]{kk[0],1,dik[kk[0]][1]});
                    }
            }
        }
        int min=Integer.MAX_VALUE;
        for(int kk:dik[n-1])if(min>kk)min=kk;
        return min==Integer.MAX_VALUE?-1:min;
    }
}
