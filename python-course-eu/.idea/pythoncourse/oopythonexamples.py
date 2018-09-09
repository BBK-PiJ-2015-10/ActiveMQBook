
print("Ale Mimado")

#getattr(x,'enery',100)

def f(x):
    f.counter = getattr(f,'counter',0)+1
    return "Monty Python"

for i in range(10):
    f(i)


print(f.counter)

class Robot:

    def __init__(self, name=None):
        self.name = name;

    def say_hi(self):
        if self.name:
            print("Hi, I am " +self.name)
        else:
            print('Hi, I am a robot with no name')

x = Robot()
x.say_hi()
y = Robot("Marvin")
y.say_hi()

class A():
    def __init__(self):
        self.__priv ="I am private"
        self._prot = "I am protected"
        self.pub = "I am public"

x = A()
print(x.pub)
print(x._prot)
print(x.__priv)

