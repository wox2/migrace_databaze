clear; clc;
%% Classification Task: Predict the origin based on mpg and year
% Vizualization of the classification tree decision boundary.

loadAndNormalizeData;

% Specify inputs and outputs
inputs = [houby.stalkSurfaceAboveRing houby.sporePrintColor];
outputs = double( houby.poisonous );
classes = {'jedovaté', 'jedlé'};
contours = [1.5 1.5];

% Convert to american/nonamerican task (comment or uncomment the following lines)
outputs(outputs ~= 1) = 2; 
% classes = {'´jedovaté', 'jedlé'}; 
% contours = [1.5 1.5];

% Prepare training and testing data, use 60 percent for training
[itrn,itst] = splitData( size(houby,1), 0.6 );

% Plot training data
gscatter( inputs(itrn,1), inputs(itrn,2), classes(outputs(itrn,:))' ); hold on;
xlabel('capShape');
ylabel('capSurface');

% Vizualize the learning process
if size(inputs,2) ~= 2 || size(outputs,2) ~= 1,
    error('Sorry, this script can vizualize only 2D inputs and 1D outputs.');
end

% Prepare the graph data
[xx,yy] = meshgrid( 0:0.01:1, 0:0.01:1 );
vizinputs = [xx(:) yy(:)];

splitminArray = [200 150 100 50 20 10 5 2];
for ik = 1:length(splitminArray),

    % Train the model
    % For other types of models, the training must be inside the loop
    model = trainClassTree( inputs(itrn,:), outputs(itrn,:), splitminArray(ik) );

    % Predict the values for testing data
    pred = predClassTree( model, inputs(itst,:) );

    % Compute error
    err = errHitOrMiss( outputs(itst,:), pred );

    % Compute predictions for graph data
    vizpred = predClassTree( model, vizinputs );
    zz = reshape(vizpred, size(xx));

    % The contour is not smooth because of the process it is drawn, 
    % not because it is not really smooth
    [foo,h] = contour(xx, yy, zz, contours); hold on
    set(h,'LineColor','b','LineWidth',2);
    title(sprintf('k = %d   err = %.3f', splitminArray(ik), err));
    
    pause;
    if ik == length(splitminArray), break; end
    delete(h);
end

