clear; clc;
%% Regression Task: Predict the acceleration based on displacement

loadAndNormalizeData;

% Specify inputs and outputs
inputs = double( auta.disp );
outputs = double( auta.acc );

% Create polynomial terms (try to increase deg)
deg = 10;
inputsMapped = polymap(inputs, deg);
% Prepare training and testing data, use 60 percent for training
[itrn,itst] = splitData( size(auta,1), 0.6 );
% Train the model
model = trainRegrLinear( inputsMapped(itrn,:), outputs(itrn,:) );
% Predict the values for testing data
pred = predRegrLinear( model, inputsMapped(itst,:) );

% Compute error
err = errRMS( outputs(itst,:), pred );

% Vizualize
if size(inputs,2) ~= 1 || size(outputs,2) ~= 1,
    error('Sorry, this script can vizualize only 1D inputs and outputs.');
end
vizinputs = (0:0.005:1)';
vizinputsMapped = polymap(vizinputs, deg);
vizpred = predRegrLinear( model, vizinputsMapped );
plot( inputs(itrn,:), outputs(itrn,:), 'k.'); hold on;
plot( inputs(itst,:), outputs(itst,:), 'r.');
plot( vizinputs, vizpred, 'b-', 'LineWidth', 2);
plot( inputs(itst,:), pred, 'bx', 'MarkerSize', 8, 'Linewidth', 2);
xlabel('disp'); ylabel('acc');
legend({'Training data','Testing data', 'Model prediction', 'Model pred. for testing data'})
title(sprintf('Linear regression acc = f(disp):   deg = %d   err = %.3f', deg, err ));

% Why the red line does not go through the red dots???

