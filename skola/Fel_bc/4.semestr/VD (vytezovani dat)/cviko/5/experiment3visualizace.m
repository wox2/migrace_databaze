loadAndNormalizeData;

% Recode the output variable (origin) to 
% 1 - american cars
% 2 - non-american cars
auta.org(auta.org ~= 1) = 2;

% Split data to 1/4 for training and 3/4 for testing
[indtren, indtest] = splitData(nAuta, .25);
auta_tren = auta(indtren,:);
auta_test = auta(indtest,:);

% Prepare the features used for classification
auta_tren_in = [auta_tren.mpg auta_tren.disp]; 
auta_test_in = [auta_test.mpg auta_test.disp];

% Train the KNN classifier
model = trainClassKNN( auta_tren_in, auta_tren.org, 1);

% Plot the data and the classification areas
figure; hold on;
pareas(gcf, model);
% Training data
indAmer = (auta_tren.org == 1);
indNAmer = (auta_tren.org ~= 1);
plot(auta_tren.mpg(indAmer), auta_tren.disp(indAmer), 'bo');
plot(auta_tren.mpg(indNAmer), auta_tren.disp(indNAmer), 'ro');
pause;
% Testing data
indAmer = (auta_test.org == 1);
indNAmer = (auta_test.org ~= 1);
plot(auta_test.mpg(indAmer), auta_test.disp(indAmer), 'bx');
plot(auta_test.mpg(indNAmer), auta_test.disp(indNAmer), 'rx');
