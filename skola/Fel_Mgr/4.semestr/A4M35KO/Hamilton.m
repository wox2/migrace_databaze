gHam = hamiltoncircuit(g, 'u');
graphedit(gHam, 'position', [1300 50 600 1050], 'fit');
edges = cell2mat(gHam.edl);
actEdge = 1;

for i = 1:size(edges, 1)
   actCity = edges(actEdge, 1);
   cityName = g.N(actCity).Name;
   cityName(size(cityName, 2)+1:18) = ' ';
   x = g.N(actCity).graphicParam(1).x;
   y = g.N(actCity).graphicParam(1).y;
   str = sprintf('City: %sCoordinates(x,y): %g, %g', cityName, x, y);
   disp(str)
   actEdge = find(edges(:, 2) == actCity);
end

distance = sum(edges(:,3));
disp(['Total distance: ' num2str(distance) ' km']);
