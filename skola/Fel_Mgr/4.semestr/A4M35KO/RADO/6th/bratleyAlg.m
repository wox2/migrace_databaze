% p - processing time, r - the vector of release times, d - the vector of
% deadlines
function [s, Cmax] = bratleyAlg(p, r, d, UB)
% display('Iteration');
%Inicializuj s, Cmax, odhadni UB pokud je prazdne (1.volani)
s = []; %The end time of each task
Cmax = [];

cSoFar = -inf;

for i=1:size(p,2)
    if(r(i)==-1)
        if(cSoFar<p(i))
            cSoFar=p(i);
        end
    end;
end

if isempty(UB)
    UB = max(d);
end

deadlinePassed = 0;
for i=1:size(p,2)
    if(r(i)==-1)
        continue;
    end;
    if r(i)+p(i)>d(i)
        deadlinePassed = 1;
    %         display(['Violation ',num2str(p(i)),' ', num2str(r(i)),' ' , num2str(d(i))])
    %         disp(['Violation ocured @ ',num2str(i)]);
        break;
    end
    
end

%Podminka 1
if deadlinePassed
%     display('Killed');
    return          %Toto vetveni nevede k pripustnemu rozvrhu
end
        
minR = inf;
sumP = 0;
for i=1:size(p,2)
    
    if(r(i)==-1)
        continue; end;
    sumP = sumP+p(i);
    if(minR>r(i))
        minR=r(i);
    end
end

LB = max(max(minR,cSoFar)+sumP,max(r+p));


%Podminka 2
if LB>UB
    %      display(['Violation LB>UB '])
    return          %Toto vetveni nevede k pripustnemu rozvrhu
end

%Vetvi kazdy vrchol na prislusny pocet dalsich
cM = inf;
for i=1:size(p,2)
    
    if(r(i)==-1)
        continue;
    end

    
    %Spocti c - dosavadni delka rozvrhu
    c =  p(i) + r(i);
    minR = inf;
    %sumP = 0;
    for w=1:size(p,2)
        
        if(r(w)==-1 || w==i)
            continue; end;
     %   sumP = sumP+p(w);
        if(minR>r(w))
            minR=r(w);
        end
    end
    
    
    q = 0;
    for j=1:size(r,2)
        if(r(j)~=-1)
            q=q+1;
        end
    end
    %Pokud uz zbyva jen jedna uloha
    if q==1
        %Spocti s, Cmax a aktualizuj UB
        s = zeros(1,size(p,2));
        s(i) = r(i);
        Cmax = c;
        UB = max(d);

        
        %Pokud je jeste treba dal vetvit
    else
        %Vyres podproblem rekurzivnim volanim s upravenymi vstupy (treba s vyuzitim delky castecneho reseni ;-) )
        p_upravene = p;
        p_upravene(i) = c;
        r_upravene = r;
        
        for j=1:size(r_upravene,2)
            if(r_upravene(j)==-1)
                continue
            end
            
            r_upravene(j)=max(c,r(j));
        end
        r_upravene(i) = -1;
        d_upravene = d;
        d_upravene(i) = -1;

        [s, Cmax] = bratleyAlg(p_upravene, r_upravene, d_upravene, UB);       
        
        %Pokud byl podproblem vyresen
        if size(s,2)>0 && Cmax<=cM
            %Spoj reseni podproblemu s aktualnim prirazenim ve vrcholu
            s(i) = r(i);
            %Spocti Cmax, aktualizuj UB
            Cmax =  max(c,Cmax);
            disp(Cmax);
            
            UB = max(d);
            cM = Cmax;
            if (r(i)<=minR)
                break;
            end
        end
        
        
    end
end
