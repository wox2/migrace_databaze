clear; clc;
%% Regression Task: Predict the acceleration based on displacement

loadAndNormalizeData;

% Specify inputs and outputs
inputs = double( auta.disp );
outputs = double( auta.acc );

% Prepare training and testing data, use 60 percent for training
[itrn,itst] = splitData( size(auta,1), 0.6 );
% Train the model (store training examples)
splitmin = 10;
model = trainRegrTree( inputs(itrn,:), outputs(itrn,:), splitmin );
% Predict the values for testing data
pred = predRegrTree( model, inputs(itst,:) );

% Compute error
err = errRMS( outputs(itst,:), pred )

% Vizualize
if size(inputs,2) ~= 1 || size(outputs,2) ~= 1,
    error('Sorry, this script can vizualize only 1D inputs and outputs.');
end
vizinputs = (0:0.005:1)';
vizpred = predRegrTree( model, vizinputs );
plot( inputs(itrn,:), outputs(itrn,:), 'k.'); hold on;
plot( inputs(itst,:), outputs(itst,:), 'r.');
plot( vizinputs, vizpred, 'b-', 'LineWidth', 2);
plot( inputs(itst,:), pred, 'bx', 'MarkerSize', 8, 'Linewidth', 2);
xlabel('disp'); ylabel('acc');
legend({'Training data','Testing data', 'Model prediction', 'Model pred. for testing data'})
title(sprintf('Regression tree acc = f(disp):   splitmin = %d   err = %.3f', splitmin, err ));
