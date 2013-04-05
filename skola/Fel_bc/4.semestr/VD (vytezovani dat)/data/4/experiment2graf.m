
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

	auta_tren_in = [auta_tren.Var1 auta_tren.Var3];
	auta_test_in = [auta_test.Var1 auta_test.Var3];

	acc = zeros(maxk,1);

	for k = kArray,
        % Train the model on the training examples
        model = trainClassKNN(auta_tren_in, auta_tren.Var11, k);
        % Predict the classes for all test data using k nearest neighbors
        classes = predClassKNN(model, auta_test_in);
        % Compute model accuracy
        isCorrect = (classes == double(auta_test.Var11));
        numCorrectClassif = sum(isCorrect);
        acc(k) = numCorrectClassif / length(auta_test);
	end
    plot(kArray, acc, colour(i));
    xlabel('k (number of nearest neighbors)');
    ylabel('Model accuracy measured on test data');
end