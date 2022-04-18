import re
import string

#Read in a list of items from a provided file name
#List all items in that file and their respective frequencies 
def ListAllItems(fileName):
    groceries = {}
    with open(fileName, "r") as f:
        for line in f:
            # Remove newline character for consistent & accurate dictionary searches
            grocery = line.rstrip('\n')  
            if grocery in groceries:
                groceries[grocery] += 1
            else:
                groceries[grocery] = 1
    
    # Use formatting prefix to print all grocery items and their respective counts
    print(*[f"{': '.join(map(str,item))}" for item in groceries.items()], sep='\n')

#Read in a list of items from a provided file name
#Display an item (query) and it's respective frequency
def SearchForItem(fileName, query):
    count = 0

    with open(fileName, "r") as f:
        for line in f:
            if (line.rstrip('\n').lower() == query.lower()):
                count += 1

    return count;

#Read in a list of items from a provided file name
#Generate a histogram from the data
def WriteHistogram(inputFileName, outputFileName):
    groceries = {}
    with open(inputFileName, "r") as inputFile:
        for line in inputFile:
            # Remove newline character for consistent & accurate dictionary searches
            grocery = line.rstrip('\n')  
            if grocery in groceries:
                groceries[grocery] += 1
            else:
                groceries[grocery] = 1

    with open(outputFileName, "w") as outputFile:
        for grocery, count in groceries.items():
            outputFile.write(f"{grocery} {count}\n")