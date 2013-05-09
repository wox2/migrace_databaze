function [A] = ILP2()
  sense=1; %sense of optimization: 1=minimization, -1=maximization
  b = [ 6 6 6 6 6 8 9 12 18 22 25 21 21 20 18 21 21 24 24 18 18 18 12 8]'; %vector b
  b = [b; -b];
  finalHour = 24;
  matrixSize = 2 * finalHour;
  % columns 1..24 x_i, 25..48 z_i
  % rows 1..24 +absolute part of equation, 25..48 -absolute part of
  % equation
  A = zeros(matrixSize, matrixSize);

  %add + absolute equation 
  for i = 1 : finalHour
      for j = i-7 : i
         %x_i 
         A(i, mod(j, 24) + 1) = 1;
      end
      %-z_i
      A(i, i + 24) = -1;
  end
  
   %add - absolute equation 
  for i = 1 : finalHour
      for j = i-7 : i
         rowIndex = 24 + i; 
         %-x_i 
         A(rowIndex, mod(j, 24) + 1) = -1;
      end
      %-z_i
      A(rowIndex, rowIndex) = -1;
  end
  

  %important ones are only z_i's -eg indices 25..48
  c = [zeros(24, 1) ; ones(24, 1)]; %vector c
  
  ctype =   repmat('L', matrixSize, 1)'; %constraint type: ’E’="=", ’L’="<=", ’G’=">="
  
  lb = zeros(48,1); %lower bound of the variables
  
  ub = repmat(50, 48,1); %upper bound of the variables
  
  vartype = repmat('I', 48, 1)'; %variable type: ’C’=continuous, ’I’=integer
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
