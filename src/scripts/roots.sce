function [xn1, err, table] = root(number, fx)
    i = 1
    table = []
    xn1 = floor(sqrt(number))
    while(1)
        table(i,1) = i-1
        table(i,2) = xn1
        xn1 = (1/2*(xn1+number/xn1))
        table(i,3) = xn1
        err = abs((sqrt(number)-xn1)/sqrt(number))
        table (i,4) = err
        i = i + 1;
        if(err<=5*10^-fx)
            break
        end 
    end
endfunction
