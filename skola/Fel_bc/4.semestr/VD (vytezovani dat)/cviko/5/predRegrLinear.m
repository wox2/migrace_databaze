function pred = predRegrLinear( model, testInputs )
% PREDREGRLINEAR Linear modeling.

    [N,D] = size( testInputs );
    pred = zeros(N,1);
    if D+1 ~= length(model.w), error('The dimensionality of testing patterns does not correspond to the dimensionality of weight vector.'); end
    
    % Homogenize inputs
    inp = [testInputs ones(N,1)];
    
    % Use the linear model
    pred = inp * model.w(:);
    
end