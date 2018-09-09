
Celsius = [39.2, 36.5, 37.3, 37.8]
Fahrenheit = [ ((float(9)/5)*x + 32) for x in Celsius]
#print(Fahrenheit)

pythagorean_triple = [(x,y,z) for x in range(1,30) for y in range(x,30) for z in range(y,30) if x**2 + y**2 == z**2]
#print(pythagorean_triple)

colours = ["red","green","yellow","blue"]
things = ["house","car","tree"]
coloured_things = [(x,y) for x in colours for y in things]
#print(coloured_things)

#Generator comprehensions return a generator
x = (x **2 for x in range(20))
#print(x)
x = list(x)
#print(x)

from math import sqrt

n = 100
sqrt_n = int(sqrt(n))

non_primes = [j for i in range(2, sqrt_n+1) for j in range(i*2,n,i)]
print(non_primes)