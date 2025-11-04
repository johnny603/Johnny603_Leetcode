/**
 * @param {Function[]} functions
 * @return {Function}
 */
var compose = function(functions) {
    return function(x) {
        // iterate from last function to first
        for (let i = functions.length - 1; i >= 0; i--) {
            x = functions[i](x); // apply function
        }
        return x;
    };
};


/**
 * const fn = compose([x => x + 1, x => 2 * x])
 * fn(4) // 9
 */