#include <iomanip>
#include <iostream>
#include <string>
#include "investmentcalculator.h"
using namespace std;

void PrintTitle();

int main() {
	int yearsToProject;     // Number of years user would like to see a projection for
	char keepGoing = 'y';   // Sentinal value: allows the user to exit the program when they are ready. 
	string userInput;       // Error trapping string for quitting program. Use to grab first character of a user's input. 

	PrintTitle();
	InvestmentCalculator calc = InvestmentCalculator();

	while (keepGoing != 'n') { 
		cout << endl;
		calc.SetInitialDeposit(calc.ValidateInput('d', "Initial Investment Amount: $"));   // 'd' tells ValidateInput the value must be a double
		calc.SetMonthlyDeposit(calc.ValidateInput('d', "Monthly Deposit Amount:    $"));   // 'd' tells ValidateInput the value must be a double
		calc.SetInterestRate(calc.ValidateInput('d', "Annual Interest Rate:      %"));     // 'd' tells ValidateInput the value must be a double
		yearsToProject = calc.ValidateInput('i', "Number of years:           ");           // 'i' tells ValidateInput the value must be an integer
		cout << endl;

		// system("pause") will only work on windows systems. 
		// Linux/Unix/MacOS systems will require a different solution
		system("pause");                                 
		
		calc.PrintInterestSummary(yearsToProject);
		
		cout << endl << "Would you like to enter another investment projection? (Y/N): ";
		cin.ignore();                          // Keep input stream from reading previous lines of information   
		getline(cin, userInput);               // Keep program from breaking by accepting a string, and then only taking the first character
		keepGoing = tolower(userInput.at(0));  // Set first character to lowercase because that is the case of the condition statement 
	}

	cout << endl;
	cout << "Thank you for trying out Airgead Banking's Investment Calculator!" << endl;
	cout << "We look forward to helping you budget again soon!" << endl << endl;
	return 0;
}

// Solely stylistic
void PrintTitle() {
	cout << R"(
                    /$$$$$$  /$$$$$$ /$$$$$$$   /$$$$$$  /$$$$$$$$  /$$$$$$  /$$$$$$$ 
                   /$$__  $$|_  $$_/| $$__  $$ /$$__  $$| $$_____/ /$$__  $$| $$__  $$
                  | $$  \ $$  | $$  | $$  \ $$| $$  \__/| $$      | $$  \ $$| $$  \ $$
                  | $$$$$$$$  | $$  | $$$$$$$/| $$ /$$$$| $$$$$   | $$$$$$$$| $$  | $$
                  | $$__  $$  | $$  | $$__  $$| $$|_  $$| $$__/   | $$__  $$| $$  | $$
                  | $$  | $$  | $$  | $$  \ $$| $$  \ $$| $$      | $$  | $$| $$  | $$
                  | $$  | $$ /$$$$$$| $$  | $$|  $$$$$$/| $$$$$$$$| $$  | $$| $$$$$$$/
                  |__/  |__/|______/|__/  |__/ \______/ |________/|__/  |__/|_______/	)" << "\n\n";
                  	cout << R"(
                   /$$$$$$$   /$$$$$$  /$$   /$$ /$$   /$$ /$$$$$$ /$$   /$$  /$$$$$$ 
                  | $$__  $$ /$$__  $$| $$$ | $$| $$  /$$/|_  $$_/| $$$ | $$ /$$__  $$
                  | $$  \ $$| $$  \ $$| $$$$| $$| $$ /$$/   | $$  | $$$$| $$| $$  \__/
                  | $$$$$$$ | $$$$$$$$| $$ $$ $$| $$$$$/    | $$  | $$ $$ $$| $$ /$$$$
                  | $$__  $$| $$__  $$| $$  $$$$| $$  $$    | $$  | $$  $$$$| $$|_  $$
                  | $$  \ $$| $$  | $$| $$\  $$$| $$\  $$   | $$  | $$\  $$$| $$  \ $$
                  | $$$$$$$/| $$  | $$| $$ \  $$| $$ \  $$ /$$$$$$| $$ \  $$|  $$$$$$/
                  |_______/ |__/  |__/|__/  \__/|__/  \__/|______/|__/  \__/ \______/ 
                  )" << "\n\n";
	cout << "Welcome! Please enter the values below in the following format for the best experience:" << endl << endl;
	cout << "Initial Investment Amount: $#.##" << endl;
	cout << "Monthly Deposit Amount: $#.##" << endl;
	cout << "Annual Interest Rate: %#.#" << endl;
	cout << "Number of years: #" << endl << endl;
	cout << "If the program asks for your input again, double check your formatting. Thank you, and happy savings!" << endl;
}