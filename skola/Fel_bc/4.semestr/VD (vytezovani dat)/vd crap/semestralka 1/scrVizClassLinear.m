clear; clc;
%% Classification Task: Predict the origin based on mpg and year
% Vizualization of the perceptron learning with adjustable degree of the
% discrimination function. Only pure monomials are considered (x^2, y^2,
% x^3, ...), the other terms (xy, x^2*y, x*y^2, ...) are ignored.

loadAndNormalizeData;

% Specify inputs and outputs
inputs = [houby.stalkSurfaceAboveRing houby.sporePrintColor];
outputs = double( houby.poisonous );
outputs(outputs ~= 1) = 2; % Convert to american/nonamerican task

% Create polynomial terms (try to increase deg)
deg = 1;
inputsMapped = polymap(inputs, deg);
% Prepare training and testing data, use 60 percent for training
[itrn,itst] = splitData( size(houby,1), 0.6 );
% Train the model
[model, wIter, errIter] = trainClassLinearPerceptron( ...
    inputsMapped(itrn,:), outputs(itrn,:), ...
    100, 0.95 );
% Predict the values for testing data
pred = predClassLinear( model, inputsMapped(itst,:) );

% Compute error
err = errHitOrMiss( outputs(itst,:), pred )

% Vizualize the learning process
if size(inputs,2) ~= 2 || size(outputs,2) ~= 1,
    error('Sorry, this script can vizualize only 2D inputs and 1D outputs.');
end

classes = {'American', 'Not American'};
gscatter( inputs(itrn,1), inputs(itrn,2), classes(outputs(itrn,:))' ); hold on;
xlabel('mpg'); ylabel('year');
[xx,yy] = meshgrid( 0:0.01:1, 0:0.01:1 );
vizinputs = [xx(:) yy(:)];
vizinputsMapped = polymap(vizinputs, deg);

for i = 1:size(wIter,1),
    
    tempmodel.w = wIter(i,:);
    vizpred = predClassLinear( tempmodel, vizinputsMapped );
    zz = reshape(vizpred, size(xx));

    % The contour is not smooth because of the process it is drawn, not
    % because it is not really smooth
    [foo,h] = contour(xx, yy, zz, [1.5 1.5]); hold on
    set(h,'LineColor','k','LineWidth',2);
    
    pause(0.001);
    if i == size(wIter,1), break; end
    delete(h);
end

