/*
1161. Maximum Level Sum of a Binary Tree   [Medium]
https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/

Runtime: 61 ms   Memory: 74.6 MB

Given the `root` of a binary tree, the level of its root is `1`, the level of its children is `2`, and so on.

Return the **smallest** level `x` such that the sum of all the values of nodes at level `x` is **maximal**.

**Example 1:**

![](https://assets.leetcode.com/uploads/2019/05/03/capture.JPG)

**Input:** root = \[1,7,0,7,-8,null,null\]
**Output:** 2
**Explanation:** 
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.

**Example 2:**

**Input:** root = \[989,null,10250,98693,-89388,null,null,null,-32127\]
**Output:** 2

**Constraints:**

*   The number of nodes in the tree is in the range `[1, 104]`.
*   `-105 <= Node.val <= 105`
*/

/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number}
 */

 /**
 we need to traverse each level 
 to traverse each level 
 we use BFS 
 we need a max_sum Variable 
 we need a lev_sum variable 
 and res variable initially res = 1
 if (curr > max_sum)
 {
  max_sum = curr 
  level = curr_lev
 }

 how to split levels 

 for a level 
first we push the root to the queue
queue.push(root)


while(queue.length > 0)
{   
   let levels = []

   levels.push(q.pop())

   for(let i in levels.length)
   {
   }
}

we can do BFS of Left and Right for each level and keep on calling BFS 



let BFS(root, )
   */
var maxLevelSum = function(root) {
    let max_sum = -Infinity;
    let ans =0;
    let lev =0;

    const q = [];

    q.push(root);
    while(q.length > 0)
    {
        let levSize = q.length;
        lev+=1;
        let curr_sum =0;

        for(let i=0;i<levSize;i++)
        {
            const node = q.shift();
            curr_sum +=node.val;

        if(node.left!==null)
        {
            q.push(node.left);
        } 
        if(node.right!==null)
        {
            q.push(node.right);
        } 
        }
        if(max_sum < curr_sum)
        {
            max_sum = curr_sum
            ans = lev
        }
    }
 return ans
};
