"""
2093. Minimum Cost to Reach City With Discounts   [Medium]
https://leetcode.com/problems/minimum-cost-to-reach-city-with-discounts/

Runtime: 1568 ms   Memory: 52.4 MB

A series of highways connect `n` cities numbered from `0` to `n - 1`. You are given a 2D integer array `highways` where `highways[i] = [city1i, city2i, tolli]` indicates that there is a highway that connects `city1i` and `city2i`, allowing a car to go from `city1i` to `city2i` **and vice versa** for a cost of `tolli`.

You are also given an integer `discounts` which represents the number of discounts you have. You can use a discount to travel across the `ith` highway for a cost of `tolli / 2` (**integer** **division**). Each discount may only be used **once**, and you can only use at most **one** discount per highway.

Return _the **minimum total cost** to go from city_ `0` _to city_ `n - 1`_, or_ `-1` _if it is not possible to go from city_ `0` _to city_ `n - 1`_._

**Example 1:**  
![](https://assets.leetcode.com/uploads/2021/11/29/image-20211129222429-1.png)

**Input:** n = 5, highways = \[\[0,1,4\],\[2,1,3\],\[1,4,11\],\[3,2,3\],\[3,4,2\]\], discounts = 1
**Output:** 9
**Explanation:**
Go from 0 to 1 for a cost of 4.
Go from 1 to 4 and use a discount for a cost of 11 / 2 = 5.
The minimum cost to go from 0 to 4 is 4 + 5 = 9.

**Example 2:**  
![](https://assets.leetcode.com/uploads/2021/11/29/image-20211129222650-4.png)

**Input:** n = 4, highways = \[\[1,3,17\],\[1,2,7\],\[3,2,5\],\[0,1,6\],\[3,0,20\]\], discounts = 20
**Output:** 8
**Explanation:**
Go from 0 to 1 and use a discount for a cost of 6 / 2 = 3.
Go from 1 to 2 and use a discount for a cost of 7 / 2 = 3.
Go from 2 to 3 and use a discount for a cost of 5 / 2 = 2.
The minimum cost to go from 0 to 3 is 3 + 3 + 2 = 8.

**Example 3:**  
![](https://assets.leetcode.com/uploads/2021/11/29/image-20211129222531-3.png)

**Input:** n = 4, highways = \[\[0,1,3\],\[2,3,2\]\], discounts = 0
**Output:** -1
**Explanation:**
It is impossible to go from 0 to 3 so return -1.

**Constraints:**

*   `2 <= n <= 1000`
*   `1 <= highways.length <= 1000`
*   `highways[i].length == 3`
*   `0 <= city1i, city2i <= n - 1`
*   `city1i != city2i`
*   `0 <= tolli <= 105`
*   `0 <= discounts <= 500`
*   There are no duplicate highways.
"""

class Solution:
    def minimumCost(
        self, n: int, highways: List[List[int]], discounts: int
    ) -> int:
        # Construct the graph from the given highways array
        graph = [[] for _ in range(n)]
        for highway in highways:
            u, v, toll = highway
            graph[u].append((v, toll))
            graph[v].append((u, toll))

        # Min-heap priority queue to store tuples of (cost, city, discounts used)
        pq = [(0, 0, 0)]  # Start from city 0 with cost 0 and 0 discounts used

        # 2D array to track minimum distance to each city with a given number of discounts used
        dist = [[float("inf")] * (discounts + 1) for _ in range(n)]
        dist[0][0] = 0

        visited = [[False] * (discounts + 1) for _ in range(n)]

        while pq:
            current_cost, city, discounts_used = heapq.heappop(pq)

            # Skip processing if already visited with the same number of discounts used
            if visited[city][discounts_used]:
                continue
            visited[city][discounts_used] = True

            # Explore all neighbors of the current city
            for neighbor, toll in graph[city]:

                # Case 1: Move to the neighbor without using a discount
                if current_cost + toll < dist[neighbor][discounts_used]:
                    dist[neighbor][discounts_used] = current_cost + toll
                    heapq.heappush(
                        pq,
                        (
                            dist[neighbor][discounts_used],
                            neighbor,
                            discounts_used,
                        ),
                    )

                # Case 2: Move to the neighbor using a discount if available
                if discounts_used < discounts:
                    new_cost_with_discount = current_cost + toll // 2
                    if (
                        new_cost_with_discount
                        < dist[neighbor][discounts_used + 1]
                    ):
                        dist[neighbor][
                            discounts_used + 1
                        ] = new_cost_with_discount
                        heapq.heappush(
                            pq,
                            (
                                new_cost_with_discount,
                                neighbor,
                                discounts_used + 1,
                            ),
                        )

        # Find the minimum cost to reach city n-1 with any number of discounts used
        min_cost = min(dist[n - 1])
        return -1 if min_cost == float("inf") else min_cost
