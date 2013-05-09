% Function approximation
clear;
load('czechRepublic.mat')


alfa = 10;
beta = 10;

c = secondApprox(x',y');

[U,pred] = dijkstra(c,1);

    q = size(U,1);
    backtrace = q;
    while pred(q,1) ~= -inf
        q = pred(q,1);
        backtrace = [backtrace q];
    end

    backtrace = sort(backtrace);
    plot(x,y,'g')
    hold on;
    plot(x(backtrace),y(backtrace),'r') 