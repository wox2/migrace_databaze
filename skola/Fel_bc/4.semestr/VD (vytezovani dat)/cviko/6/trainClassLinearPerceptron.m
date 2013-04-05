function [model, wIter, errIter] = trainClassLinearPerceptron( inputs, outputs, maxIter, dampFactor )
% TRAINLINEARPERCEPTRON Trains linear 2-class classifier using the batch perceptron algorithm.
% Uses pocket algorithm to store the best solution

    if nargin < 3, maxIter = 100; end
    if nargin < 4, dampFactor = 0.95; end

    [N,D] = size( inputs );
    
    % Homogenize inputs
    inputs = [inputs ones(N,1)];
    % Invert the points not in class 1
    ind = ( outputs ~= 1 );
    inputs(ind,:) = -inputs(ind,:);
    
    % Initilize the weight vector
    w = inputs(1,:);
    wIter = w;
    eta = 1;
    
    % Start iterating
    errIter = [];
    for iter = 1:maxIter,
        % Predict outputs using the current weight vector
        out = inputs * w';
        % Find the misclassified points and compute error
        ind = (out < 0);
        errIter(end+1) = sum(ind) / N;
        % Update the weight vector using the misclassified points
        w = w + eta * sum( inputs(ind,:) );
        wIter(end+1,:) = w;
        % Update the learning rate
        eta = eta * dampFactor;
    end

    [foo, imin] = min(errIter);
    model.w = wIter(imin, :);
    model.fun = 'predClassLinear';
end