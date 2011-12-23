measPdist = pdist(meas,'euclidean');
measTree = linkage(measPdist,'average');
[h,nodes] = dendrogram(measTree,0);
set(gca,'TickDir','out','TickLength',[.002 0],'XTickLabel',[]);

cophenet(measTree,measPdist)
