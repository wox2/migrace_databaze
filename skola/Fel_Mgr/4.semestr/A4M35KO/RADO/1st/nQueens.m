% Assigment n1: n Queens problem
% Author: Radovan Murin, for course Kombinatoricka Optimalizace 
% Course: Thursdays 
% FEE CTU 2012, Spring Semester
% murinrad@fel.cvut.cz
% 

clear;
n=5;
matSize = n^2;     %16
adjMat = zeros(matSize,matSize);

for i=1:matSize
    for j=i:getNextRound(i,n);
        if(i==j) continue;end
        adjMat(i,j)=1;
        adjMat(j,i)=1;
        
    end
end
a=1;
for z=1:n:matSize
% z
for i=z:a*n
%     i
    for j=i:n:(i+(matSize-n));
        if(j>matSize) break;end
%          j
        if(i==j) continue;end
        adjMat(i,j)=1;
        adjMat(j,i)=1;
        
    end
end
a=a+1;
end


for i=1:matSize
    for j=i:(n+1):matSize
        if(j>matSize) break;end;
        if(i==j || mod(i,n)==0) continue;end
        
        adjMat(i,j)=1;
        adjMat(j,i)=1;
        if(mod(j,n)==0) break; end;
    end
end

for i=1:matSize
    if(mod(i,n)==1) continue;end;
    a=1;
    for j=i:(n-1):matSize
        if(j>matSize) break;end;
        if(i==j) continue;end
        i
        j
        adjMat(i,j)=1;
        adjMat(j,i)=1;
        if(mod(j,n)==1) break; end;
    end
end


%  Mat
g= graph('adj',adjMat);
x = 500;
y = 500;
inc = -50;
g = graphcoloring(g);


for i=1:matSize
    if(mod(i-1,n)==0)
         y=500;
    x=x+inc;
    end
         g.N(i).graphicParam(1).x = y;
        g.N(i).graphicParam(1).y = x;
        y=y-inc;
   
    
end

 g.adj=zeros(matSize,matSize);
graphedit('viewedgesnames', 'off','viewnodesnames', 'off','arrowsvisibility', 'off')

graphedit(g)

        
