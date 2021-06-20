function [table, root, err] = bisection(func, inter1, inter2, fx, maxit)
    format(10)
    table = []
    i = 1
    a = inter1
    b = inter2
    while(1)
        table(i,1) = i-1
        table(i,2) = a
        table(i,4) = b
        xm = (a + b)/2
        table(i,3) = xm
        fa = func(a)
        table(i,5) = fa
        fb = func(b)
        table(i,6) = fb
        fxm = func(xm)
        table(i,7) = fxm
        err = abs(fxm)
        table(i,8) = err
        if(or([err<=5*10^-fx, i>maxit]))
            break
        end 
        if(fxm<0)
            if(fa<0)
                a=xm
            else
                b=xm
            end
        else
            if(fa>0)
                a=xm
            else
                b=xm
            end
        end
        i = i + 1
    end
    for j=1:1:i-1
        if(table(j,8)>table(j+1,8))
            root = table(j+1,3)
            err = table(j+1,8)
        end
    end 
endfunction
