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
