clear; clc;
%% Classification Task: Predict the origin

loadAndNormalizeData;

% Specify inputs and outputs
inputs = double( auta(:,varInd) );
outputs = double( auta.org );
% outputs(outputs ~= 1) = 2; % Convert to american/nonamerican task

% Prepare training and testing data, use 60 percent for training
[itrn,itst] = splitData( size(auta,1), 0.6 );

kArray = 1:1:50;
crErr = zeros(size(kArray));
trErr = crErr;
tstErr = crErr;
for ik = 1:length(kArray),

    % Prepare the predictor function
    trainKNN = @(a,b) trainClassKNN(a,b,kArray(ik));

    % Use crossvalidation
    crErr(ik) = crossvalidate(...
        trainKNN, ...
        @predClassKNN, ...
        @errHitOrMiss, ...
        inputs, outputs );
    
    % Train the classifier on all training data
    model = trainKNN( inputs(itrn,:), outputs(itrn,:) );

    % Predict the values for training data and get error
    predTrn = predClassKNN( model, inputs(itrn,:) );
    trErr(ik) = errHitOrMiss( outputs(itrn,:), predTrn ); 

    % Predict the values for testing data
    pred = predClassKNN( model, inputs(itst,:) );
    tstErr(ik) = errHitOrMiss( outputs(itst,:), pred ); 
end

plot(kArray, trErr, 'b-', ...
    kArray, crErr, 'r-', ...
    kArray, tstErr, 'k-', ...
    'Linewidth', 2);
legend({'Training error', 'Crossvalidation error', 'Testing error'}, 'Location', 'NorthWest');
xlabel('k (number of nearest neighbors)');
ylabel('error');