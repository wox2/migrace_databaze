clear; clc;
%% Regression Task: Predict the acceleration based on displacement

loadAndNormalizeData;

% Specify inputs and outputs
inputs = double( auta.disp );
outputs = double( auta.acc );

% Prepare training and testing data, use 60 percent for training
[itrn,itst] = splitData( size(auta,1), 0.6 );

splitminArray = [200 180 150 120 100 80 60 40 20 10 5 2];
crErr = zeros(size(splitminArray));
trErr = crErr;
tstErr = crErr;
for ik = 1:length(splitminArray),

    % Prepare the training function
    trainTree = @(a,b) trainRegrTree(a, b, splitminArray(ik));

    crErr(ik) = crossvalidate(...
        trainTree, ...
        @predRegrTree, ...
        @errRMS, ...
        inputs, outputs );
    
    % Train the the model on all training data
    model = trainTree( inputs(itrn,:), outputs(itrn,:) );

    % Predict the values for training data and get error
    predTrn = predRegrTree( model, inputs(itrn,:) );
    trErr(ik) = errRMS( outputs(itrn,:), predTrn ); 
    
    % Predict the values for testing data and get error
    pred = predRegrTree( model, inputs(itst,:) );
    tstErr(ik) = errRMS( outputs(itst,:), pred ); 

end

plot(splitminArray, trErr, 'b-', ...
    splitminArray, crErr, 'r-', ...
    splitminArray, tstErr, 'k-', ...
    'Linewidth', 2);
legend({'Training error', 'Crossvalidation error', 'Testing error'}, 'Location', 'NorthWest');
xlabel('splitmin');
ylabel('error');