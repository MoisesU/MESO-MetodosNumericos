function [res, tab] = particionUniforme(f, a, b, rect)
    h = (b - a)/rect
    tab = []
    x = a
    res = 0
    for i=1:1:rect
        tab(1, i) = x;
        tab(2, i) = f(x);
        x = x + h;
        res = res + tab(2, i);
    end
    res = res * h;
endfunction
