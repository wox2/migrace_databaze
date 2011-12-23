function pred = predClassKNN( model, testInputs )
% PREDCLASSKNN k-NN classification

    indNN = knnsearch( testInputs, model.in, model.k );
    nClasses = length( unique( model.out ) );
	pred = classify2( indNN, model.out, nClasses );

end

