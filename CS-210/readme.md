# SNHU-CS-210
Southern New Hampshire University CS-210 Portfolio Repository

## Module 8 Journal Submission
For my journal assignment in Module 8, I would like to showcase the third project of CS-210, a grocery tracking application. 

### Project Summary
The grocery tracking application provided multiple functions for the theoretical client, Corner Grocer, to analyze what items are purchased the most. 
1. The application allows the user to view the frequency of each item purchased by analyzing a .txt file containing a list of every item purchased in one day. The program then output a list of each item followed by the number of times that item was purchased according to the .txt file. The .txt file was hard coded into the program, but the program could be easily modified to allow the user to type in the name of the file they wish to analyze. 
2. The user can search for a specific item from the grocery list to see its frequency.
3. The program creates a histogram based off the information provided from the first function. That histogram is output to a file called frequency.dat. The program then reads that .dat file and outputs the histogram to the screen. 

### Particular Successes
The grocery tracker application utilizes Python and C++. Combining the languages allows for C++ to handle the graphic user interface (GUI) aspect of the program while Python manages the data analysis aspects of the app. The way that information is passed between Python and C++ was created to be modular and reusable. By altering the C++ wrapper functions, the program can pass additional arguments to the program. For this specific instance, the ability to change the file that the program analyzes to obtain information is easily able to be altered from hard code to soft code. 

### Challenges 
Altering the C++ wrapper functions to operate in the method they do in this specific program was not necessary for the project's completion. It was likely not included because understanding the PyObject module can be slightly difficult. The documentation of PyObject is not extremely clear, so it proved to be difficult to design the application how I intended. After a significant amount of time browsing StackOverflow, reviewing documentation, and observing the template code provided by the project files, I was able to successfully create the program how I had imagined. There was one more difficulty that came along with that process, though. Since the compiler is working in C++, it does not do a great job of returning errors that happen during Python program execution. This made tracking down issues and bugs significantly more difficult. There was no easy answer for this problem. I simply used debug mode to track down the error. 

### Potential Enhancements
There are multiple ways that the program can be made more enhanced:
- Allow users to enter the file name they want to be analyzed. This would increase the program's versatility.
- Allow users to designate the histogram symbol they would prefer to be used. This would increase user satisfaction.
- Clean and sanitize data entry. This would increase the program's security. 

### Program design
This application was created to be maintainable and readable by using clear, comprehendable variable and function names, and providing extensive comments for clarification on program decisions. The program was created to be adaptable by including the functionality mentioned in the "Particular Successes" section. 

### Project Takeaways
Learning how to utilize multiple languages in a single application will likely be the most transferable skill I obtained from this project. I am confident this knowledge will serve me academically and professionally in the future. 
