% Load data
auta = dataset('file','poker-hand-training-true.data', ...
    'ReadVarNames', false, 'ReadObsNames', false, ...
    'delimiter', ',');
auta = auta(1:300,:);
% Normalize them
varInd = 1:10;
auta_norm = datasetfun( @minmax, auta(:,varInd), 'UniformOutput', false );
auta_norm = [auta_norm{:}];
auta = replacedata( auta, auta_norm, varInd );

nAuta = length(auta);