#ifndef CPPPYTHON_MENU_H
#define CPPPYTHON_MENU_H
#include <string>
#include <Python.h>
#include <iostream>
#include <Windows.h>
#include <cmath>
using namespace std;

class Menu {
public:

	//Default Constructor
	Menu();
	void CallVoidFileProcedure(string pName, string fileName, string outputFileName = "none");
	int CallSearchFunc(string proc, string fileName, string query);
	int ValidateMenuInput(string errorText);
	void OutputHistogram(string inputFileName, string outputFileName);
	// int CallIntFunc(string proc, int param);
	// int ValidateNumberInput(string errorText); // Commented out because it is currently redundent, but the function itself is reusable
};

#endif
