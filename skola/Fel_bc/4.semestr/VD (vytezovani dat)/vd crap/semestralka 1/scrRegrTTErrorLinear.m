clear; clc;
%% Regression Task: Predict the acceleration based on displacement

loadAndNormalizeData;

% Specify inputs and outputs
inputs = double( auta.disp );
outputs = double( auta.acc );

% Prepare training and testing data, use 60 percent for training
[itrn,itst] = splitData( size(auta,1), 0.6 );

degArray = 0:17;
crErr = zeros(size(degArray));
trErr = crErr;
tstErr = crErr;
for iDeg = 1:length(degArray),

    % Create polynomial terms (try to increase deg)
    inputsMapped = polymap(inputs, degArray(iDeg) );

    [crErr(iDeg), trErr(iDeg)] = crossvalidate(...
        @trainRegrLinear, ...
        @predRegrLinear, ...
        @errRMS, ...
        inputsMapped, outputs );
    
    % Train the the model on all training data
    model = trainRegrLinear( inputsMapped(itrn,:), outputs(itrn,:) );

    % Predict the values for training data and get error
    predTrn = predRegrLinear( model, inputsMapped(itrn,:) );
    trErr(iDeg) = errRMS( outputs(itrn,:), predTrn ); 
    
    % Predict the values for testing data and get error
    pred = predRegrLinear( model, inputsMapped(itst,:) );
    tstErr(iDeg) = errRMS( outputs(itst,:), pred ); 

end

plot(degArray, trErr, 'b-', ...
    degArray, crErr, 'r-', ...
    degArray, tstErr, 'k-', ...
    'Linewidth', 2);
legend({'Training error', 'Crossvalidation error','Testing error'}, 'Location', 'NorthWest');
xlabel('degree of polynomial');
ylabel('error');