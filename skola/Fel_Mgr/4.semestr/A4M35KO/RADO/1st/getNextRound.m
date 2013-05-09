function n = getNextRound(number,base)
n=number;
modulo = mod(n,base);
while(modulo~=0)
    n=n+1;
    modulo= mod(n,base);
end

end

