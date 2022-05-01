// --== CS400 Project Three File Header ==--
// Name: Kaden Almizyed
// CSL Username: kaden
// Email: kalmizyed@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader:


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

/**
 * Tests for the Backend Developer.
 * Contains unit tests for the Trie and StarWarsSocialNetworkBackend classes.
 */
public class BackendDeveloperTests {
    
    /**************/
    /* UNIT TESTS */
    /**************/

    /**
     * Tests that the Trie.indexOfChar() method returns the correct values.
     */
    @Test
    public void testTrieCharIndex() {
        assertEquals(0, TrieNode.indexOfChar('A'));
        assertEquals(25, TrieNode.indexOfChar('Z'));
        assertEquals(26, TrieNode.indexOfChar(' '));
        assertEquals(27, TrieNode.indexOfChar('-'));
        assertEquals(28, TrieNode.indexOfChar('0'));
        assertEquals(37, TrieNode.indexOfChar('9'));
    }

    /**
     * Tests that the Trie.getChild() method returns the correct values.
     */
    @Test
    public void testTrieGetChild() {
        TrieNode root = new TrieNode();
        root.children[0] = new TrieNode('A');
        TrieNode aChild = root.getChild('A');
        aChild.setWord(true);

        // Child should contain the character used to get it
        assertTrue(aChild.character == 'A');

        // New children shouldn't be words
        assertTrue(aChild.isWord);

        
    }

    /**
     * Tests that the Trie.getChild() method returns the correct values when creating a new child.
     */
    @Test
    public void testTrieGetChildNew() {
        TrieNode root = new TrieNode();

        // Getting child that doesn't exist should create a new node
        TrieNode aChild = root.getChild('A');
        assertTrue(aChild != null);

        // Child should contain the character used to get it
        assertTrue(aChild.character == 'A');

        // New children shouldn't be words
        assertFalse(aChild.isWord);

        // All children of the new child should be empty
        for (TrieNode grandchild : aChild.children) {
            assertTrue(grandchild == null);
        }
    }

    /**
     * Tests the Trie.indexOfChar() and Trie.getChild() methods with invalid characters.
     */
    @Test
    public void testTrieIllegalChar() {
        try {
            // indexOfChar()
            TrieNode.indexOfChar('a');
            TrieNode.indexOfChar('+');

            // getChild()
            new TrieNode().getChild('a');
            new TrieNode().getChild('+');
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    /**
     * Tests the Trie.add() method.
     */
    @Test
    public void testTrieAdd() {
        Trie trie = new Trie();
        trie.add("TEST");
        assertTrue(trie.getNode("TEST").isWord);
    }

    /**
     * Tests the Trie.getWords() method.
     */
    @Test
    public void testTrieGetWords() {
        Trie trie = new Trie();
        trie.add("TEST");
        trie.add("TEST2");
        trie.add("NOTATEST");

        List<String> words = trie.getWords();
        assertTrue(words.contains("TEST"));
        assertTrue(words.contains("TEST2"));
        assertTrue(words.contains("NOTATEST"));

        List<String> wordsPrefix = trie.getWords("TEST");
        assertTrue(wordsPrefix.contains("TEST"));
        assertTrue(wordsPrefix.contains("TEST2"));
        assertFalse(wordsPrefix.contains("NOTATEST"));
    }

    // Used for the following Backend tests
    StarWarsSocialNetworkBackend backend = new StarWarsSocialNetworkBackend(new GraphBuilderPlaceholderBD());

    /**
     * Tests the backend getPathsBetween() method using a placeholder map.
     */
    @Test
    public void testBackendPathsBetween() {
        List<List<String>> paths = backend.getPathsBetween("JAR JAR BINKS", "PRINCESS LEIA");

        assertEquals(2, paths.size());
        assertEquals(4, paths.get(0).size());
        assertTrue(paths.get(0).get(0).equals("JAR JAR BINKS"));
        assertTrue(paths.get(0).get(3).equals("PRINCESS LEIA"));
    }

    /**
     * Tests the backend loadCharacters() method using a placeholder map.
     */
    @Test
    public void testBackendLoadCharacters() {
        Trie characters = backend.loadCharacters();

        assertTrue(characters.contains("HAN SOLO"));
        assertTrue(characters.contains("PRINCESS LEIA"));
        assertTrue(characters.contains("LUKE SKYWALKER"));
        assertTrue(characters.contains("DARTH VADER"));
        assertTrue(characters.contains("JAR JAR BINKS"));
        assertTrue(characters.contains("OBI WAN KENOBI"));
    }

    /**
     * Tests the backend getCharacters() methods using a placeholder map.
     */
    @Test
    public void testBackendGetCharacters() {
        List<String> characters = backend.getCharacters();
        List<String> charactersPrefix = backend.getCharacters("HAN");

        assertEquals(6, characters.size());
        assertTrue(characters.contains("HAN SOLO"));
        assertTrue(characters.contains("PRINCESS LEIA"));
        assertTrue(characters.contains("LUKE SKYWALKER"));
        assertTrue(characters.contains("DARTH VADER"));
        assertTrue(characters.contains("JAR JAR BINKS"));
        assertTrue(characters.contains("OBI WAN KENOBI"));

        assertEquals(1, charactersPrefix.size());
        assertTrue(charactersPrefix.contains("HAN SOLO"));
        assertFalse(charactersPrefix.contains("PRINCESS LEIA"));
        assertFalse(charactersPrefix.contains("LUKE SKYWALKER"));
        assertFalse(charactersPrefix.contains("DARTH VADER"));
        assertFalse(charactersPrefix.contains("JAR JAR BINKS"));
        assertFalse(charactersPrefix.contains("OBI WAN KENOBI"));
    }


    /*********************/
    /* INTEGRATION TESTS */
    /*********************/

    /**
     * Tests the backend loadCharacters() method.
     */
    @Test
    public void testBackendLoadCharactersIntegrated() {
        StarWarsSocialNetworkBackend backendIntegrated = new StarWarsSocialNetworkBackend(new GraphBuilder());
        Trie characters = backendIntegrated.loadCharacters();

        assertTrue(characters.contains("HAN"));
        assertTrue(characters.contains("LEIA"));
        assertTrue(characters.contains("LUKE"));
        assertTrue(characters.contains("DARTH VADER"));
        assertTrue(characters.contains("JAR JAR"));
        assertTrue(characters.contains("OBI-WAN"));
    }

    /**
     * Tests the backend getCharacters() methods.
     */
    @Test
    public void testBackendGetCharactersIntegrated() {
        StarWarsSocialNetworkBackend backendIntegrated = new StarWarsSocialNetworkBackend(new GraphBuilder());
        List<String> characters = backendIntegrated.getCharacters();
        List<String> charactersPrefix = backendIntegrated.getCharacters("HAN");

        assertEquals(6, characters.size());
        assertTrue(characters.contains("HAN"));
        assertTrue(characters.contains("LEIA"));
        assertTrue(characters.contains("LUKE"));
        assertTrue(characters.contains("DARTH VADER"));
        assertTrue(characters.contains("JAR JAR"));
        assertTrue(characters.contains("OBI-WAN"));

        assertEquals(1, charactersPrefix.size());
        assertTrue(charactersPrefix.contains("HAN"));
        assertFalse(charactersPrefix.contains("LEIA"));
        assertFalse(charactersPrefix.contains("LUKE"));
        assertFalse(charactersPrefix.contains("DARTH VADER"));
        assertFalse(charactersPrefix.contains("JAR JAR"));
        assertFalse(charactersPrefix.contains("OBI-WAN"));
    }



    /*********************/
    /* CODE REVIEW TESTS */
    /*********************/

    GraphBuilder builder = new GraphBuilder();

    /**
     * Tests the GraphBuilder.getCharacters() method by checking
     * that the returned list contains the following six characters.
     * @throws IOException
     */
    @Test
    public void testBuilderGetCharacters() throws IOException {
        List<String> characters = builder.getCharacters();

        assertTrue(characters.contains("HAN"));
        assertTrue(characters.contains("LEIA"));
        assertTrue(characters.contains("LUKE"));
        assertTrue(characters.contains("DARTH VADER"));
        assertTrue(characters.contains("JAR JAR"));
        assertTrue(characters.contains("OBI-WAN"));
    }

    /**
     * Tests the GraphBuilder.getCharacters() method by checking that
     * the returned graph contains the following six characters.
     * @throws IOException
     */
    @Test
    public void testBuilderGetGraph() {
        ExtendedGraphADT<String> characters = builder.getGraph();

        assertTrue(characters.containsVertex("HAN"));
        assertTrue(characters.containsVertex("LEIA"));
        assertTrue(characters.containsVertex("LUKE"));
        assertTrue(characters.containsVertex("DARTH VADER"));
        assertTrue(characters.containsVertex("JAR JAR"));
        assertTrue(characters.containsVertex("OBI-WAN"));
    }
}
