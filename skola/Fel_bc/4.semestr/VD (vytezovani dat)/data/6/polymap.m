function outData = polymap(inData, deg)

    [N,D] = size( inData );
    outData = zeros(N, D*deg);
    
    for iD = 1:D,
        for iDeg = 1:deg,
            outData(:, (iD-1)*deg + iDeg) = inData(:,iD).^iDeg;
        end
    end

end