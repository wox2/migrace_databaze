function model = trainRegrLinear( inputs, outputs )
% TRAINREGRLINEAR Trains linear model using linear regression

    [N,D] = size( inputs );
    
    % Homogenize inputs
    inputs = [inputs ones(N,1)];
    
    model.w = regress( outputs, inputs )';
    model.fun = 'predRegrLinear';
end