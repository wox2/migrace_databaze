% p - processing time, r - the vector of release times, d - the vector of
% deadlines
function [s, Cmax] = bratleyAlg(p, r, d, UB)
%Initialize s, Cmax,
s = []; %The end time of each task
Cmax = [];

cSoFar = -inf;

% flag for noticing end of tasks - is find in release time vector
END_FLAG = -1;

%find highest p(i) of already completed task - changed in recursion
for i=1:size(p,2)
    if(r(i)==END_FLAG && cSoFar<p(i))
        cSoFar=p(i);
    end;
end

%initialisation of UB when not set - eg first call
if isempty(UB)
    UB = max(d);
end

%bool noticing that algorithm crossed over deadline
deadlinePassed = 0;

for i=1:size(p,2)
    if(r(i)~=END_FLAG && r(i)+p(i)>d(i))
        deadlinePassed = 1;
        break;
    end
    
end

%Condition 1 - cross over deadline
if deadlinePassed
    return
end
        
minR = inf;
sumP = 0;

%count sumP && find minimal r(i)
for i=1:size(p,2)
    
    if(r(i)==END_FLAG)
        continue; end;
    sumP = sumP+p(i);
    if(minR>r(i))
        minR=r(i);
    end
end

LB = max(max(minR,cSoFar)+sumP,max(r+p));


%Condition 2
if LB>UB
    return          
end

%Branch each node to the appropriate number of other nodes
cM = inf;
for i=1:size(p,2)
    
    if(r(i)==END_FLAG)
        continue;
    end

    %get current schedule makespan
    c =  p(i) + r(i);
    minR = inf;
    %sumP = 0;
    for w=1:size(p,2)
        
        if(r(w)==END_FLAG || w==i)
            continue; end;
     %   sumP = sumP+p(w);
        if(minR>r(w))
            minR=r(w);
        end
    end
    
    %count number of not processed tasks
    q = 0;
    for j=1:size(r,2)
        if(r(j)~=END_FLAG)
            q=q+1;
        end
    end
    
    %if there is only one task
    if q==1
        %count Cmax
        s = zeros(1,size(p,2));
        s(i) = r(i);
        Cmax = c;
        UB = max(d);

    %If branching is still needed
    else
        %Solve subproblem by recursive call with modified inputs (e.g with use of the length of the partial solution)
        p_upravene = p;
        p_upravene(i) = c;
        r_upravene = r;
        
        for j=1:size(r_upravene,2)
            %if release is lower than makespan then update release
            if(r_upravene(j)~=END_FLAG)
                r_upravene(j)=max(c,r(j));
            end
            
        end
        r_upravene(i) = -1;
        d_upravene = d;
        d_upravene(i) = -1;

        [s, Cmax] = bratleyAlg(p_upravene, r_upravene, d_upravene, UB);       
        
        %If the subproblem is solved
        % backtrack
        if size(s,2)>0 && Cmax<=cM
            %start time equals to modified release
            s(i) = r(i);
            %Calculate Cmax and update UB
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
