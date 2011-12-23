function model = trainRegrKNN( inputs, outputs, k )
% TRAINREGRKNN Trains KNN model by storing the training data only.

    model.in = inputs;
    model.out = outputs;
    model.k = k;
    model.fun = 'predRegrKNN';
end