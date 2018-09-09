class A:
    a = "I am a class attribute"

x = A()
y = A()

#print(x.a)
#print(y.a)
#print(A.a)

x.a = "This creates a new instance attribute for x"
#print(y.a)
#print(A.a)
#print(x.a)

#print(x.__dict__)
#print(y.__dict__)
#print(A.__dict__)


"""
class Robot:

     Three_Laws = (
         "Love your self",
         "Love God"",
         "Love Others"
     )

     def __init__(self, name, build_year):
         self.name = name
         self.build_year = build_year

     #other methods as usual

for number, text in enumerate(Robot.Three_Laws):
    print(str(number+1) +":\n" + text)


class C:

   counter = 0

    def __init__(self):
        type(self).counter +=1

    def __del__(self):
        type(self).counter -=1


if __name__ == "__main__":
    x = C()
    print("Number of instances: : " +str(C.counter))
    y = C()
    print("Number of instances: : " +str(C.counter))
    del x
    print("Number of instances: : " +str(C.counter))
    del y
    print("Number of instances: : " +str(C.counter))

"""

class Robot:

    __counter = 0

    def __init__(self):
        type(self).__counter +=1

    @staticmethod
    def RobotInstances():
        return Robot.__counter

    @classmethod
    def RobotTypes(cls):
        return cls, Robot.__counter

#print(Robot.RobotInstances())
#x = Robot()
#print(x.RobotInstances())


class Pets:
    name ="pet animals"

    @staticmethod
    def about():
        print("This will always yield {}!".format(Pets.name))

    @classmethod
    def materia(cls):
        print("This will change to {}".format(cls.name))

class Dogs(Pets):
    name = "dogs"

class Cats(Pets):
    name = "cats"

p = Pets()
d = Dogs()
c = Cats()

p.about()
p.materia()

d.about()
d.materia()

c.about()
c.materia()