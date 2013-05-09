function [t] = test_cviko()
    clear all;
    inc = [1 1 0 0 0 ; -1 0 1 1 0 ; 0 -1 -1 0 1 ; 0 0 0 -1 -1];
    sense=-1; %sense of optimization: 1=minimization, -1=maximization
  
    %rows = 5;
    %columns = 1;
    %lb = zeros(rows, columns);
    
    % a/1. snazim se maximalizovat sum(s(e+)) - sum(s(e-)), do zdroje nic
    % nevede, takze jen sum (s(e+))
    % a/2. jako omezeni zde mam Kirchhoffuv zakon sum (f(e+)) = sum(f(e-)) pro
    % kazdy vrchol, kterych je 5
    % lower a upper bound jsou omezeni hodnoty promenne
    % 
    
    %c =? :)
    %maximalizujeme to, co vytece ze zdroje - to, co do zdroje priteka, coz
    %je nula
    c = [1 1 0 0 0];
    
    % Kirchoffuv zakon - krome s a t plati pro kazdy uzel - to co tece
    % dovnitr vyteka i ven, ma pocet radku incidencni matice - s a t
    A = [-1 0 1 1 0 ; 0 -1 -1 0 1];
    % soucet v Kirchoffove zakone je nula
    b = zeros(2, 1);
    ctype = repmat('E', 2, 1);
    lb = zeros(5, 1);
    ub = [4 ; 2 ; 2 ; 3 ; 5];
    vartype = repmat('I', 5, 1);
    
    %optimization parameters
    schoptions=schoptionsset('ilpSolver','glpk','solverVerbosity',2);
    %call command for ILP
    [xmax,fmin,status,extra] = ilinprog(schoptions,sense,c,A,b,ctype,lb,ub,vartype);
    disp('xmax:'); 
    disp(xmax);
  
    %vypis ve formatu: pocatecni vrchol, koncovy vrchol, tok
    for i = 1 : 5
        for j = 1 : 5 
            if( inc(i, j) == 1 ) % disp pocatecni vrchol
        end
            
            for j = 1 : 5
            if( inc(i, j) == -1 ) % disp koncovy vrchol
        end
        %disp(xmax(i))
    end
        
end