function pred = predRegrTree( model, testInputs )
% PREDREGRTREE Regression tree predictions

    pred = eval( model, testInputs );
    
end