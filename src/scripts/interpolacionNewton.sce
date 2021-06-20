function y = interpolNewton(func, grado, x)
    [n, m] = size(func)
    dif = zeros(grado, grado)
    y = 0
    h1 = 0;
    h2 = 0;
    if(n <> 2)
        error("No es una función tabular")
    end
    for i=1:1:m-2
        h1 = func(1,i + 1) - func(1,i);
        h2 = func(1,i + 2) - func(1,i + 1);
        if h1 ~= h2
            error('Este metodo solo puede utilizarse en incrementos de X iguales');
        end
    end
    grado = round(grado);
    if (or([~isreal(grado), grado <= 0]))
        error('El grado del polinomio debe ser mayor que 0');
    end
    if grado >= size(func,2)
        error('El grado del polinomio es mayor al permitido por el metodo.');
    end
    for j=1:1:grado
        for i=1:1:grado - j + 1
            if j == 1
                dif(i+j-1, j) = func(2, i + 1) - func(2, i);
            else
                dif(i+j-1, j) = dif(i+j-1, j-1) - dif(i+j-2, j-1); 
            end
        end
    end
    k = (x - func(1, 1)) / h1;
    
    for i=1:1:grado + 1
        num = 1;
        j = 0;
        while j <= i - 2
            num = num * (k-j);
            j = j+1;
        end
        if i == 1
            y = y + (num / factorial(i-1)) * func(2,1);
        else
            y = y + (num / factorial(i-1)) * dif(i-1, i-1);
        end
    end
endfunction
