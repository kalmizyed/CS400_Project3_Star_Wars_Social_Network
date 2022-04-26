// --== CS400 Project Three File Header ==--
// Name: Kaden Almizyed
// CSL Username: kaden
// Email: kalmizyed@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader:

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
     * Returns whether or not the specified word is in the Trie.
     */
    public boolean contains(String word) {
        return getNode(word).isWord;
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
    public List<String> getWords(String prefix) { // Start getWords algorithm at node with last prefix letter
        return getWordsHelper(getNode(prefix), prefix);
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