clc; clear;

%% Regression Task: Predict the acceleration based on other variables

loadAndNormalizeData;

% Specify inputs and outputs
inputs = double( auta(:,1:5) );
outputs = double( auta.acc );

% Create the learning curve
nRuns = 20;
[errors, dataFractions] = lc(...
    @trainRegrLinear, ...
    @predRegrLinear, ...
    @errRMS, ...
    inputs, outputs, ...
    nRuns, [0.05:0.1:0.95]);