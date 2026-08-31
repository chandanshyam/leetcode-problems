/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode[]} lists
 * @return {ListNode}
 */
function mergeKLists(lists) {
    let nodes = [];
    let dummy = new ListNode(0);
    let point = dummy;
    lists.forEach((l) => {
        while (l) {
            nodes.push(l.val);
            l = l.next;
        }
    });
    nodes
        .sort((a, b) => a - b)
        .forEach((n) => {
            point.next = new ListNode(n);
            point = point.next;
        });
    return dummy.next;
}
