load poker-hand-training-true.data;
[measIDs, measC]=kmeans(meas, 2);
gplotmatrix(meas, meas, measIDs);
silhouette(meas, measIDs);
