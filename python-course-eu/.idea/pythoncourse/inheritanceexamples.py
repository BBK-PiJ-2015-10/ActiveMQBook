# Method overloading, doesn't work as in Java, instead use * and default parameters

def f(*x):
    if len(x) == 1:
        return x[0] + 42
    else:
        return x[0] + x[1] +42

print(f(3))
print(f(3,5))


# In case you had two parameters, give a default to the second
def g(n, m=None):
    if m:
        return n + m +42
    else:
        return n +42;

print(g(3))
print(g(3,5))


class Person:

    def __init__(self, first, last, age):
        self.first = first
        self.last = last
        self.__age = age

    def __str__(self):
        return self.first + " " +self.last + " " +self.__age

class Employee(Person):

    def __init__(self, first, last, age, id):
        super().__init__(first,last,age)
        self.id = id

    def __str__(self):
        return super().__str__() + " " +self.id


x =  Person("Ale","Palacios","42")
print(x)

y = Employee("Ale","Pal","42","10")
print(y)


class Swimmer:

    def __init__(self, name):
        self.name = name

    def swim(self):
        return "Butterfly"

    def __str__(self):
        return self.name

class Scientist:

    def __init__(self, language):
        self.language = language

    def program(self):
        return "Doing it in " +language

    def __str__(self):
        return self.language

class Mimado(Swimmer,Scientist):

    def __init__(self,name,language):
        Swimmer.__init__(self,name)
        Scientist.__init__(self,language)


    def __str__(self):
        return Swimmer.__str__(self) + " " +  Scientist.__str__(self)



tonto = Mimado("Delfino","Python")

print(tonto)