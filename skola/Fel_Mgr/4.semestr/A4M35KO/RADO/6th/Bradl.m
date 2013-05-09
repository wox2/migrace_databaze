% Radovan Murin
% Kombinatoricka optimalizacia
% 2012, LS
% FEL CVUT




clear;

%d=[7,5,6,4];
%r=[4,1,1,0];
%p=[2,1,2,2];

d = [17, 15, 12, 8, 17];
r = [10, 10, 5, 5, 2];
p = [3, 2, 4, 2, 2];

[s, Cmax] = bratleyAlg(p,r,d,[]);

T = taskset(p);
T.ReleaseTime = (r);
T.Deadline = (d);
for i=1:length(s)
    T.Name(i) = ['T' num2str(i)];
end
T.add_schedule('ko du bratley',s,T.ProcTime,ones(1,length(s)));
plot(T)