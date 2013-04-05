function model = trainStore( inputs, outputs )
% TRAINSTORE Trains 'model' by storing the training data only (usefull for NN models)

    model.in = inputs;
    model.out = outputs;
end