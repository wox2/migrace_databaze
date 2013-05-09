% Assigment n1: Sudoku
% Author: Radovan Murin, for course Kombinatoricka Optimalizace 
% FEE CVUT 2012, Spring Semester
% murinrad@fel.cvut.cz
% 

clear;
baseMatSize = 3; 
nOfSmallMatrices = baseMatSize^2; %4
smallMatElems = nOfSmallMatrices; %4
pseudoLenght = baseMatSize^3;
matSize = baseMatSize^4 %16
diagDownLeft = baseMatSize-1; 
diagDownRight = baseMatSize+1;
adjMat = zeros(matSize,matSize);
%Diagonals
for n = 1:+smallMatElems:matSize
    
   for i = n:+diagDownRight:n+smallMatElems
       for j = i+diagDownRight:+diagDownRight:n+smallMatElems
           adjMat(i,j)=1;
          % adjMat(j,i)=1;
       end    
   end 
end

for n = 1:+smallMatElems:matSize
    
   for i = n+diagDownLeft:+diagDownLeft:n+smallMatElems-diagDownLeft
       if(mod(i,smallMatElems) ==0) continue;end
       
       for j = i+diagDownLeft:+diagDownLeft:n+smallMatElems-diagDownLeft
           if(mod(j,smallMatElems) ==0) continue;end
           adjMat(i,j)=1;
          % adjMat(j,i)=1;
       end    
   end 
end
%Lines

matLen = (baseMatSize^2)-1 + (baseMatSize-2)*baseMatSize^2 + baseMatSize;
Mat = zeros(smallMatElems,smallMatElems);
cP = 0;
for n = 1:+pseudoLenght:matSize %Orange
   % disp('Orange')
    
    for i=n:+baseMatSize:n+smallMatElems-1 %Pink
        cP=cP+1;
      %  disp('Pink')        
         cY =0;
        for j=i:+smallMatElems:i+matLen; %Green
            for k=j:j+baseMatSize-1 %Yellow
                cY =cY+1;   
                Mat(cP,cY)=k; 
            end
        end
    end
   
end
for n=1:smallMatElems
    for i=1:smallMatElems
        for j=i:smallMatElems
            if(i==j)continue;end
            adjMat(Mat(n,i),Mat(n,j)) = 1;
           % adjMat(Mat(n,j),Mat(n,i)) = 1;
        end
    end
end

for n=1:smallMatElems
    for i=1:smallMatElems
        for j=i:smallMatElems
            if(i==j)continue;end
            adjMat(Mat(i,n),Mat(j,n)) = 1;
            %adjMat(Mat(j,n),Mat(i,n)) = 1;
        end
    end
end

g= graph('adj',adjMat);
x = 500;
y = 500;
inc = -50;
g = graphcoloring(g);
for n=1:smallMatElems
    for i=1:smallMatElems
        g.N(Mat(n,i)).graphicParam(1).x = y;
        g.N(Mat(n,i)).graphicParam(1).y = x;
        
    
       y=y-inc;
    end
    y=500;
    x=x+inc;
end




g.adj=zeros(matSize,matSize);
graphedit('viewedgesnames', 'off','viewnodesnames', 'off','arrowsvisibility', 'off')

graphedit(g)

        
