
cities = ['London','Paris','Berlin']

#for city in cities:
 #   print("location :" +city)


expertises = ['java','scala','python']
expertises_iterator = iter(expertises)
#print(next(expertises_iterator))
#print(next(expertises_iterator))
#print(next(expertises_iterator))
#print(next(expertises_iterator))

other_cities = ["Strasbourg","Freiburg","Stuttgart","Wien","Hannover","Berlin","Zurich"]
#city_iterator = iter(other_cities)
#while city_iterator:
 #   try:
  #      city = next(city_iterator)
   #     print(city)
    #except StopIteration:
     #   break

capitals = {"France":"Paris", "Netherlands":"Amsterdam", "Germany":"Berlin"}
#for country in capitals:
 #   print("The capital of : " +country +" is " +capitals[country])


def city_generator():
    yield("London")
    yield("Paris")
    yield("Berlin")

citta = city_generator()
#print(next(citta))
#print(next(citta))
#print(next(citta))

def fibonacci(n):
    """A generator for creating the Fibonacci numbers"""
    current, next, counter = 0, 1, 0
    while True:
        if (counter > n):
            return
        yield current
        current, next = next, current+next
        counter += 1

f = fibonacci(12)
#for x in f:
 #   print(x, " ", end="") #
#print()

def simple_coroutine():
    print("coroutine has started!")
    x = yield
    print("coroutine received: " +x)

#cr = simple_coroutine()
#next(cr)
#cr.send("ale")

def infinite_looper(myString):
    count = 0
    while True:
        if count >= len(myString):
            count = 0
        message = yield myString[count]
        if message != None:
            count = 0 if message < 0 else message
        else:
            count += 1

x = infinite_looper("Alejandro Palacios")

print(next(x))
print(next(x))
print(x.send(5))
print(next(x))



