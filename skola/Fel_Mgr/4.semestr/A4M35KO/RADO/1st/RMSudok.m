% Assigment n1: Sudoku
% Author: Radovan Murin, for course Kombinatoricka Optimalizace 
% Course: Thursdays 
% FEE CTU 2012, Spring Semester
% murinrad@fel.cvut.cz
% 

clear;
baseMatSize = 2; 
nOfSmallMatrices = baseMatSize^2; %4
smallMatElems = nOfSmallMatrices; %4
pseudoLenght = baseMatSize^3;
matSize = baseMatSize^4;     %16
diagDownLeft = baseMatSize-1; 
diagDownRight = baseMatSize+1;
adjMat = zeros(matSize,matSize);
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
%Colls
for n=1:smallMatElems
    for i=1:smallMatElems
        for j=i:smallMatElems
            if(i==j)continue;end
            adjMat(Mat(i,n),Mat(j,n)) = 1;
            %adjMat(Mat(j,n),Mat(i,n)) = 1;
        end
    end
end


% Small squares to complete graphs
for n=1:+baseMatSize:smallMatElems %cols
    
    for i=1:+baseMatSize:smallMatElems %rows
        
        for c1=n:n+baseMatSize-1
            for c2=i:i+baseMatSize-1  
                 for c3=n:n+baseMatSize-1
                      for c4=i:i+baseMatSize-1  
                          if (Mat(c1,c2)==Mat(c3,c4)) continue;end
                          if(adjMat(Mat(c3,c4),Mat(c1,c2))==1) continue;end 
                        adjMat(Mat(c1,c2),Mat(c3,c4)) = 1;
                
                      end
                    end
               
                
            end
        end
        
    end
end


%  Mat
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




% g.adj=zeros(matSize,matSize);
graphedit('viewedgesnames', 'off','viewnodesnames', 'off','arrowsvisibility', 'off')

graphedit(g)

        
