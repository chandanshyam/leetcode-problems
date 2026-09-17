/*
3321. Find X-Sum of All K-Long Subarrays II   [Hard]
https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-ii/

Runtime: 377 ms   Memory: 189 MB

You are given an array `nums` of `n` integers and two integers `k` and `x`.

The **x-sum** of an array is calculated by the following procedure:

*   Count the occurrences of all elements in the array.
*   Keep only the occurrences of the top `x` most frequent elements. If two elements have the same number of occurrences, the element with the **bigger** value is considered more frequent.
*   Calculate the sum of the resulting array.

**Note** that if an array has less than `x` distinct elements, its **x-sum** is the sum of the array.

Return an integer array `answer` of length `n - k + 1` where `answer[i]` is the **x-sum** of the subarray `nums[i..i + k - 1]`.

**Example 1:**

**Input:** nums = \[1,1,2,2,3,4,2,3\], k = 6, x = 2

**Output:** \[6,10,12\]

**Explanation:**

*   For subarray `[1, 1, 2, 2, 3, 4]`, only elements 1 and 2 will be kept in the resulting array. Hence, `answer[0] = 1 + 1 + 2 + 2`.
*   For subarray `[1, 2, 2, 3, 4, 2]`, only elements 2 and 4 will be kept in the resulting array. Hence, `answer[1] = 2 + 2 + 2 + 4`. Note that 4 is kept in the array since it is bigger than 3 and 1 which occur the same number of times.
*   For subarray `[2, 2, 3, 4, 2, 3]`, only elements 2 and 3 are kept in the resulting array. Hence, `answer[2] = 2 + 2 + 2 + 3 + 3`.

**Example 2:**

**Input:** nums = \[3,8,7,8,7,5\], k = 2, x = 2

**Output:** \[11,15,15,15,12\]

**Explanation:**

Since `k == x`, `answer[i]` is equal to the sum of the subarray `nums[i..i + k - 1]`.

**Constraints:**

*   `nums.length == n`
*   `1 <= n <= 105`
*   `1 <= nums[i] <= 109`
*   `1 <= x <= k <= nums.length`
*/

import java.util.*;

class Solution {
    private static final class Entry {
        final int f;  
        final int v;  
        Entry(int f, int v) { this.f = f; this.v = v; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Entry)) return false;
            Entry e = (Entry) o;
            return f == e.f && v == e.v;
        }
        @Override public int hashCode() { return Objects.hash(f, v); }
    }

    private final Comparator<Entry> byFreqDescValDesc = (a, b) -> {
        if (a.f != b.f) return Integer.compare(b.f, a.f);
        return Integer.compare(b.v, a.v);
    };

    private Map<Integer, Integer> freq;
    private TreeSet<Entry> top, rest;
    private long currSum;

    private void insertVal(int v, int x) {
        int f = freq.getOrDefault(v, 0);

        if (f > 0) {
            Entry old = new Entry(f, v);
            if (top.remove(old)) {
                currSum -= 1L * f * v;
            } else {
                rest.remove(old);
            }
        }

        f += 1;
        freq.put(v, f);
        Entry now = new Entry(f, v);
        top.add(now);
        currSum += 1L * f * v;

        if (top.size() > x) {
            Entry smallestTop = top.last();
            currSum -= 1L * smallestTop.f * smallestTop.v;
            rest.add(smallestTop);
            top.remove(smallestTop);
        }
    }

    private void eraseVal(int v, int x) {
        Integer cur = freq.get(v);
        if (cur == null || cur == 0) return;
        int f = cur;

        Entry curEntry = new Entry(f, v);
        if (top.remove(curEntry)) {
            currSum -= 1L * f * v;
        } else {
            rest.remove(curEntry);
        }

        f -= 1;
        if (f == 0) {
            freq.remove(v);
        } else {
            freq.put(v, f);
            rest.add(new Entry(f, v));
        }

        if (top.size() < x && !rest.isEmpty()) {
            Entry bestRest = rest.first();
            rest.remove(bestRest);
            top.add(bestRest);
            currSum += 1L * bestRest.f * bestRest.v;
        }
    }

    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int sz = n - k + 1;
        long[] ans = new long[sz];

        freq = new HashMap<>(Math.max(16, n * 2));
        top = new TreeSet<>(byFreqDescValDesc);
        rest = new TreeSet<>(byFreqDescValDesc);
        currSum = 0L;

        for (int i = 0; i < k; i++) insertVal(nums[i], x);
        ans[0] = currSum;

        for (int l = 1, r = k; r < n; l++, r++) {
            eraseVal(nums[l - 1], x);
            insertVal(nums[r], x);
            ans[l] = currSum;
        }
        return ans;
    }
}
