  [measIDs,measC] = kmeans(meas,2,'dist','sqeuclidean');
  [silh1] = silhouette(meas,measIDs,'sqeuclidean');


  [measIDs, measC]=kmeans(meas, 3, 'replicates', 5, 'dist','sqeuclidean');
  [silh2] = silhouette(meas,measIDs,'sqeuclidean');

  [measIDs, measC]=kmeans(meas, 3, 'dist','sqeuclidean');
  [silh3] = silhouette(meas,measIDs,'sqeuclidean');


  [measIDs, measC]=kmeans(meas, 3, 'dist', 'cos');
  [silh4]=silhouette(meas, measIDs, 'cos');


  [mean(silh1) mean(silh2) mean(silh3) mean(silh4)]
