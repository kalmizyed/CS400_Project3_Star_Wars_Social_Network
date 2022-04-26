// --== CS400 Project Three File Header ==--
// Name: Kaden Almizyed
// CSL Username: kaden
// Email: kalmizyed@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader:

import java.util.List;

/**
 * Provides backend functionality for the Star Wars Social Network app.
 */
public class StarWarsSocialNetworkBackend implements IStarWarsSocialNetworkBackend {

    protected ExtendedGraphADT<String> map;
    protected Trie characters;

    /**
     * Creates a new Backend object.
     * @param map the map of characters the app will use.
     */
    public StarWarsSocialNetworkBackend(ExtendedGraphADT<String> map) {
        this.map = map;
        characters = loadCharacters();
    }

    /**
     * Returns a list of the shortest path(s) between two characters in the map.
     */
    @Override
    public List<List<String>> getPathsBetween(String startCharacter, String endCharacter) {
        return map.getAllShortestPaths(startCharacter, endCharacter);
    }

    /**
     * Returns a list of character names for display on the Frontend.
     */
    @Override
    public List<String> getCharacters() {
        return characters.getWords();
    }

    /**
     * Returns a list of characters filtered by a prefix for display on the Frontend.
     */
    @Override
    public List<String> getCharacters(String prefix) {
        return characters.getWords(prefix);
    }

    /**
     * Iterates through the map and adds all character names to a trie for quick access.
     * @return
     */
    protected Trie loadCharacters() {
        Trie trie = new Trie();

        for (String character : map) {
            trie.add(character);
        }

        return trie;
    }
    
}
