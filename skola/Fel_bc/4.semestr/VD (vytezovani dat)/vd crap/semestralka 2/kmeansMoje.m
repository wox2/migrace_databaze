loadAndNormalizeData;
meas = karty_norm;


  [measIDs, measC]=kmeans(meas, 2, 'replicates', 5, 'dist','sqeuclidean');
  [silh2] = silhouette(meas,measIDs,'sqeuclidean');

  [measIDs, measC]=kmeans(meas, 2, 'replicates', 5, 'dist','cityblock');
  [silh3] = silhouette(meas,measIDs,'cityblock');


  [measIDs, measC]=kmeans(meas, 2, 'replicates', 5, 'dist', 'cos');
  [silh4]=silhouette(meas, measIDs, 'cos');


  [mean(silh2) mean(silh3) mean(silh4)]
  pause;
 
  [measIDs, measC]=kmeans(meas, 5, 'replicates', 5, 'dist','sqeuclidean');
  [silh2] = silhouette(meas,measIDs,'sqeuclidean');

  [measIDs, measC]=kmeans(meas, 5, 'replicates', 5, 'dist','cityblock');
  [silh3] = silhouette(meas,measIDs,'cityblock');


  [measIDs, measC]=kmeans(meas, 5, 'replicates', 5, 'dist', 'cos');
  [silh4]=silhouette(meas, measIDs, 'cos');


  [mean(silh2) mean(silh3) mean(silh4)]
  
  pause;
  
    [measIDs, measC]=kmeans(meas, 10, 'replicates', 5, 'dist','sqeuclidean');
  [silh2] = silhouette(meas,measIDs,'sqeuclidean');

  [measIDs, measC]=kmeans(meas, 10, 'replicates', 5, 'dist','cityblock');
  [silh3] = silhouette(meas,measIDs,'cityblock');


  [measIDs, measC]=kmeans(meas, 10, 'replicates', 5, 'dist', 'cos');
  [silh4]=silhouette(meas, measIDs, 'cos');


  [mean(silh2) mean(silh3) mean(silh4)]
  
  pause;
  
    
    [measIDs, measC]=kmeans(meas, 15, 'replicates', 5, 'dist','sqeuclidean');
  [silh2] = silhouette(meas,measIDs,'sqeuclidean');

  [measIDs, measC]=kmeans(meas, 15, 'replicates', 5, 'dist','cityblock');
  [silh3] = silhouette(meas,measIDs,'cityblock');


  [measIDs, measC]=kmeans(meas, 15, 'replicates', 5, 'dist', 'cos');
  [silh4]=silhouette(meas, measIDs, 'cos');
  
  [mean(silh2) mean(silh3) mean(silh4)]
    pause;
  
    
    [measIDs, measC]=kmeans(meas, 15, 'replicates', 5, 'dist','sqeuclidean');
  [silh2] = silhouette(meas,measIDs,'sqeuclidean');

  [measIDs, measC]=kmeans(meas, 15, 'replicates', 5, 'dist','cityblock');
  [silh3] = silhouette(meas,measIDs,'cityblock');


  [measIDs, measC]=kmeans(meas, 15, 'replicates', 5, 'dist', 'cos');
  [silh4]=silhouette(meas, measIDs, 'cos');

  [mean(silh2) mean(silh3) mean(silh4)]
      pause;
  
    
    [measIDs, measC]=kmeans(meas, 20, 'replicates', 5, 'dist','sqeuclidean');
  [silh2] = silhouette(meas,measIDs,'sqeuclidean');

  [measIDs, measC]=kmeans(meas, 20, 'replicates', 5, 'dist','cityblock');
  [silh3] = silhouette(meas,measIDs,'cityblock');


  [measIDs, measC]=kmeans(meas, 20, 'replicates', 5, 'dist', 'cos');
  [silh4]=silhouette(meas, measIDs, 'cos');
  
  [mean(silh2) mean(silh3) mean(silh4)]


  [mean(silh2) mean(silh3) mean(silh4)]
  pause;
