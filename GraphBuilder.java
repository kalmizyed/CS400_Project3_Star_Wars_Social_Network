// --== CS400 Project Three File Header ==--
// Name: Joseph Cai
// CSL Username: josephc
// Email: jbcai@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader:

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphBuilder implements IGraphBuilder{
    //File containing all characters and interactions from all 6 movies
    public static final String dataFile = "data/starwars-full-interactions-allCharacters.json";

    /**
     * Reads the JSON input file and creates a graph
     * of characters from it
     * @return graph of characters, null if file not found
     */
    @Override
    public ExtendedGraphADT<String> getGraph() {
        ExtendedGraphADT<String> graph = new GraphADTPlaceholderDW<String>();
        try{
            List<String> characters = getCharacters();
            List<int[]> interactions = getInteractions();
            for(String character : characters){
                graph.insertVertex(character);
            }
            for(int[] interaction : interactions){
                String character1 = characters.get(interaction[0]);
                String character2 = characters.get(interaction[1]);
                graph.insertEdge(character1, character2, 1);
                graph.insertEdge(character2, character1, 1);
            }
        }
        catch(IOException e){
            System.err.println("Data file not found.");
            return null;
        }

        return graph;
    }

    /**
     * gets the list of characters from the file as strings
     * @return list of characters
     * @throws IOException
     */
    protected List<String> getCharacters() throws IOException{
        List<String> characters = new ArrayList<String>();
        BufferedReader in = new BufferedReader(new FileReader(dataFile));
        while(in.read()!='['); //Reads until nodes
        String line = in.readLine();
        while(!line.contains("]")){
            if(line.trim().startsWith("\"name\"")){
                //Get the name
                String nameValue = line.trim().split(": ")[1];
                String name = nameValue.substring(1,nameValue.length()-2);
                characters.add(name);
            }
            line = in.readLine();
        }
        in.close();
        return characters;
    }

    /**
     * gets interactions from the file as pairs of ints
     * @return list of interactions, where each interaction is an int array {source,target}
     * @throws IOException
     */
    protected List<int[]> getInteractions() throws IOException{
        List<int[]> interactions = new ArrayList<int[]>();
        BufferedReader in = new BufferedReader(new FileReader(dataFile));
        while(in.read()!='['); //Reads until nodes
        while(in.read()!=']'); //Reads until end of nodes
        while(in.read()!='['); //Reads until start of links
        String line = in.readLine();
        while(!line.contains("]")){
            if(line.trim().startsWith("\"source\"")){
                //Get the source
                String sourceValue = line.trim().split(": ")[1];
                int source = Integer.parseInt(sourceValue.substring(0,sourceValue.length()-1));
                line = in.readLine();
                //Get the target
                String targetValue = line.trim().split(": ")[1];
                int target = Integer.parseInt(targetValue.substring(0,targetValue.length()-1));
                interactions.add(new int[]{source,target});
            }
            line = in.readLine();
        }
        in.close();
        return interactions;
    }
}
