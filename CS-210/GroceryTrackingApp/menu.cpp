#include <Python.h>
#include <iomanip>
#include <iostream>
#include <fstream>
#include <Windows.h>
#include <cmath>
#include <string>
#include <regex>
#include <conio.h>
#include "menu.h"

using namespace std;

//Default constructor - empty.
Menu::Menu() {}

// Call a passed in Python procedure (pName). Intended for functions that do not expect return values
void Menu::CallVoidFileProcedure(string pName, string fileName, string outputFileName)
{
	// procname is the name of the Python procedure being called
	char* procname = new char[pName.length() + 1];
	std::strcpy(procname, pName.c_str());

	char* fName = new char[fileName.length() + 1];
	std::strcpy(fName, fileName.c_str());

	char* oName = new char[outputFileName.length() + 1];
	std::strcpy(oName, outputFileName.c_str());

	PyObject* pModule, * pDict, * pFunc, * pValue = nullptr, * presult = nullptr;
	
	// Initialize the Python Interpreter
	Py_Initialize();
	
	// Load the module object
	pModule = PyImport_ImportModule("PythonCode");
	
	// pDict is a borrowed reference
	pDict = PyModule_GetDict(pModule);
	
	// pFunc is also a borrowed reference
	pFunc = PyDict_GetItemString(pDict, procname);

	if (PyCallable_Check(pFunc))
	{
		if (outputFileName.compare("none") == 0) {        // Tests if default string is passed, in which case a single value function is called
			pValue = Py_BuildValue("(z)", fName);
			PyErr_Print();
			presult = PyObject_CallObject(pFunc, pValue);
			PyErr_Print();
		}
		else {
			pValue = Py_BuildValue("(zz)", fName, oName); // Creates a PyObject tuple to pass to Python function
			PyErr_Print();
			presult = PyObject_CallObject(pFunc, pValue);
			PyErr_Print();
		}
	}
	else
	{
		PyErr_Print();
	}
	
	// Clean up
	Py_DECREF(pValue);
	Py_DECREF(pModule);

	// Finish the Python Interpreter
	Py_Finalize();

	// clean
	delete[] procname;
	delete[] fName;
	delete[] oName;
}

// Call a passed search function (proc) to search a file (fileName) for the the search item (query)
int Menu::CallSearchFunc(string proc, string fileName, string query)
{
	char* procname = new char[proc.length() + 1];
	std::strcpy(procname, proc.c_str());

	char* fName = new char[fileName.length() + 1];
	std::strcpy(fName, fileName.c_str());

	char* searchItem = new char[query.length() + 1];
	std::strcpy(searchItem, query.c_str());

	PyObject* pModule, * pDict, * pFunc, * pValue = nullptr, * pItem = nullptr, * presult = nullptr;
	
	// Initialize the Python Interpreter
	Py_Initialize();
	
	// Load the module object
	pModule = PyImport_ImportModule("PythonCode");
	
	// pDict is a borrowed reference
	pDict = PyModule_GetDict(pModule);
	
	// pFunc is also a borrowed reference
	pFunc = PyDict_GetItemString(pDict, procname);
	
	if (PyCallable_Check(pFunc))
	{
		pValue = Py_BuildValue("(zz)", fName, searchItem);
		PyErr_Print();
		presult = PyObject_CallObject(pFunc, pValue);
		PyErr_Print();
	}
	else
	{
		PyErr_Print();
	}
	//printf("Result is %d\n", _PyLong_AsInt(presult));
	Py_DECREF(pValue);
	// Clean up
	Py_DECREF(pModule);
	// Finish the Python Interpreter
	Py_Finalize();

	// clean
	delete[] procname;
	delete[] fName;
	delete[] searchItem;


	return _PyLong_AsInt(presult);
}

// Ensure valid input from user.
int Menu::ValidateMenuInput(string errorText) {

	int userValue;             // Number form of user's input
	string userInput;          // String is used to reduce errors from incompatible types
	bool invalid = true;       // While loop conditional variable

	while (invalid) {
		try {
			getline(cin, userInput);

			// Use regex to test that userInput is a single digit
			if (!regex_match(userInput, regex("\\d"))) {
				throw runtime_error(errorText);
			}

			userValue = stoi(userInput);
			switch (userValue) {
			case 1:
			case 2:
			case 3:
			case 4:
				invalid = false;
				break;
			default:
				cout << errorText << endl;
				break;

			}
		}
		catch (runtime_error& err) {
			cout << err.what() << endl;
		}
	}
	return userValue;
}

// Write a histogram using Python, and then read the frequency.dat file generated from "writeHistogram" in Python
void Menu::OutputHistogram(string inputFileName, string outputFileName) {
	ifstream fileToRead;
	string grocery;
	string histogramString;
	int itemQuantity = -1;

	CallVoidFileProcedure("WriteHistogram", inputFileName, outputFileName);
	fileToRead.open(outputFileName);
	if (fileToRead.is_open()) {
		while (fileToRead >> grocery >> itemQuantity) {
			histogramString = "";
			for (int i = 0; i < itemQuantity; i++) {
				histogramString.append("[] ");
			}
			cout << left << setw(12) << grocery;
			cout << right << setw(histogramString.length() + 5) << histogramString << endl;
		}
		cout << endl << "End of histogram." << endl;
		fileToRead.close();
	}
	else {
		cout << "File open failed. Please try again. If error persists, please contact an administrator." << endl;
	}
}

/*
*  Ensure valid input from user. 
*  Code has been commented out because it is currently redundent.
*  However, validation functions are always useful, so it has not been
*  removed to preserve future reusability.
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
int Menu::ValidateNumberInput(string errorText) {

	int userValue;        // Number form of user's input
	string userInput;     // String is used to reduce errors from incompatible types
	bool invalid = true;  // While loop conditional variable


	while (invalid) {
		try {
			getline(cin, userInput);

			// Use regex to test that userInput is an integer
			if (regex_match(userInput, regex("-+\\d+"))) {
				throw runtime_error("\nInput must be a real number over 0. Please try again.\n");
			}
			else if (!regex_match(userInput, regex("\\d+"))) {
				throw runtime_error(errorText + "\n");
			}
			else {

				userValue = stoi(userInput);
				if (userValue <= 0) {
					throw runtime_error("\nInput must be more than 0. Please try again.\n");
				}
				else {
					invalid = false;
				}
			}
		}
		catch (runtime_error& err) {
			cout << err.what() << endl;
		}
	}
	return userValue;
}
*/

