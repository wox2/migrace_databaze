eucD = pdist(meas,'euclidean');
clustTreeSng = linkage(eucD,'single');
[h,nodes] = dendrogram(clustTreeSng,0);
set(gca,'TickDir','out','TickLength',[.002 0],'XTickLabel',[]);
