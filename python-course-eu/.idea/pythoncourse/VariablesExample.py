
"""
def f():
    print(s)

s = "Parigi mi manca"

#f()

def g():
    x ="I love London"
    print(x)

x = "I love Roma"

#g()
#print(x)

def n(q):
    q = "ale culon"
    print(q)


l = "Mimado"
#n(l)
#print(l)

def m():
    global a
    print(a)
    a = "Only in spring, but London is always amazing"
    print(a)

a ="I am moving to Parigi"
#m()
#print(a)

def foo(x,y):
    global b
    b =42
    x,y = y,x
    c = 33
    d = 100
    print(b,c,x,y)

b,c,x,y = 1, 15, 3, 4
#foo(17,4)
#print(b,c,x,y)

def fu():
    x = 42
    def gu():
        global x
        x = 43
    print("Before calling gu : " +str(x))
    print("Calling gu now")
    gu()
    print("After calling gu: " +str(x))

fu()
print("x in main is : " +str(x))


def f():
    nonlocal x
    print(x)

x = 3
f()


def f():
    x = 42
    def g():
         x
        x = 43
    print("Before calling g: " +str(x))
    print("Calling g now:")
    g()
    print("After calling g: " +str(x))

x = 3
f()
print("x in main is: " +str(x))

"""

def f():
    x = 42
    def g():
        global x
        x = 43
    print("Before calling g: " +str(x))
    g()
    print("After calling g: " +str(x))

x = 3
f()
print("x in main is " +str(x))


