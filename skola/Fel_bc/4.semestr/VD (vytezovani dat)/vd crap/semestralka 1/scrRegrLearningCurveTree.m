clear; clc;
%% Regression Task: Predict the acceleration based on other variables

loadAndNormalizeData;

% Specify inputs and outputs
inputs = double( houby(:,1:5) );
outputs = double( houby.poisonous );

% Prepare the call for the tree model 
splitmin = 10;
trainTree = @(a,b) trainRegrTree( a, b, splitmin );

% Create the learning curve
nRuns = 20;
[errors, dataFractions] = lc(...
    trainTree, ...
    @predRegrTree, ...
    @errRMS, ...
    inputs, outputs, ...
    nRuns, [0.05:0.1:0.95]);