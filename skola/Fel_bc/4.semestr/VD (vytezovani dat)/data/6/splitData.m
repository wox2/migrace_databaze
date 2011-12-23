function [itrain, itest] = splitData(N, trainingFraction)
% SPLITDATA Returns indices of training and testing cases

    % Version 1
    rvec = rand(N,1);
    itrain = (rvec <= trainingFraction);
    itest = (rvec > trainingFraction);

%     % Version 2
%     ntrain = ceil(N * trainingFraction);
%     ind = randperm(N);
%     itrain = ind(1:ntrain);
%     itest = ind(ntrain+1:end);
end