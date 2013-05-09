function c = fce(cprev,I)
black = 0;
white = 1;
 c = zeros(size(cprev,1),size(cprev,1));
 s = size(I,1);
 for i=2:s-1
     for j=2:s-1
         if (I(i,j) == white)
                    summa = I(i-1,j-1) + I(i,j-1)+ I(i+1,j-1)+ ... 
                       I(i-1,j)   + I(i+1,j)+...
                       I(i-1,j+1) + I(i,j+1) + I(i+1,j+1);
%                summa = sum(sum(I(i-1:i+1,j-1:j+1)))-1;
               if(summa == 0)
                c(i,s+j) = 1;
               end
               if(summa == 1)
                    c(i,s+j) = 0.2;
               end
               
               if(summa == 2)
                      c(i,s+j) = 0.1;
               end
         end
          if(I(i,j) == black &&...
             ((I(i,j-1)==white && I(i,j+1)==white)||...
             ((I(i-1,j)==white && I(i+1,j)==white))));
            c(i,s+j) = -0.1;
      
         end
         
     end
 end
 

          c  = c+ 0.5*cprev;
 
    
 


end

