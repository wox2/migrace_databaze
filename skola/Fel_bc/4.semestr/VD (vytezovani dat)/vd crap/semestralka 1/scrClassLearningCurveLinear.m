clc; clear;

loadAndNormalizeData;

% Transform the task to solve the american/non-american problem
outputs = houby.poisonous;
outputs( outputs ~= 1 ) = 2;

% Prepare the call for the training function
maxIter = 300;
dampFactor = 0.99;
trainFunc = @(a,b) trainClassLinearPerceptron(a, b, maxIter, dampFactor);

% Create the learning curve
nRuns = 20;
[errors, dataFractions] = lc(...
    trainFunc, ...
    @predClassLinear, ...
    @errHitOrMiss, ...
    double(houby(:,2:22)), outputs, ...
    nRuns, [0.05:0.1:0.95]);