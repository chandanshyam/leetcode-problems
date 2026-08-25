var doubleIt = function(head) {
    const reverse = (node) => {
        let prev = null, curr = node;
        while (curr) {
            let nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    };

    let reversedHead = reverse(head);
    let curr = reversedHead;
    let carry = 0;

    let prev = null;
    while (curr) {
        let sum = curr.val * 2 + carry;
        curr.val = sum % 10;
        carry = Math.floor(sum / 10);
        prev = curr;
        curr = curr.next;
    }

    if (carry > 0) {
        prev.next = new ListNode(carry);
    }

    return reverse(reversedHead);
};

