function [itrn, itst] = crossvalIndices(N, nFolds)
% CROSSVALINDICES Prepares data indices for crossvalidation

    if nargin < 2 || nFolds < 2, nFolds = 2; end
    
    % The number of testing cases in eac fold
    nTst = ceil( N/nFolds );
    % The provisional number of data
    nHigher = nFolds * nTst;
    % Prepare vector of data indices, fill in zeros
    vec = [1:N zeros(1, nHigher-N)];
    % Randomize indices
    perm = vec(randperm(nHigher));
    % Reshape permutation
    perm = reshape(perm, nTst, nFolds);
    
    for iFold = 1:nFolds,
        % Extract the n-th testing data
        temp = perm(:,iFold);
        % ... and remove zeros from them
        itst{iFold} = temp(temp ~= 0);
        % Extract the n-th training data
        temp = perm(:);
        % ... remove zeros from them
        temp = temp(temp ~= 0);
        % ... remove the testing data 
        itrn{iFold} = setdiff(temp, itst{iFold});
    end
    
end