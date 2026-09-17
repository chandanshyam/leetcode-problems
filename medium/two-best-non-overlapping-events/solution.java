/*
2054. Two Best Non-Overlapping Events   [Medium]
https://leetcode.com/problems/two-best-non-overlapping-events/

Runtime: 35 ms   Memory: 177.2 MB

You are given a **0-indexed** 2D integer array of `events` where `events[i] = [startTimei, endTimei, valuei]`. The `ith` event starts at `startTimei` and ends at `endTimei`, and if you attend this event, you will receive a value of `valuei`. You can choose **at most** **two** **non-overlapping** events to attend such that the sum of their values is **maximized**.

Return _this **maximum** sum._

Note that the start time and end time is **inclusive**: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time `t`, the next event must start at or after `t + 1`.

**Example 1:**

![](https://assets.leetcode.com/uploads/2026/01/03/untitled-diagramdrawio.png)

**Input:** events = \[\[1,3,2\],\[4,5,2\],\[2,4,3\]\]
**Output:** 4
**Explanation:** Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.

**Example 2:**

![Example 1 Diagram](https://assets.leetcode.com/uploads/2026/01/03/2054b.png)

**Input:** events = \[\[1,3,2\],\[4,5,2\],\[1,5,5\]\]
**Output:** 5
**Explanation:** Choose event 2 for a sum of 5.

**Example 3:**

![](https://assets.leetcode.com/uploads/2026/01/03/2054c.png)

**Input:** events = \[\[1,5,3\],\[1,5,1\],\[6,6,5\]\]
**Output:** 8
**Explanation:** Choose events 0 and 2 for a sum of 3 + 5 = 8.

**Constraints:**

*   `2 <= events.length <= 105`
*   `events[i].length == 3`
*   `1 <= startTimei <= endTimei <= 109`
*   `1 <= valuei <= 106`
*/

import java.util.*;

class Solution {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);

        int n = events.length;
        int[] endT = new int[n];
        int[] best = new int[n];

        for (int i = 0; i < n; i++) {
            endT[i] = events[i][1];
            best[i] = events[i][2];
            if (i > 0) best[i] = Math.max(best[i], best[i - 1]);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int st = events[i][0];
            int val = events[i][2];

            int l = 0, r = n - 1, idx = -1;
            while (l <= r) {
                int mid = (l + r) >>> 1;
                if (endT[mid] < st) {
                    idx = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }

            if (idx != -1) ans = Math.max(ans, val + best[idx]);
            ans = Math.max(ans, val);
        }

        return ans;
    }
}
