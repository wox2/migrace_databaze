clear; clc;
%% Regression Task: Predict the acceleration based on other variables

loadAndNormalizeData;

% Specify inputs and outputs
inputs = double( auta(:,1:5) );
outputs = double( auta.acc );

% Prepare the call for the NN classifier, 
k = 5;
trainKNN = @(a,b) trainRegrKNN(a, b, k);

% Create the learning curve
nRuns = 20;
[errors, dataFractions] = lc(...
    trainKNN, ...
    @predRegrKNN, ...
    @errRMS, ...
    inputs, outputs, ...
    nRuns, [0.05:0.1:0.95]);