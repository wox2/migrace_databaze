function [errors, dataFractions] = lc(...
    learningFunc, predFunc, errorFunc, ...
    inputs, outputs, varargin)
% LC Learning curve
% Function constructs the learning curve of the specified learning
% algorithm for the specified classifier using the specified error function
% definition on the provided data.
%
% Synopsis:
%   lc(learningFunc, predFunc, errorFunc, inputs, outputs[, reps[,dataFractions]])
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
%      reps is the number of repetitions, default is 1.
%
%      dataFractions is a vector of ordered values between 0 and 1
%         determining where to split the data on training and testing;
%         default is 0.1:0.1:0.9.
%
%      Function returns at most two outputs:
%      - the matrix with errors
%      - the dataFractions

    if length(varargin) > 0, reps = varargin{1}; else reps = 1; end
    if length(varargin) > 1, dataFractions = varargin{2}; else dataFractions = 0.1:0.1:0.9; end

    N = size(inputs,1);
    errors = zeros(reps, length(dataFractions));
    
    for irep = 1:reps,
        for ifrac = 1:length(dataFractions),
            % Split data to training and testing
            % BEWARE! With very low or very high fraction, either the
            % training or the testing data set might be empty!!!
            [itrain, itest] = splitData(N, dataFractions(ifrac) );
            % Create model
            model = learningFunc( inputs(itrain,:), outputs(itrain,:) );
            % Use the model 
            predictions = predFunc( model, inputs(itest,:) );
            % Compute errors
            errors(irep,ifrac) = errorFunc( outputs(itest,:), predictions );
        end
        fprintf('Iteration %d done.\n', irep);
    end
    
    figure; hold on;
    if reps > 1,
        meanErr = mean(errors, 1);
        stdErr = std(errors, 0, 1);
        plot(dataFractions, meanErr, 'k-', 'Linewidth', 2); 
        plot(dataFractions, meanErr+1.96*stdErr, 'r-', 'Linewidth', 2);
        plot(dataFractions, meanErr-1.96*stdErr, 'r-', 'Linewidth', 2);
        legend({'Mean(Error)', 'Mean(Error) + 1.96 * St.D.(Error)', 'Mean(Error) - 1.96 * St.D.(Error)'}, ...
            'Location', 'SouthWest');
    end
    plot(dataFractions, errors); 
    
    xlabel('Training data fraction');
    ylabel('Error');
    title('Learning curve');
    hold off;
    
end