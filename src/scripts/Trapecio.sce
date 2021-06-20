function [x, tab] = trapecio(fx, a, b, n)
     h=(b-a)/n;
     xi = a;
     tab = [];
     i = 1;
     while xi <= b
        tab(2, i) = fx(xi);
        tab(1, i) = xi
        i = i+1;
        xi = xi + h;
     end
     area = tab(2, 1) + tab(2, n+1);
     for i=2:1:n
        area = area + 2 * tab(2, i);
     end
        area = area * h / 2;
        x = area;
endfunction
