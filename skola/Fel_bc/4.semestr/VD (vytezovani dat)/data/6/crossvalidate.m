function [crossErr, trErr] = crossvalidate(...
    learningFunc, predFunc, errorFunc, ...
    inputs, outputs, varargin)
% CROSSVALIDATE Computes the crossvalidation error
% Function computes the crossovalidation error by dividing the data into
% nFolds training and testing datasets, trains and evaluates the model
% repeatedly, and computes the average error.
%
% Synopsis:
%   crossvalidate(learningFunc, predFunc, errorFunc, inputs, outputs, nFolds)
%
%      learningFunc is a handle on a function with the following prototype:
%         model = learningFunc(traningInputs, trainingOutputs)
%         i.e. it is a learning algorithm, which takes the training data
%         and returns the created model.
%
%      predFunc is a handle on a function with the following prototype:
%         testingPredictions = predFunc(model, testingInputs)
%         i.e. it is a predictor algorithm that for the given testingInputs
%         return the predictions using the given model.
%
%      errorFunc is a handle on a function with the following prototype:
%         err = errorFunc(testingOutputs, testingPredictions),
%         i.e. it takes the predictions and the true output values and
%         returns a measure of error.
%
%      inputs are the input variables for the available data
%
%      outputs are the output variables for the available data
%
%      nFolds is the number of folds, default is 5.
%
%      Function returns the crossvalidated error

    if length(varargin) > 0, nFolds = varargin{1}; else nFolds = 5; end

    crossErr = 0;
    trErr = 0;
    [N,D] = size( inputs );
    
    [iTrain, iTest] = crossvalIndices(N, nFolds);
    
    for iFold = 1:nFolds,
        itrn = iTrain{iFold};
        itst = iTest{iFold};
        % Create model
        model = learningFunc( inputs(itrn,:), outputs(itrn,:) );
        % Use the model 
        predTrn = predFunc( model, inputs(itrn,:) );
        predTst = predFunc( model, inputs(itst,:) );
        % Compute errors
        trErr = trErr + errorFunc( outputs(itrn,:), predTrn );
        crossErr = crossErr + errorFunc( outputs(itst,:), predTst );
    end
    
    crossErr = crossErr / nFolds;
    trErr = trErr / nFolds;
end