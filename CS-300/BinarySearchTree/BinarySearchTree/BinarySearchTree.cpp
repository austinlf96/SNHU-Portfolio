//============================================================================
// Name        : BinarySearchTree.cpp
// Author      : Austin Frey
// Version     : 1.0
// Copyright   : Copyright © 2017 SNHU COCE
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <time.h>

#include "CSVparser.hpp"

using namespace std;

//============================================================================
// Global definitions visible to all methods and classes
//============================================================================

// forward declarations
double strToDouble(string str, char ch);

// define a structure to hold bid information
struct Bid {
    string bidId; // unique identifier
    string title;
    string fund;
    double amount;
    Bid() {
        amount = 0.0;
    }
};

void displayBid(Bid bid);

// Internal structure for tree node
struct Node {
    Bid bid;
    Node *left;
    Node *right;

    // default constructor
    Node() {
        left = nullptr;
        right = nullptr;
    }

    // initialize with a bid
    Node(Bid aBid) :
            Node() {
        bid = aBid;
    }
};

//============================================================================
// Binary Search Tree class definition
//============================================================================

/**
 * Define a class containing data members and methods to
 * implement a binary search tree
 */
class BinarySearchTree {

private:
    Node* root;
    int size;
    void addNode(Node* node, Bid bid);
    void inOrder(Node* node);
    void removeNode(Node* &node, string bidId);

public:
    BinarySearchTree();
    virtual ~BinarySearchTree();
    void InOrder();
    void PostOrder();
    void PreOrder();
    void Insert(Bid bid);
    Node* findMaximumKey(Node* node);
    void Remove(string bidId);
    void postOrder(Node* node);
    void preOrder(Node* node);
    Bid Search(string bidId);
    int Size();
};

/**
 * Default constructor
 */
BinarySearchTree::BinarySearchTree() {
    // FixMe (1): initialize housekeeping variables
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
    // FixMe (2): In order root
    // call inOrder fuction and pass root 
    inOrder(root);
}

/**
 * Traverse the tree in post-order
 */
void BinarySearchTree::PostOrder() {
    // FixMe (3): Post order root
    // postOrder root
    postOrder(root);
}

/**
 * Traverse the tree in pre-order
 */
void BinarySearchTree::PreOrder() {
    // FixMe (4): Pre order root
    // preOrder root
    preOrder(root);
}

/**
 * Insert a bid
 */
void BinarySearchTree::Insert(Bid bid) {
    // FIXME (5) Implement inserting a bid into the tree
    
    // if root equarl to null ptr
      // root is equal to new node bid
    // else
      // add Node root and bid
    if (root == nullptr) {
        root = new Node(bid);
        root->left = nullptr;
        root->right = nullptr;
        size++;
    }
    else addNode(root, bid);
}

/**
 * Remove a bid
 */
void BinarySearchTree::Remove(string bidId) {
    // FIXME (6) Implement removing a bid from the tree
    // remove node root bidID
    removeNode(root, bidId);
}

/**
 * Search for a bid
 */
Bid BinarySearchTree::Search(string bidId) {
    // FIXME (7) Implement searching the tree for a bid
    // set current node equal to root
    Node* currentNode = root;

    // keep looping downwards until bottom reached or matching bidId found
        // if match found, return current bid

        // if bid is smaller than current node then traverse left
        // else larger so traverse right
    while (currentNode != nullptr) {
        if (bidId.compare(currentNode->bid.bidId) == 0) return currentNode->bid;
        else if (bidId.compare(currentNode->bid.bidId) < 0) currentNode = currentNode->left;
        else currentNode = currentNode->right;
    }

    Bid bid;
    return bid;
}

/**
 * Add a bid to some node (recursive)
 *
 * @param node Current node in tree
 * @param bid Bid to be added
 */
void BinarySearchTree::addNode(Node* node, Bid bid) {
    // FIXME (8) Implement inserting a bid into the tree

    Node* nodeToAdd = new Node(bid);
    
    // if node is larger then add to left
    if (node->bid.bidId.compare(nodeToAdd->bid.bidId) > 0) {

        // if no left node
            // this node becomes left
        // else recurse down the left node
        if (node->left == nullptr) {
            node->left = nodeToAdd;
            size++;
        }

        else addNode(node->left, bid);
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
        else addNode(node->right, bid);
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
 * @param bidId Bid to remove
 */
void BinarySearchTree::removeNode(Node* &node, string bidId) {
    
    // bidId is not in tree
    if (node == nullptr) return;

    // If bidId is less than node's bidId, descend left subtree
    if (bidId.compare(node->bid.bidId) < 0) removeNode(node->left, bidId);

    // If bidId is greater than node's bidId, descend right subtree
    else if (bidId.compare(node->bid.bidId) > 0) removeNode(node->right, bidId);

    //bidId found
    else {
        // Leaf node
        if (node->left == nullptr && node->right == nullptr) {
            free(node);
            node = nullptr;
        }

        // Node with 2 children
        else if (node->left !=nullptr && node->right != nullptr) {
            
            // Find predecessor node
            Node* predecessor = findMaximumKey(node->left);

            // Move predecessor's bidId
            node->bid.bidId = predecessor->bid.bidId;

            // Recursive call
            removeNode(node->left, predecessor->bid.bidId);
            
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
      // FixMe (9): Pre order root
      //if node is not equal to null ptr
      //InOrder not left
      //output bidID, title, amount, fund
      //InOder right

    if (node == nullptr) return;
    if (node->left != nullptr) inOrder(node->left);
    displayBid(node->bid);
    inOrder(node->right);
}

void BinarySearchTree::postOrder(Node* node) {
      // FixMe (10): Pre order root
      //if node is not equal to null ptr
      //postOrder left
      //postOrder right
      //output bidID, title, amount, fund

    if (node == nullptr) return;
    postOrder(node->left);
    postOrder(node->right);
    displayBid(node->bid);

}

void BinarySearchTree::preOrder(Node* node) {
      // FixMe (11): Pre order root
      //if node is not equal to null ptr
      //output bidID, title, amount, fund
      //postOrder left
      //postOrder right   

    if (node == nullptr) return;
    displayBid(node->bid);
    preOrder(node->left);
    preOrder(node->right);
}

int BinarySearchTree::Size() {
    return size;
}

//============================================================================
// Static methods used for testing
//============================================================================

/**
 * Display the bid information to the console (std::out)
 *
 * @param bid struct containing the bid info
 */
void displayBid(Bid bid) {
    cout << bid.bidId << ": " << bid.title << " | " << bid.amount << " | "
            << bid.fund << endl;
    return;
}

/**
 * Load a CSV file containing bids into a container
 *
 * @param csvPath the path to the CSV file to load
 * @return a container holding all the bids read
 */
void loadBids(string csvPath, BinarySearchTree* bst) {
    cout << "Loading CSV file " << csvPath << endl;

    // initialize the CSV Parser using the given path
    csv::Parser file = csv::Parser(csvPath);

    // read and display header row - optional
    vector<string> header = file.getHeader();
    for (auto const& c : header) {
        cout << c << " | ";
    }
    cout << "" << endl;

    try {
        // loop to read rows of a CSV file
        for (unsigned int i = 0; i < file.rowCount(); i++) {

            // Create a data structure and add to the collection of bids
            Bid bid;
            bid.bidId = file[i][1];
            bid.title = file[i][0];
            bid.fund = file[i][8];
            bid.amount = strToDouble(file[i][4], '$');

            //cout << "Item: " << bid.title << ", Fund: " << bid.fund << ", Amount: " << bid.amount << endl;

            // push this bid to the end
            bst->Insert(bid);
        }
    } catch (csv::Error &e) {
        std::cerr << e.what() << std::endl;
    }
}

/**
 * Simple C function to convert a string to a double
 * after stripping out unwanted char
 *
 * credit: http://stackoverflow.com/a/24875936
 *
 * @param ch The character to strip out
 */
double strToDouble(string str, char ch) {
    str.erase(remove(str.begin(), str.end(), ch), str.end());
    return atof(str.c_str());
}

/**
 * The one and only main() method
 */
int main(int argc, char* argv[]) {

    // process command line arguments
    string csvPath, bidKey;
    switch (argc) {
    case 2:
        csvPath = argv[1];
        bidKey = "98109";
        break;
    case 3:
        csvPath = argv[1];
        bidKey = argv[2];
        break;
    default:
        csvPath = "eBid_Monthly_Sales_Dec_2016.csv";
        bidKey = "98109";
    }

    // Define a timer variable
    clock_t ticks;

    // Define a binary search tree to hold all bids
    BinarySearchTree* bst;
    bst = new BinarySearchTree();
    Bid bid;

    int choice = 0;
    while (choice != 9) {
        cout << "Menu:" << endl;
        cout << "  1. Load Bids" << endl;
        cout << "  2. Display All Bids" << endl;
        cout << "  3. Find Bid" << endl;
        cout << "  4. Remove Bid" << endl;
        cout << "  9. Exit" << endl;
        cout << "Enter choice: ";
        cin >> choice;

        switch (choice) {

        case 1:
            
            // Initialize a timer variable before loading bids
            ticks = clock();

            // Complete the method call to load the bids
            loadBids(csvPath, bst);

            cout << bst->Size() << " bids read" << endl;

            // Calculate elapsed time and display result
            ticks = clock() - ticks; // current clock ticks minus starting clock ticks
            cout << "time: " << ticks << " clock ticks" << endl;
            cout << "time: " << ticks * 1.0 / CLOCKS_PER_SEC << " seconds" << endl;
            break;

        case 2:
            bst->InOrder();
            break;

        case 3:
            cout << "Please enter the bid id you would like to find: " << endl;
            cin >> bidKey;
            ticks = clock();

            bid = bst->Search(bidKey);

            ticks = clock() - ticks; // current clock ticks minus starting clock ticks

            if (!bid.bidId.empty()) {
                displayBid(bid);
            } else {
            	cout << "Bid Id " << bidKey << " not found." << endl;
            }

            cout << "time: " << ticks << " clock ticks" << endl;
            cout << "time: " << ticks * 1.0 / CLOCKS_PER_SEC << " seconds" << endl;

            break;

        case 4:
            cout << "Please enter the bid id you would like to remove: " << endl;
            cin >> bidKey;
            bst->Remove(bidKey);
            break;
        }
    }

    cout << "Good bye." << endl;

	return 0;
}
