% Function approximation by Radovan Murin
% Combinatorical optimization
% Spring semester 2012
function [U,pred] = dijkstra(W,source)
nOfNodes = size(W,1);
nodes = [1:nOfNodes];

U = inf*ones(nOfNodes,1);
U(source) = 0;
pred = inf*ones(nOfNodes,1);
pred(source) = -inf;
D = [];

vis = setdiff(nodes,D);

c1 = 0;
for i=1:size(vis,2)
    if U(vis(i))~=inf
        c1=1;
    end
end

while(c1==1)
    x = vis(1);
   for i=1:size(vis,2)
    if U(x)>U(vis(i))
        x=vis(i);
    end
   end     
   D = union(D,x);
    
  

    for p=1:size(W,1)
        if(W(x,p)~=0)
            if U(x)+W(x,p) < U(p)
                U(p) = U(x)+W(x,p);
                pred(p) = x;
            end
        end
    end   
vis = setDiff(nodes,D);
c1 = 0;
for i=1:size(vis,2)
    if U(vis(i))~=inf
        c1=1;
    end
end
end







