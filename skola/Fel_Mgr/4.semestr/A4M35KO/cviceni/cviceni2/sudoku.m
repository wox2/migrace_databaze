function [sudokuVal] = sudoku(subsquareSize)
   rowSize = subsquareSize^2;
   elementsCount = rowSize^2;
   
   sudokuVal = zeros(elementsCount, elementsCount);
   for row = 1 : elementsCount
       for column = 1 : elementsCount 
            if(row ~= column)
                if(areInSubsquare(row, column, subsquareSize))
                   sudokuVal(row,column) = 1;
                end;    
                if(areInColumn(row, column,rowSize))
                   sudokuVal(row,column) = 1;
                end;    
                if(areInRow(row, column, rowSize))
                   sudokuVal(row,column) = 1;
                end;
            end
       end
   end
   
   %Visualisation part
   g = graph('adj', sudokuVal);
   g = graphcoloring(g);
   radius = 20;
   x = 100;
   y = rowSize*radius+(rowSize*10);
   step = 1;

   for k=1:elementsCount
       g.N(k).Name = '';
       g.N(k).graphicParam(1).width = radius;
       g.N(k).graphicParam(1).height = radius;
       g.N(k).graphicParam(1).x = x+(radius+3)*(step-1);
       g.N(k).graphicParam(1).y = y;
       step=step+1;
       if(mod(k,rowSize)== 0)
           y = y-(radius*1.25);
           step=1;
       end
   end

  graphedit(g);
  graphedit('viewedgesnames', 'off','viewnodesnames', 'off','arrowsvisibility', 'off');

end

function [boolVal] = areInSubsquare(first, second, subsquareSize)
   squareRowSize = subsquareSize^3;
   rowSize = subsquareSize^2;
   firstSubsquareRow = ceil(first / squareRowSize);
   secondSubsquareRow = ceil(second / squareRowSize);
   firstSubsquareColumn = ceil(mod(first,rowSize)/subsquareSize);    
   secondSubsquareColumn = ceil(mod(second,rowSize)/subsquareSize);    

   boolVal = firstSubsquareRow == secondSubsquareRow && firstSubsquareColumn == secondSubsquareColumn;
end

function [boolVal] = areInRow( first, second, matrixSize)
   br1 = floor((first -1)/matrixSize);
   br2 = floor((second-1)/matrixSize);
   boolVal = br1== br2;     
end

function [boolVal] = areInColumn( first, second, matrixSize)
   boolVal = mod(first, matrixSize) == mod(second, matrixSize);
end

  