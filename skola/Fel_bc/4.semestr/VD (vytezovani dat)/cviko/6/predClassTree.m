function pred = predClassTree( model, testInputs )
% PREDCLASSTREE Classification tree predictions

    pred = eval( model, testInputs );
    
    % For classification, eval returns a cell array of chars, we have to
    % convert it to array of doubles:
    pred = [pred{:}] - '0';
    pred = pred';

end