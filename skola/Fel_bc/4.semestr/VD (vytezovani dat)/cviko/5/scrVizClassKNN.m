clear; clc;
%% Classification Task: Predict the origin based on mpg and year
% Vizualization of the KNN classifiers with adjustable number of NN.

loadAndNormalizeData;

% Specify inputs and outputs
inputs = [auta.mpg auta.year];
outputs = double( auta.org );
classes = {'American', 'European', 'Japan'};
contours = [1.5 2.5];

% Convert to american/nonamerican task (comment or uncomment the following lines)
outputs(outputs ~= 1) = 2; 
classes = {'American', 'Not American'}; 
contours = [1.5 1.5];

% Prepare training and testing data, use 60 percent for training
[itrn,itst] = splitData( size(auta,1), 0.6 );

% Plot training data
gscatter( inputs(itrn,1), inputs(itrn,2), classes(outputs(itrn,:))' ); hold on;
xlabel('mpg');
ylabel('year');

% Vizualize the learning process
if size(inputs,2) ~= 2 || size(outputs,2) ~= 1,
    error('Sorry, this script can vizualize only 2D inputs and 1D outputs.');
end

% Prepare the graph data
[xx,yy] = meshgrid( 0:0.01:1, 0:0.01:1 );
vizinputs = [xx(:) yy(:)];

kArray = [1 2 3 5 10 20 30 50 70 100 120 150];
for ik = 1:length(kArray),

    % Train the model
    % For k-NN model, it is not needed to call this inside the loop, the
    % same data are stored over and over
    % For other types of models, the training must be inside the loop
    model = trainClassKNN( inputs(itrn,:), outputs(itrn,:), kArray(ik) );

    % Predict the values for testing data
    pred = predClassKNN( model, inputs(itst,:) );

    % Compute error
    err = errHitOrMiss( outputs(itst,:), pred );

    % Compute predictions for graph data
    vizpred = predClassKNN( model, vizinputs );
    zz = reshape(vizpred, size(xx));

    % The contour is not smooth because of the process it is drawn, 
    % not because it is not really smooth
    [foo,h] = contour(xx, yy, zz, contours); hold on
    set(h,'LineColor','b','LineWidth',2);
    title(sprintf('k = %d   err = %.3f',kArray(ik), err));
    
    pause;
    if ik == length(kArray), break; end
    delete(h);
end

