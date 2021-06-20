function [table, root, err] = bisection(func, inter1, inter2, fx)
    table = []
    i = 0
    a = inter1
    b = inter2
    while(1){
        xm = (a + b)/2
        fa = func(a)
        fb = func(b)
        fxm = func(xm)
        if(err<=5*10^-fx)
            break
        end 
        i = i + 1;
    } 
endfunction
