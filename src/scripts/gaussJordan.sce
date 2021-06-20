function rpta = gaussJordan(A, b)
    [m,n] = size(A)   
    if (m <> n) then    
        error("La matriz no es cuadrada.")
    end
    for i = 1:n-1
        [q,p] = max(A(i:n,i))
        p = p+i-1
        A = intercambiarFilas(A, i, p)
        b = intercambiarFilas(b, i, p)
        for j = i+1:n
            r = A(j,i)/A(i,i)
            A(j,:) = A(j,:) - r*A(i,:)
            b(j,:) = b(j,:) - r*b(i,:)
        end
    end 
    for j = n:-1:1
        suma = 0
        for k = j+1:n
            suma = suma + A(j,k)*x(k)
        end
        x(j) = ( b(j) - suma ) / A(j,j)
    end 
    rpta = x
endfunction
function rpta = intercambiarFilas(A, i, j)
    temp = A(i,:)
    A(i,:) = A(j,:)
    A(j,:) = temp
    rpta = A
endfunction


