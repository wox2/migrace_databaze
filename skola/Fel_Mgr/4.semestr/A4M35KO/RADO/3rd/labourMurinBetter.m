% Assigment n2: Fiver
% Author: Radovan Murin, for course Kombinatoricka Optimalizace 
% Course: Thursdays 
% FEE CTU 2012, Spring Semester
% murinrad@fel.cvut.cz
% 

clear;

sense=1;				%typ optimalizace: 1=minimalizace, -1=maximalizace

nOfHrs=24;
A = zeros(nOfHrs);
AA = -eye(nOfHrs);

for i=1:7
    for j=i+17:24
        A(i,j)=1;
    end
    for j=1:i
        A(i,j)=1;
    end
end

for i=8:24
    for j=i-7:i
        A(i,j)=1;
    end
end

A=[A,AA;-A,AA];

A;

b = [6 6 6 6 6 8 9 12 18 22 25 21 21 20 18 21 21 24 24 18 18 18 12 8]';      
b =  [b ; -b]; %vektor b
c = [zeros(1,24) ones(1,24)]';           %vektor c

ctype = zeros(48,1);   %typ podminky: 'E'="=", 'L'="<=", 'G'=">="
for i=1:48
    ctype(i,1)='L';
end
ctype = char(ctype);



lb = 0*ones(48,1);            %dolni mez promenych
ub = 50*ones(48,1);            %horni mez promenych

vartype = zeros(48,1);   %Typ promenne 'C'=spojita, 'I'=celociselna
for i=1:48 
    vartype(i,1)='I';
end
vartype = char(vartype);



%Parametry optimalizace
schoptions=schoptionsset('ilpSolver','glpk','solverVerbosity',2);

%spusteni optimalizace z TORSCHE
[xmin,fmin,status,extra] = ilinprog(schoptions,sense,c,A,b,ctype,lb,ub,vartype);




if(status==1)
    
    disp('Reseni:');
%  xmin
res = zeros(24,1);
for i=1:23
    oneOver = 0;
    for j=i:i+7
        res(mod(j,24)+1,1)=res(mod(j,24)+1,1)+xmin(i+1,1);
    end
    
end

   
    disp('Hodnota cilove funkce:');
    fmin
else
    disp('Problem nema reseni!');
end;
bar([res,b(1:24)],'GROUP')
%konec souboru

