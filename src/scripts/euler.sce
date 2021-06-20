function datos = euler(fxy, x1, y1, h, n)
            datos = []
            for i=1:n+1
                datos(i, 1) = i-1 
                datos(i, 2) = x1
                datos(i, 3) = y1
                f2xy = fxy(x1, y1)
                datos(i, 4) = f2xy
                datos(i, 5) = h*f2xy
                datos(i, 6) = datos(i, 5) + y1;
                y1 = datos(i, 6)
                x1 = x1+h
            end
endfunction

function datos = eulerMejorado(fxy, x1, y1, h, n)
            datos = []
            for i=1:n+1
                d = 0
                yc = 0
                yaux = 0
                datos(i, 1) = i-1 
                datos(i, 2) = x1
                datos(i, 3) = y1
                f2xy = fxy(x1, y1)
                datos(i, 4) = h * f2xy
                yp = y1 + h* f2xy
                datos(i, 5) = yp
                x1 = x1 + h
                datos(i, 6) = f2xy + fxy(x1, yp) 
                datos(i, 7) = h*(datos(i, 6) + datos(i, 4))/2;
                datos(i, 8) = datos(i, 7) + y1;
                y1 = datos(i, 8);
            end
endfunction
