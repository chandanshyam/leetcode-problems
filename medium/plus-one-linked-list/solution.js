/*
369. Plus One Linked List   [Medium]
https://leetcode.com/problems/plus-one-linked-list/

Runtime: 40 ms   Memory: 53.8 MB

Given a non-negative integer represented as a linked list of digits, _plus one to the integer_.

The digits are stored such that the most significant digit is at the `head` of the list.

**Example 1:**

**Input:** head = \[1,2,3\]
**Output:** \[1,2,4\]

**Example 2:**

**Input:** head = \[0\]
**Output:** \[1\]

**Constraints:**

*   The number of nodes in the linked list is in the range `[1, 100]`.
*   `0 <= Node.val <= 9`
*   The number represented by the linked list does not contain leading zeros except for the zero itself.
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
var plusOne = function(head) {


    
    let dummy = new ListNode(0);
    dummy.next = head;
    let curr = head;


    let not_nine = dummy;

    while(curr!== null)
    {
        if(curr.val!==9){ not_nine = curr}
        curr=curr.next;
    }

    not_nine.val+=1
    curr=not_nine.next;

    while(curr!== null)
    {
        curr.val=0
        curr=curr.next;
    }

    if(dummy.val === 1)
    {
        return dummy;
    }

    return dummy.next;
    
};
