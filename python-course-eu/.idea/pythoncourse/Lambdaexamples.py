
sum = lambda x, y: x + y

#print(sum(3,4))

def fahrenheit(T):
    return ((float(9)/5)*T + 32)

def celsius(T):
    return  (float(5)/9)*(T-32)

temperatures = (36.5, 37, 37.5 , 38, 39)

F = map(fahrenheit,temperatures)
C = map(celsius,temperatures)

temperatures_in_farenheit = list(F)
#print(temperatures_in_farenheit)

temperatures_in_Cesius = list(C)
#print(temperatures_in_Cesius)

#Example with Lambdas

C = [39.2, 36.5, 37.3, 37.8]
F = list(map(lambda x: (float(9)/5)*x+32,C))

#print(F)

C = list(map(lambda x: (float(5)/9)*(x-32),F,))
#print(C)


a = [1, 2, 3,4 ]
b = [17,12,11,10]
c = [-1,-4,5,9]

D = list(map(lambda x,y: x+y, a,b))
#print(D)

E = list(map(lambda x,y,z : x+y+z, a,b,c ))
#print(E)

F = list(map(lambda x, y, z : 2.5*x + 2*y - z, a, b, c))
#print(F)

from math import sin, cos, tan, pi

def map_function(x,functions):
    """ map an iterable of functions on the object x """
    res = []
    for func in functions:
        res.append(func(x))
    return res

family_of_functions= (sin, cos, tan)
G = map_function(pi, family_of_functions)
#print(G)

def mapc_functions(x,functions):
    return [func(x) for func in functions]

H = map_function(pi,family_of_functions)
#print(H)

fibonacci = [0,1,1,2,3,5,8,13,21,34,55]

odd_numbers = list(filter(lambda x: x%2, fibonacci))

#print(odd_numbers)

even_numbers = list(filter(lambda x: x%2==0, fibonacci))
#print(even_numbers)

import functools

I = functools.reduce(lambda x,y: x+y,[47,11,42,13])
#print(I)

from functools import reduce

#Maximum of a list of values by using reduce
values = [47,11,42,102,13]
J = reduce(lambda x,y: x if x > y else y, values )
#print(J)

figures = range(1,4)
F = reduce(lambda x,y: x*y,figures)
#print(F)

orders = [ ["34587", "Learning Python, Mark Lutz", 4, 40.95],
           ["98762", "Programming Python, Mark Lutz", 5, 56.80],
           ["77226", "Head First Python, Paul Barry", 3,32.95],
           ["88112", "Einführung in Python3, Bernd Klein", 	3, 24.99]]

ordero = [ ["34587", "Learning Python, Mark Lutz", 4, 40],
           ["98762", "Programming Python, Mark Lutz", 5, 50],
           ["77226", "Head First Python, Paul Barry", 3,30],
           ["88112", "Einführung in Python3, Bernd Klein", 	3, 25]]

min_order = 100

#invoice_totals = list(map(lambda x: x if x[1] >= min_order else (x[0], x[1]+10), map(lambda x: (x[0],x[2] * x[3]), orders)))

tupple_calculations = list(map(lambda x: (x[0],x[2]*x[3]), ordero))
tupple_results = list(map(lambda x: x if x[1] >= min_order else (x[0], x[1]+10),tupple_calculations))
#print(tupple_calculations)
#print(tupple_results)

#print(invoice_totals)

from functools import reduce

ordering = [ [1, ("5464", 4, 9.99), ("8274",18,12.99), ("9744", 9, 44.95)],
          [2, ("5464", 9, 9.99), ("9744", 9, 44.95)],
          [3, ("5464", 9, 9.99), ("88112", 11, 24.99)],
          [4, ("8732", 7, 11.99), ("7733",11,18.99), ("88112", 5, 39.95)] ]


min_order = 400

first_step = list(map(lambda x: [x[0]], ordering))


invoice_totals = list(map(lambda x: [x[0]] +list(map(lambda y: y[1]*y[2], x[1:])),ordering))

print(invoice_totals)

invoice_totals = list(map(lambda x: [x[0]] + [reduce(lambda a,b: a+b, x[1:])],invoice_totals))
print(invoice_totals)

invoice_totals = list(map(lambda x: x if x[1] >= min_order else (x[0], x[1]+10),invoice_totals))
print(invoice_totals)

