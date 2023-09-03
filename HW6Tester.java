import java.io.IOException;
import java.io.ByteArrayInputStream;

import java.time.LocalDate;
import java.time.Month;

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

/**
 * A class that represents and performs the function as the JUnit 4 Tester for all the Homework 6 classes and methods.
 *
 * @author David Nguyen
 * @since 04/30/2023
 * @version 1.0
 */
public class HW6Tester {

    // A field that represents a test graph
    private Graph<String, String> testGraph;

    // A field that represents a wordLadder for testing
    private WordLadders wordLadders;

    /**
     * Sets up the test for Graph and WordLadder
     */
    @Before
    public void setUp() {
        testGraph = new Graph<String, String>();
        wordLadders = new WordLadders();
    }

    /**
     * Test the addNode() method
     */
    @Test
    public void testAddNode() {
        assertTrue(testGraph.addNode("A", "apple"));
        assertFalse(testGraph.addNode("A", "apple")); // Should be false because the node already exists
        assertTrue(testGraph.addNode("B", "banana"));
        assertFalse(testGraph.addNode("B", "bruh")); // Should be false because the key already exists
        assertTrue(testGraph.addNode("C", "cherry"));
        assertFalse(testGraph.addNode(null, "null"));
    }

    /**
     * Test the addNodes() method
     */
    @Test
    public void testAddNodes() {
        System.out.println("Extra testing the addNodes() method with printGraph():");
        String[] names = {"A", "B", "C"};
        String[] data = {"apple", "banana", "cherry"};
        assertTrue(testGraph.addNodes(names, data));
        String[] names1 = {"A", "C"};
        String[] data1 = {"apple", "cherry"};
        assertFalse(testGraph.addNodes(names1, data1)); // Should be false because the node C already exists
        String[] names2 = {"D", "E", "F"};
        String[] data2 = {"Data", "Egg", "Fruit"};
        assertTrue(testGraph.addNodes(names2, data2));
        String[] names3 = {null, "G", "H"};
        String[] data3 = {"null", "Guitar", "Hat"};
        assertTrue(testGraph.addNodes(names3, data3));
        testGraph.printGraph();
        System.out.println();
    }

    /**
     * Test the addNodes() method with arrays of different lengths (Edge case):
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNodesDifferentLengthArrays() {
        String[] names = {"A", "B"};
        String[] data = {"apple", "banana", "cherry"};
        testGraph.addNodes(names, data);
    }

    /**
     * Test the addEdge() method
     */
    @Test
    public void testAddEdge() {
        testGraph.addNode("A", "apple");
        testGraph.addNode("B", "banana");
        assertTrue(testGraph.addEdge("A", "B"));
        assertFalse(testGraph.addEdge("C", "D")); // Should be false because no such nodes exist in the graph
        assertFalse(testGraph.addEdge("D", "E")); // Should be false because no such nodes exist in the graph
        testGraph.addNode("C", "cherry");
        testGraph.addNode("D", "data");
        assertTrue(testGraph.addEdge("A", "D"));
        assertTrue(testGraph.addEdge("B", "D"));
        assertTrue(testGraph.addEdge("A", "C"));
        assertTrue(testGraph.addEdge("C", "D"));
        assertFalse(testGraph.addEdge("C", "D")); // Should be false because the edges already exist
        assertFalse(testGraph.addEdge(null, null)); // Should be false because the key is null
        assertFalse(testGraph.addEdge("E", null)); // Should be false because the key is null
        assertFalse(testGraph.addEdge(null, "F")); // Should be false because the key is null
    }

    /**
     * Test the addEdges() method
     */
    @Test
    public void testAddEdges() {
        System.out.println("Testing the addEdges() method with printGraph():");
        testGraph.addNode("A", "apple");
        testGraph.addNode("B", "banana");
        testGraph.addNode("C", "cherry");
        assertTrue(testGraph.addEdges("A", "B", "C"));
        assertFalse(testGraph.addEdges("B", "A", "C")); // Should be false because the edges already exist
        testGraph.addNode("D", "data");
        testGraph.addNode("E", "eggs");
        testGraph.addNode("F", "finance");
        assertTrue(testGraph.addEdges("D", "E", "F"));
        assertFalse(testGraph.addEdges("F", "E", "D")); // Should be false because the edges already exist
        assertFalse(testGraph.addEdges("F", "D", "E")); // Should be false because the edges already exist
        assertFalse(testGraph.addEdges("G", "F", "E")); // Should be false because the nodes do not exist in the graph
        assertFalse(testGraph.addEdges(null, "X", "Y"));
        testGraph.addNode("Z", "zurich");
        assertTrue(testGraph.addEdges("D", null, "Z"));
        testGraph.printGraph();
        System.out.println();
    }

    /**
     * Test the removeNode() method
     */
    @Test
    public void testRemoveNode() {
        testGraph.addNode("A", "apple");
        testGraph.addNode("B", "bruh");
        testGraph.addNode("C", "cheers");
        testGraph.addNode("D", "data");
        assertTrue(testGraph.removeNode("A"));
        assertFalse(testGraph.removeNode("A")); // Should be false because node is already removed
        assertTrue(testGraph.removeNode("B"));
        assertTrue(testGraph.removeNode("C"));
        assertTrue(testGraph.removeNode("D"));
        assertFalse(testGraph.removeNode("C")); // Should be false because node is already removed
        assertFalse(testGraph.removeNode(null));
    }

    /**
     * Test the removeNodes() method
     */
    @Test
    public void testRemoveNodes() {
        testGraph.addNode("A", "apple");
        testGraph.addNode("B", "banana");
        testGraph.addNode("C", "cherry");
        assertTrue(testGraph.removeNodes("A", "B"));
        assertFalse(testGraph.removeNode("A"));  // Should be false because node is already removed
        assertFalse(testGraph.removeNode("B"));  // Should be false because node is already removed
        assertFalse(testGraph.removeNodes("A")); // Should be false because node is already removed
        assertTrue(testGraph.removeNodes("C"));
        testGraph.addNode("D", "data");
        testGraph.addNode("E", "eggs");
        testGraph.addNode("F", "fintech");
        testGraph.addEdges("D", "E", "F");
        assertTrue(testGraph.removeNodes("D", "E", "F"));
        assertFalse(testGraph.removeNodes(null, null, null));
        testGraph.addNode("G","guitar");
        assertFalse(testGraph.removeNodes("G", null, null));
    }

    /**
     * Test the DFS() method
     */
    @Test
    public void testDFS() {
        testGraph.addNode("A", "apple");
        testGraph.addNode("B", "banana");
        testGraph.addNode("C", "cherry");
        testGraph.addEdge("A", "B");
        testGraph.addEdge("B", "C");
        Object[] dfsPath = testGraph.DFS("A", "C"); // Test the expected path of DFS between A and C
        String[] castedPath = Arrays.copyOf(dfsPath, dfsPath.length, String[].class);
        assertNotNull(castedPath);
        assertEquals(3, castedPath.length);
        assertEquals("A", castedPath[0]);
        assertEquals("B", castedPath[1]);
        assertEquals("C", castedPath[castedPath.length - 1]);
        testGraph.addNode("D", "data");
        testGraph.addNode("E", "egg");
        Object[] dfsPath1 = testGraph.DFS("D", "E"); // Test the expected path of DFS between D and E
        assertEquals(0, dfsPath1.length);
        Object[] dfsPath2 = testGraph.DFS("F", "G"); // Test the expected path of DFS between F and G
        assertEquals(0, dfsPath2.length);
        Object[] dfsPath3 = testGraph.DFS("D", "H"); // Test the expected path of DFS between D and H
        assertEquals(0, dfsPath3.length);
        assertNotNull(dfsPath3);
    }

    /**
     * Test the BFS() method
     */
    @Test
    public void testBFS() {
        testGraph.addNode("A", "apple");
        testGraph.addNode("B", "banana");
        testGraph.addNode("C", "cherry");
        testGraph.addEdge("A", "B");
        testGraph.addEdge("B", "C");
        Object[] bfsPath = testGraph.BFS("A", "C"); // Test the expected path of BFS between A and C
        String[] castedPath = Arrays.copyOf(bfsPath, bfsPath.length, String[].class);
        assertNotNull(castedPath);
        assertEquals(3, castedPath.length);
        assertEquals("A", castedPath[0]);
        assertEquals("B", castedPath[1]);
        assertEquals("C", castedPath[castedPath.length - 1]);
        testGraph.addNode("D", "data");
        testGraph.addNode("E", "egg");
        Object[] bfsPath1 = testGraph.BFS("D", "E"); // Test the expected path of BFS between D and E
        assertEquals(0, bfsPath1.length);
        Object[] bfsPath2 = testGraph.BFS("F", "G"); // Test the expected path of BFS between F and G
        assertEquals(0, bfsPath2.length);
        Object[] bfsPath3 = testGraph.BFS("D", "H"); // Test the expected path of BFS between D and H
        assertEquals(0, bfsPath3.length);
        Object[] bfsPath4 = testGraph.BFS("D", "E"); // Test the expected path of BFS between D and E
        assertEquals(0, bfsPath4.length);
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode("D", "Data");
        graph.addNode("E", "Eggplant");
        Object[] bfsPath5 = testGraph.BFS("D", "E"); // Test the expected path of BFS between D and E
        assertEquals(0, bfsPath5.length);
    }

    /**
     * Test the readWordGraph() method with Length3WordGraph file and a nonexistent file
     */
    @Test
    public void testReadWordGraphWithLength3WordGraph() {
        // Case 1: Test readWordGraph() with Length3WordGraph to see if it can process it
        System.out.println("Test the readWordGraph() method with invalid filename:");
        String filename = "Length3WordGraph";
        Graph<Integer, String> wordGraph = WordLadders.readWordGraph(filename);
        assertNotNull(wordGraph);
        // Case 2: Test readWordGraph() with an invalid file to see if it can give any error messages to System.out
        String fileNameInvalid = "InvalidFileName";
        Graph<Integer, String> wordGraph1 = WordLadders.readWordGraph(fileNameInvalid);
        assertNotNull(wordGraph1);
    }

    /**
     * Test the readWordGraph() method with an invalid file
     */
    @Test
    public void testReadWordGraphWithInvalidGraph() {
        String filename = "bruh.xyz";
        Graph<Integer, String> wordGraph = WordLadders.readWordGraph(filename);
        assertNotNull(wordGraph);
    }

    /**
     * Test the printGraph() method
     */
    @Test
    public void testPrintGraph() {
        System.out.println("Initial test of the printGraph() method:");
        testGraph.printGraph();
        // Be sure to monitor command outputs - This method will use System.out.print
        testGraph.addNode("A", "apple");
        testGraph.addNode("B", "banana");
        testGraph.addNode("C", "cherry");
        testGraph.addEdge("A", "B");
        testGraph.addEdge("B", "C");
        testGraph.printGraph();
        System.out.println();
    }

    /**
     * Test read() method with an invalid filename, expected to print an indicative error message to a screen
     */
    @Test
    public void testReadException() {
        System.out.println();
        System.out.println("Test the read() method with invalid filename:");
        Graph.read("invalid.txt");
    }

    // -------------------------------- PERFORMING EXTRA TESTS OF ALL METHODS -------------------------------- //

    // A field that stores a test graph for an extra test
    private Graph<String, Integer> testGraph2;

    /**
     * Sets up the extra tests
     */
    @Before
    public void setUpForExtraTests() {
        testGraph2 = new Graph<String, Integer>();
        testGraph2.addNode("A", 1);
        testGraph2.addNode("B", 2);
        testGraph2.addNode("C", 3);
        testGraph2.addEdge("A", "B");
        testGraph2.addEdge("A", "C");
        testGraph2.addEdge("B", "C");
    }

    /**
     * Extra testing the printGraph() method
     */
    @Test
    public void extraTestPrintGraph() {
        System.out.println("Testing the printGraph() method with a graph with key and value:");
        testGraph2.printGraph();
        System.out.println();
    }

    /**
     * Testing the read() method
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("Test the read() method with text_input.txt:");
        Graph<String, String> readGraph = Graph.read("text_input.txt");
        readGraph.printGraph();
        System.out.println();
    }

    /**
     * Performs an extra test on read() method
     */
    @Test
    public void extraTestRead() {
        System.out.println("Test the read() method with testFileForRead.txt:");
        Graph<String, String> readGraph = Graph.read("testFileForRead.txt");
        readGraph.printGraph();
        System.out.println();
    }

    /**
     * Performs an extra test on addNode() method
     */
    @Test
    public void extraTestAddNode() {
        Graph<String, String> graph = new Graph<String, String>();
        assertTrue(graph.addNode("A", "dataA"));
        assertFalse(graph.addNode("A", "dataA")); // Should be false because node already exists
        assertTrue(graph.addNode("B", "dataB"));
    }

    /**
     * Performs an extra test on addNodes() method
     */
    @Test
    public void extraTestAddNodes() {
        Graph<String, String> graph = new Graph<String, String>();
        String[] names = {"A", "B", "C"};
        String[] data = {"dataA", "dataB", "dataC"};
        assertTrue(graph.addNodes(names, data));
        String[] duplicatedNodes = {"A", "B", "D"};
        assertFalse(graph.addNodes(duplicatedNodes, data)); // Should be false because 2 of these nodes already exist
        // TEST IF NULL VALUES ARE TO BE ADDED (THEY SHOULDN'T BE ADDED):
        String[] names1 = {"A", null, "B"};
        String[] data1 = {"A", "B", null};
        Graph<String, String> graph1 = new Graph<String, String>();
        assertTrue(graph1.addNodes(names1, data1));
        graph1.printGraph();
        // Expected output: A(A) and B
    }

    /**
     * Performs an extra test on addNodes() method - with exception expected
     */
    @Test(expected = IllegalArgumentException.class)
    public void extraTestAddNodesException() {
        Graph<String, String> graph = new Graph<String, String>();
        String[] names = {"A", "B"};
        String[] data = {"dataA", "dataB", "dataC"};
        graph.addNodes(names, data);
    }

    /**
     * Performs an extra test on addEdge() method
     */
    @Test
    public void extraTestAddEdge() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode("A", "dataA");
        graph.addNode("B", "dataB");
        assertTrue(graph.addEdge("A", "B"));
        assertFalse(graph.addEdge("A", "C"));  // Should be false because C does not exist
    }

    /**
     * Performs an extra test on addEdges() method
     */
    @Test
    public void extraTestAddEdges() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode("A", "dataA");
        graph.addNode("B", "dataB");
        graph.addNode("C", "dataC");
        assertTrue(graph.addEdges("A", "B", "C"));
        assertFalse(graph.addEdges("A", "B", "D")); // Should be false because D does not exist
    }

    /**
     * Performs an extra test on removeNode() method
     */
    @Test
    public void extraTestRemoveNode() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode("A", "dataA");
        graph.addNode("B", "dataB");
        graph.addEdge("A", "B");
        assertTrue(graph.removeNode("A"));
        assertFalse(graph.removeNode("A"));  // Should be false because A is already removed
        assertFalse(graph.removeNode("C"));  // Should be false because C does not exist
    }

    /**
     * Performs an extra test on removeNodes() method
     */
    @Test
    public void extraTestRemoveNodes() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode("A", "dataA");
        graph.addNode("B", "dataB");
        graph.addNode("C", "dataC");
        assertTrue(graph.removeNodes("A", "B"));
        assertFalse(graph.removeNodes("A", "D")); // Should be false as D does not exist, A is already removed
    }

    /**
     * Performs an extra test on DFS() method
     */
    @Test
    public void extraTestDFS() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode("A", "dataA");
        graph.addNode("B", "dataB");
        graph.addNode("C", "dataC");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        String[] expectedPath = {"A", "B", "C"};
        assertArrayEquals(expectedPath, graph.DFS("A", "C"));
        String[] emptyPath = {};
        assertArrayEquals(emptyPath, graph.DFS("A", "D")); // No path should be returned because D is not in the graph
    }

    /**
     * Performs an extra test on BFS() method
     */
    @Test
    public void extraTestBFS() {
        Graph<String, String> graph = new Graph<String, String>();
        graph.addNode("A", "dataA");
        graph.addNode("B", "dataB");
        graph.addNode("C", "dataC");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        String[] expectedPath = {"A", "B", "C"};
        assertArrayEquals(expectedPath, graph.BFS("A", "C"));
        String[] emptyPath = {};
        assertArrayEquals(emptyPath, graph.BFS("A", "D")); // No path should be returned because D is not in the graph
    }

    /**
     * Performs an extra test on readWordGraph() method
     * @throws IOException Throw if the file with given name is not found
     */
    @Test
    public void extraTestReadWordGraph() throws IOException {
        System.out.println("Test the readWordGraph() with testWordGraph.txt:");
        Graph<Integer, String> graph = WordLadders.readWordGraph("testWordGraph.txt");
        assertNotNull(graph);
        assertFalse(graph.addNode(1, "apple"));  // Should be false because this node already exists
        assertFalse(graph.addNode(2, "banana")); // Should be false because this node already exists
        assertFalse(graph.addNode(3, "orange")); // Should be false because this node already exists
        assertFalse(graph.addEdge(1, 2)); // Should be false because this edge already exists
        assertFalse(graph.addEdge(1, 3)); // Should be false because this edge already exists
        assertFalse(graph.addEdge(2, 3)); // Should be false because this edge already exists
        System.out.println();
    }

    /**
     * Performs an extra test on readWordGraph() method
     * @throws IOException Throw if the file with given name is not found
     */
    @Test
    public void extraTestReadWordGraph_2() throws IOException {
        Graph<Integer, String> graph = WordLadders.readWordGraph("testWordGraph2.txt");
        assertNotNull(graph);
        assertFalse(graph.addNode(1, "apple")); // Should be false because this node already exists
        assertFalse(graph.addNode(2, "banana")); // Should be false because this node already exists
        assertFalse(graph.addNode(3, "orange")); // Should be false because this node already exists
        assertFalse(graph.addNode(4, "cherry")); // Should be false because this node already exists
        assertFalse(graph.addEdge(1, 2)); // Should be false because this edge already exists
        assertFalse(graph.addEdge(1, 3)); // Should be false because this edge already exists
        assertFalse(graph.addEdge(2, 3)); // Should be false because this edge already exists
        assertTrue(graph.addEdge(1, 4)); // Should be true because this edge does not already exist
        assertTrue(graph.addEdge(2, 4)); // Should be true because this edge does not already exist
        assertTrue(graph.addEdge(3, 4)); // Should be true because this edge does not already exist
    }

    /**
     * Testing the WordLadders class's Main method using Java's ByteArrayInputStream (with the file "Length3WordGraph"
     * and start word "duo" and end word "eke" as well as when the user does not enter any words to test if the program
     * will correctly halt; Also test when the user enters an invalid search type as well as when the user enters words
     * that do not exist in the file.) BE SURE TO MONITOR THE OUTPUTS OF THE PROGRAM WHEN RUNNING THIS TEST METHOD!
     */
    @Test
    public void testWordLaddersMainMethod() {
        System.out.println();
        System.out.println("TESTING THE MAIN METHOD OF WORDLADDERS:");
        // Case 1: Test when the user enters valid words and the program should return a valid path:
        System.out.println("First, test with valid starting and ending words:");
        System.out.println();
        String input = "Length3WordGraph\n" +
                "duo\n" +
                "eke\n" +
                "BFS\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);
        WordLadders.main(new String[]{});
        scanner.close();
        System.out.println();
        System.out.println();
        // Case 2: Test when the user enters no words and the program should stop:
        System.out.println("Second, test if the program will end when the user stops entering words:");
        System.out.println();
        String input2 = "Length3WordGraph\n" +
                "\n" +
                "\n";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        Scanner scanner2 = new Scanner(System.in);
        WordLadders.main(new String[]{});
        scanner2.close();
        System.out.println();
        // Case 3: Test when the user enters invalid words and the program should return a blank path
        System.out.println("Third, test with invalid starting and ending words:");
        System.out.println();
        String input3 = "Length3WordGraph\n" +
                "bruh\n" +
                "xxyz\n";
        System.setIn(new ByteArrayInputStream(input3.getBytes()));
        Scanner scanner3 = new Scanner(System.in);
        WordLadders.main(new String[]{});
        scanner3.close();
        System.out.println();
        System.out.println();
        // Case 4: Test when the user enters invalid search type
        System.out.println("Fourth, test when the user enters invalid search type:");
        System.out.println();
        String input4 = "Length3WordGraph\n" +
                "duo\n" +
                "eke\n" +
                "XYZ\n";
        System.setIn(new ByteArrayInputStream(input4.getBytes()));
        Scanner scanner4 = new Scanner(System.in);
        WordLadders.main(new String[]{});
        scanner4.close();
        System.out.println();
        System.out.println();
        // Case 5: Test when the user enters invalid filename
        System.out.println("Fifth, test when the user enters an invalid filename:");
        System.out.println();
        String input5 = "InvalidFilename\n" +
                "duo\n" +
                "eke\n" +
                "XYZ\n";
        System.setIn(new ByteArrayInputStream(input5.getBytes()));
        Scanner scanner5 = new Scanner(System.in);
        WordLadders.main(new String[]{});
        scanner5.close();
        System.out.println();
        System.out.println();
        System.out.println("Testing the WordLadders.Main() method is complete.");
        // Case 6: Test when the user enters chooses DFS as search type
        System.out.println("Sixth, test when the user chooses DFS as search type:");
        System.out.println();
        String input6 = "Length3WordGraph\n" +
                "due\n" +
                "due\n" +
                "DFS\n";
        System.setIn(new ByteArrayInputStream(input6.getBytes()));
        Scanner scanner6 = new Scanner(System.in);
        WordLadders.main(new String[]{});
        scanner6.close();
        System.out.println();
        System.out.println();
    }

    /**
     * Test the readWordGraph() method with LargeWordGraph_SmallerFileForExtraTest file with 10,000 lines of data
     */
    @Test
    public void extraTestReadWordGraphWithVeryLargeWordGraph() {
        System.out.println(); // Test if readWordGraph() can process the given file
        System.out.println("Test the readWordGraph() method with LargeWordGraph_SmallerFileForExtraTest file:");
        String filename = "LargeWordGraph_SmallerFileForExtraTest";
        Graph<Integer, String> wordGraph = WordLadders.readWordGraph(filename);
        assertNotNull(wordGraph);
        System.out.println();
    }

    /**
     * Extra Test on printGraph() method with Integer data types
     */
    @Test
    public void extraTestPrintGraph2() {
        System.out.println("Test the printGraph() method with Integer data types:");
        Graph<Integer, Integer> graph = new Graph<Integer, Integer>();
        graph.addNode(1, null);
        graph.addNode(2, null);
        graph.addNode(3, null);
        graph.addNode(4, null);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.printGraph();
        System.out.println();
    }

    /**
     * Extra Test on printGraph() method with Character and Double data types
     */
    @Test
    public void extraTestPrintGraph3() {
        System.out.println("Test the printGraph() method with Character and Double data types:");
        Graph<Character, Double> charGraph = new Graph<Character, Double>();
        charGraph.addNode('A', 1.1);
        charGraph.addNode('D', 4.4);
        charGraph.addNode('C', 3.3);
        charGraph.addNode('B', 2.2);
        charGraph.addEdge('A', 'B');
        charGraph.addEdge('A', 'C');
        charGraph.addEdge('B', 'C');
        charGraph.addEdge('C', 'D');
        charGraph.printGraph();
        System.out.println();
    }

    /**
     * A class that helps test the printGraph() method
     *
     * @author David Nguyen
     * @since 04/30/2023
     * @version 1.0
     */
    public class CustomKeyClass {

        // A field that stores the name of the customKeyClass
        private String name;

        /**
         * Constructor for CustomKeyClass class
         * @param name Name for CustomKeyClass object
         */
        public CustomKeyClass(String name) {
            this.name = name;
        }

        /**
         * A method that converts this object to its String representation
         * @return This CustomKeyClass object's String representation
         */
        @Override
        public String toString() {
            return name;
        }

    }

    /**
     * Extra Test on printGraph() method with CustomKeyClass and String data types
     */
    @Test
    public void extraTestPrintGraph4() {
        System.out.println("Test the printGraph() method with CustomKeyClass and String data types:");
        Graph<CustomKeyClass, String> customGraph = new Graph<CustomKeyClass, String>();
        CustomKeyClass node1 = new CustomKeyClass("X");
        CustomKeyClass node2 = new CustomKeyClass("Y");
        CustomKeyClass node3 = new CustomKeyClass("Z");
        CustomKeyClass node4 = new CustomKeyClass("W");
        customGraph.addNode(node1, "Data1");
        customGraph.addNode(node2, "Data2");
        customGraph.addNode(node3, "Data3");
        customGraph.addNode(node4, "Data4");
        customGraph.addEdge(node1, node2);
        customGraph.addEdge(node1, node3);
        customGraph.addEdge(node2, node3);
        customGraph.addEdge(node3, node4);
        customGraph.printGraph();
        System.out.println();
    }

    /**
     * Extra Test on printGraph() method with LocalDate and Double data types
     */
    @Test
    public void extraTestPrintGraph5() {
        System.out.println("Test the printGraph() method with LocalDate and Double data types:");
        Graph<LocalDate, Double> dateGraph = new Graph<LocalDate, Double>();
        LocalDate date1 = LocalDate.of(2020, Month.JANUARY, 1);
        LocalDate date2 = LocalDate.of(2020, Month.FEBRUARY, 1);
        LocalDate date3 = LocalDate.of(2020, Month.MARCH, 1);
        LocalDate date4 = LocalDate.of(2020, Month.APRIL, 1);
        dateGraph.addNode(date1, 1.1);
        dateGraph.addNode(date2, 2.2);
        dateGraph.addNode(date3, 3.3);
        dateGraph.addNode(date4, 4.4);
        dateGraph.addEdge(date1, date2);
        dateGraph.addEdge(date1, date3);
        dateGraph.addEdge(date2, date3);
        dateGraph.addEdge(date3, date4);
        dateGraph.printGraph();
        System.out.println();
    }

    /**
     * Extra Test on printGraph() method with Boolean and String data types
     */
    @Test
    public void extraTestPrintGraph6() {
        System.out.println("Test the printGraph() method with Boolean and String data types:");
        Graph<Boolean, String> booleanGraph = new Graph<Boolean, String>();
        booleanGraph.addNode(true, "TrueNode");
        booleanGraph.addNode(false, "FalseNode");
        booleanGraph.addEdge(true, false);
        booleanGraph.printGraph();
        System.out.println();
    }

    /**
     * Extra test for DFS() and BFS() methods
     */
    @Test
    public void extraTestDFSandBFS() {
        Graph<String, String> graph = new Graph<String, String>();
        // Add nodes to the graph
        graph.addNode("A", null);
        graph.addNode("B", null);
        graph.addNode("C", null);
        graph.addNode("D", null);
        graph.addNode("E", null);
        // Add edges between nodes
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "E");
        // Test cases:
        // 1. DFS with Valid path:
        Object[] expectedPath1 = new Object[]{"A", "B", "D"};
        assertArrayEquals(expectedPath1, graph.DFS("A", "D"));
        // 2. DFS with Valid path with multiple branches:
        Object[] expectedPath2 = new Object[]{"A", "B", "E"};
        assertArrayEquals(expectedPath2, graph.DFS("A", "E"));
        // 3. DFS with Non-existent path:
        Object[] expectedPath3 = new Object[]{"D", "B", "A", "C"};
        assertArrayEquals(expectedPath3, graph.DFS("D", "C"));
        // 4. DFS with Non-existent node:
        Object[] expectedPath4 = new Object[]{};
        assertArrayEquals(expectedPath4, graph.DFS("A", "F"));
        // 5. DFS with Null node:
        assertArrayEquals(expectedPath4, graph.DFS("A", null));
        assertArrayEquals(expectedPath4, graph.DFS(null, "D"));
        assertArrayEquals(expectedPath4, graph.DFS(null, null));
        // 6. BFS with Valid path:
        Object[] expectedPath5 = new Object[]{"D", "B", "A", "C", "E"};
        assertArrayEquals(expectedPath5, graph.DFS("D", "E"));
        // 7. BFS with Valid path:
        Object[] expectedPath6 = new Object[]{"D", "B", "E"};
        assertArrayEquals(expectedPath6, graph.BFS("D", "E"));
        // 8. BFS with Valid path:
        Object[] expectedPath7 = new Object[]{"A", "B", "E"};
        assertArrayEquals(expectedPath7, graph.BFS("A", "E"));
        // 9. BFS with Invalid path:
        Object[] expectedPath8 = new Object[]{};
        assertArrayEquals(expectedPath8, graph.BFS("A", "F"));
        // 10. BFS with NULL path:
        Object[] expectedPath9 = new Object[]{};
        assertArrayEquals(expectedPath9, graph.BFS("A", null));
        assertArrayEquals(expectedPath9, graph.BFS(null, "E"));
        assertArrayEquals(expectedPath9, graph.DFS(null, null));
    }

}