function [A] = ILP1()
  sense=1; %sense of optimization: 1=minimization, -1=maximization
  b = [ 6 6 6 6 6 8 9 12 18 22 25 21 21 20 18 21 21 24 24 18 18 18 12 8]'; %vector b
  finalHour = 24;
  A = zeros(finalHour, finalHour);

  %first equation 8..24
  for i = 8 : finalHour
      for j = i-7 : i
         A( i, j) = 1;
      end
  end
  
  %second equation 1..7
  for i = 1 : 7
     for j = i + 17 : finalHour 
        A(i, j) = 1;
     end
     for j = 1 : i
         A(i, j) = 1;
     end
  end
  
  
  c = ones(24, 1)'; %vector c
  
  %ctype =   ['G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G']'; %constraint type: ’E’="=", ’L’="<=", ’G’=">="
  ctype =   repmat('G', finalHour, 1)'; %constraint type: ’E’="=", ’L’="<=", ’G’=">="
  
  %ctype =   ['L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L','L']'; %constraint type: ’E’="=", ’L’="<=", ’L’=">="
  lb = zeros(24,1); %lower bound of the variables
  
  ub = zeros(24,1); %upper bound of the variables
  for i = 1 : finalHour
      ub(i, 1) = 1000;
  end    
  
  vartype = ['I','I','I','I','I','I','I','I','I','I','I','I','I','I','I','I','I','I','I','I','I','I','I','I']'; %variable type: ’C’=continuous, ’I’=integer
  %optimization parameters
  schoptions=schoptionsset('ilpSolver','glpk','solverVerbosity',2);
  %call command for ILP
  [xmin,fmin,status,extra] = ilinprog(schoptions,sense,c,A,b,ctype,lb,ub,vartype);
  
  %xmin - start of shifts
  
  %working hours is stored in res
  res=zeros(1,24);
  
  % for each hour
  for i=0:23
    %for the shift  
    for j=0:7
       res(mod(i+j,24)+1)=res(mod(i+j,24)+1)+xmin(i+1);
    end
  end

  %show the solution
  if(status==1)
    disp('Solution: '); disp(xmin)
    disp('Objective function: '); disp(fmin)
    disp(extra);
    colormap(cool);
    bar(1:24,[res' b(1:24)]);
    %bar(1:24,res' );
  else
    disp('No feasible solution found!');
  end;
end
