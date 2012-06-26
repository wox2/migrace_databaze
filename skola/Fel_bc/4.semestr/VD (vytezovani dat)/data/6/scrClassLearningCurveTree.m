clc; clear;

loadAndNormalizeData;

% Transform the task to solve the american/non-american problem
outputs = auta.org;
outputs( outputs ~= 1 ) = 2;

% Prepare the call for the training function
splitmin = 10;
trainFunc = @(a,b) trainClassTree(a, b, splitmin);

% Create the learning curve
nRuns = 20;
[errors, dataFractions] = lc(...
    trainFunc, ...
    @predClassTree, ...
    @errHitOrMiss, ...
    double(auta(:,1:5)), outputs, ...
    nRuns, [0.05:0.1:0.95]);