// --== CS400 Project Three File Header ==--
// Name: Kaden Almizyed
// CSL Username: kaden
// Email: kalmizyed@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader:

import java.util.List;

public interface ITrie {
    /**
     * Adds a word to the trie.
     */
    public void add(String word);
   
    /**
     * Returns a list of all words.
     */
    public List<String> getWords();
 
    /**
     * Returns a list of all words starting with the given prefix.
     */
    public List<String> getWords(String prefix);
}

