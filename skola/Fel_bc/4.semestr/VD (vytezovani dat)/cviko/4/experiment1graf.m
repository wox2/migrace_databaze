loadAndNormalizeData;

% Split data to 1/4 for training and 3/4 for testing
[indtren, indtest] = splitData(nAuta, .25);
auta_tren = auta(indtren,:);
auta_test = auta(indtest,:);

% Prepare the features used for classification
auta_tren_in = [auta_tren.mpg auta_tren.disp]; 
auta_test_in = [auta_test.mpg auta_test.disp];

acc = [];
for nTrain = 1:length(auta_tren),

	disp(['Training set size: ' num2str(nTrain)]);

    % Train the model on the first nTrain examples only
    model = trainClassKNN(auta_tren_in(1:nTrain,:), auta_tren.org(1:nTrain), 1);
    % Predict the classes for all test data
    classes = predClassKNN(model, auta_test_in);
    % Compute model accuracy
	isCorrect = (classes == double(auta_test.org));
	numCorrectClassif = sum(isCorrect);
    acc(end+1) = numCorrectClassif / length(auta_test);

	disp(['Number of correctly classified cars: ' num2str(numCorrectClassif) ' out of ' num2str(length(auta_test))]);
	disp(['% of correctly classified cars:      ' num2str(acc(end) * 100) ' %']);
end

plot([1:length(auta_tren)]/length(auta_tren), acc, 'bx-');
xlabel('Training set percentage used for training');
ylabel('Model accuracy measured on test data');