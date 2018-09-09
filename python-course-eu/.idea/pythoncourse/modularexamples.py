
# import math
# import math, random
# from math import sin, pi
# from math import *
# import math as ext

#from simplepackage import a, b

#import sound
# from sound import *
from sound.effects import *
# import sound.effects  -> This is import the subpackage, otherwise no subpackages are imported

def fib(n):
    if n==0:
        return 0
    elif n ==1:
       return 1
    else:
        return fib(n-1) + fib(n-2)


#a.bar()

#b.foo()

#sound.effects

print(dir())