measPdistCos = pdist(meas,'cosine');
measTreeCos = linkage(measPdistCos,'average');
cophenet(measTreeCos,measPdistCos)


[h,nodes] = dendrogram(measTreeCos,0);
set(gca,'TickDir','out','TickLength',[.002 0],'XTickLabel',[]);


[h,nodes] = dendrogram(measTreeCos,12);
[sum(ismember(nodes,[11 12 9 10])) sum(ismember(nodes,[6 7 8])) sum(ismember(nodes,[1 2 4 3])) sum(nodes==5)]
