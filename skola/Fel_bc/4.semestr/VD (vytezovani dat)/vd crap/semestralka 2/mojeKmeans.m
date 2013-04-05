
  [measIDs, measC]=kmeans(meas, 2, 'replicates', 5, 'dist','sqeuclidean');
  [silh2] = silhouette(meas,measIDs,'sqeuclidean');
gplotmatrix(meas, meas, measIDs);
silhouette(meas, measIDs);

pause;
 [measIDs, measC]=kmeans(meas, 2, 'replicates', 5, 'dist','cityblock');
  [silh3] = silhouette(meas,measIDs,'cityblock');
  gplotmatrix(meas, meas, measIDs);
silhouette(meas, measIDs);

pause;
  [measIDs, measC]=kmeans(meas, 2, 'replicates', 5, 'dist', 'cos');
  [silh4]=silhouette(meas, measIDs, 'cos');
  gplotmatrix(meas, meas, measIDs);
silhouette(meas, measIDs);

pause;