/*
101. Symmetric Tree   [Easy]
https://leetcode.com/problems/symmetric-tree/

Runtime: 0 ms   Memory: 42.1 MB

Given the `root` of a binary tree, _check whether it is a mirror of itself_ (i.e., symmetric around its center).

**Example 1:**

![](https://assets.leetcode.com/uploads/2021/02/19/symtree1.jpg)

**Input:** root = \[1,2,2,3,4,4,3\]
**Output:** true

**Example 2:**

![](https://assets.leetcode.com/uploads/2021/02/19/symtree2.jpg)

**Input:** root = \[1,2,2,null,3,null,3\]
**Output:** false

**Constraints:**

*   The number of nodes in the tree is in the range `[1, 1000]`.
*   `-100 <= Node.val <= 100`

**Follow up:** Could you solve it both recursively and iteratively?
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {

        if(root==null)
        {
            return true;
        }
        return isMirror(root.left, root.right);

    }


    public boolean isMirror(TreeNode a, TreeNode b)
    {
         if(a==null && b==null)
        {
            return true;
        }
        
        if(a==null || b==null)
        {
            return false;
        }

       

        if(a.val != b.val)
        {
            return false;
        }

        return isMirror(a.left, b.right) && isMirror(b.left, a.right);
    }
}
