% Function approximation
clear;
x = [ 0 1.26 2.51 3.77 5.03 6.28];
f = [ 0.01 1.16 0.70 -0.34 -0.80 0.2100];


alfa = 2;
beta = 10;

c = ApproxMatrix(alfa,beta,x,f);

[U,pred] = dijkstra(c,1);

    q = size(U,1);
    backtrace = q;
    while pred(q,1) ~= -inf
        q = pred(q,1);
        backtrace = [backtrace q];
    end

    backtrace = sort(backtrace);
    plot(x,f,'g')
    hold on;
    plot(x(backtrace),f(backtrace),'r') 