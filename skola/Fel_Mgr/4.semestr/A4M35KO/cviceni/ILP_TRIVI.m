function [A] = ILP_TRIVI()
  sense=1; %sense of optimization: 1=minimization, -1=maximization
  b = [2 -2 0]'; %vector b
  A = [1 1; 1 -1 ; 1 0];  
  
  c = [1 0]; %vector c
  
  %ctype =   ['G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G']'; %constraint type: ’E’="=", ’L’="<=", ’G’=">="
  ctype =   ['G', 'L', 'G']'; %constraint type: ’E’="=", ’L’="<=", ’G’=">="
  
  %ctype =   ['L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L']'; %constraint type: ’E’="=", ’L’="<=", ’L’=">="
  lb = [-500 -500]'; %lower bound of the variables
  
  ub = [+500 +500]'; %upper bound of the variables
  
  vartype = ['I' 'I']'; %variable type: ’C’=continuous, ’I’=integer
  %optimization parameters
  schoptions=schoptionsset('ilpSolver','glpk','solverVerbosity',2);
  %call command for ILP
  [xmin,fmin,status,extra] = ilinprog(schoptions,sense,c,A,b,ctype,lb,ub,vartype);
  
  %show the solution
  if(status==1)
    disp('Solution: '); disp(xmin)
    disp('Objective function: '); disp(fmin)
    disp(extra);
  else
    disp('No feasible solution found!');
  end;
end