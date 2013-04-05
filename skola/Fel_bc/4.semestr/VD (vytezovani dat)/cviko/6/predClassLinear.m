function pred = predClassLinear( model, testInputs )
% PREDCLASSLINEAR Linear classification rule for dichotomy.

    [N,D] = size( testInputs );
    pred = zeros(N,1);
    if D+1 ~= length(model.w), error('The dimensionality of testing patterns does not correspond to the dimensionality of weight vector.'); end
    
    % Homogenize inputs
    inp = [testInputs ones(N,1)];
    
    % Use the linear model
    out = inp * model.w(:);
    
    % Set outputs (performs the 'signum' transfer function with -1 replaced by 2)
    pred( out >= 0 ) = 1;
    pred( out < 0)   = 2;

end