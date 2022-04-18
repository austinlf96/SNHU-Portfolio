#include "investmentcalculator.h"
#include <string>
#include <iomanip>
#include <iostream>
#include <stdexcept>
#include <regex>
#include <stdio.h>
using namespace std;

// Default constructor
InvestmentCalculator::InvestmentCalculator() {
	m_initialDeposit = 0.0;
	m_monthlyDeposit = 0.0;
	m_interestRate = 0.0;
}

// Overloaded constructor
InvestmentCalculator::InvestmentCalculator(double t_initial = 0.0, double t_monthly = 0.0, double t_interest = 0.0) {
	m_initialDeposit = t_initial;
	m_monthlyDeposit = t_monthly;
	m_interestRate = t_interest;
}

// Output all of the data calculated
// Runs a loop for the number of years, printing the values for that
// respective year, represented by i (the iteration variable) + 1 
void InvestmentCalculator::PrintInterestSummary(int t_years) {
	
	double monthlyDeposit = GetMonthlyDeposit();
	
	cout << endl;
	cout << "    " << setfill('$') << setw(58) << "" << endl;
	cout << setfill(' ') <<  setw(62) << right << "$ Balance & Interest Summary WITHOUT additional deposits $" << endl;
	cout << "    " << setfill('$') << setw(58) << "" << endl;
	cout << setfill('-') << setw(66) << "" << endl;
	cout << right << setfill(' ') << setw(10) << "Year |";
	cout << right << setw(23) << "Year End Balance |";
	cout << right << setw(29) << "Year End Earned Interest" << endl;
	cout << setfill('-') << setw(66) << "" <<endl;

	for (int i = 0; i < t_years; i++) {
		CalculateInterest(i + 1, 0.00);
	}
	
	cout << endl;
	cout << "    " << setfill('$') << setw(58) << "" << endl;
	cout << setfill(' ') << setw(62) << right << "$  Balance & Interest Summary WITH additional deposits   $" << endl;
	cout << "    " << setfill('$') << setw(58) << "" << endl;
	cout << setfill('-') << setw(66) << "" << endl;
	cout << right << setfill(' ') << setw(10) << "Year |";
	cout << right << setw(23) << "Year End Balance |";
	cout << right << setw(29) << "Year End Earned Interest" << endl;
	cout << setfill('-') << setw(66) << "" << endl;

	for (int i = 0; i < t_years; i++) {
		CalculateInterest(i + 1, monthlyDeposit);
	}
}

// Ensure valid input from user. Takes a character to designate what type of input is acceptable. 
// Currently only uses 'd' for double and 'i' for integer, but this functionality can be expanded.
double InvestmentCalculator::ValidateInput(char t_dataType, string t_textToRepeat) {
	
	double userValue;     // Number form of user's input
	string userInput;     // String is used to reduce errors from type incompatibilies
	bool invalid = true;  // While loop conditional variable
	regex regexPattern;   // Verify that userInput is a valid format to be entered into userValue

	if (t_dataType == 'd') {
		regexPattern = regex("\\d+\.\\d+"); // If a double, must have a leading number and at least one decimal number
	}
	else if (t_dataType == 'i') {
		regexPattern = regex("\\d+");       // If an integer, must have at least one number
	}
	else {
		regexPattern = regex(".*");         // Catch all for other characters, will match with anything. 
	}

	
	while (invalid) {
		try {
			cout << t_textToRepeat;
			cin >> userInput;

			// If input is not a valid decimal format, throw an error
			if (!(regex_match(userInput, regexPattern)) && t_dataType == 'd') {
				throw runtime_error("\nImproper format - please try again. Decimals should be in #.## form.\n");
			}
			
			// If input is not a valid integer format, throw an error
			if (!(regex_match(userInput, regexPattern)) && t_dataType == 'i') {
				throw runtime_error("\nImproper format - please try again. Years should be a single positive number greater than 0.\n");
			}

			userValue = stod(userInput);
		
			// If input is an invalid amount (less than zero), throw an error
			if (userValue < 0.01 && t_dataType == 'd') {
				throw runtime_error("\nInvalid amount. Must be more than 0.00.\n");
			}

			// If input is an integer less than zero, throw an error
			if (userValue < 1 && t_dataType == 'i') {
				throw runtime_error("\nInvalid amount. Must be 1 or more years.\n");
			}
			invalid = false;
			return userValue;
		} 
		catch(runtime_error& err) {
			cout << err.what() << endl;
		}
	}
	
}

// Accessor method
double InvestmentCalculator::GetInitialDeposit() {
	return m_initialDeposit;
}

// Mutator method
void InvestmentCalculator::SetInitialDeposit(double t_initial) {
	m_initialDeposit = t_initial;
}

// Accessor method
double InvestmentCalculator::GetMonthlyDeposit() {
	return m_monthlyDeposit;
}

// Mutator method
void InvestmentCalculator::SetMonthlyDeposit(double t_monthly) {
	m_monthlyDeposit = t_monthly;
}

// Accessor method
double InvestmentCalculator::GetInterestRate() {
	return m_interestRate;
}

// Mutator method
void InvestmentCalculator::SetInterestRate(double t_interest) {
	m_interestRate = t_interest;
}

// Does the actual math, and prints the appropriate information
void InvestmentCalculator::CalculateInterest(int t_years, double t_monthly) {
	int months = t_years * 12;
	double balance = GetInitialDeposit();
	double yearEarnedInterest = 0.0;
	
	for (int i = 0; i < months; i++) {
		balance += t_monthly; 

		// If an entire year has completed, reset yearEarnedInterest for that year
		if ((i + 1) % 12 == 1) {
			yearEarnedInterest = 0.0;
		}
		yearEarnedInterest += balance * ((GetInterestRate() / 100) / 12);
		balance = balance + (balance * ((GetInterestRate() / 100) / 12));
	}

	cout << right << setfill(' ') << setw(10) << to_string(t_years) + " |";
	printf("%*c%#3.2f |", 18 - to_string(int(balance)).length(), '$', balance);
	printf("%*c%#3.2f", 26 - to_string(int(yearEarnedInterest)).length(), '$', yearEarnedInterest);
	cout << endl;
}
