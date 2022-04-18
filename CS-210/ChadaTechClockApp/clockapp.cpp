/*
* Author: Austin Frey
* Project: Chada Tech ISO-8601 Clock
* Due: Jan 23, 2022
* Notes:
*
* 1) The functional documentation did not specify if reaching a time cap (24h/60m/60s) should
* increment the appropriate unit of time. Since there is way to decrement the clock built in, that
* functionality has not been included.
*
* 2) While the flowchart specified to clear the screen between prints, there is no safe or reliable
* way to do this without calling to a specific operating system. However, using system()
* to clear console is resource intensive and risky. A clear screen method has been created and commented out,
* but since C++ has no understanding of a console there is no built in functionality to clear the screen.
*/

#include <iostream>
#include <time.h>
#include <typeinfo>
#include <iomanip>
#include <string>
using namespace std;


void validateInput(char& userChar);
int incrementTime(int timeNum, char userInput);
void printClocks(int hrs, int mins, int secs);
void printMenu();
void clearScreen();

int main() {
    tm localTime;                           // Create reference variable for structure from localtime_s() to populate
    time_t systemTime;                      // systemTime is the time_t object declared in time.h 
    int sec;
    int min;
    int hr;
    string userString;
    char userInput = ' ';

    systemTime = time(NULL);                // Using time(NULL) grabs the numbers of seconds since 00:00 Jan 1, 1970 UTC (Unix timestamp)
    localtime_s(&localTime, &systemTime);   // Populate localTime with a struct tm that contains the values below

    sec = (&localTime)->tm_sec;           // Assign variable for readability and ease of use
    min = (&localTime)->tm_min;           // Assign variable for readability and ease of use
    hr = (&localTime)->tm_hour;           // Assign variable for readability and ease of use

    while (userInput != '4') {
        // clearScreen();
        printClocks(hr, min, sec);
        printMenu();
        cout << "Please enter a selection: ";
        getline(cin, userString);
        cout << endl;
        userInput = userString.at(0);
        validateInput(userInput);

        switch (userInput) {
        case '1':
            hr = incrementTime(hr, userInput);
            break;
        case '2':
            min = incrementTime(min, userInput);
            break;
        case '3':
            sec = incrementTime(sec, userInput);
            break;
        default:
            break;
        }
    }

    return 0;
}

void validateInput(char& userChar) {
    string userInput;
    bool valid = false;

    if (userChar == '1' || userChar == '2' || userChar == '3' || userChar == '4') {
        return;
    }
    else {
        while (!valid) {
            cout << "\nThat was an invalid input. Please try again: ";
            getline(cin, userInput);
            userChar = userInput.at(0);
            cout << endl;
            if (userChar == '1' || userChar == '2' || userChar == '3' || userChar == '4') {
                return;
            }
        }
    }
}

int incrementTime(int timeNum, char userInput) {

    // Increment hours by one. Circle back to 0 if necesssary
    if (userInput == '1') {
        if (++timeNum > 23) {
            timeNum = 0;
        }
    }

    // Increment minutes and seconds by one. Circle back to 0 if necesssary
    else {
        if (++timeNum > 59) {
            timeNum = 0;
        }
    }

    return timeNum;
}

void printClocks(int hrs, int mins, int secs) {

    int hrs12;
    string hrs12Abbreviation;

    // Calculates 12-hour format if 24-hour time is more than 12
    if (hrs > 12) {
        hrs12 = hrs - 12;
        hrs12Abbreviation = " P M";
    }
    // Sets 12-hour format abbreviation to account for noon
    else if (hrs == 12) {
        hrs12 = hrs;
        hrs12Abbreviation = " P M";
    }
    // Set 12-hour format to 12 for midnight (a.k.a. 00:xx:xx for 24-hour format)
    else if (hrs == 0) {
        hrs12 = 12;
        hrs12Abbreviation = " A M";
    }
    // 12-hour format and 24-hour format are the same in this instance
    else {
        hrs12 = hrs;
        hrs12Abbreviation = " A M";
    }

    // Top row of stars
    cout << "    " << right << setw(29) << setfill('*') << "    ";
    cout << left << setw(29) << setfill('*') << "    " << endl;

    // Clock format row (12/24h)
    cout << "    " << left << setw(6) << setfill(' ') << "*";
    cout << "12-Hour Clock" << right << setw(6) << "*";
    cout << "        " << left << setw(6) << "*";
    cout << "24-Hour Clock" << right << setw(6) << "*" << endl;

    // Actual clock display row
    cout << "    " << left << setw(6) << "*";
    cout << right << setw(2) << setfill('0') << hrs12;
    cout << ":" << right << setw(2) << setfill('0') << mins << ":";
    cout << right << setw(2) << setfill('0') << secs;
    cout << left << setw(10) << setfill(' ') << hrs12Abbreviation << "*";
    cout << "        " << left << setw(8) << "*";
    cout << right << setw(2) << setfill('0') << hrs;
    cout << ":" << right << setw(2) << setfill('0') << mins << ":";
    cout << right << setw(2) << setfill('0') << secs;
    cout << right << setw(9) << setfill(' ') << "*" << endl;

    // Bottom row of stars
    cout << "    " << right << setw(29) << setfill('*') << "    ";
    cout << left << setw(29) << setfill('*') << "    " << endl << endl;


}

void printMenu() {

    // Top row of stars
    cout << right << setw(20) << setfill(' ') << "*";
    cout << left << setw(27) << setfill('*') << "*";
    cout << left << setw(15) << setfill(' ') << endl;

    // Option 1
    cout << right << setw(20) << setfill(' ') << "*";
    cout << left << setw(26) << " 1 - Add One Hour";
    cout << "*" << endl;

    // Option 2
    cout << right << setw(20) << setfill(' ') << "*";
    cout << left << setw(26) << " 2 - Add One Minute";
    cout << "*" << endl;

    // Option 3
    cout << right << setw(20) << setfill(' ') << "*";
    cout << left << setw(26) << " 3 - Add One Second";
    cout << "*" << endl;

    // Option 4
    cout << right << setw(20) << setfill(' ') << "*";
    cout << left << setw(26) << " 4 - Exit Program";
    cout << "*" << endl;


    // Bottom row of stars
    cout << right << setw(20) << setfill(' ') << "*";
    cout << left << setw(27) << setfill('*') << "*";
    cout << left << setw(15) << setfill(' ') << endl << endl;

}

void clearScreen() {
    cout << string(100, '\n');
}