function [s, Cmax] = bratleyAlg(p, r, d, UB)

    % flag for noticing end of tasks - is find in release time vector
    END_FLAG = -1;


    %Initialize s, Cmax and estimate UB if it is empty (1st function call)
    s = [];
    Cmax = [];

    %find highest p(i) of already completed task - changed in recursion
    cReached = getMaximalCReached(p, r, END_FLAG);

    %minimal release time
    minR = getMinR(p, r, END_FLAG);

    %sum of p_j for each not yet scheduled task
    sumP = getSumP(p, r, END_FLAG);

    %compute LB
    LB = max(max(minR,cReached)+sumP,max(r+p));


    %initialisation of UB when not set
    if isempty(UB)
        %latest deadline 
        UB = max(d);
    end

    deadlineCrossedOver = checkDeadlines(p, r, d, END_FLAG);
    %Condition 1 - exceeding deadline
    if deadlineCrossedOver
        return %This branching does not lead to feasible solution
    end

    %Condition 2
    if LB > UB
        return          %This branching does not lead to feasible solution
    end

    %maximal found makespan
    cM = inf;
    %Branch each node to the appropriate number of other nodes
    % according to p
    for i = 1 : size(p, 2)
        %skip already scheduled tasks
        if(r(i)==-1)
            continue;
        end
    
        %Calculate c  -  equals to the partial schedule length of new created
        %schedule
        c = p(i) + r(i);
        minR = inf;
        sumP = 0;
    
        %count remaining jobs
        remainingJobs = countRemainingJobs(r, END_FLAG);
    
        %If only one job remains
        if remainingJobs == 1
            %Calculate s, Cmax and update UB
            s = zeros(1, size(p, 2));
            Cmax = c;
            UB = max(d);
        
        %If branching is still needed
        else
            %Solve subproblem by recursive call with modified inputs (e.g with use of the length of the partial solution)
            p_upravene = p;
    
            % store length of partial solution
            p_upravene(i) = c;        
            r_upravene = r;
        
            %update not processed task release times 
            % set them to c if they are lower 
            for k = 1 : size(r, 2)
                if(not(r_upravene == -1))
                    r_upravene(k) = max(c, r(k));
                end
            end
        
            r_upravene(i) = -1;
            d_upravene = d;
            [s, Cmax] = bratleyAlg(p_upravene, r_upravene, d_upravene, UB);
            
            %If the subproblem is solved - eg recursive function returned vector longer than zero 
            % this solution must be better than cMax
            if size(s, 2) > 0 && cMax <= cM
                %Join the subproblem solution with the actual assignment in the node
                s = r(i);

                %Calculate Cmax and update UB
                Cmax = max(c, Cmax); 
                UB = max(d);
                cM = Cmax;
               %if beggins in the minimal r task... 
               if (r(i)<=minR)
                   break;
               end
            end
            
        end
    end
end


function [deadlineCrossedOver] = checkDeadlines(p, r, d, END_FLAG)
    deadlineCrossedOver = 0;
    %for each not processed task - check deadlines
    for index=1:size(p,2)
        if(not(r(index) == END_FLAG) && r(index) + p(index) > d(index))
             deadlineCrossedOver = 1;
        end
    end
end

function [cReached] = getMaximalCReached(p, r, END_FLAG)
    cReached = -inf;

    %find highest p(i) of already completed task - changed in recursion
    for index = 1 : size(p, 2)
        if (r(index) == END_FLAG)
            if (cReached < p(index))
                cReached = p(index);
            end
        end
    end
end

function [sumP] = getSumP(p, r, END_FLAG)
    sumP = 0;
    for index = 1:size(p,2)
        if(not(r(index)== END_FLAG))
            sumP = sumP + p(index);
        end
    end
end

function [minR] = getMinR(p, r, END_FLAG)
    minR = inf;
    for index = 1:size(p,2)
        if(not(r(index)== END_FLAG) && minR>r(index))
            minR=r(index);
        end
    end
end

function [remainingJobs] = countRemainingJobs(r, END_FLAG)
    remainingJobs = 0;
    %count remaining jobs 
    for j = 1 : size(r, 2)
        if(not(r(j) == END_FLAG))
            remainingJobs = remainingJobs + 1;
        end
    end
end