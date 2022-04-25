// --== CS400 Project Three File Header ==--
// Name: Joseph Cai
// CSL Username: josephc
// Email: jbcai@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader:

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.io.IOException;
import java.util.List;

public class DataWranglerTests {

    @Test
    public void testNumberCharacters() throws IOException{
        List<String> characters = new GraphBuilder().getCharacters();
        assertEquals(characters.size(), 112);
    }

    @Test
    public void testNumberInteractions() throws IOException{
        List<int[]> interactions = new GraphBuilder().getInteractions();
        assertEquals(interactions.size(), 450);
    }

    @Test
    public void testGraphContainsCharacters(){
        ExtendedGraphADT<String> graph = new GraphBuilder().getGraph();
        assertTrue(graph.containsVertex("DARTH VADER"));
        assertTrue(graph.containsVertex("LEIA"));
        assertTrue(graph.containsVertex("JAR JAR"));
    }

    @Test
    public void testGraphContainsEdges(){
        ExtendedGraphADT<String> graph = new GraphBuilder().getGraph();
        assertTrue(graph.containsEdge("LUKE","DARTH VADER"));
        assertTrue(graph.containsEdge("JAR JAR","QUI-GON"));
        assertTrue(graph.containsEdge("ANAKIN","MACE WINDU"));
    }

    @Test
    public void  testGraphSizes(){
        ExtendedGraphADT<String> graph = new GraphBuilder().getGraph();
        assertEquals(graph.getVertexCount(),112);
        assertEquals(graph.getEdgeCount(),900);
        assertEquals(graph.getPathCost("LUKE","DARTH VADER"), 1);
    }
}
