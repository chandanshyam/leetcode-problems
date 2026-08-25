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
