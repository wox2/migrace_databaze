function model = trainClassKNN( inputs, outputs, k )
% TRAINCLASSKNN Trains KNN model by storing the training data only.

    model.in = inputs;
    model.out = outputs;
    model.k = k;
    model.fun = 'predClassKNN';
end