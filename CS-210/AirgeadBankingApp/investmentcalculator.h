#ifndef AIRGEADBANKINGAPP_INVESTMENTCALCULATOR_H
#define AIRGEADBANKINGAPP_INVESTMENTCALCULATOR_H
#include <string>
using namespace std;

class InvestmentCalculator {
	public:
		// Default Constructor
		InvestmentCalculator();

		// Overloaded Constructor
		InvestmentCalculator(double t_initial, double t_monthly, double t_interest);
		
		// Output all of the data calculated 
		void PrintInterestSummary(int t_years);

		// Ensure valid input from user
		double ValidateInput(char t_dataType, string t_textToRepeat);

		// Accessor method
		double GetInitialDeposit();

		// Mutator method
		void SetInitialDeposit(double t_initial);

		// Accessor method
		double GetMonthlyDeposit();

		// Mutator method
		void SetMonthlyDeposit(double t_monthly);

		// Accessor method
		double GetInterestRate();

		// Mutator method
		void SetInterestRate(double t_interest);

	private:
		// Does the actual math, and prints the appropriate information
		void CalculateInterest(int t_years, double t_monthly);

		// Internal state values
		double m_initialDeposit;
		double m_monthlyDeposit;
		double m_interestRate;
		
};

#endif //AIRGEADBANKINGAPP_INVESTMENTCALCULATOR_H