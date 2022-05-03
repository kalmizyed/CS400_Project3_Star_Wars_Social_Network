// --== CS400 Project Three File Header ==--
// Name: Joseph Cai
// CSL Username: josephc
// Email: jbcai@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader:

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

public class DataWranglerTests {

    /**
     * Tests the number of characters (vertices) in the graph
     * @throws IOException
     */
    @Test
    public void testNumberCharacters() throws IOException{
        List<String> characters = new GraphBuilder().getCharacters();
        assertEquals(characters.size(), 112);
    }

    /**
     * Tests the number of interactions (edges) in the graph
     * @throws IOException
     */
    @Test
    public void testNumberInteractions() throws IOException{
        List<int[]> interactions = new GraphBuilder().getInteractions();
        assertEquals(interactions.size(), 450);
    }

    /**
     * Tests if the graph contains specific characters
     */
    @Test
    public void testGraphContainsCharacters(){
        ExtendedGraphADT<String> graph = new GraphBuilder().getGraph();
        assertTrue(graph.containsVertex("DARTH VADER"));
        assertTrue(graph.containsVertex("LEIA"));
        assertTrue(graph.containsVertex("JAR JAR"));
    }

    /**
     * Tests if the graph contains specific interactions
     */
    @Test
    public void testGraphContainsEdges(){
        ExtendedGraphADT<String> graph = new GraphBuilder().getGraph();
        assertTrue(graph.containsEdge("LUKE","DARTH VADER"));
        assertTrue(graph.containsEdge("JAR JAR","QUI-GON"));
        assertTrue(graph.containsEdge("ANAKIN","MACE WINDU"));
    }

    /**
     * Tests if the graph has the correct number of characters and interactions
     */
    @Test
    public void testGraphSizes(){
        ExtendedGraphADT<String> graph = new GraphBuilder().getGraph();
        assertEquals(graph.getVertexCount(),112);
        assertEquals(graph.getEdgeCount(),900);
        assertEquals(graph.getPathCost("LUKE","DARTH VADER"), 1);
    }

    /*********************
     * INTEGRATION TESTS *
     *********************/

    /**
     * Tests if the backend trie contains specific characters
     */
    @Test
    public void testBackendLoadCharacters(){
        StarWarsSocialNetworkBackend backend = new StarWarsSocialNetworkBackend(new GraphBuilder());
        assertEquals(backend.getCharacters().size(), 112);
        assertTrue(backend.getCharacters().contains("HAN"));
        assertTrue(backend.getCharacters().contains("LUKE"));
        assertTrue(backend.getCharacters().contains("LEIA"));
        assertTrue(backend.getCharacters().contains("JAR JAR"));
        assertFalse(backend.getCharacters().contains("HAN SOLO"));
    }

    /**
     * Tests if the backend get prefix is correct and all the characters are loaded in
     */
    @Test
    public void testBackendTrie(){
        StarWarsSocialNetworkBackend backend = new StarWarsSocialNetworkBackend(new GraphBuilder());
        assertEquals(backend.getCharacters("A").size(), 3);
        assertEquals(backend.getCharacters("CAPTAIN").size(), 4);
        assertEquals(backend.getCharacters("DARTH").size(), 2);
    }

    /*********************
     * TESTS FOR BACKEND *
     *********************/

    /**
     * Tests if Trie getWords is correct
     */
    @Test
    public void testTrieGetWords(){
        Trie trie = new Trie();
        trie.add("ABCD");
        trie.add("AB");
        trie.add("ABCDE");
        trie.add("ACDEF");
        assertEquals(trie.getWords().size(), 4);
        assertEquals(trie.getWords("AB").size(), 3);
    }

    /**
     * Tests if Trie contains is correct
     */
    @Test
    public void testTrieContains(){
        Trie trie = new Trie();
        trie.add("ABCD");
        trie.add("AB");
        trie.add("ABCDE");
        trie.add("ACDEF");
        assertTrue(trie.contains("AB"));
        assertFalse(trie.contains("ABC"));
        assertTrue(trie.contains("ABCD"));
    }
}
