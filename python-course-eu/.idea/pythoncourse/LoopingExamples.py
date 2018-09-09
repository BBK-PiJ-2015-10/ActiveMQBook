
n = 100

s = 0
counter = 1

while counter < n:
    s = s+counter
    counter +=1

#print("Sum of 1 until %d: %d" % (n,s))

#import sys

#text = ""
#while 1:
 #   c = sys.stdin.read(1)
  #  text = text +c
   # if c == '\n':
    #    break

#print("Input: %s" % text)

#name = input("What is your name?\n")
#print(name)

#if the look is break the else part is not executed

import random

n = 5


#to_be_guessed = int(n * random.random()) + 1
#guess = 0
#while guess != to_be_guessed:
 #   guess = int(input("New number:"))
  #  if guess > 0:
   #     if guess > to_be_guessed:
    #        print("Number too large")
     #   elif guess < to_be_guessed:
     #       print("Number too little")
    #else:
     #   print("Sorry that you are giving up")
      #  break
#else:
 #   print("Congratulations you made it")

languages = ["Scala","Java","Python"]
#for x in languages:
 #   print(x)

edibles = ["ham","spam","eggs","nuts"]
#for food in edibles:
   # if food == "spam":
   #     print("No more spam, please")
  #      continue
 #   print("Great delicious food")
#else:
 #   print("I am so glad no span")
#print("Finally, I finished stuffing myself")

#print(list(range(4,50,5)))

#print(list(range(42,-12,-7)))

n = 100

sum = 0
for counter in range(0,n+1):
    sum = sum + counter

#print("Sum of 1 until %d: %d" % (n,sum))

from math import sqrt
#n = int(input("Maximal Number? "))
#for a in range(1,n+1):
 #   for b in range(a,n):
  #      c_square = a**2 + b**2
   #     c = int(sqrt(c_square))
    #    if ((c_square - c**2)==0):
     #       print(a,b,c)

fibonacci = [0,1,1,2,3,5,8,13,21]
#for i in range(len(fibonacci)):
 #   print(i,fibonacci[i])
#print()

colours = ["red"]
for i in colours:
    if i == "red":
        colours += ["black"]
    if i == "black":
        colours +=["white"]

print(colours)

#with the below the elements of the loop remain the same during the iteration
colores = ["red"]
for i in colores[:]:
    if i == "red":
        colores += ["black"]
    if i == "black":
        colores += ["white"]
print(colores)