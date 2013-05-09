%function [c] = weights(alpha, beta, f_x, f_quote_x)
function [c] = weights()
    alpha = 2;
    beta = 10;
    %x = [0.8 2.15 2.9 4.1];
    %f_x = [0.1 0.7 1.3 0.65];
    %upperBound = 4;
    x = [0 1.26 2.51 3.77 5.03 6.28];
    f_x = [0.01 1.16 0.7 -0.34 -0.8 0.21];
    upperBound = 6;
    
    %weights
    c = zeros(upperBound,upperBound);
    for i = 1 : upperBound
        for j = (i + 1) : upperBound
            sum = 0;
            for k = i : j
                f_quote_x = f_x(i) + (x(k) - x(i)) * ((f_x(j) - f_x(i)) / (x(j) - x(i))); 
                sum = sum + (f_x(k) - f_quote_x)^2;
            end
            c(i,j) = ceil((alpha + beta * sum) * 100) /100;   
        end
    end
    
    %g = graph(c);
    %graphedit(g);
end