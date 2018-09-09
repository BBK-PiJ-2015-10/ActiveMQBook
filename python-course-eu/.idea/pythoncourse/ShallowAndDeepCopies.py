

x = 3
y = x

#print(id(x),id(y))
y = 4
#print(id(x),id(y))
#print(x,y)

colours1 = ["red","blue"]
colours2 =  colours1
#print(colours1)
#print(colours2)
#print(id(colours1),id(colours2))
colours2= ["white","yellow"]
#print(colours1)
#print(colours2)

colours3 = ["red","blue"]
colours4 = colours3
colours4[0] = "black"
#print(colours3)

# Copy with slice operator
colours5 = ["red","blue"]
colours6 = colours5[:]
colours6[0] = "black"
#print(colours5)
#print(colours6)

listo = ["a","b",["ab","ba"]]
lista = listo[:]

listo[0] = "m"
listo[2][0] = "ale"
#print(listo)
#print(lista)

from copy import deepcopy

list3 = ["a","b",["ab","ba"]]
list4 = deepcopy(list3)

list3[0] ="m"
list3[2][0] = "ale"

print(list3)
print(list4)





