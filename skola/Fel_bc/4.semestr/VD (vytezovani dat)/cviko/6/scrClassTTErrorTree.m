clear; clc;
%% Classification Task: Predict the origin

loadAndNormalizeData;

% Specify inputs and outputs
inputs = double( auta(:,varInd) );
outputs = double( auta.org );
% outputs(outputs ~= 1) = 2; % Convert to american/nonamerican task

% Prepare training and testing data, use 60 percent for training
[itrn,itst] = splitData( size(auta,1), 0.6 );

splitminArray = [200 180 150 120 100 80 60 40 20 10 5 2];
crErr = zeros(size(splitminArray));
trErr = crErr;
tstErr = crErr;
for ik = 1:length(splitminArray),

    % Prepare the learning function
    trainTree = @(a,b) trainClassTree(a, b, splitminArray(ik));

    % Use crossvalidation
    crErr(ik) = crossvalidate(...
        trainTree, ...
        @predClassTree, ...
        @errHitOrMiss, ...
        inputs, outputs );
    
    % Train the classifier on all training data
    model = trainTree( inputs(itrn,:), outputs(itrn,:) );

    % Predict the values for training data and get error
    predTrn = predClassTree( model, inputs(itrn,:) );
    trErr(ik) = errHitOrMiss( outputs(itrn,:), predTrn ); 

    % Predict the values for testing data
    pred = predClassTree( model, inputs(itst,:) );
    tstErr(ik) = errHitOrMiss( outputs(itst,:), pred ); 
end

plot(splitminArray, trErr, 'b-', ...
    splitminArray, crErr, 'r-', ...
    splitminArray, tstErr, 'k-', ...
    'Linewidth', 2);
legend({'Training error', 'Crossvalidation error', 'Testing error'}, 'Location', 'NorthWest');
xlabel('splitmin');
ylabel('error');