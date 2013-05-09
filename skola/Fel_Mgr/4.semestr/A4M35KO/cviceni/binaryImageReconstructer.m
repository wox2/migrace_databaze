function [c, F] = binaryImageReconstructer()
    clear all;
    load projectionData.mat

    n1 = size(sumC, 2);
    n2 = size(sumR, 2);
    countOfIterations = 62;
    
    I = zeros(n1, n2);
    cPrev = zeros(n1 + n2, n1 + n2 );
    b = [sumR -sumC]';
    u = [zeros(n1,n1),ones(n1, n2); ...
        zeros(n2, n1), zeros(n2, n2)];
    l = zeros(n2 + n1, n1 + n2);

    for ic = 1 : countOfIterations
        c = fce(cPrev, I);
        cPrev = c;
        G = graph;
        %generate b, l, u, c
        %...
        % solve minimal cost flow problem
        F = G.mincostflow(c,l,u,b);
        I = F (1:20, 21:40);
        
        subplot(11,10,ic);
        imagesc(logical(I));
        colormap(gray);
        axis off;
        axis square;
        ic
    end;    
end

function [c] = fce(cPrev, I)
    c_size = size(cPrev, 1);
    c = zeros (c_size, c_size);
    
    imageSize = size(I, 1);
    white = 1;
    black = 0;
    
    for i = 2 : imageSize -1 
        for j = 2 : imageSize -1
            %section 2 check existence of Neuman's and Moore neighbourhood
            if hasNeighbourhood(i, j, imageSize);
                %white section - eg 3, 4 and 5
                if (I(i, j) == white) 
                    %whites in 
                    whitesNeumansNeigh = countWhitesInMooresNeighbourhood(i, j, I, white); 
                    %section 3
                    if whitesNeumansNeigh == 0
                        c(i, imageSize + j) = 1;
                    %section 4
                    else if whitesNeumansNeigh == 1
                           c(i, imageSize + j) = 0.2;
                        %section 5
                        else if whitesNeumansNeigh == 2
                                c(i, imageSize + j) = 0.1;
                            end;
                        end;
                    end;
                %BLACK - section 6    
                else if I (i, j) == black && isBetweenWhites(i, j, I, white)
                        c(i, imageSize + j) = -0.1;
                    end;
                end;
            end;
            % section 7 
            c(i, imageSize  + j) = c(i, imageSize  + j) + 0.5 * cPrev(i, imageSize  + j); 
        end;
    end;
end

function [retVal] = hasNeighbourhood(i, j, imageSize)
    retVal = imageSize > i  &&  i > 1 && imageSize > j && j > 1; 
end

%check if the central point i, j is 
function [sumWhites] = countWhitesInMooresNeighbourhood(i, j, image, white)
    sumWhites = 0;    
    for k = i -1 : i + 1
        for l = j - 1 : j + 1
            if image(k, l) == white && (k ~= i || l ~= j)
                sumWhites = sumWhites+1;
            end;
        end;    
    end;
end

function [isBetween] = isBetweenWhites(i, j, image, white)
    areVerticalWhite = image(i - 1, j) == white && image(i+1, j) == white;    
    areHorizontalWhite = image(i, j - 1) == white && image(i, j + 1) == white;
    isBetween =  areVerticalWhite || areHorizontalWhite; 
end
    