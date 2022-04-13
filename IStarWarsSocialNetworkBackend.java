// --== CS400 Project Three File Header ==--
// Name: Kaden Almizyed
// CSL Username: kaden
// Email: kalmizyed@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader:

import java.util.List;

public interface IStarWarsSocialNetworkBackend {
    /**
     * Returns a list containing the shortest path(s), if any, that link the starting character to another character.
     */
    public List<List<String>> getPathsBetween(String startCharacter, String endCharacter);
   
    /**
     * Returns a list of all character names.
     */
    public List<String> getCharacters();
 
    /**
     * Returns a list of all character names that start with the given prefix.
     */
    public List<String> getCharacters(String prefix);
}

