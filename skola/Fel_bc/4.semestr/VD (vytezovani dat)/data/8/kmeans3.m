         [measIDs, measC]=kmeans(meas, 2, 'display', 'final', 'replicates', 5);
         [measIDs, measC]=kmeans(meas, 3, 'display', 'final', 'replicates', 5);
         [measIDs, measC]=kmeans(meas, 3, 'display', 'final', 'replicates', 5, 'dist', 'cos');
         silhouette(meas, measIDs);
