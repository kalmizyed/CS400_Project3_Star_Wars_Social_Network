import java.util.List;

public class StarWarsSocialNetworkBackend implements IStarWarsSocialNetworkBackend {

    protected Trie characters;

    public StarWarsSocialNetworkBackend() {
        characters = loadCharacters();
    }

    @Override
    public List<List<String>> getPathsBetween(String startCharacter, String endCharacter) {
        // TODO use Dijkstra to find paths
        return null;
    }

    @Override
    public List<String> getCharacters() {
        return characters.getWords();
    }

    @Override
    public List<String> getCharacters(String prefix) {
        return characters.getWords(prefix);
    }

    private Trie loadCharacters() {
        // TODO iterate over graph to build Trie
        return null;
    }
    
}
