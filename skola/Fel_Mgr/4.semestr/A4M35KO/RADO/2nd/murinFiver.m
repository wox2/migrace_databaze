% Assigment n2: Fiver
% Author: Radovan Murin, for course Kombinatoricka Optimalizace 
% Course: Thursdays 
% FEE CTU 2012, Spring Semester
% murinrad@fel.cvut.cz
% 

clear;
gameSize = 3;
cells  = gameSize^2;
sense=1;				%typ optimalizace: 1=minimalizace, -1=maximalizace

xs = zeros(cells);
for i=1:cells
    xs(i,i)=1;
    if(i-1>=1 && mod(i,gameSize)~=1)
    xs(i,i-1)=1;
    end
    if(i+1<=cells && mod(i,gameSize)~=0)
    xs(i,i+1)=1;
    end
    if(i-gameSize>=1 && i>gameSize)
    xs(i,i-gameSize) = 1;
    end
    if(i+gameSize<=cells && i<=(gameSize^2-gameSize))
    xs(i,i+gameSize) = 1;
    end
   
end


A = [ xs , -2*eye(cells)];

b = ones(1,cells)';                             %vektor b
c = [ones(1,cells) zeros(1,cells)]';            %vektor c

ctype = zeros(cells,1);   %typ podminky: 'E'="=", 'L'="<=", 'G'=">="
for i=1:cells 
    ctype(i,1)='E';
end
ctype = char(ctype);



lb = zeros(2*cells,1);            %dolni mez promenych
ub = ones(2*cells,1);            %horni mez promenych
for i=cells+1:2*cells
    ub(i,1) = 2;
end

vartype = zeros(2*cells,1);   %Typ promenne 'C'=spojita, 'I'=celociselna
for i=1:2*cells 
    vartype(i,1)='I';
end
vartype = char(vartype);



%Parametry optimalizace
schoptions=schoptionsset('ilpSolver','glpk','solverVerbosity',2);

%spusteni optimalizace z TORSCHE
[xmin,fmin,status,extra] = ilinprog(schoptions,sense,c,A,b,ctype,lb,ub,vartype);




if(status==1)
    
    disp('Reseni:');
   % xmin
   control = 1;
   res = zeros(gameSize);
   for i=1:gameSize
       for j=1:gameSize
           res(i,j) = xmin(control,1);
           control=control+1;
       end
   end
   res
   
    disp('Hodnota cilove funkce:');
    fmin
else
    disp('Problem nema reseni!');
end;

%konec souboru
