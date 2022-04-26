/**
 * Represents a node in the Trie.
 * 
 * Each node has a boolean flag indicating if it represents the end of a word
 * and can have a child for every valid character.
 * 
 * Has a method to return the child node representing a specific character.
 */
public class TrieNode {
    public boolean isWord;
    protected TrieNode[] children;
    protected char character;

    /**
     * Default constructor.  Only used to create the root of the Trie.
     */
    public TrieNode() {
        isWord = false;
        children = new TrieNode[38]; // [A-Z, space, -, 0-9]
    }

    /**
     * Character constructor.  Used to create any child nodes.
     * @param character the character represented by the current node.
     */
    public TrieNode(char character) {
        isWord = false;
        children = new TrieNode[38]; // [A-Z, space, -, 0-9]
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

    protected static int indexOfChar(char c) throws IllegalArgumentException {
        if (c >= 'A' && c <= 'Z') {
            return (int) c - 'A'; // 0-25
        }

        else if (c == ' ') return 26;

        else if (c == '-') return 27;

        else if (c >= '0' && c <= '9') {
            return (int) c - '0' + 28; // 28-37
        }
        
        else {
            throw new IllegalArgumentException("Unknown character encountered.  Trie only accepts characters [A-Z, space, -, 0-9].");
        }
    }
}