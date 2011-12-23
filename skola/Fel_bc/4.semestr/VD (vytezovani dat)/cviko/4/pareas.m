function h = pareas(fh, model)
% PAREAS Plots the areas belonging to classes in binary classification.
%   Assumes that one class is coded is 1, the other can have any other
%   code.

% The model.fun function must accept points as row vectors

    class1color = [0.7 0.7 1];
    class2color = [1 0.7 0.7];

    figure(fh);
    ax = axis;
    xv = linspace(ax(1),ax(2),101);
    yv = linspace(ax(3),ax(4),101);
    [xx,yy] = meshgrid(xv,yv);
    X = [xx(:) yy(:)];
    z = feval( model.fun, model, X );
    z(z~=1) = 2;
    z = reshape(z, size(xx));
    [c,h] = contourf(xv, yv, z, [-inf,1.5] );
    ch = get(h,'Children');
    for i = 1:length(ch),
        if get(ch(i),'CData') < 1.5,
            set(ch(i), 'FaceColor', class1color, 'EdgeColor', class1color);
        else
            set(ch(i), 'FaceColor', class2color, 'EdgeColor', class2color);
        end
    end
end
