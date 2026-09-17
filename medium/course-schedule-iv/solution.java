/*
1462. Course Schedule IV   [Medium]
https://leetcode.com/problems/course-schedule-iv/

Runtime: 53 ms   Memory: 46.6 MB

There are a total of `numCourses` courses you have to take, labeled from `0` to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you **must** take course `ai` first if you want to take course `bi`.

*   For example, the pair `[0, 1]` indicates that you have to take course `0` before you can take course `1`.

Prerequisites can also be **indirect**. If course `a` is a prerequisite of course `b`, and course `b` is a prerequisite of course `c`, then course `a` is a prerequisite of course `c`.

You are also given an array `queries` where `queries[j] = [uj, vj]`. For the `jth` query, you should answer whether course `uj` is a prerequisite of course `vj` or not.

Return _a boolean array_ `answer`_, where_ `answer[j]` _is the answer to the_ `jth` _query._

**Example 1:**

![](https://assets.leetcode.com/uploads/2021/05/01/courses4-1-graph.jpg)

**Input:** numCourses = 2, prerequisites = \[\[1,0\]\], queries = \[\[0,1\],\[1,0\]\]
**Output:** \[false,true\]
**Explanation:** The pair \[1, 0\] indicates that you have to take course 1 before you can take course 0.
Course 0 is not a prerequisite of course 1, but the opposite is true.

**Example 2:**

**Input:** numCourses = 2, prerequisites = \[\], queries = \[\[1,0\],\[0,1\]\]
**Output:** \[false,false\]
**Explanation:** There are no prerequisites, and each course is independent.

**Example 3:**

![](https://assets.leetcode.com/uploads/2021/05/01/courses4-3-graph.jpg)

**Input:** numCourses = 3, prerequisites = \[\[1,2\],\[1,0\],\[2,0\]\], queries = \[\[1,0\],\[1,2\]\]
**Output:** \[true,true\]

**Constraints:**

*   `2 <= numCourses <= 100`
*   `0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)`
*   `prerequisites[i].length == 2`
*   `0 <= ai, bi <= numCourses - 1`
*   `ai != bi`
*   All the pairs `[ai, bi]` are **unique**.
*   The prerequisites graph has no cycles.
*   `1 <= queries.length <= 104`
*   `0 <= ui, vi <= numCourses - 1`
*   `ui != vi`
*/

public class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Set<Integer>> adj = new ArrayList<>();
        List<Set<Integer>> isPrereq = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj.add(new HashSet<>());
            isPrereq.add(new HashSet<>());
        }

        for (int[] pre : prerequisites) {
            adj.get(pre[0]).add(pre[1]);
            indegree[pre[1]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbor : adj.get(node)) {
                isPrereq.get(neighbor).add(node);
                isPrereq.get(neighbor).addAll(isPrereq.get(node));
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) q.offer(neighbor);
            }
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            res.add(isPrereq.get(query[1]).contains(query[0]));
        }
        return res;
    }
}
