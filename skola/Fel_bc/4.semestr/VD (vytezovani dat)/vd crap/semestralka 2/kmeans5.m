[measIDs, measC]=kmeans(meas, 3, 'dist', 'cos');
lnsymb = {'b-','r-','m-'};
names = {'SL','SW','PL','PW'};
meas0 = meas ./ repmat(sqrt(sum(meas.^2,2)),1,10);
ymin = min(min(meas0));
ymax = max(max(meas0));
for i = 1:3
    subplot(1,3,i); plot(meas0(measIDs==i,:)',lnsymb{i});
    hold on; plot(measC(i,:)','k-','LineWidth',2); hold off;
    title(sprintf('Cluster %d',i));
    set(gca,'Xlim',[.9 4.1],'XTick',1:4,'XTickLabel',names,'YLim',[ymin ymax])
end
