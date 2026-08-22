/**
 * @param {string[]} tokens
 * @return {number}
 */
var evalRPN = function(tokens) {
    const stack = [];

    for(const c  of tokens)
    {
        if(c==='+' || c==='-' || c==='*' || c==='/')
        {
            const b = stack.pop()
            const a = stack.pop()
            switch(c){
                case '+': stack.push(a+b); break;
                case '-': stack.push(a-b); break;
                case '*': stack.push(a*b); break;
                case '/': stack.push(Math.trunc(a/b)); break;
            }
        }
        else{
            stack.push(parseInt(c, 10));
        }
    }

        return stack[0];

    };
