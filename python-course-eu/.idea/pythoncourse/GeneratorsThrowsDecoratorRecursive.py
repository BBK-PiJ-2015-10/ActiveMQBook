
def infinite_looper(objects):
    count = 0
    while True:
        if count >= len(objects):
            count = 0
        msg = yield objects[count]
        if msg != None:
            count = 0 if msg < 0 else msg
        else:
            count +=1

#next always sends and receives an object. It always sends a None object

x = infinite_looper("Astringwithsomewords")

#print(next(x))
#print(next(x))
#print(x.send(9))

#print(x.send(10))

#print(next(x))

def infinite_looper2(objects):
    count = 0
    while True:
        if count >= len(objects):
            count = 0
        try:
            message = yield objects[count]
        except Exception:
            print("index: " +str(count))
        if message != None:
            count = 0 if message < 0 else message
        else:
            count +=1

looper = infinite_looper2("Python")

#print(next(looper))
#print(next(looper))
#looper.throw(Exception)
#print(next(looper))

class StateOfGenerator(Exception):
    def __init__(self, message=None):
        self.message= message

def infinite_looper3(objects):
    count = 0
    while True:
        if count >= len(objects):
            count = 0
        try:
            message = yield objects[count]
        except StateOfGenerator:
            print("index: " +str(count))
        if message != None:
            count = 0 if message < 0 else message
        else:
            count += 1

looper3 = infinite_looper3("Scala")
#print(next(looper3))
#print(next(looper3))
looper3.throw(StateOfGenerator)
#print(next(looper3))

#Decorating Generators

#yield from

# recursive generators

# a generator of generators