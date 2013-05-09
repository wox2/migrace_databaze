function [r] = swapEdges(circuit, firstIndex, secondIndex, distances)
    r = circuit;
    endFirst = r(2, firstIndex);
    endSecond = r(2, secondIndex);
    startFirst = r(1, firstIndex);
    startSecond = r(1, secondIndex);
    r(2, firstIndex) = startSecond;
    r(1, secondIndex) = endFirst;
    
    r(3, firstIndex) = distances(startFirst, startSecond);
    r(3, secondIndex) = distances(endFirst, endSecond);
    
    minI = min(firstIndex, secondIndex);
    maxI = max(firstIndex, secondIndex);
    %swap edge vertices
    for i = minI + 1 : maxI -1
        tmp = r(1, i);
        r(1, i) = r(2, i);
        r(2, i) = tmp;
    end
    
    %swap edges to make path
    for i = 1 : ceil((maxI - minI)/2) - 1
        r = swapColumns(r, firstIndex + i, secondIndex - i, 1);
        r = swapColumns(r, firstIndex + i, secondIndex - i, 2);
        r = swapColumns(r, firstIndex + i, secondIndex - i, 3);
    end
end

function [array]  = swapColumns(array, firstColumn, secondColumn, row)
    tmp = array(row, firstColumn);
    array(row, firstColumn) = array(row, secondColumn);
    array(row, secondColumn) = tmp;
end

