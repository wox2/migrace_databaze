clear all;
% TEST Kombinatoricka Optimalizacia,

% RADOVAN MURIN
% One source, one destination, 12 pilots and 4 test dates
numberOfNodes = 17;
pilotsN = 12;
tests = 4;
L = zeros(numberOfNodes);
U = zeros(numberOfNodes);

% c) Hodnota nejlevnejsiho toku, ktory pilot pojde na ktory termin a potom
% sucty pilotov v jednotlivych tasks.


% d) ak zmenime konstantu c vsade rovnako, tak ziaden, ak nie tak na
% vysledok to bude mat dopad, pretoze hovorime, ze treningy stoja viac
% alebo menej v zavyslosti od toho ktory pilot sa kedy trenuje, mohli by
% sme tym modelovat situaciu kedy je pre nas lacnejsie ak je pilot na
% treningu v mieste bydlista a nemusime mu platit za sluzobnu cestu.



c=ones(numberOfNodes);


% % The upper bound represent the time posibilities of pilots
U(1,13) = 1;
U(1,14) = 0;
U(1,15) = 0;
U(1,16) = 1;
U(2,13) = 0;
U(2,14) = 1;
U(2,15) = 0;
U(2,16) = 1;
U(3,13) = 0;
U(3,14) = 0;
U(3,15) = 1;
U(3,16) = 1;
U(4,13) = 0;
U(4,14) = 0;
U(4,15) = 1;
U(4,16) = 1;
U(5,13) = 1;
U(5,14) = 1;
U(5,15) = 0;
U(5,16) = 1;
U(6,13) = 0;
U(6,14) = 1;
U(6,15) = 0;
U(6,16) = 0;
U(7,13) = 0;
U(7,14) = 1;
U(7,15) = 1;
U(7,16) = 1;
U(8,13) = 0;
U(8,14) = 0;
U(8,15) = 0;
U(8,16) = 1;
U(9,13) = 0;
U(9,14) = 0;
U(9,15) = 1;
U(9,16) = 1;
U(10,13) = 1;
U(10,14) = 0;
U(10,15) = 1;
U(10,16) = 0;
U(11,13) = 0;
U(11,14) = 1;
U(11,15) = 0;
U(11,16) = 1;
U(12,13) = 0;
U(12,14) = 1;
U(12,15) = 1;
U(12,16) = 1;


% This matches the examinator time possibilities
U(13,17) = 3;
U(14,17) = 2;
U(15,17) = 4;
U(16,17) = 4;

% One source one target as 12 pilots are needed to be trained, 11 is the
% supply and each pilot has a supply of one and 12 is the demand of the target 
% Tasks are transitionary
b = [1 1 1 1 1 1 1 1 1 1 1 1 0 0 0 0  -12]';

g = graph;
    
F = g.mincostflow(c,L,U,b);
% This outputs the number of pilots that will attend the courses. In the
% order from first to fourth.
F(13:16,17)



