function class = classify2(nearestIdxs, trainingClasses, numClasses)

	class = zeros(length(nearestIdxs), 1);
	for i = 1:length(nearestIdxs)
		classesCount = zeros(1,numClasses);
		for j = 1:numClasses
			classesCount(j) = sum( trainingClasses(nearestIdxs(i,:),:) == j);
		end
		[cnt,finalClass] = max(classesCount);
		class(i) = finalClass;
	end

end