ptsymb = {'bs','r^','md','go','c+'};
for i = 1:2
    clust = find(measIDs==i);
    plot3(meas(clust,1),meas(clust,2),meas(clust,3),ptsymb{i});
    hold on
end

plot3(measC(:,1),measC(:,2),measC(:,3),'ko', 'MarkerSize', 14,  'LineWidth',2);
plot3(measC(:,1),measC(:,2),measC(:,3),'kx', 'MarkerSize', 14,  'LineWidth',2);

view(-137,10);
grid on
