function [xn1, err] = root(xn, number, fix)
        xn1 = (1/2*(xn+2/xn))
        err = abs((sqrt(number)-xn1)/sqrt(number))
endfunction
