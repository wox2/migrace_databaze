function [a] = generateSet(size)
    a = [];
    for i=1 : size
        for j = 1 : size 
          if i ~= j
            a = [a, [i j]'];
          end
        end
    end
end