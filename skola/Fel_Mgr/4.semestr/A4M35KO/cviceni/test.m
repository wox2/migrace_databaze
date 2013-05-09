function [c2] = test()
    tsp_skeleton();
    c2 = circuit;
    w = matrixOfWeights;
    c2 = swapEdges(c2, 1, 3, w);
    
end