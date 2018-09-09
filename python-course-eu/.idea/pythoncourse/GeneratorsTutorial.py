
#source: https://www.dataquest.io/blog/python-generators-tutorial/

ez_dict = {1 : "First", 2 : "Second"}

#for k, v in ez_dict.items():
 #   print(k,v)

#generator function

def function_a():
    return "a"

def generator_a():
    yield "a"

#print(function_a())

#print(next(generator_a()))

def multi_generate():
    yield "a"
    yield "b"
    yield "c"

#mg = multi_generate()
#print(next(mg))
#print(next(mg))
#print(next(mg))
#print(next(mg))

#print(next(multi_generate()))
#print(next(multi_generate()))


#lc_example = [n**2 for n in [1,2,3,4,5]]
#genex_example = (n**2 for n in [1,2,3,4,5])
#genex_example2 = (n**2 for n in [1,2,3,4,5] if n>=3)

#def beerDataGenerator():
 #   file = "recipeData.csv"
  #  for row in open(file, encoding="ISO-8859-1"):
     #   yield row

#beer = beerDataGenerator()
#print(next(beer))
#print(next(beer))
#print(next(beer))

beer_data = "recipeData.csv"
lines_generator = (line for line in open(beer_data, encoding="ISO-8859-1"))
lists_generator  = (l.split(",") for l in lines_generator)

lines_generator2 = (line for line in open(beer_data, encoding="ISO-8859-1"))
lists_generator2  = (l.split(",") for l in lines_generator2)

# Take the column names out of the generator and store them, leaving only data
columns_list = next(lists_generator)

# Take these columns and use them to create an informative distionary
beer_dicts_generator =(dict(zip(columns_list,data_list)) for data_list in lists_generator)
beer_dicts_generator2 =(dict(zip(columns_list,data_list2)) for data_list2 in lists_generator2)

beer_counts = {}

abv = 0

for bd in beer_dicts_generator:
    if bd["Style"] not in beer_counts:
        beer_counts[bd["Style"]] = 1
    else:
        beer_counts[bd["Style"]] +=1

most_popular = 0
most_popular_type = None
for beer, count in beer_counts.items():
    if count > most_popular:
        most_popular = count
        most_popular_type = beer

#print(most_popular_type)

abv = (float(bd["ABV"]) for bd in beer_dicts_generator2 if bd["Style"] == "American IPA")

# Get the average ABV for the most popular beer
print(sum(abv)/most_popular)