function [table, root, err] = newtonRaphson(f, inter1, inter2, fx, maxit)
    table =[]
    xn = (inter2+inter1)/2
    i = 1
    while(1)
        table(i,1)=i-1
        table(i,2)=xn
        y = f(xn)
        table(i,3)=y
        dy = numderivative(f,xn)
        table(i,4)=dy
        table(i,5)=y/dy
        table(i,6)=xn-table(i,5)
        err = abs((xn - table(i,6))/table(i,6))
        table(i,7)=err
        xn=table(i,6)
        root = xn
        if(or([err<=5*10^-fx, i>maxit]))
            break
        end 
        i = i + 1
    end
endfunction
