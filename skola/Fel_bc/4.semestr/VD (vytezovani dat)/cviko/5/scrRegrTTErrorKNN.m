clear; clc;
%% Regression Task: Predict the acceleration based on displacement

loadAndNormalizeData;

% Specify inputs and outputs
inputs = double( auta.disp );
outputs = double( auta.acc );

% Prepare training and testing data, use 60 percent for training
[itrn,itst] = splitData( size(auta,1), 0.6 );

kArray = 1:1:50;
crErr = zeros(size(kArray));
trErr = crErr;
tstErr = crErr;
for ik = 1:length(kArray),

    % Prepare the predictor function
    trainKNN = @(a,b) trainRegrKNN(a, b, kArray(ik));

    crErr(ik) = crossvalidate(...
        trainKNN, ...
        @predRegrKNN, ...
        @errRMS, ...
        inputs, outputs );
    
    % Train the the model on all training data
    model = trainKNN( inputs(itrn,:), outputs(itrn,:) );

    % Predict the values for training data and get error
    predTrn = predRegrKNN( model, inputs(itrn,:) );
    trErr(ik) = errRMS( outputs(itrn,:), predTrn ); 
    
    % Predict the values for testing data and get error
    pred = predRegrKNN( model, inputs(itst,:) );
    tstErr(ik) = errRMS( outputs(itst,:), pred ); 

end

plot(kArray, trErr, 'b-', ...
    kArray, crErr, 'r-', ...
    kArray, tstErr, 'k-', ...
    'Linewidth', 2);
legend({'Training error', 'Crossvalidation error', 'Testing error'}, 'Location', 'NorthWest');
xlabel('k (number of nearest neighbors)');
ylabel('error');