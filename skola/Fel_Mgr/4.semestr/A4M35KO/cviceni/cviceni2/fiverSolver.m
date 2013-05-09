function [ solutionMatrix ] = fiverSolver( size )
% minimalization
sense = 1;

% Problem size
n = size; 

% Number of variables
v = n^2;

% Vector of 


% Vector of criteria - leads to min c'x
c = ones(1,v);

% Vector of right side
b = ones(v,1);

% Linear problem Matrix Construcution

% Left side
l = zeros(v,v);
for i = 1:v
    
    l(i,i) = 1;
    % Skip first row
    if i > n
        l(i,i-n) = 1;
    end
    
    % Skip last row
    if i < (v - n + 1)
        l(i,i+n) = 1;
    end
    
    % Skip first column
    if mod(i,n) ~= 1
        l(i,i-1) = 1;
    end  
    
    % Skip last column
    if mod(i,n) ~= 0
        l(i,i+1) = 1;
    end
    
        
end

% Right side (help variable y)
r = eye(v,v);
r = -2 * r;

A = [ l r ];

% ctype (equitations in this case)
ctype = char(ones(v,1) * 'E');

% lower bound = 0 both for x_i,j and y_i,j
lb = zeros(2*v,1);

% upper bound = 1 for x_i,j and = 2 for y_i,j
ub = [ ones(v,1) ; 2*ones(v,1) ];

% vartype = I both for x_i,j and y_i,j (discrete, not continuous)
vartype = char(ones(2*v,1) * 'I');

% parameters
% Do not display errors, time limit set to 8 minutes
schoptions=schoptionsset('ilpSolver','glpk','solverVerbosity',0,'solverTiLim',60*8);

% call blackbox to solve problem
[xmin,fmin,status,extra] = ilinprog(schoptions,sense,c,A,b,ctype,lb,ub,vartype);

solutionMatrix = zeros (n,n);
for i = 1:n
    for j = 1:n
        solutionMatrix(i,j) = xmin(i*n+j-n,1);
    end
end


