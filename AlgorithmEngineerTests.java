// --== CS400 File Header Information ==--
// Name: Matthew Chang
// Email: mchang43@wisc.edu
// Team: DC
// TA: April
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * AlgorithmEngineerTests class tests the implemented 
 * Graph with the testAllShortestPaths method
 * @author matthewchang
 *
 */
class AlgorithmEngineerTests {

  private Graph<String> graph;
  
  /**
   * Creates the graph for test methods
   */
  @BeforeEach
  void makeGraph() {
    graph = new Graph<String>();
    graph.insertVertex("A");
    graph.insertVertex("B");
    graph.insertVertex("C");
    graph.insertVertex("D");
    graph.insertVertex("E");
    graph.insertVertex("F");
    
    graph.insertEdge("A", "C", 1);
    graph.insertEdge("A", "D", 1);
    graph.insertEdge("A", "E", 1);
    graph.insertEdge("A", "F", 1);
    
    graph.insertEdge("C", "B", 1);
    graph.insertEdge("D", "B", 1);
    graph.insertEdge("E", "B", 1);
    graph.insertEdge("F", "B", 1);
    
    graph.insertEdge("C", "D", 1);
    graph.insertEdge("D", "E", 1);
    graph.insertEdge("E", "F", 1);
    
    
    /**
     * Graph Representation
     * 
     *   -> C ->
     * A -> D -> B
     *   -> E ->
     *   -> F ->
     * 
     */
  }
  
  /**
   * Tests getAllShortestPaths returns a list
   */
  @Test
  void testAllShortestReturn() {
    assertTrue(graph.getAllShortestPaths("A", "B") instanceof List<?>);
  }
  
  /**
   * Tests getPathCost method
   */
  @Test
  void testCostAtoB() {
    assertTrue(graph.getPathCost("A", "B") == 2);
  }
  
  /**
   * Tests that all shortest paths are returned
   */
  @Test
  void testPathsContain() {
    List<List<String>> pathsList = graph.getAllShortestPaths("A", "B");
    assertTrue(pathsList.toString().contains("[A, C, B]"));
    assertTrue(pathsList.toString().contains("[A, D, B]"));
    assertTrue(pathsList.toString().contains("[A, E, B]"));
    assertTrue(pathsList.toString().contains("[A, F, B]"));
  }
  
  /**
   * Tests invalid paths/nodes
   */
  @Test
  void testBadData() {
    try {
      graph.getPathCost("B", "C");
      fail("Didn't throw NoSuchElementException");
    }
    catch(NoSuchElementException e) {
    }
    catch(Exception e) {
      fail("Didn't throw NoSuchElementException");
    }
    
    try {
      graph.getPathCost("A", "R");
      fail("Didn't throw NoSuchElementException");
    }
    catch(NoSuchElementException e) {
    }
    catch(Exception e) {
      fail("Didn't throw NoSuchElementException");
    }
  }
  
  /**
   * Tests the overriden iterator
   */
  @Test
  void testIterable() {
    assertTrue(graph.iterator() instanceof Iterator<?>);
    
    ArrayList<String> DFSArray = new ArrayList<>();
    for (Iterator<String> it = graph.iterator(); it.hasNext(); ) {
      DFSArray.add(it.next());
    }
    
    assertTrue(DFSArray.toString().equals("[A, C, B, D, E, F]"));
    
  }
  
}

