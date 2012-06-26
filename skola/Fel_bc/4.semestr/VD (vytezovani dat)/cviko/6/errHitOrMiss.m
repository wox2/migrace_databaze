function err = errHitOrMiss(outputs, predictions)
% ERRHITORMISS 0-1 error function.

    if numel(outputs) ~= numel(predictions),
        error('The number of outputs is not equal to the number of predictions.');
    end
    if ~all(size(outputs) == size(predictions)),
        warning('The output matrix has different shape than the predictions matrix.');
    end
    
    err = sum(outputs(:) ~= predictions(:)) / numel(outputs);
end