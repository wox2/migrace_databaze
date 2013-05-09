%start - starting node,  W - weighted edges matrix of distances
%originally r, E
function [precedessors, distances]  = dijkstraSolver(start, W)

    vertexCount=size(W,2); % vertices count in graph
    vertices =1:vertexCount; % vector of vertices
    distances=inf(1, vertexCount); % init..distances are infinite - eg no path found
    distances(start) = 0;
    %vector used to backtrack path to startNode
    precedessors=inf(1, vertexCount);
    precedessors(start) = -1;
    %is complement to closed nodes list
    toBeProcessed = vertices;

    %while there are vertices with not infinite distance that can be
    %processed - filters out disconnected graphs
    while size(distances(distances(toBeProcessed) ~= inf), 2) > 0
        %in this case min returns minimal value + it's index
        [minimalValue, minDistIndex] = min(distances(toBeProcessed));
        
        processedNode = toBeProcessed(minDistIndex);
        
        %close currently processed
        toBeProcessed = setdiff(toBeProcessed, processedNode);
        
        % select end nodes of edges starting in processedNode
        endNodes = vertices(W(processedNode, :) ~= inf);
        
        for endNode = endNodes
            %relaxation
            if distances(processedNode) + W(processedNode, endNode) < distances(endNode)
                distances(endNode) = distances(processedNode) + W(processedNode, endNode);
                precedessors(endNode) = processedNode;
            end
        end
    end
    %disp(distances);
end