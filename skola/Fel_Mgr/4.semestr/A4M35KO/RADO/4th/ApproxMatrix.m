function c = ApproxMatrix(alpha,beta,x,f)

c = zeros(size(x,2));
for j=1:size(x,2)
    for i=1:j-1
        sum =0;
        for k=i:j
            sum = sum + (f(k)-(f(i)+(x(k)-x(i))*((f(j)-f(i))/(x(j)-(x(i))))))^2;
        end
        c(i,j) = alpha + beta*sum; 
    end
end