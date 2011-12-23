function pred = predRegrKNN(model, testInputs)
% PREDREGRKNN k-NN regression

    indNN = knnsearch( testInputs, model.in, model.k );
    pred = model.out( indNN );
    pred = mean( pred, 2 );
    
end