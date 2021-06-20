function [x, tab] = simp38(fx, a, b, n)
            h = (b-a)/n;
            xi = a;
            tab = []
            i = 1;
            while xi <= b
                tab(2, i) = fx(xi);
                tab(1, i) = xi
                i = i+1;
                xi = xi + h;
            end
            area = tab(2, 1) + tab(2, n+1);
            for i=2:1:n
                if modulo(i-1, 3) == 0
                    area = area + 2 * tab(2, i);
                else
                    area = area + 3 * tab(2, i);
                end
            end
            area = 3 * area * h / 8;
            x = area;
        endfunction
