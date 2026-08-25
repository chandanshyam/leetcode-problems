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
