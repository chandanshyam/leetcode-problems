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
    public int maxLevelSum(TreeNode root) {
        int sum = 0, level = 1, max_sum = Integer.MIN_VALUE, ans = 1;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while(q.size()>1) {
            TreeNode temp = q.poll();
            if(temp!=null) {
                sum += temp.val;
                if(temp.left!=null) q.add(temp.left);
                if(temp.right!=null) q.add(temp.right);
            }else{
                if(sum>max_sum) {
                    max_sum = sum;
                    ans = level;
                }
                level++;
                q.add(null);
                sum = 0;
            }
        }
        if(sum>max_sum) {
            max_sum = sum;
            ans = level;
        }
        return ans;
    }
}
