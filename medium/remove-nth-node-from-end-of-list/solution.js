/*
19. Remove Nth Node From End of List   [Medium]
https://leetcode.com/problems/remove-nth-node-from-end-of-list/

Runtime: 0 ms   Memory: 54.5 MB

Given the `head` of a linked list, remove the `nth` node from the end of the list and return its head.

**Example 1:**

![](https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg)

**Input:** head = \[1,2,3,4,5\], n = 2
**Output:** \[1,2,3,5\]

**Example 2:**

**Input:** head = \[1\], n = 1
**Output:** \[\]

**Example 3:**

**Input:** head = \[1,2\], n = 1
**Output:** \[1\]

**Constraints:**

*   The number of nodes in the list is `sz`.
*   `1 <= sz <= 30`
*   `0 <= Node.val <= 100`
*   `1 <= n <= sz`

**Follow up:** Could you do this in one pass?
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
 * @param {number} n
 * @return {ListNode}
 */
var removeNthFromEnd = function(head, n) {

    let dummy = new ListNode(0)
    dummy.next = head;
    let prev = dummy;

    let diff = Length(head) - n;

    for(let i=0; i<diff;i++)
    {
        prev = prev.next;
    }

    prev.next = prev.next.next;


    return dummy.next;
    
};

function Length(head)
{
    let curr = head;
    let len =0;
    while(curr!==null)
    {
        len++;
        curr=curr.next;
    }
    return len;
}
