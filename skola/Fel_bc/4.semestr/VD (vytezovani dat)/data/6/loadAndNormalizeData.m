% Load data
auta = test;

% Normalize them
varInd = 1:10;
auta_norm = datasetfun( @minmax, auta(:,varInd), 'UniformOutput', false );
auta_norm = [auta_norm{:}];
auta = replacedata( auta, auta_norm, varInd );

nAuta = length(auta);
