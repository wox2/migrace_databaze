clear; clc;
%% Classification Task: Predict the origin

loadAndNormalizeData;

% Specify inputs and outputs
inputs = double( houby(:,varInd) );
outputs = double( houby.poisonous );
outputs(outputs ~= 1) = 2; % Convert to american/nonamerican task

% Prepare training and testing data, use 60 percent for training
[itrn,itst] = splitData( size(houby,1), 0.6 );

% Prepare the call to training routine
trainPerc = @(a,b) trainClassLinearPerceptron(a,b, 500, 0.99);

degArray = 1:20;
crErr = zeros(size(degArray));
trErr = crErr;
tstErr = crErr;
for iDeg = 1:length(degArray),

    inputsMapped = polymap(inputs, degArray(iDeg) );
    
    % Use crossvalidation
    crErr(iDeg) = crossvalidate(...
        trainPerc, ...
        @predClassLinear, ...
        @errHitOrMiss, ...
        inputsMapped, outputs );
    
    % Train the classifier on all training data
    model = trainClassLinearPerceptron( ...
        inputsMapped(itrn,:), outputs(itrn,:), 500, 0.99 );

    % Predict the values for training data and get error
    predTrn = predClassLinear( model, inputsMapped(itrn,:) );
    trErr(iDeg) = errHitOrMiss( outputs(itrn,:), predTrn ); 

    % Predict the values for testing data
    pred = predClassLinear( model, inputsMapped(itst,:) );
    tstErr(iDeg) = errHitOrMiss( outputs(itst,:), pred ); 
end

plot(degArray, trErr, 'b-', ...
    degArray, crErr, 'r-', ...
    degArray, tstErr, 'k-', ...
    'Linewidth', 2);
legend({'Training error', 'Crossvalidation error', 'Testing error'}, 'Location', 'NorthWest');
xlabel('deg (degree of polynomials)');
ylabel('error');