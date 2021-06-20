function [x, tab] = simp13(fx, a, b, n)
            h = (b-a)/n
            xi = a
            i = 1
            tab = []
            while xi <= b
                tab(2, i) = fx(xi);
                tab(1, i) = xi
                i = i+1;
                xi = xi + h;
             end
            area = tab(2, 1) + tab(2, n+1)
            for i=2:1:n
                if modulo(i, 2) == 0
                    area = area + 4 * tab(2, i)
                else
                    area = area + 2 * tab(2, i)
                end
            end
            area = area * h / 3
            x = area
        endfunction
