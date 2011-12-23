% Load data
karty = dataset('file','poker-hand-training-true.data', ...
    'ReadVarNames', false, 'ReadObsNames', false, ...
    'delimiter', ',', ...
    'VarNames', {'S1', 'C1', 'S2', 'C2', 'S3', 'C3', 'S4', 'C4','S5', 'C5', 'class'});

% Normalize them
varInd = 1:10;
karty_norm = datasetfun( @minmax, karty(:,varInd), 'UniformOutput', false );
karty_norm = [karty_norm{:}];
karty = replacedata( karty, karty_norm, varInd );

nKarty = length(karty);
