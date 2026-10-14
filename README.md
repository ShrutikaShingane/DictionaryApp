Dictionary Lookup Console Application

Project Overview

Dictionary Lookup Console Application is a Java-based application that allows users to manage word definitions. Users can add new words, search for definitions, delete entries, and display all saved words. The application uses a HashMap to store data and file handling to save dictionary entries permanently.

Features

- Add new words and definitions
- Search for word definitions
- Delete dictionary entries
- Display all saved words
- Store words and definitions using HashMap
- Save dictionary data to a text file
- Load saved data automatically when the application starts
- Case-insensitive word searching

Technologies Used

- Java
- Eclipse IDE
- HashMap
- File Handling
- Java Collections Framework

Concepts Practiced

- Object-Oriented Programming (OOP)
- Classes and objects
- HashMap and key-value pairs
- File reading and writing
- Exception handling
- User input using Scanner
- Menu-driven programming

Project Structure

DictionaryApp/
└── src/
    └── dictionary/
        ├── DictionaryEntry.java
        └── DictionaryApp.java

How to Run

1. Open Eclipse IDE.
2. Create or import the DictionaryApp Java project.
3. Ensure both Java files are inside the dictionary package.
4. Open DictionaryApp.java.
5. Run it as a Java Application.
6. Use the console menu to manage dictionary entries.

Menu Options

1. Add Entry
2. Search Word
3. Delete Entry
4. List All Words
5. Exit

Data Storage

The application stores dictionary entries in a file named "dictionary.txt".

- New entries are saved to the file.
- Deleted entries are removed from the saved data.
- Existing entries are loaded automatically when the application starts.

Learning Outcomes

This project demonstrates how to use Java HashMap for efficient data management, implement search and delete operations, and preserve data using file handling.

Future Improvements

- Add a graphical user interface (GUI).
- Add word categories.
- Support editing existing definitions.
- Add example sentences for each word.

Note

This project is developed for educational purposes to practice Java programming, collections, and file handling.