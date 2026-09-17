/*
1086. High Five   [Easy]
https://leetcode.com/problems/high-five/

Runtime: 4 ms   Memory: 46.6 MB

Given a list of the scores of different students, `items`, where `items[i] = [IDi, scorei]` represents one score from a student with `IDi`, calculate each student's **top five average**.

Return _the answer as an array of pairs_ `result`_, where_ `result[j] = [IDj, topFiveAveragej]` _represents the student with_ `IDj` _and their **top five average**. Sort_ `result` _by_ `IDj` _in **increasing order**._

A student's **top five average** is calculated by taking the sum of their top five scores and dividing it by `5` using **integer division**.

**Example 1:**

**Input:** items = \[\[1,91\],\[1,92\],\[2,93\],\[2,97\],\[1,60\],\[2,77\],\[1,65\],\[1,87\],\[1,100\],\[2,100\],\[2,76\]\]
**Output:** \[\[1,87\],\[2,88\]\]
**Explanation:** 
The student with ID = 1 got scores 91, 92, 60, 65, 87, and 100. Their top five average is (100 + 92 + 91 + 87 + 65) / 5 = 87.
The student with ID = 2 got scores 93, 97, 77, 100, and 76. Their top five average is (100 + 97 + 93 + 77 + 76) / 5 = 88.6, but with integer division their average converts to 88.

**Example 2:**

**Input:** items = \[\[1,100\],\[7,100\],\[1,100\],\[7,100\],\[1,100\],\[7,100\],\[1,100\],\[7,100\],\[1,100\],\[7,100\]\]
**Output:** \[\[1,100\],\[7,100\]\]

**Constraints:**

*   `1 <= items.length <= 1000`
*   `items[i].length == 2`
*   `1 <= IDi <= 1000`
*   `0 <= scorei <= 100`
*   For each `IDi`, there will be **at least** five scores.
*/

class Solution {

    private int K;

    public int[][] highFive(int[][] items) {
        this.K = 5;
        Arrays.sort(
                items,
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] a, int[] b) {
                        if (a[0] != b[0])
                            // item with lower id goes first
                            return a[0] - b[0];
                        // in case of tie for ids, item with higher score goes first
                        return b[1] - a[1];
                    }
                });
        List<int[]> solution = new ArrayList<>();
        int n = items.length;
        int i = 0;
        while (i < n) {
            int id = items[i][0];
            int sum = 0;
            // obtain total using the top 5 scores
            for (int k = i; k < i + this.K; ++k)
                sum += items[k][1];
            // ignore all the other scores for the same id
            while (i < n && items[i][0] == id)
                i++;
            solution.add(new int[] {id, sum / this.K});
        }
        int[][] solutionArray = new int[solution.size()][];
        return solution.toArray(solutionArray);
    }
}
