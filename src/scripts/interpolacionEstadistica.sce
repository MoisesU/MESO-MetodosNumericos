//Rodríguez Montiel Moisés Ulises
//2NM51
function [y, func] = interpolEstadistica(funcTab, grado, x)
    y = 0
    func = []
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
    a = []
    c = []
    a(1) = m
    b(1) = 0
    for i=1:1:m
        b(1) = b(1) + funcTab(2, i)
        for j=1:1:grado*2
            if size(a) < j+1
                a(j+1) = funcTab(1, i)^j
            else
                a(j+1) = a(j+1) + funcTab(1, i)^j
            end
        end
        for j=1:1:grado
            if size(b) < j + 1
                b(j+1) = funcTab(2, i) * funcTab(1, i)^j
            else
                b(j+1) = b(j+1) + funcTab(2, i) * funcTab(1, i)^j
            end
        end
    end
    for i=1:1:grado+1
        for j=1:1:grado+1
            c(i, j) = a(i+j-1)
        end
        c(i, grado+2) = b(i)
    end
    for i=1:1:grado+1
        pivote=c(i,i)
        for j=1:1:grado+2
           c(i,j)=c(i,j)/pivote
        end
        for k=1:1:grado+1
           if(k~=i) 
              cero=c(k,i)
              for j=1:1:grado+2
                 c(k,j)=c(k,j)-cero*c(i,j) 
              end
           end
        end
    end
    func(1)=c(1, grado + 2)
    y = c(1, grado + 2)
    for i=1:1:grado
        func(i+1) = c(i+1, grado + 2);
        y = y + c(i+1, grado + 2)*x^i
    end
endfunction
