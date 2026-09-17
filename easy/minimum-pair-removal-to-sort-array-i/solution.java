/*
3507. Minimum Pair Removal to Sort Array I   [Easy]
https://leetcode.com/problems/minimum-pair-removal-to-sort-array-i/

Runtime: 3 ms   Memory: 44.6 MB

Given an array `nums`, you can perform the following operation any number of times:

*   Select the **adjacent** pair with the **minimum** sum in `nums`. If multiple such pairs exist, choose the leftmost one.
*   Replace the pair with their sum.

Return the **minimum number of operations** needed to make the array **non-decreasing**.

An array is said to be **non-decreasing** if each element is greater than or equal to its previous element (if it exists).

**Example 1:**

**Input:** nums = \[5,2,3,1\]

**Output:** 2

**Explanation:**

*   The pair `(3,1)` has the minimum sum of 4. After replacement, `nums = [5,2,4]`.
*   The pair `(2,4)` has the minimum sum of 6. After replacement, `nums = [5,6]`.

The array `nums` became non-decreasing in two operations.

**Example 2:**

**Input:** nums = \[1,2,2\]

**Output:** 0

**Explanation:**

The array `nums` is already sorted.

**Constraints:**

*   `1 <= nums.length <= 50`
*   `-1000 <= nums[i] <= 1000`
*/

class Solution {
    public int minPair(List<Integer> v) {
        int minSum = (int)1e9;
        int pos = -1;

        for(int i = 0; i < v.size() - 1; i ++){
            int sum = v.get(i) + v.get(i + 1);
            if (sum < minSum) {
                minSum = sum;
                pos = i;
            }
        }
        return pos;
    }

    public void mergePair(List<Integer> v, int pos) {
        v.set(pos, v.get(pos) + v.get(pos + 1));
        v.remove(pos + 1);
    }

    public int minimumPairRemoval(int[] nums) {
        List<Integer> v = new ArrayList<>();
        for(int x : nums) v.add(x);

        int ops = 0;
        while(!isSorted(v)){
            int pos = minPair(v);
            mergePair(v, pos);
            ops++;
        }
        return ops;
    }

    private boolean isSorted(List <Integer> v) {
        for(int i = 0; i < v.size() - 1; i ++){
            if(v.get(i) > v.get(i + 1)) return false;
        }
        return true;
    }
}
