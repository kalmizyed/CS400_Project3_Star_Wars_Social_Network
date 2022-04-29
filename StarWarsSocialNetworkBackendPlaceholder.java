import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StarWarsSocialNetworkBackendPlaceholder implements IStarWarsSocialNetworkBackend {

    private String[] characters = new String[] { "CHEWBACCA", "YODA", "COUNT DOOKU", "LUKE", "SEBULBA", "WATTO",
            "PADME",
            "OBI-WAN" };

    @Override
    public List<List<String>> getPathsBetween(String startCharacter, String endCharacter) {
        ArrayList<List<String>> paths = new ArrayList<List<String>>();
        if (startCharacter.equals("CHEWBACCA") && endCharacter.equals("YODA")) {
            paths.add(Arrays.asList(new String[] { "CHEWBACCA", "YODA" }));
        }
        if (startCharacter.equals("COUNT DOOKU") && endCharacter.equals("LUKE")) {
            paths.add(Arrays.asList(new String[] { "COUNT DOOKU", "OBI-WAN", "LUKE" }));
            paths.add(Arrays.asList(new String[] { "COUNT DOOKU", "PADME", "LUKE" }));
            paths.add(Arrays.asList(new String[] { "COUNT DOOKU", "WATTO", "LUKE" }));
            for (int i = 0; i < 40; i++) {
                paths.add(Arrays.asList(new String[] { "COUNT DOOKU", "SEBULBA", "LUKE" }));
            }
        }
        return paths;
    }

    @Override
    public List<String> getCharacters() {
        ArrayList<String> toReturn = new ArrayList<String>();
        for (String name : characters) {
            toReturn.add(name);
        }
        return toReturn;
    }

    @Override
    public List<String> getCharacters(String prefix) {
        List<String> toReturn = this.getCharacters();
        ((ArrayList<String>) toReturn).removeIf(word -> !word.startsWith(prefix));
        return toReturn;
    }

}
