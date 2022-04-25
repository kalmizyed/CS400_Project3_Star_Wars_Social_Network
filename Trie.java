import java.util.ArrayList;
import java.util.List;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * A Trie data structure with capabilities required for the app.
 */
public class Trie implements ITrie {

    private TrieNode root;

    /**
     * Creates a new Trie.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Traverses the trie to find the node representing the given word.
     * @param word the word to traverse to
     * @return the node the word ends at
     */
    protected TrieNode getNode(String word) {
        StringCharacterIterator iterator = new StringCharacterIterator(word);

        TrieNode current = root;
        char c = iterator.first();
        while (c != CharacterIterator.DONE) {
            current = current.getChild(c);
            c = iterator.next();
        }

        return current;
    }

    /**
     * Adds a word to the trie.
     */
    @Override
    public void add(String word) throws IllegalArgumentException {
        getNode(word).setWord(true);
    }

    /**
     * Returns a list of all words.
     */
    @Override
    public List<String> getWords() {
        return getWordsHelper(root, new String());
    }

    /**
     * Returns a list of all words starting with the given prefix.
     */
    @Override
    public List<String> getWords(String prefix) {
        return getWordsHelper(getNode(prefix), new String());
    }

    /**
     * Recursive helper method for getWords().
     * Returns a list of all words in this subtrie as well as the current word if the current node is a word.
     * @param currentNode current subtrie
     * @param currentWord word formed by all parents through the current node
     * @return a list of all words in the current subtrie & the current word
     */
    private List<String> getWordsHelper(TrieNode currentNode, String currentWord) {
        ArrayList<String> words = new ArrayList<String>();

        // If the current node is a word, then add it to the list
        if (currentNode.isWord) words.add(currentWord.toString());

        // Add all child words to the list
        for (TrieNode child : currentNode.children) {
            if (child != null) words.addAll(getWordsHelper(child, currentWord + child.character));
        }

        return words;
    }
    
}

/**
 * Represents a node in the Trie.
 * 
 * Each node has a boolean flag indicating if it represents the end of a word
 * and can have a child for every valid character.
 * 
 * Has a method to return the child node representing a specific character.
 */
class TrieNode {
    public boolean isWord;
    protected TrieNode[] children;
    protected char character;

    /**
     * Default constructor.  Only used to create the root of the Trie.
     */
    public TrieNode() {
        isWord = false;
        children = new TrieNode[28]; // [A-Z, space, -]
    }

    /**
     * Character constructor.  Used to create any child nodes.
     * @param character the character represented by the current node.
     */
    public TrieNode(char character) {
        isWord = false;
        children = new TrieNode[28]; // [A-Z, space, -]
        this.character = character;
    }

    public void setWord(boolean isWord) {
        this.isWord = isWord;
    }

    public TrieNode getChild(char c) throws IllegalArgumentException {
        if (children[indexOfChar(c)] == null) {
            children[indexOfChar(c)] = new TrieNode(c);
        }

        return children[indexOfChar(c)];
    }

    private int indexOfChar(char c) throws IllegalArgumentException {
        if (c >= 'A' && c <= 'Z') {
            return (int) c - 'A'; // 0-25
        }

        else if (c == ' ') return 26;

        else if (c == '-') return 27;
        
        else {
            throw new IllegalArgumentException("Unknown character encountered.  Trie only accepts characters [A-Z, space, -].");
        }
    }
}
