/*
24. Swap Nodes in Pairs   [Medium]
https://leetcode.com/problems/swap-nodes-in-pairs/

Runtime: 0 ms   Memory: 53 MB

Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

**Example 1:**

**Input:** head = \[1,2,3,4\]

**Output:** \[2,1,4,3\]

**Explanation:**

![](https://assets.leetcode.com/uploads/2020/10/03/swap_ex1.jpg)

**Example 2:**

**Input:** head = \[\]

**Output:** \[\]

**Example 3:**

**Input:** head = \[1\]

**Output:** \[1\]

**Example 4:**

**Input:** head = \[1,2,3\]

**Output:** \[2,1,3\]

**Constraints:**

*   The number of nodes in the list is in the range `[0, 100]`.
*   `0 <= Node.val <= 100`
*/

/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @return {ListNode}
 */

 /**
 how to swap pairs when we have to use a stack

 use a dummy and a prev

 dummy.next = head 
 prev= dummy 

 while(curr)
 {
 stack.push(curr)
 curr=curr.next
 if(stack.length===2)
 {
 
    dummy.next = stack.pop()

 }
 }
 
 
  */



var swapPairs = function(head) {

    const dummyNode = new ListNode(0);
    let prev=dummyNode;
    let curr = head;
    const stack = [];

    while(curr !== null)
    {
        stack.push(curr)
        curr=curr.next;
        if(stack.length===2 || curr==null) 
        {
            while(stack.length >0)
            {
                let node = stack.pop();
                prev.next = node; //dummy -> 2
                prev=prev.next; //2 -> 
            }
        }
    }


    prev.next=null;
    return dummyNode.next;

    
};
