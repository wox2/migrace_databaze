clear
p = 2;
p2 = p^2;
p4 = p^4;
p3 = p^3;
B = zeros(p4,p4);


for i=1:p4
   radek = ceil(i/p2);
   sloupec = mod(i,p2);  
   if(sloupec==0)
       sloupec = p2;
   end
   
%radek   
   pocitadlo = 0;
   maxIndex = p2*radek;
   for u=1:p2
      B(i,maxIndex-pocitadlo) = 1;
      pocitadlo= pocitadlo+1;
   end
   
   %sloupec
   pocitadlo = 0;
   for v=1:p2
      B(i,sloupec+pocitadlo*p2) = 1;
      pocitadlo= pocitadlo+1;
   end
      
   
   cisloBunky = (floor((radek-1)/p))*p + floor((sloupec-1)/p)+1;
   radekBunky = ceil(cisloBunky/p);
   poradiBunkyNaRadku = cisloBunky - (p*(radekBunky-1));
   pocetBunekNaStejnemRadku = poradiBunkyNaRadku-1;
   pocetBunekNaPredchozRadkach = cisloBunky - pocetBunekNaStejnemRadku -1;

   sourPrvniBodBunky = pocetBunekNaPredchozRadkach*p2 + pocetBunekNaStejnemRadku*p + 1;
   
   for j=1:p
       for g=1:p
           pos = sourPrvniBodBunky+((j-1)*p2) + (g-1);
    B(i,pos) = 1;
       end
   end
   B(i,i) = 0;
   
end

g = graph('adj', B);
g = graphcoloring(g);
prumer = 20;
x = 100;
y = p2*prumer+(p2*10);
krok = 1;

for k=1:p4
    g.N(k).Name = '';
    g.N(k).graphicParam(1).width = prumer;
    g.N(k).graphicParam(1).height = prumer;
    g.N(k).graphicParam(1).x = x+(prumer+3)*(krok-1);
    g.N(k).graphicParam(1).y = y;
    krok=krok+1;
    if(mod(k,p2)== 0)
        y = y-(prumer*1.25);
        krok=1;
    end
end

g.adj = zeros(p4,p4);
graphedit(g)
graphedit('viewedgesnames', 'off','viewnodesnames', 'off','arrowsvisibility', 'off')