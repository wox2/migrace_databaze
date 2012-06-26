function err = errRMS(outputs, predictions)
% ERRRMS Root mean square error function.

    if numel(outputs) ~= numel(predictions),
        error('The number of outputs is not equal to the number of predictions.');
    end
    if ~all(size(outputs) == size(predictions)),
        warning('The output matrix has different shape than the predictions matrix.');
    end
    
    err = sqrt ( mean(( outputs(:) - predictions(:) ) .^ 2) );
end