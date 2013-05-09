%Kombinatoricka optimalizace (A4M35KO)
%Celoèíselné lineární programování
%
%Premysl Sucha (suchap@fel.cvut.cz)
%22.2.2010

clear;

sense=1;				%typ optimalizace: 1=minimalizace, -1=maximalizace

b = [2 1]';             %vektor b
A = [ 1 -1; ...         %matice A
      0  1; ...
    ];
c = [ 1 1]';            %vektor c

ctype = ['L','E']';     %typ podminky: 'E'="=", 'L'="<=", 'G'=">="
lb = [0,0]';            %dolni mez promenych
ub = [2,2]';            %horni mez promenych
vartype = ['C','C']';   %Typ promenne 'C'=spojita, 'I'=celociselna

%Parametry optimalizace
schoptions=schoptionsset('ilpSolver','glpk','solverVerbosity',2);

%spusteni optimalizace z TORSCHE
[xmin,fmin,status,extra] = ilinprog(schoptions,sense,c,A,b,ctype,lb,ub,vartype);

if(status==1)
    disp('Reseni:');
    xmin
    disp('Hodnota cilove funkce:');
    fmin
else
    disp('Problem nema reseni!');
end;

%konec souboru
