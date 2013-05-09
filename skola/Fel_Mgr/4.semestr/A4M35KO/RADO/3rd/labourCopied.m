%Kombinatoricka optimalizace (A4M35KO)
%Celo??seln? line?rn? programov?n?
%
% Planovani smen
clc; 
clear;
close all;

%%
r=48;
b = [6 6 6 6 6 8 9 12 18 22 25 21 21 20 18 21 21 24 24 18 18 18 12 8]';
b=[b;-b]
c = [zeros(1,r/2) ones(1,r/2)]' % minimalizace z
A = zeros(r/2,r); % rovnice rozsirene o y
for i=1:24
    A(i,24+i)=-1;
end
for i=1+17:24+17
    for j=0:7
        A(i-17,mod(i+j-1,24)+1)=1;
    end
end
A=[A;-A]
for i=25:48
    A(i,i)=-1;
end


sense=1;                                %typ optimalizace: 1=minimalizace, -1=maximalizace
ctype = repmat('L',r,1)';     %typ podminky: 'E'="=", 'L'="<=", 'G'=">="
lb = zeros(1,r)';            %dolni mez promenych (0)
ub = [25*ones(1,r)]';            %horni mez promenych (1,2)
vartype = repmat('I',r,1)';   %Typ promenne 'C'=spojita, 'I'=celociselna
%Parametry optimalizace
schoptions=schoptionsset('ilpSolver','glpk','solverVerbosity',2);
%%
%spusteni optimalizace z TORSCHE
[xmin,fmin,status,extra] = ilinprog(schoptions,sense,c,A,b,ctype,lb,ub,vartype);

res=zeros(1,24);
for i=0:23
    for j=0:7
    res(mod(i+j,24)+1)=res(mod(i+j,24)+1)+xmin(i+1);
    end
end

if(status==1)
    disp('Reseni:');
    xmin
    disp('Hodnota cilove funkce:');
    fmin
else
    disp('Problem nema reseni!');
end;

bar(1:24,[res' b(1:24)])