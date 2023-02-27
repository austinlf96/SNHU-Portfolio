// ABCUCourses.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <fstream>
#include <stdio.h>
#include "CSVparser.hpp"

using namespace std;

// define a structure to hold course information
struct Course {
    string courseId; // unique identifier
    string courseName;
    vector<string> preRequisites;
    
    // Default constructor
    Course() {
        courseId = "-1";
        courseName = "Empty";
    }

    // Constructor with course information - no prerequisites
    Course(string id, string name) {
        courseId = id;
        courseName = name;
    }

    // Constructor with course information and prerequisites
    Course(string id, string name, vector<string> preReqs) {
        courseId = id;
        courseName = name;
        preRequisites = preReqs;
    }
};

void displayCourse(Course course);

struct Node {
    Course course;
    Node* left;
    Node* right;

    // default constructor
    Node() {
        left = nullptr;
        right = nullptr;
    }

    // initialize with a course
    Node(Course aCourse) :
        Node() {
        course = aCourse;
    }
};

class BinarySearchTree {

private:
    Node* root;
    int size;
    void addNode(Node* node, Course course);
    void inOrder(Node* node);
    void removeNode(Node*& node, string courseId);

public:
    BinarySearchTree();
    virtual ~BinarySearchTree();
    void InOrder();
    void PostOrder();
    void PreOrder();
    void Insert(Course course);
    Node* findMaximumKey(Node* node);
    void Remove(string courseId);
    void postOrder(Node* node);
    void preOrder(Node* node);
    Course Search(string courseId);
    int Size();
};

/**
 * Default constructor
 */
BinarySearchTree::BinarySearchTree() {
    //root is equal to nullptr
    root = nullptr;
    size = 0;
}

/**
 * Destructor
 */
BinarySearchTree::~BinarySearchTree() {
    // recurse from root deleting every node

}

/**
 * Traverse the tree in order
 */
void BinarySearchTree::InOrder() {
    // call inOrder fuction and pass root 
    inOrder(root);
}

/**
 * Traverse the tree in post-order
 */
void BinarySearchTree::PostOrder() {
    // postOrder root
    postOrder(root);
}

/**
 * Traverse the tree in pre-order
 */
void BinarySearchTree::PreOrder() {
    // preOrder root
    preOrder(root);
}

/**
 * Insert a course
 */
void BinarySearchTree::Insert(Course course) {

    // if root equarl to null ptr
      // root is equal to new node course
    // else
      // add Node root and course
    if (root == nullptr) {
        root = new Node(course);
        root->left = nullptr;
        root->right = nullptr;
        size++;
    }
    else addNode(root, course);
}

/**
 * Remove a course
 */
void BinarySearchTree::Remove(string courseId) {
    // remove node root courseID
    removeNode(root, courseId);
}

/**
 * Search for a course
 */
Course BinarySearchTree::Search(string courseId) {
    // set current node equal to root
    Node* currentNode = root;

    // keep looping downwards until bottom reached or matching courseId found
        // if match found, return current course

        // if course is smaller than current node then traverse left
        // else larger so traverse right
    while (currentNode != nullptr) {
        if (courseId.compare(currentNode->course.courseId) == 0) return currentNode->course;
        else if (courseId.compare(currentNode->course.courseId) < 0) currentNode = currentNode->left;
        else currentNode = currentNode->right;
    }

    Course course;
    return course;
}

/**
 * Add a course to some node (recursive)
 *
 * @param node Current node in tree
 * @param course Course to be added
 */
void BinarySearchTree::addNode(Node* node, Course course) {

    Node* nodeToAdd = new Node(course);

    // if node is larger then add to left
    if (node->course.courseId.compare(nodeToAdd->course.courseId) > 0) {

        // if no left node
            // this node becomes left
        // else recurse down the left node
        if (node->left == nullptr) {
            node->left = nodeToAdd;
            size++;
        }

        else addNode(node->left, course);
    }
    // if node is smaller then add to right
    else {
        // if no right node
            // this node becomes right
        if (node->right == nullptr) {
            node->right = nodeToAdd;
            size++;
        }
        //else
            // recurse down the left node
        else addNode(node->right, course);
    }
}

// Helper function to find the maximum value node in the subtree rooted at `node`
Node* BinarySearchTree::findMaximumKey(Node* node)
{
    while (node->right != nullptr) {
        node = node->right;
    }
    return node;
}

/*
 * Remove a node (recursive)
 *
 * @param node Current node in tree
 * @param courseId Course to remove
 */
void BinarySearchTree::removeNode(Node*& node, string courseId) {

    // courseId is not in tree
    if (node == nullptr) return;

    // If courseId is less than node's courseId, descend left subtree
    if (courseId.compare(node->course.courseId) < 0) removeNode(node->left, courseId);

    // If courseId is greater than node's courseId, descend right subtree
    else if (courseId.compare(node->course.courseId) > 0) removeNode(node->right, courseId);

    //courseId found
    else {
        // Leaf node
        if (node->left == nullptr && node->right == nullptr) {
            free(node);
            node = nullptr;
        }

        // Node with 2 children
        else if (node->left != nullptr && node->right != nullptr) {

            // Find predecessor node
            Node* predecessor = findMaximumKey(node->left);

            // Move predecessor's courseId
            node->course.courseId = predecessor->course.courseId;

            // Recursive call
            removeNode(node->left, predecessor->course.courseId);

        }
        // Nodes with 1 child (Left or right)
        else {
            Node* child = (node->left) ? node->left : node->right;
            Node* currentNode = node;
            node = child;
            free(currentNode);
        }
    }


}

void BinarySearchTree::inOrder(Node* node) {
    //if node is not equal to null ptr
    //InOrder not left
    //output courseID, courseName, prerequisites
    //InOder right

    if (node == nullptr) return;
    if (node->left != nullptr) inOrder(node->left);
    displayCourse(node->course);
    inOrder(node->right);
}

void BinarySearchTree::postOrder(Node* node) {
    //if node is not equal to null ptr
    //postOrder left
    //postOrder right
    //output courseID, courseName, prerequisites

    if (node == nullptr) return;
    postOrder(node->left);
    postOrder(node->right);
    displayCourse(node->course);

}

void BinarySearchTree::preOrder(Node* node) {
    //if node is not equal to null ptr
    //output courseID, courseName, prerequisites
    //postOrder left
    //postOrder right   

    if (node == nullptr) return;
    displayCourse(node->course);
    preOrder(node->left);
    preOrder(node->right);
}

int BinarySearchTree::Size() {
    return size;
}

// Displays all course information
void displayCourse(Course course) {
    // ERROR TRAP: Invalid course inquiry
    if (course.courseId.compare("-1") == 0) cout << "Course not found. Please try again." << endl;
    
    else {
        cout << "Course ID: " << course.courseId << endl;
        cout << "Course Title: " << course.courseName << endl;
        if (course.preRequisites.size() == 0) cout << "Course Prerequisites: None" << endl;
        else {
            cout << "Course Prerequisites: " << endl;
            for (int prereq = 0; prereq < course.preRequisites.size(); prereq++) {
                cout << "  " << prereq + 1 << ". " << course.preRequisites.at(prereq) << endl;
            }
        }
        cout << endl;
    }
}

// Parses files, creates course objects, and inserts them into bst
void loadCourses(string filePath, BinarySearchTree* bst) {
    cout << "Loading CSV file " << filePath << endl << endl;

    // initialize the CSV Parser using the given path
    csv::Parser file = csv::Parser(filePath);

    // read and display header row - optional
    vector<string> header = file.getHeader();
    for (auto const& c : header) {
        cout << c << " | ";
    }
    cout << "" << endl;

    try {
        // loop to read rows of a CSV file
        for (unsigned int i = 0; i < file.rowCount(); i++) {
            cout << file[i] << endl;
            // Create a data structure and add to the collection of courses
            Course course;

            // Validate that a course has the necessary fields
            if (file[i].size() < 2) {
                cout << "Invalid course entered: " << file[i] << endl;
                continue;
            }
            // Course has no prerequisites
            else if (file[i].size() == 2) {
                course.courseId = file[i][0];
                course.courseName = file[i][1];
            }
            // Course has prerequisites
            else {
                course.courseId = file[i][0];
                course.courseName = file[i][1];

                // Iterate through prerequsites and add them to course
                for (int j = 2; j < file[i].size(); j++) {
                    
                    // Tracking variable for printing prerequisite error message
                    bool valid = false;

                    // Check all course IDs to ensure prerequisite is valid
                    for (int k = 0; k < file.rowCount(); k++) {

                        // If prerequisite field is empty
                        if (file[i][j].empty()) continue;
                        // If prerequisite id is within the list of courses
                        else if (file[k][0].compare(file[i][j]) == 0) {
                            // cout << "Valid prerequisite: " << file[i][j] << " is " << file[k][0] << endl;
                            valid = true;
                            course.preRequisites.push_back(file[i][j]);
                            break;
                        }
                    }
                    if (!valid && !file[i][j].empty()) cout << "Invalid prerequisite: " << file[i][j] << " of size " << file[i][j].size() << endl;

                }
            }

            // push this course to the end
            bst->Insert(course);
        }
    }
    catch (csv::Error& e) {
        std::cerr << e.what() << std::endl;
    }
}

// One and only main() function
int main()
{
    // Obtain path to the file the user intends to use for the program
    string filePath;
    bool validFile = false;
    while (!validFile) {
        cout << "Please enter the file name you wish to load (If file is not in current directory, please specify the path to the file):" << endl;
        cin >> filePath;
        
        // ERROR TRAP: Invalid file path
        
        // Test that filePath points to an existing file
        ifstream file(filePath);
        if (!file) cout << "File not found. Please try again." << endl;
        else {
            cout << "File found. Loading... " << endl;
            validFile = true;
        }
    }

    // Initialize BinarySearchTree to hold courses
    BinarySearchTree* bst;
    bst = new BinarySearchTree();
    Course course;
    string courseID;

    // Start menu 
    char choice = '0';
    while (choice != '9') {

        //Print menu
        cout << "\nMenu:" << endl;
        cout << "  1. Load courses" << endl;
        cout << "  2. Display courses" << endl;
        cout << "  3. Search for a course" << endl;
        cout << "  9. Exit program" << endl << endl;
        cout << "Enter your choice: ";
        cin >> choice;
        cout << endl;

        switch (choice) {
            case '1':
                loadCourses(filePath, bst);
                break;
            case '2':
                cout << "Here is a sample schedule: " << endl;
                bst->InOrder();
                break;
            case '3':
                cout << "Please enter the course you wish to search for: " << endl;
                cin >> courseID;
                cout << endl;
                displayCourse(bst->Search(courseID));
                break;
            case '9':
                cout << "\nThank you for using the course planner!" << endl;
                break;
            default:
                cout << choice << " is invalid. Please enter a valid option (1, 2, 3, or 9)." << endl;
        }
    }
}

