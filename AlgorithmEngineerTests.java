import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class AlgorithmEngineerTests {

  private Graph<String> graph;
  
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
  }
  
  @Test
  void testAllShortestReturn() {
    assertTrue(graph.getAllShortestPaths("A", "B") instanceof List<?>);
  }
  
  @Test
  void testCostAtoB() {
    assertTrue(graph.getPathCost("A", "B") == 2);
  }
  
  @Test
  void testPathsContain() {
    List<List<String>> pathsList = graph.getAllShortestPaths("A", "B");
    assertTrue(pathsList.toString().contains("[A, C, B]"));
    assertTrue(pathsList.toString().contains("[A, D, B]"));
    assertTrue(pathsList.toString().contains("[A, E, B]"));
    assertTrue(pathsList.toString().contains("[A, F, B]"));
  }
  
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
  
  @Test
  void testIterable() {
    assertTrue(graph.iterator(graph.vertices, "A") instanceof Iterator<?>);
    
    ArrayList<String> DFSArray = new ArrayList<>();
    for (Iterator<String> it = graph.iterator(graph.vertices, "A"); it.hasNext(); ) {
      DFSArray.add(it.next());
    }
    
    assertTrue(DFSArray.toString().equals("[A, C, B, D, E, F]"));
  }
}

