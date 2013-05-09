clear all;
load projectionData.mat;
n1 = size(sumC,2);
n2 = size(sumR,2);
sumN1N2 = n1+n2;

white = 1;
black = 0;
iterations = 62;
I = zeros(n1,n2);
cprev = zeros(sumN1N2,sumN1N2);
b = [sumR -sumC]';
u = [[zeros(n1,n2);zeros(n1,n2)],[ones(n1,n2);zeros(n1,n2)]];
l = [[zeros(n1,n2);zeros(n1,n2)],[zeros(n1,n2);zeros(n1,n2)]];
for ic = 1:iterations
    
    
    c = fce(cprev,I);
    cprev = c;
    
    g = graph;
    
    F = g.mincostflow(c,l,u,b);
    I = F(1:20,21:40);
    
    subplot(11,6,ic);
    imagesc(logical(I));
    colormap(gray);
    axis off;
    axis square;
    ic
    
end


