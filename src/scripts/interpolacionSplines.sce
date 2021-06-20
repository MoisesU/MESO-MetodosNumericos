//Rodríguez Montiel Moisés Ulises
//2NM51
function y = interpolSplin(funcTab, x)
    [n, m] = size(funcTab)
    if(n <> 2)
        error("No es una función tabular")
    end
    if ~(isreal(funcTab))
      error('Elementos no numericos o complejos en el arreglo.');
    end
    if(~isreal(x))
        error('El numero de x ingresado no es valido');
    end
    valx = []
    valy = []
    for i=1:1:m
        valx(i) = funcTab(1, i)
        valy(i) = funcTab(2, i)
    end
    d = splin(valx, valy)
    y = interp(x, valx, valy, d)
endfunction
