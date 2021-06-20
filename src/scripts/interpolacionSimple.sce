function [y, func] = interpolSimple(table, x)
    b = []
    a = []
    func = []
    [n, m] = size(table)
    if(n <> 2)
        error("No es una función tabular")
    end
    for i=1:1:m
        b(i)=table(2, i)
    end
    for j=1:1:m
        for i=1:1:m
            a(j, i) = table(1, j)^(4-i)
        end
    end
    z = a^-1*b
    y = 0
    for i=1:1:m
        func(i) = z(i);
        term = x^(4-i)*z(i)
        y = y + x^(4-i)*z(i)
        
    end
endfunction
