% Load data
auta = dataset('file','auto-mpg.data-mod-names.csv', ...
    'ReadVarNames', false, 'ReadObsNames', false, ...
    'delimiter', ',', ...
    'VarNames',{'mpg', 'cyl', 'disp', 'hp', 'wgt', 'acc', 'year', 'org', 'name'});

% Normalize them
varInd = 1:7;
auta_norm = datasetfun( @minmax, auta(:,varInd), 'UniformOutput', false );
auta_norm = [auta_norm{:}];
auta = replacedata( auta, auta_norm, varInd );

nAuta = length(auta);
