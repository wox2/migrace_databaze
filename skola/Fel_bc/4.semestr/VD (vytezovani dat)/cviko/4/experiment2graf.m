loadAndNormalizeData;

figure
hold on
colour = 'rbgcmykrbgcmykrbgcmykrbgcmykrbgcmykrbgcmyk';


maxk = 100;
kArray = 1:maxk;

% zopakuj cely experiment 5x
for i = 1:5
    [indtrain, indtest] = splitData(nAuta, 0.33);
	auta_tren = auta(indtrain,:);
	auta_test = auta(indtest,:);

	auta_tren_in = [auta_tren.mpg auta_tren.disp];
	auta_test_in = [auta_test.mpg auta_test.disp];

	acc = zeros(maxk,1);

	for k = kArray,
        % Train the model on the training examples
        model = trainClassKNN(auta_tren_in, auta_tren.org, k);
        % Predict the classes for all test data using k nearest neighbors
        classes = predClassKNN(model, auta_test_in);
        % Compute model accuracy
        isCorrect = (classes == double(auta_test.org));
        numCorrectClassif = sum(isCorrect);
        acc(k) = numCorrectClassif / length(auta_test);
	end
    plot(kArray, acc, colour(i));
    xlabel('k (number of nearest neighbors)');
    ylabel('Model accuracy measured on test data');
end