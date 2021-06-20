//Rodríguez Montiel Moisés Ulises
//2NM51
function y = interpolLagrange(funcTab, grado, x)
    y = 0
    [n, m] = size(funcTab)
    if(n <> 2)
        error("No es una función tabular")
    end
    if ~(isreal(funcTab))
      error('Elementos no numericos o complejos en el arreglo.');
    end
    grado = round(grado);
    if (or([~isreal(grado), grado <= 0]))
        error('El grado tiene que ser un valor entero positivo (mayor a 0).');
    end
    if grado > m
        error('El grado del polinomio es mayor al permitido por el metodo.');
    end
    for i=1:1:grado
        numerador = 1;
        denominador = 1;
        for j=1:1:grado
            if j ~= i
                numerador = numerador * (x - funcTab(1, j));
                denominador = denominador * (funcTab(1, i) - funcTab(1, j));
            end
        end
        y = y + (numerador/denominador) * funcTab(2, i);
    end
endfunction
