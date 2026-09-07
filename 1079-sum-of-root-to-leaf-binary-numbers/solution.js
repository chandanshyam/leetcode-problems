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
/*
some quesyions to check 
if root 0 return 0 

*/
var sumRootToLeaf = function(root) {
    let sum =0;
    
    function dfs(node, current)
    {
        if(node === null)
            {
                return;
            }
        
        current = current * 2 + node.val;
        
        if(node.left === null && node.right === null)
            {
                sum += current;
                return;
                
            }
        dfs(node.left, current);
        dfs(node.right, current);
    }
    
    dfs(root, 0);
    
    return sum;
    
};
