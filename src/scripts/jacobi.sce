//Rodríguez Montiel Moises Ulises
//2MN51
function X = jacobi(A, B, fx)
    [m,n] = size(A)  
    iter = 0 
    if (m <> n) then    
        error("La matriz no es cuadrada.")
    end
    e = 5*10^-fx
    X = zeros(n, 1);
    y=[];
    fin=%F;
    while fin==%F
        fin=%T;
        for i=1:1:n
           y(i)=B(i);
           for j=1:1:n
               if (i~=j)
                   y(i)=y(i)-A(i,j)*X(j);
               end
           end
           y(i)=y(i)/A(i,i);
           delta=abs(X(i)-y(i));
           if (delta>e)
               fin=%F;
           end
        end
        for i=1:1:n
           X(i)=y(i); 
        end
        iter = iter + 1;
        if(iter>100)
            break;
        end
    end
endfunction
