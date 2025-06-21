import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Arrays;

/**
 * A class that represents and stores a Graph data structure. It holds data of types Key (K) and Value (V). Each edge in
 * the graph is done by adding the nodes to be connected to each other's list of neighbors.
 *
 * @param <K> Any generic type K.
 * @param <V> Any generic type V.
 *
 * @author David Nguyen
 * @since 04/30/2023
 * @version 1.0
 */
public class Graph<K, V> {

    // A field that is a list that stores all nodes in this graph
    private List<GraphNode> listOfGraphNodes;

    /**
     * A constructor that creates a Graph data structure
     */
    public Graph() {
        // Initialize necessary fields
        this.listOfGraphNodes = new ArrayList<GraphNode>();
    }

    /**
     * A method that adds a node to this graph and checks for duplicates. If a node with the same name and data already
     * exists in the graph, it will not be added to the graph and the method returns FALSE. Otherwise, it will be added
     * to the graph and the method will return TRUE.
     *
     * @param name Any name to add a new node to the graph with.
     * @param data The data of the new node to be added.
     * @return True or False depending on whether the node is successfully added to the graph or not.
     */
    public boolean addNode(K name, V data) {
        // A variable that keeps track of whether the new node is successfully added or not
        boolean addNodeSuccess = false;
        // Checks if the key given is null to avoid NullPointerException
        if (name == null) {
            return false; // Return false if the key is null
        }
        // Otherwise, let the method continue normally
        else {
            ;
        }
        /*
         * Find if the node with the specified name and data already exists in the graph. If it exists, then the method
         * stops early and the new node will not be added. It will also return false by setting the addNodeSuccess
         * variable to false.
         */
        if (findNodeWithName(name) != null) {
            // Sets the addNodeSuccess variable to false
            addNodeSuccess = false;
            // Return the variable so that the method will stop here
            return addNodeSuccess;
        }
        // Otherwise, do nothing and let the method continue as normal
        else {
            ;
        }
        // A variable that stores the new node that will be added to the graph
        GraphNode nodeToBeAdded = new GraphNode(name, data);
        // Add that node to the graph's list of nodes to add it to the graph
        this.listOfGraphNodes.add(nodeToBeAdded);
        // Sets the addNodeSuccess variable to true to indicate that the node has been successfully added
        addNodeSuccess = true;
        // Return the addNodeSuccess variable in order to end the method
        return addNodeSuccess;
    }

    /**
     * A method that adds a list of nodes to this graph and also checks for duplicated nodes. If any node with the same
     * name and data already exists in the graph, it will not be added to the graph and the method returns FALSE.
     * Otherwise, it will be added normally and the method returns TRUE. The two input arrays must be of the same length.
     * Note that null keys will not be added to avoid NullPointerExceptions later on.
     *
     * @param names Any name to add a new node to the graph with.
     * @param data The data of the new node to be added.
     * @return True or False depending on whether the node is successfully added to the graph or not.
     * @throws IllegalArgumentException Throws an IllegalArgumentException if the two given arrays have different lengths.
     */
    public boolean addNodes(K[] names, V[] data) {
        // A variable that keeps track of whether the new node is successfully added or not
        boolean addNodesSuccess = true;
        // Throws an IllegalArgumentException if the two given arrays have different lengths:
        if (names.length != data.length) {
            throw new IllegalArgumentException("The given names and data arrays have different length - " +
                    "They must have the same length!");
        }
        // Otherwise, let the method continue as normal
        else {
            ;
        }
        // A loop that iterates through the two given arrays to add the nodes to this graph, one by one:
        for (int index = 0; index < names.length && index < data.length; index = index + 1) {
            // If the node to be added's key is null, skip it because we cannot allow null keys in our graph
            if (names[index] == null) {
                continue; // Skip the null key
            }
            // Otherwise, let the method continue normally
            else {
                ;
            }
            /*
             * A variable that stores whether a node constructed by the data in the given two arrays has been successfully
             * added to this graph or not
             */
            boolean ifAddThisNodeSuccessful = addNode(names[index], data[index]);
            /*
             * Updates the addNodeSuccess variable to the value of the comparison between itself and the variable
             * ifAddThisNodeSuccessful in order to keep track of whether the new node has been successfully added to the
             * graph or not
             */
            addNodesSuccess = addNodesSuccess && ifAddThisNodeSuccessful;
        }
        // Return the addNodeSuccess variable in order to end the method
        return addNodesSuccess;
    }

    /**
     * A method that adds an undirected edge from Node FROM to Node TO. It will connect the two nodes together using an
     * edge, and these two nodes will also become neighbors of each other.
     * Please Note: Duplicated edges are not allowed, and they will not be added.
     *
     * @param from Any node to add the edge from.
     * @param to Any node to add the edge to.
     * @return True or False depending on whether the new edge has been successfully added to the graph or not.
     */
    public boolean addEdge(K from, K to) {
        // If the node to add edge from or node to add edge to is null, no edge should be added here to avoid exceptions
        if (from == null || to == null) {
            return false; // Return false if either of the keys is null
        }
        // Otherwise, let the method continue normally
        else {
            ;
        }
        // A variable that stores the node from which the new edge will be added and connected
        GraphNode addEdgeFromThisNode = findNodeWithName(from);
        // A variable that stores the node to which the new edge will be added and connected
        GraphNode connectEdgeToThisNode = findNodeWithName(to);
        // A variable that keeps track of whether the new edge is successfully added or not
        boolean addEdgeSuccess = false;
        // Checks if Node FROM is null. If it is, no new edge can be added and the method stops early and returns false
        if (addEdgeFromThisNode == null) {
            // Sets the addEdgeSuccess variable to false in order to indicate that no new edge can be added
            addEdgeSuccess = false;
            // Return the addEdgeSuccess variable to stop the method
            return addEdgeSuccess;
        }
        // Otherwise, let the method continue as normal
        else {
            ;
        }
        // Check if Node TO is null. If it is, no new edge can be added and the method stops early and returns false
        if (connectEdgeToThisNode == null) {
            // Sets the addEdgeSuccess variable to false in order to indicate that no new edge can be added
            addEdgeSuccess = false;
            // Return the addEdgeSuccess variable to stop the method
            return addEdgeSuccess;
        }
        // Otherwise, let the method continue as normal
        else {
            ;
        }
        // A variable that stores whether the Node FROM has the same neighbors as the Node TO
        boolean isFromNodeHasSameNeighborsAsToNode = addEdgeFromThisNode.getNeighborsList().contains(connectEdgeToThisNode);
        /*
         * If the Node FROM has the same neighbors as the Node TO, then no new edge will be added and the method returns
         * FALSE.
         */
        if (isFromNodeHasSameNeighborsAsToNode == true) {
            // Sets the addEdgeSuccess variable to false in order to indicate that no new edge can be added
            addEdgeSuccess = false;
            // Return the addEdgeSuccess variable to stop the method
            return addEdgeSuccess;
        }
        // Otherwise, let the method continue as normal
        else {
            ;
        }
        // Add the Node TO to Node FROM's list of neighbors in order to connect them together - establishing an 'Edge'
        addEdgeFromThisNode.getNeighborsList().add(connectEdgeToThisNode);
        // Add the Node FROM to Node TO's list of neighbors in order to connect them together
        connectEdgeToThisNode.getNeighborsList().add(addEdgeFromThisNode);
        // Sets the addEdgeSuccess variable to true in order to indicate that an edge has been successfully added
        addEdgeSuccess = true;
        // Return the addEdgeSuccess variable in order to stop the method
        return addEdgeSuccess;
    }

    /**
     * A method that adds an undirected edge from Node FROM to each and all nodes in the given toList. It will connect
     * Node FROM to each node in toList together, and each pair of nodes will also become neighbors of each other.
     * Please Note: Duplicated edges are not allowed, and they will not be added.
     *
     * @param from Any node to add edges from.
     * @param toList The list of nodes to add edges to.
     * @return True or False depending on whether the new edges have been successfully added to the graph or not.
     */
    public boolean addEdges(K from, K... toList) {
        // If node from is null, no further operation can be appropriately done with it, return false
        if (from == null) {
            return false;
        }
        // Otherwise, let the method continue normally
        else {
            ;
        }
        // A variable that keeps track of whether the new edges are successfully added or not
        boolean addEdgesSuccess = true;
        // A loop that adds each edge from Node FROM to each node in toList
        for (K nodeToAddEdgeTo : toList) {
            // Checks if the nodeToAddEdgeTo is null, it will be skipped as no operations can be done with it
            if (nodeToAddEdgeTo == null) {
                continue; // Skip the null key
            }
            // Otherwise, let the method continue normally
            else {
                ;
            }
            // Variable that stores whether each pair of nodes has been successfully connected or not
            boolean ifAddThisEdgeSuccessful = addEdge(from, nodeToAddEdgeTo);
            /*
             * Updates the addEdgeSuccess variable to the value of the comparison between itself and the variable
             * ifAddThisEdgeSuccessful in order to keep track of whether the new edge has been successfully added to the
             * graph or not.
             */
            addEdgesSuccess = addEdgesSuccess && ifAddThisEdgeSuccessful;
        }
        // Return the addEdgeSuccess variable in order to stop the method
        return addEdgesSuccess;
    }

    /**
     * A method that removes a node from the graph along with all connected edges. It will take the name of the node to
     * be removed and removes it from the graph.
     *
     * @param name The name of the node to be removed
     * @return True or False depending on whether the node have been successfully removed from the graph or not.
     */
    public boolean removeNode(K name) {
        // Checks if the given node name is null and stop the method early in this case to avoid NullPointerExceptions
        if (name == null) {
            return false; // Return false if the key is null
        }
        // Otherwise, let the method continue normally
        else {
            ;
        }
        // A variable that stores the node to be removed from the graph with the specified name
        GraphNode nodeToRemove = findNodeWithName(name);
        // A variable to store whether the specified node has been successfully removed or not
        boolean removeNodeSuccess = false;
        // Checks if the node to be removed is NULL or not. If it is, it can't be removed and the method returns false.
        if (nodeToRemove == null) {
            // Sets the removeNodeSuccess variable to false in order to indicate that the specified node cannot be removed
            removeNodeSuccess = false;
            // Return the removeNodeSuccess variable to end the method
            return removeNodeSuccess;
        }
        // Otherwise, let the method continue as normal
        else {
            ;
        }
        // A variable that stores the length of the neighbors list of the node to be removed
        int lengthOfNodeNeighbors = nodeToRemove.getNeighborsList().size();
        // A loop that iterates through the node to be removed's list of neighbors to remove all of its connected edges
        for (int index = 0; index < lengthOfNodeNeighbors; index = index + 1) {
            // A variable that stores each neighbor of the neighbors list of the node to be removed
            GraphNode everyNeighborOfRemovedNode = nodeToRemove.getNeighborsList().get(index);
            // Remove the node to be removed from the neighbor's list of neighbors to remove the connection between them
            everyNeighborOfRemovedNode.getNeighborsList().remove(nodeToRemove);
        }
        // Remove the node to be removed from the graph
        this.listOfGraphNodes.remove(nodeToRemove);
        // Sets the removeNodeSuccess to true to indicate that the node to be removed has been successfully removed
        removeNodeSuccess = true;
        // Returns the removeNodeSuccess in order to stop the method
        return removeNodeSuccess;
    }

    /**
     * A method that removes each node in nodelist and all of their associated edges from this graph.
     *
     * @param nodelist The list containing all nodes to be removed from this graph.
     * @return True or False depending on whether all nodes in nodelist have been successfully removed or not.
     */
    public boolean removeNodes(K... nodelist) {
        // A variable to store whether all nodes in nodelist have been successfully removed or not.
        boolean removeNodesSuccess = true;
        // A loop that removes each node in nodelist from this graph
        for (K everyNodeToBeRemoved : nodelist) {
            // A variable that stores the current node in the list that will be removed from this graph
            boolean ifRemoveThisNodeSuccessful = removeNode(everyNodeToBeRemoved);
            /*
             * Updates the removeNodesSuccess variable to the value of the comparison between itself and the variable
             * ifRemoveThisNodeSuccessful in order to keep track of whether the new node has been successfully removed
             * from this graph or not.
             */
            removeNodesSuccess = removeNodesSuccess && ifRemoveThisNodeSuccessful;
        }
        // Return the removeNodesSuccess variable in order to stop the method
        return removeNodesSuccess;
    }

    /**
     * A method that prints the graph in this adjacency list format: (nodename1) (neighbor1) (neighbor2) (neighbor3).
     * The nodes and their neighbors will also be listed in ALPHABETICAL ORDER. If there is any value associated with
     * this node, it will also be printed next to nodename1, like this: (nodename1) (nodevalue1) (neighbor1) (neighbor2)
     * (neighbor3), and so on.
     */
    public void printGraph() {
        // Sort the list of graph nodes based on their key's string representations (ascending order)
        this.listOfGraphNodes.sort(new Comparator<GraphNode>() {
            // A method that compare each pair of nodes with each other - Override the inherited method from Comparator:
            @Override
            public int compare(GraphNode node1, GraphNode node2) {
                // A variable that stores node 1's key in its string representation
                String node1Key = node1.getKey().toString();
                // A variable that stores node 2's key in its string representation
                String node2Key = node2.getKey().toString();
                /*
                 * A variable that stores the comparison results of node 1's key and node 2's key in their string
                 * representations:
                 */
                int comparisonResult = node1Key.compareTo(node2Key);
                // Returns comparisonResult variable to end the method early
                return comparisonResult;
            }
        });
        // A loop that iterates through each node in the list of graph nodes
        for (int index1 = 0; index1 < this.listOfGraphNodes.size(); index1 = index1 + 1) {
            // A variable that stores every node in the list of graph nodes of this Graph
            GraphNode everyNodeInGraph = this.listOfGraphNodes.get(index1);
            // A variable that stores the keys of every node in the list of graph nodes of this Graph
            K keyOfEveryNode = everyNodeInGraph.getKey();
            // Print the node's key to System.out and, if available, its value
            System.out.print(keyOfEveryNode);
            // A variable that stores every node in the graph's value in order to print it to System.out later
            if (everyNodeInGraph.getValue() != null) {
                // Print every node in the graph's value to System.out
                System.out.print("(" + everyNodeInGraph.getValue() + ")");
            }
            // Otherwise, let the method continue as normal
            else {
                ;
            }
            // Print the neighbors of each node in the graph to System.out
            System.out.print(" ");
            // Now, sort the neighbors of the current node based on their key's string representation
            everyNodeInGraph.getNeighborsList().sort(new Comparator<GraphNode>() {
                /*
                 * A method that compare each pair of neighbor nodes of this node with each other - Override the
                 * inherited method from Comparator:
                 */
                @Override
                public int compare(GraphNode neighbor1, GraphNode neighbor2) {
                    // A variable that stores node 1's neighbor's key in its string representation
                    String neighbor1Key = neighbor1.getKey().toString();
                    // A variable that stores node 2's neighbor's key in its string representation
                    String neighbor2Key = neighbor2.getKey().toString();
                    /*
                     * A variable that stores the comparison results of node 1's neighbor's key and node 2's neighbor's
                     * key in their string representations:
                     */
                    int comparisonResult = neighbor1Key.compareTo(neighbor2Key);
                    // Returns comparisonResult variable to end the method early
                    return comparisonResult;
                }
            });
            // A loop that iterates through each neighbor of the current node and print their key to System.out
            for (int index2 = 0; index2 < everyNodeInGraph.getNeighborsList().size(); index2 = index2 + 1) {
                GraphNode everyNeighbor = everyNodeInGraph.getNeighborsList().get(index2);
                // Print all the neighbors' their key to System.out
                System.out.print(everyNeighbor.getKey() + " ");
            }
            // Print a new line before moving to the next node to give the command output some space
            System.out.println();
        }
    }

    /**
     * A method that constructs a graph from a text file using the format: (nodename1) (neighbor1) (neighbor2) ... and
     * (nodename2) (neighbor1) (neighbor2) ... Names of node can be any alphanumeric strings using white spaces as
     * separators. It is able to construct more complex graphs with a simple specification from a text file.
     *
     * @param filename The specified name of file from which a new graph will be constructed.
     * @return A new graph that has its key of type String and its value of any type that is constructed from the given
     * file.
     * @param <V> Any type V for the value of the graph to be created.
     */
    public static <V> Graph<String, V> read(String filename) {
        // Create a new empty graph to construct from the given, specified file
        Graph<String, V> graphToBeConstructed = new Graph<String, V>();
        // Try-with-resources to ensure the BufferedReader (and its FileReader) are closed
        try (FileReader fileReaderForGivenFileName = new FileReader(filename);
             BufferedReader bufferedReaderForGivenFileName = new BufferedReader(fileReaderForGivenFileName)) {
            // A variable to store each line in the file
            String everySingleLineInFile = new String();
            // A loop that loops through all lines in the file to read them and construct nodes in the graph from them
            while ((everySingleLineInFile = bufferedReaderForGivenFileName.readLine()) != null) {
                /*
                 * Split the line into an array of strings using whitespace as the delimiter and store it in a variable
                 * of type String
                 */
                String[] splitLines = everySingleLineInFile.split("\\s+");
                /*
                 * A variable that stores the node from the first element of the array - this node will be the node to
                 * be added to graph later
                 */
                String nodeFromSplitLine = splitLines[0];
                // Add the node to the graph with a null value
                graphToBeConstructed.addNode(nodeFromSplitLine, null);
                // A loop that iterates through the remaining elements in the array, which represent neighbors
                for (int index = 1; index < splitLines.length; index = index + 1) {
                    // A string variable that stores the neighbor's name
                    String neighborOfNodeToBeAdded = splitLines[index];
                    // Add an edge between the current node and its neighbor
                    graphToBeConstructed.addEdge(nodeFromSplitLine, neighborOfNodeToBeAdded);
                }
            }
        }
        // If any IOException is caught, print its message to System.out
        catch (IOException exception) {
            // If there's an error reading the file, print error messages to System.out
            System.out.println("Unfortunately, there is an error reading this file: " + exception.getMessage());
            // Prompts the user to re-check their desired filename (file must be in the same directory as the project)
            System.out.println("Please check your filename!");
        }
        // Return the constructed graph from the given file
        return graphToBeConstructed;
    }

    /**
     * A method that finds and returns the path (i.e. a list of node names) of depth-first search between node FROM and
     * node TO. It will return an empty array if no path exists between the two given nodes. The first entry of the
     * returned array will be the FROM node and the last value of the array will be the TO node.
     *
     * @param from A node from which the path will be found using DFS.
     * @param to A node to which the path will be found using DFS.
     * @return A path between node FROM and node TO found using DFS.
     */
    public K[] DFS(K from, K to) {
        // A node from which the path will be found using DFS
        GraphNode nodeToPerformDFSFrom = findNodeWithName(from);
        // A node to which the path will be found using DFS
        GraphNode nodeToPerformDFSUntil = findNodeWithName(to);
        // If node FROM is null or does not exist in the graph, returns an empty list or array as no path can be found with it
        if (nodeToPerformDFSFrom == null) {
            // A variable that stores the return array which will be empty in this case
            Object[] returnArray = new Object[0];
            // A variable that stores the above array but was casted to the type of K
            K[] castedReturnArray = (K[]) returnArray;
            // Returns the casted return array as above
            return castedReturnArray;
        }
        // Otherwise, let the method continue as normal
        else {
            ;
        }
        // If node TO is null or does not exist in the graph, returns an empty list or array as no path can be found with it
        if (nodeToPerformDFSUntil == null) {
            // A variable that stores the return array which will be empty in this case
            Object[] returnArray = new Object[0];
            // A variable that stores the above array but was casted to the type of K
            K[] castedReturnArray = (K[]) returnArray;
            // Returns the casted return array as above
            return castedReturnArray;
        }
        // Otherwise, let the method continue as normal
        else {
            ;
        }
        /*
         * A list that will store the path that will be the result of this DFS method.
         * It will call another helper method to help find the DFS path.
         */
        List<K> pathOfDFS = performDFSearch(nodeToPerformDFSFrom, nodeToPerformDFSUntil, new ArrayList<GraphNode>());
        // If there is no such path between the two given nodes FROM and TO, return an empty list (i.e. path)
        if (pathOfDFS == null) {
            // A variable that stores the return array which will be empty in this case
            Object[] returnArray = new Object[0];
            // A variable that stores the above array but was casted to the type of K
            K[] castedReturnArray = (K[]) returnArray;
            // Returns the casted return array as above
            return castedReturnArray;
        }
        // Otherwise, let the method continue as normal
        else {
            ;
        }
        // A variable that stores the return array - the path between the two given nodes, found with DFS
        Object[] pathOfDFSInNodes = pathOfDFS.toArray(new Object[0]);
        // A variable that stores the above array but was casted to the type of K
        K[] castedPath = (K[]) pathOfDFSInNodes;
        // Returns the casted return array as above
        return castedPath;
    }

    /**
     * A method that finds and returns the path (i.e. a list of node names) of breadth-first search between node FROM
     * and node TO. It will return an empty array if no path exists between the two given nodes. The first entry of the
     * returned array will be the FROM node and the last value of the array will be the TO node.
     *
     * @param from A node from which the path will be found using BFS.
     * @param to A node to which the path will be found using BFS
     * @return A path between node FROM and node TO found using BFS.
     */
    public K[] BFS(K from, K to) {
        // A node from which the path will be found using BFS
        GraphNode nodeToPerformBFSFrom = findNodeWithName(from);
        // A node to which the path will be found using BFS
        GraphNode nodeToPerformBFSTo = findNodeWithName(to);
        // If node FROM is null or does not exist in the graph, returns an empty list or array as no path can be found with it
        if (nodeToPerformBFSFrom == null) {
            // A variable that stores the return array which will be empty in this case
            Object[] returnArray = new Object[0];
            // A variable that stores the above array but was casted to the type of K
            K[] castedReturnArray = (K[]) returnArray;
            // Returns the casted return array as above
            return castedReturnArray;
        }
        // Otherwise, let the method continue as normal
        else {
            ;
        }
        // If node TO is null or does not exist in the graph, returns an empty list or array as no path can be found with it
        if (nodeToPerformBFSTo == null) {
            // A variable that stores the return array which will be empty in this case
            Object[] returnArray = new Object[0];
            // A variable that stores the above array but was casted to the type of K
            K[] castedReturnArray = (K[]) returnArray;
            // Returns the casted return array as above
            return castedReturnArray;
        }
        // Otherwise, let the method continue as normal
        else {
            ;
        }
        /*
         * A list that will store the path that will be the result of this BFS method.
         * It will call another helper method to help find and determine the BFS path.
         */
        List<K> pathOfBFS = performBFSearch(nodeToPerformBFSFrom, nodeToPerformBFSTo);
        // If there is no such path between the two given nodes FROM and TO, return an empty list (i.e. path)
        if (pathOfBFS == null) {
            // A variable that stores the return array which will be empty in this case
            Object[] returnArray = new Object[0];
            // A variable that stores the above array but was casted to the type of K
            K[] castedReturnArray = (K[]) returnArray;
            // Returns the casted return array as above
            return castedReturnArray;
        }
        // Otherwise, let the method continue as normal
        else {
            ;
        }
        // A variable that stores the return array - the path between the two given nodes, found with BFS
        Object[] pathOfBFSInNodes = pathOfBFS.toArray(new Object[0]);
        // A variable that stores the above array but was casted to the type of K
        K[] castedPath = (K[]) pathOfBFSInNodes;
        // Returns the casted return array as above
        return castedPath;
    }

    /**
     * A private class that represents and creates a node in the graph. Each node will have its key (i.e. name) and
     * value (i.e. data).
     *
     * @author David Nguyen
     * @since 04/30/2023
     * @version 1.0
     */
    private class GraphNode {

        // A field that stores this graph node's key (or name)
        K key;

        // A field that stores this graph node's value (or data)
        V value;

        // A field that stores a list of this graph node's edges (or its neighbors)
        List<GraphNode> neighborsOfThisNode;

        /**
         * A constructor that creates this instance of GraphNode that takes in a key (or name) and value (or data).
         *
         * @param key Any key (or name) to create a new GraphNode with.
         * @param value Any value (or data) to create a new GraphNode with.
         */
        public GraphNode(K key, V value) {
            // Initializes all necessary fields:
            this.key = key;
            this.value = value;
            this.neighborsOfThisNode = new ArrayList<GraphNode>();
        }

        /**
         * A getter method for the key (or name) of this GraphNode instance.
         *
         * @return The key (or name) of this GraphNode instance.
         */
        private K getKey() {
            return this.key;
        }

        /**
         * A getter method for the value (or data) of this GraphNode instance.
         *
         * @return The value (or data) of this GraphNode instance.
         */
        private V getValue() {
            return this.value;
        }

        /**
         * A getter method for the list of this GraphNode instance's neighbors.
         *
         * @return The list of this GraphNode instance's neighbors.
         */
        private List<GraphNode> getNeighborsList() {
            return this.neighborsOfThisNode;
        }

    }

    /**
     * A recursive helper method that helps perform DFS search and find its path in this Graph instance.
     *
     * @param beginningNode The node from which DFS will be performed.
     * @param endingNode The node to which DFS will be performed.
     * @param visitedNodes The list of nodes that are visited along the finding of the path.
     * @return The path found between beginningNode and endingNode using DFS.
     */
    private List<K> performDFSearch(GraphNode beginningNode, GraphNode endingNode, List<GraphNode> visitedNodes) {
        // A variable that checks if the beginning node is the same as the ending node
        boolean checkIfBeginningEqualsEndingNode = beginningNode.equals(endingNode);
        // If they are equal to each other, it is the BASE CASE. Here, stops the recursion:
        if (checkIfBeginningEqualsEndingNode == true) {
            // A variable that stores the list that is also the path found by DFS from the beginningNode to the endingNode
            List<K> pathFromBeginningToEnd = new ArrayList<K>();
            // A variable that stores the key of the beginning node
            K keyOfBeginningNode = beginningNode.getKey();
            // Add the key to the path
            pathFromBeginningToEnd.add(keyOfBeginningNode);
            // Then, return the path having only one key here in the base case
            return pathFromBeginningToEnd;
        }
        // Otherwise, let the method continue as normal
        else {
            ;
        }
        // Add the beginningNode to the list of visited nodes
        visitedNodes.add(beginningNode);
        // A loop that iterates through the beginningNode's neighbors list to perform DFS with:
        for (int index = 0; index < beginningNode.getNeighborsList().size(); index = index + 1) {
            // A list that stores the list of neighbors of the beginningNode
            List<GraphNode> neighborsOfBeginningNodeList = beginningNode.getNeighborsList();
            // A variable that stores a neighbor of the above list of neighbors
            GraphNode neighborOfBeginningNode = neighborsOfBeginningNodeList.get(index);
            // If the neighbor node above is NOT already visited, then perform DFS to find a path to it:
            if (visitedNodes.contains(neighborOfBeginningNode) == false) {
                /*
                 * A list that recursively calls this performDFSearch to find the path from the neighbor node above to
                 * the ending node, with the visitedNodes also being accounted for:
                 */
                List<K> pathFromBeginningToEnd = performDFSearch(neighborOfBeginningNode, endingNode, visitedNodes);
                /*
                 * If the path cannot be found or does not exist, add the key of the beginning node to the path and stops
                 * the method:
                 */
                if (pathFromBeginningToEnd != null) {
                    // A variable that stores the key of the beginning node
                    K keyOfBeginningNode = beginningNode.getKey();
                    // Add the key of the beginning node to the path above
                    pathFromBeginningToEnd.add(0, keyOfBeginningNode);
                    // Returns the path and stops the method and ends this instance of recursion
                    return pathFromBeginningToEnd;
                }
                // Otherwise, let the method continue as normal
                else {
                    ;
                }
            }
            // Otherwise, do not find a DFS path to the neighbor node:
            else {
                ;
            }
        }
        // At the end, if no such path can be found between beginning and ending nodes, returns a null and empty list
        List<K> returnNullList = null;
        // Returns a null and empty list in this case
        return returnNullList;
    }

    /**
     * A recursive helper method that helps perform BFS search and find the path between the beginning node and the
     * ending node in this Graph instance.
     *
     * @param beginningNode The node from which DFS will be performed.
     * @param endingNode The node to which DFS will be performed.
     * @return The path found between beginningNode and endingNode using BFS.
     */
    private List<K> performBFSearch(GraphNode beginningNode, GraphNode endingNode) {
        // A list that stores another list of BFS possible search paths between the beginning and ending nodes
        List<List<GraphNode>> listOfBFSearchPaths = new ArrayList<List<GraphNode>>();
        // A list that stores all the visited nodes
        List<GraphNode> listOfVisitedNodes = new ArrayList<GraphNode>();
        // A list that stores the first, initial path that will be considered for BFS search
        List<GraphNode> firstPathForBFSearch = new ArrayList<GraphNode>();
        // Add the beginning node to the first, initial path above
        firstPathForBFSearch.add(beginningNode);
        // Also add the first, initial path above to the list of possible BFS search paths
        listOfBFSearchPaths.add(firstPathForBFSearch);
        // A loop that iterates through the list of BFS search path and do the following:
        while (listOfBFSearchPaths.isEmpty() == false) {
            // A variable that stores a second, alternative path for BFSearch
            List<GraphNode> alternativePathForBFSearch = listOfBFSearchPaths.get(0);
            // Remove the first path in the list of BFS search paths
            listOfBFSearchPaths.remove(0);
            // A variable that stores the current node in the graph
            GraphNode currentGraphNode = alternativePathForBFSearch.get(alternativePathForBFSearch.size() - 1);
            // A variable that checks whether the current node is the ending node
            boolean ifCurrentNodeIsEndingNode = currentGraphNode.equals(endingNode);
            // If the current node is the node ending node:
            if (ifCurrentNodeIsEndingNode == true) {
                // A list that stores the final path of the BFS search from the beginning to the ending nodes
                List<K> finalPathForBFSearch = new ArrayList<K>();
                // A loop that iterates through the alternative path of the BFS to determine and find the final path for BFS
                for (int index = 0; index < alternativePathForBFSearch.size(); index = index + 1) {
                    // A variable that stores the final nodes from the above alternative path to be returned later on
                    GraphNode finalNodeInPath = alternativePathForBFSearch.get(index);
                    /*
                     * A variable that stores the key of the final nodes from the above alternative path to be returned
                     * later on
                     */
                    K keyOfFinalNodeInPath = finalNodeInPath.getKey();
                    // Add the key of the final nodes in the final path to the final path that will be returned later on
                    finalPathForBFSearch.add(keyOfFinalNodeInPath);
                }
                // A list that stores the final path for BFS search to be returned now
                List<K> finalPathForReturn = finalPathForBFSearch;
                // Return the above list to stop the method
                return finalPathForReturn;
            }
            // Otherwise, let the method continue as normal
            else {
                ;
            }
            /*
             * However, if the current node has not been visited, add it to the list of visited nodes and add it to
             * the lists of visited nodes and possible BFS paths above.
             */
            boolean ifCurrentNodeIsVisited = listOfVisitedNodes.contains(currentGraphNode);
            /*
             * If the current node is NOT visited, add it to the list of visited nodes and add the path to it to the list
             * of all possible BFS paths above.
             */
            if (ifCurrentNodeIsVisited == false) {
                // Add the current node to the list of visited nodes
                listOfVisitedNodes.add(currentGraphNode);
                // A variable that stores the size of the current node's neighbors list
                int sizeOfCurrentNodeNeighborsList = currentGraphNode.getNeighborsList().size();
                // A loop that iterates through the list of the neighbors of the current node
                for (int index = 0; index < sizeOfCurrentNodeNeighborsList; index = index + 1) {
                    // A variable that stores each and every neighbor of the current node
                    GraphNode neighborOfCurrentNode = currentGraphNode.getNeighborsList().get(index);
                    // If the neighbor has not been visited, add the path to it to the list of the possible BFS paths
                    if (listOfVisitedNodes.contains(neighborOfCurrentNode) == false) {
                        // A variable that stores the path from the current node to its neighbor nodes
                        List<GraphNode> pathToNeighborNodes = new ArrayList<GraphNode>(alternativePathForBFSearch);
                        // Add the neighbor of the current node to the list (or path) above
                        pathToNeighborNodes.add(neighborOfCurrentNode);
                        // Add the path to the neighbor nodes above to the list of all possible search paths of BFS
                        listOfBFSearchPaths.add(pathToNeighborNodes);
                    }
                    // Otherwise, let the method continue as normal
                    else {
                        ;
                    }
                }
            }
            // Otherwise, let the method continue as normal
            else {
                ;
            }
        }
        // At the end, if no such path can be found between beginning and ending nodes, returns a null and empty list
        ArrayList<K> returnVal = null;
        // Returns a null and empty list in this case
        return returnVal;
    }

    /**
     * A method that helps find the node in this Graph instance with the given, specified name. It iterates through the
     * graph's list of nodes in order to find it.
     *
     * @param name Any name to find the node in this Graph instance with.
     * @return The node in this Graph instance that has the given, specified name.
     */
    private GraphNode findNodeWithName(K name) {
        // A loop that iterates through the list of nodes in this Graph instance to find the exact node with the given name
        for (int index = 0; index < this.listOfGraphNodes.size(); index = index + 1) {
            // A variable that stores every node in this graph during the loop
            GraphNode everyNodeInGraph = this.listOfGraphNodes.get(index);
            // A variable that stores the key of the above node
            K keyOfEveryNodeInGraph = everyNodeInGraph.getKey();
            // If the above, current node has its key the same as the given, specified key, return it
            if (keyOfEveryNodeInGraph.equals(name) == true) {
                // Return the node that has its key the same as the given, specified key
                return everyNodeInGraph;
            }
            // Otherwise, let the method continue as normal
            else {
                ;
            }
        }
        /*
         * A variable that stores the return value of null for this method if no nodes with the given name is found in
         * this graph instance
         */
        GraphNode returnVal = null;
        // Return the null variable above in order to end the method
        return returnVal;
    }

}
