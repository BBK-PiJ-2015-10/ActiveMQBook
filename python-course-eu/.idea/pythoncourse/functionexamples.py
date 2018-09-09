
def Hello(name="everybody"):
    """Greets a person"""
    print("Hello " +name + "!")

#Hello("Peter")
#Hello()

#print("The docstring of the function Hello: " +Hello.__doc__)

def sumsub(a, b, c=0, d=0):
    return a - b + c - d

#print(sumsub(12,4))
#print(sumsub(42,15,d=10))

def no_return(x,y):
    c = x + y

res = no_return(4,5)
#print(res)

def fib_intervall(x):
    """returns the largest fibonacci number smaller than x and the lowest fibonacci number higher than x"""
    if x < 0:
        return -1
    (old,new,lub) = (0,1,0)
    while True:
        if new < x:
            lub = new
            (old,new) = (new,old+new)
        else:
            return (lub,new)

#while True:
 #   x = int(input("Your number: "))
  #  if x <=0:
   #     break
   # (lub,sup) = fib_intervall(x)
   # print("Largest Fibonacci Number smaller than x :" +str(lub))
   # print("Smallest Fibonacci Number larger than x :" +str(sup))

def arithmetic_mean(first, *values):
    """This function calculates the arithmetic mean of a non-tempy arbitrary number of numerical values"""

    return (first + sum(values)) / (1 + len(values))

#print(arithmetic_mean(45,32,89,78))
#print(arithmetic_mean(45))


my_list = [('a',232),('b',343),('c',543),('d',23)]

#print(list(zip(*my_list)))

def f(**kwargs):
    print(kwargs)

f()
f(de="German",en="English",fr="French")
