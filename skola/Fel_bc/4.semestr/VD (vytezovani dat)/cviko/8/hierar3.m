hidx = cluster(measTreeCos,'criterion','distance','cutoff',.006);
for i = 1:5
    clust = find(hidx==i);
    plot3(meas(clust,1),meas(clust,2),meas(clust,3),ptsymb{i});
    hold on
end
hold off
xlabel('Sepal Length'); ylabel('Sepal Width'); zlabel('Petal Length');
view(-137,10);
grid on
