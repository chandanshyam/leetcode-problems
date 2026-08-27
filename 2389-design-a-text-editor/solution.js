// class Node
// {
//     constructor(val=null, next=null, prev=null)
//     {
//         this.val = val;
//         this.next = next;
//         this.prev = prev;
//     }
// }


var TextEditor = function() {
    this.left = ''
    this.right = ''
    
};

/** 
 * @param {string} text
 * @return {void}
 */
TextEditor.prototype.addText = function(text) {
    this.left += text;
};

/** 
 * @param {number} k
 * @return {number}
 */
TextEditor.prototype.deleteText = function(k) {
    k= Math.min(k, this.left.length);

    const deletedText = this.left.slice(-k);
    this.left = this.left.slice(0, -k);

    return deletedText.length;
};

/** 
 * @param {number} k
 * @return {string}
 */
TextEditor.prototype.cursorLeft = function(k) {
    k = Math.min(k, this.left.length);

    const cutted = this.left.slice(-k);
    this.right = cutted + this.right;
    this.left = this.left.slice(0, -k);

    return this.left.slice(-10);
    
};

/** 
 * @param {number} k
 * @return {string}
 */
TextEditor.prototype.cursorRight = function(k) {
    k = Math.min(k, this.right.length);

    const cutted = this.right.slice(0, k);
    this.left += cutted;
    this.right = this.right.slice(k);

    return this.left.slice(-10);
    
};

/** 
 * Your TextEditor object will be instantiated and called as such:
 * var obj = new TextEditor()
 * obj.addText(text)
 * var param_2 = obj.deleteText(k)
 * var param_3 = obj.cursorLeft(k)
 * var param_4 = obj.cursorRight(k)
 */
