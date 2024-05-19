# Word Ladders Game (Graph DSA)

## Overview
The Word Ladders game is a Java application that utilizes a Graph data structure to find connections between words. By entering a starting and ending word, the program calculates the shortest path (word ladder) between these two words using either Depth-First Search (DFS) or Breadth-First Search (BFS). This application supports large datasets, making it capable of processing extensive word lists efficiently.

## Features
- **Flexible Word Graph Construction**: Supports construction from text files, enabling easy customization and expansion of the word database.
- **User-Interactive Gameplay**: Allows users to input starting and ending words to generate word ladders.
- **Search Algorithm Selection**: Users can choose between DFS and BFS to find the word ladder.
- **Scalability**: Efficiently processes large word lists, including datasets with tens of thousands of words.

## Prerequisites
- Java Development Kit (JDK) 11 or later installed on your machine.

## Setup
1. Ensure Java is properly installed by running `java -version` in your terminal or command prompt.
2. Download or clone this repository to your local machine.
3. Navigate to the directory containing `WordLadders.java` and any required word list files.

## Running the Program
Compile and run the application using the Java compiler and runtime environment:
```bash
javac WordLadders.java
java WordLadders
```
Upon execution, the program prompts you to enter the filename of your desired word list. Valid filenames include `Length3WordGraph` or `LargeWordGraph`, although you can use any text file formatted according to the program's specifications.

After loading the word graph, follow the on-screen instructions to input your starting and ending words, and select the search algorithm (DFS or BFS).

## Word List File Format
The program expects word list files to be formatted as follows:
```
word1 neighbor1 neighbor2 ...
word2 neighbor1 neighbor2 ...
...
```
Each line represents a word and its immediate neighbors (words differing by one letter). The program reads these files to construct the word graph used to find word ladders.

## Contributing
Contributions to enhance the functionality or performance of the Word Ladders game are welcome. Please feel free to fork the repository, make your changes, and submit a pull request detailing your improvements.

## License
This project is open-source and available under the [MIT license](https://opensource.org/licenses/MIT).

## Contact
For any inquiries or contributions, please contact the repository owner.

---

Created with ❤️ by [Son Nguyen](https://github.com/hoangsonww) in 2023.
