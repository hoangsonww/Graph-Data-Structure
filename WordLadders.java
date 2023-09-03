import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Hashtable;

/**
 * A class that represents a Word Ladders game that is also runnable and playable by running the main method.
 *
 * @author David Nguyen
 * @since 04/30/2023
 * @version 1.0
 */
public class WordLadders {

    // A field that stores a hash table that keeps the node names as its keys
    private static Hashtable<String, Integer> hashTableForWordGraph = new Hashtable<String, Integer>();

    /**
     * A Main Method that runs the Word Ladders game/program. This program will work with both the Length3WordGraph and
     * LargeWordGraph files. Note that if the user stops entering words (if both starting and ending words are empty),
     * the program will stop, per the requirement of the assignment prompt.
     * Note that when the LargeWordGraph file is chosen, it will take a while to process all 48,000 lines in it.
     * Normally it will take approximately 20-40 seconds to complete processing the graph, depending on the computer's
     * processing power.
     *
     * @param args Any parameter to run the game/program with.
     */
    public static void main(String[] args) {
        // A Scanner instance that is used to get the user's inputs (typed)
        Scanner userInputsGetter = new Scanner(System.in);
        try {
            // Prompts the user to enter the filename of their desired file
            System.out.print("Please enter the filename you wish to use: ");
            // Gets the user's input
            String fileNameFromUser = userInputsGetter.nextLine();
            // Checks if the user has entered a valid filename
            boolean isValidFileName = checkFileName(fileNameFromUser);
            // Now, if the user has entered a valid filename:
            if (isValidFileName == true) {
                // Prompts the user that the program is now reading the file with the given name and asks them to wait
                System.out.println("We're now reading the file and constructing the word graph. Please wait...");
                // A graph that reads the words from the given input file and will construct a word ladder from it
                Graph<Integer, String> graphForWordLadder = readWordGraph(fileNameFromUser);
                // Prompts the user that the program has now finished constructing the word graph based on the given file
                System.out.println("Your word graph has been constructed!");
                // A variable that stores whether the program will be continued or not
                boolean continueProgram = true;
                // A loop that technically runs for forever unless ended by the user and performs the following tasks:
                while (continueProgram == true) {
                    // Prompts the user to enter their starting word from which a path will be found
                    System.out.print("Please enter the STARTING word: ");
                    // Stores the starting word that user typed in
                    String startingWord = userInputsGetter.nextLine();
                    // Prompts the user to enter their starting word to which a path will be found
                    System.out.print("Please enter the ENDING word: ");
                    // Stores the starting word that user typed in
                    String endingWord = userInputsGetter.nextLine();
                    /*
                     * If the entered words are empty (no character entered), break the loop and end the program as they are
                     * invalid inputs and the program is required to stop when the user stops entering words
                     */
                    if ((startingWord.isEmpty() == true && endingWord.isEmpty() == true)) {
                        // Also prompts the user that the program will halt and breaks it
                        System.out.println("You have not entered any words, so the program will be stopped. Feel free to " +
                                "re-run it anytime!");
                        break;
                    }
                    // Otherwise, let the program continue as normal
                    else {
                        ;
                    }
                    // If the entered words are null, break the loop and end the program as they are invalid inputs
                    if ((startingWord == null && endingWord == null)) {
                        // Also prompts the user that the program will halt and breaks it
                        System.out.println("You have not entered any words, so the program will be stopped. Feel free to " +
                                "re-run it anytime!");
                        break;
                    }
                    // Otherwise, let the program continue as normal
                    else {
                        ;
                    }
                    // A variable that stores the Integer value of the starting node
                    Integer valueOfStartingNode = hashTableForWordGraph.get(startingWord);
                    // A variable that stores the Integer value of the ending node
                    Integer valueOfEndingNode = hashTableForWordGraph.get(endingWord);
                    // Prompts the user to enter the search type - BFS or DFS
                    System.out.print("Enter search type (DFS or BFS): ");
                    // Gets the user's input for the search type - BFS or DFS
                    String searchType = userInputsGetter.nextLine();
                    // Prompts that the program will now perform their requested search
                    System.out.println("Now we will perform " + searchType + " to get your desired word ladder!");
                    // Adds a whitespace for aesthetics
                    System.out.println();
                    // A variable array that stores the path from the starting to the ending word
                    Object[] pathFromStartingToEndingWord = new Object[]{};
                    // Now, if the user requests DFS search type, perform DFS between their requested words
                    if (searchType.equalsIgnoreCase("DFS")) {
                        pathFromStartingToEndingWord = graphForWordLadder.DFS(valueOfStartingNode, valueOfEndingNode);
                    }
                    // Else, if the user requests BFS search type, perform BFS between their requested words
                    else if (searchType.equalsIgnoreCase("BFS")) {
                        pathFromStartingToEndingWord = graphForWordLadder.BFS(valueOfStartingNode, valueOfEndingNode);
                    }
                    // Otherwise, the user has likely entered an invalid search type. Prompt them to use only DFS or BFS.
                    else {
                        System.out.println("You have entered a NON-VALID search technique. Please use only DFS or BFS!");
                        // Skips this iteration of the above while-loop
                        continue;
                    }
                    // First, if no path can be found, prompts the user that no paths can be found between their two given words
                    if (pathFromStartingToEndingWord == null || pathFromStartingToEndingWord.length <= 0) {
                        System.out.println("No path can be found between your two specified words! Try again with other " +
                                "pair of words.");
                        System.out.println();
                    }
                    /*
                     * Otherwise, if there is a path between the starting and ending words and the path's length is greater than
                     * 0, print it to the screen and do the following:
                     */
                    else {
                        // Prompts the user that their word ladder is now ready
                        System.out.println("The Word Ladder Is Below: ");
                        // Prints a whitespace for aesthetic purposes and to easily distinguish the word ladder later on
                        System.out.println();
                        // Prints the word ladder to the screen (i.e. the path between two words the user enters)
                        for (int index = 0; index < pathFromStartingToEndingWord.length; index = index + 1) {
                            // A variable that stores the key of the current node to be printed
                            Integer currentNode = (Integer) pathFromStartingToEndingWord[index];
                            /*
                             * A variable that stores the value of the current node to be printed (this will be printed to
                             * the screen):
                             */
                            String singleWordInWordLadder = lookUpWordFromHashTable(currentNode);
                            // Print the words to the screen in a vertical format (to make it look like a LADDER!):
                            System.out.print(singleWordInWordLadder);
                            System.out.println();
                        }
                        /*
                         * Prompts the user that they can play this game again and repeatedly. Also adds some spaces for extra
                         * aesthetics so that the program display can be easier for the user to look at!
                         */
                        System.out.println();
                        System.out.println("You can also play with this Word Ladder game again!");
                        System.out.println();
                    }
                }
            }
            // Otherwise, if the user has entered an invalid filename:
            else {
                // We will halt the program and prompt the user to try running it again with another filename
                System.out.println("You have entered an invalid filename! Try running the program again with " +
                        "Length3WordGraph or LargeWordGraph only!");
            }
        }
        // If a NoSuchElementException is caught, close the userInputsGetter and to stop getting the user's input
        catch (NoSuchElementException exception) {
            // Stops the program here
            userInputsGetter.close();
        }
        /*
         * Even if no exception is caught, if no input is got from the user, still close the userInputsGetter
         * and stop getting the user's input:
         */
        finally {
            // Stops the program here
            userInputsGetter.close();
        }
    }

    /**
     * A method that constructs a graph from a text file using the following format: (nodename1) (nodedata1) (neighbor1)
     * (neighbor2) (neighbor3) and (nodenade2) (nodedata2) (neighbor2) (neighbor2) (neighbor3) ... with each node name
     * is of type Integer and each node data is of type String.
     *
     * @param filename The specified name of file from which a new graph will be constructed.
     * @return A new graph that has its value of type String and its key of type Integer that is constructed from the
     * given file.
     */
    public static Graph<Integer, String> readWordGraph(String filename) {
        // A variable that stores a graph that will be constructed and returned after reading in the file with given filename
        Graph<Integer, String> graphConstructedForGivenFile = new Graph<Integer, String>();
        // A variable that keeps track of the number of lines from the word graph that have been processed by the program
        int numberOfLinesProcessed = 0;
        /*
         * A variable that helps the program display notification messages to System.out to notify the user of the
         * progress of the reading process
         */
        int notificationForEvery1000LinesProcessed = 1000;
        // Try-catch block to detect possible IOException from invalid file or filename
        try {
            // A variable of type FileReader to open the file and read it to the program with the given filename
            FileReader fileReaderForGivenFileName = new FileReader(filename);
            // A variable of type BufferedReader to create a buffered reader to read the file line by line
            BufferedReader bufferedReaderForGivenFileName = new BufferedReader(fileReaderForGivenFileName);
            // A variable that stores the index for adding the neighbors to the graph
            int indexForAddingNeighborsToGraph = 1;
            // A variable to store each line in the file
            String everySingleLineInFile = new String();
            // A loop that loops through all lines in the file to read them and construct nodes in the graph from them
            while ((everySingleLineInFile = bufferedReaderForGivenFileName.readLine()) != null) {
                // Increment the numLinesProcessed variable by 1 for every line processed
                numberOfLinesProcessed = numberOfLinesProcessed + 1;
                // Provide the user with a notification update of the process for each 1,000 lines processed
                if ((numberOfLinesProcessed % notificationForEvery1000LinesProcessed) == 0) {
                    // Updates the user by printing to System.out with the number of lines processed
                    System.out.println("We've processed " + numberOfLinesProcessed + " lines. Please wait...");
                }
                // Otherwise, let the method continue as normal
                else {
                    ;
                }
                /*
                 * For each 20,000 lines processed, notify them that the program is still processing their request and
                 * ask them to wait for a bit more~
                 */
                if ((numberOfLinesProcessed % 10000) == 0) {
                    System.out.println("This file is a fairly large one. Please hold on and we'll get it ready soon!");
                }
                // Otherwise, let the method continue as normal
                else {
                    ;
                }
                /*
                 * Now, split the line into an array of strings using whitespace as the delimiter and store it in a variable
                 * of type String. For example, the resulting String[] array will now contain: [nodename1, nodedata1,
                 * neighbor1, neighbor2, neighbor3, and so on]:
                 */
                String[] splitLinesInFile = everySingleLineInFile.split(" ");
                /*
                 * If the split line's length is smaller than the index variable above, skip this iteration and goes
                 * onto the next
                 */
                if (splitLinesInFile.length < indexForAddingNeighborsToGraph) {
                    continue;
                }
                // Otherwise, let the method continue as normal
                else {
                    ;
                }
                // A variable that reads the current split line in the file
                String currentSplitLineInFile = splitLinesInFile[0];
                // A variable that represents and stores the key of the current split line and its associated node
                Integer keyOfCurrentNode = Integer.parseInt(currentSplitLineInFile);
                // A variable that represents and stores the value of the current node
                String valueOfCurrentNode = splitLinesInFile[1];
                // Add the above pair of key and value to the graph to be constructed
                graphConstructedForGivenFile.addNode(keyOfCurrentNode, valueOfCurrentNode);
                // Add the above pair of key and value to the hashtable for the word graph to be constructed
                hashTableForWordGraph.put(valueOfCurrentNode, keyOfCurrentNode);
                // A loop that iterates through the split lines read from the file and add the neighbors of the current nodes
                for (int index = (indexForAddingNeighborsToGraph) + 1; index < splitLinesInFile.length; index = index + 1) {
                    // A variable that stores the current line being read from the file
                    String currentLineInFile = splitLinesInFile[index];
                    // A variable that stores the values of the neighbor nodes of the current node
                    Integer valueOfNeighborNodeOfCurrentNode = Integer.parseInt(currentLineInFile);
                    // Add the edges between them to the graph above
                    graphConstructedForGivenFile.addEdge(keyOfCurrentNode, valueOfNeighborNodeOfCurrentNode);
                }
            }
            // Stops and closes the reader for the file as the reading operation is now complete
            bufferedReaderForGivenFileName.close();
        }
        // If an IOException is caught, prompts the user that the given file is invalid
        catch (IOException exception) {
            System.out.println("The given filename is INVALID and the file cannot be read!");
        }
        // Return the constructed graph from the given file
        return graphConstructedForGivenFile;
    }

    /**
     * A helper method that lookups words from the hash table given an Integer value. The hashtable field was set up in
     * a way that allows for the quick look up of word nodes by their keys. This method is, therefore, quick & efficient.
     *
     * @param valueToLookUpWordWith Any Integer value to look up its associated String key with.
     * @return The String key associated with the given Integer value. Return null if no key in the table matches the
     * given Integer value.
     */
    private static String lookUpWordFromHashTable(Integer valueToLookUpWordWith) {
        // A loop that iterates through the hash table's keys to find the matching value and return the associated key
        for (String tempWordInHashTable : hashTableForWordGraph.keySet()) {
            // A variable that stores a temporary value from the hash table during the execution of the loop
            Integer valueOfWordInHashTable = hashTableForWordGraph.get(tempWordInHashTable);
            // If a matching value in the hash table is found, return its associated String key
            if (valueOfWordInHashTable.equals(valueToLookUpWordWith)) {
                return tempWordInHashTable;
            }
            // Otherwise, let the method continue as normal
            else {
                ;
            }
        }
        // Return null if no key in the table matches the given Integer value.
        return null;
    }

    /**
     * A helper that checks if the user has entered a valid filename.
     *
     * @param fileName The filename that the user enters.
     * @return True or false depending on the validity of the given filename.
     */
    private static boolean checkFileName(String fileName) {
        // Try-catch block to determine if the user has entered a valid filename
        try {
            // A variable to stores the FileReader to read the file
            FileReader fileReader = new FileReader(fileName);
            // Close the file reader if the user has entered a valid filename
            fileReader.close();
            // Return true in this case
            return true;
        }
        // If an IOException is caught, the user has entered an invalid filename
        catch (IOException e) {
            // Return false in this case
            return false;
        }
    }

}
