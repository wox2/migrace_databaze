package javafxdemo;

var c = 2;

bound function multiply(n: Integer): Integer {
    return n * c
}

var x = 5;
var p = bind multiply(x + 1);
println(p);
x = 8;
println(p);
c = 3;
println(p);
