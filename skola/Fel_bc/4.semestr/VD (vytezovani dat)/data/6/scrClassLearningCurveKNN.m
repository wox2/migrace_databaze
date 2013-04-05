clear; clc;

%% Learning curve for k-NN classifier

loadAndNormalizeData;

% Specify inputs and outputs
varInd = 1:6;
inputs = double(auta(:,varInd));
outputs = double(auta.org);
% outputs(outputs ~= 1) = 2; % Classify american/nonamerican only

% Prepare the call for the NN classifier, 
k = 2;
trainKNN = @(a,b) trainClassKNN(a, b, k);

% Create the learning curve
nRuns = 20;
[errors, dataFractions] = lc(...
    trainKNN, ...
    @predClassKNN, ...
    @errHitOrMiss, ...
    inputs, outputs, ...
    nRuns, [0.05:0.1:0.95]);
title(sprintf('Learning curve - k-NN classifier: k = %d',k));