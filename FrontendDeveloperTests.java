// --== CS400 Project Three File Header ==--
// Name: Jack Blake
// CSL Username: jblake
// Email: jhblake2@wisc.edi
// Lecture #: 004 @4:00pm
// Notes to Grader:

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Class with JUnit test methods to check the functionality of the
 * StarWarsSocialNetworkFrontend class
 */
public class FrontendDeveloperTests {

    StarWarsSocialNetworkFrontendWithBackendPlaceholder frontendWithBackendPlaceholder = new StarWarsSocialNetworkFrontendWithBackendPlaceholder();

    /**
     * Run Platform.startup before any of the tests to start up JavaFX Toolkit so
     * that it's possible to call the frontends's instance methods
     * Found out to do this from:
     * https://stackoverflow.com/questions/11273773/javafx-2-1-toolkit-not-initialized
     */
    @BeforeAll
    public static void init() {
        Platform.startup(() -> {
        });
    }

    /**
     * Blackbox test to make sure the loadWordsWithPrefixInDropdown method functions
     * correctly in several cases
     */
    @Test
    public void testLoadWordsWithPrefixInDropdown() {
        // Characters in backend placeholder are "CHEWBACCA", "YODA", "COUNT DOOKU",
        // "LUKE", "SEBULBA", "WATTO", "PADME", "OBI-WAN"

        // Case 0: adding words with prefix to an empty dropdown works
        ListView<String> dropdown = new ListView<String>();
        frontendWithBackendPlaceholder.loadWordsWithPrefixInDropdown(dropdown, "C");
        assertEquals(2, dropdown.getItems().size());
        assertTrue(dropdown.getItems().contains("CHEWBACCA"));
        assertTrue(dropdown.getItems().contains("COUNT DOOKU"));

        // Case 1: calling method on a non-empty dropdown should get rid of old words,
        // then add the ones with the give prefix
        frontendWithBackendPlaceholder.loadWordsWithPrefixInDropdown(dropdown, "C");
        assertEquals(2, dropdown.getItems().size());
        assertTrue(dropdown.getItems().contains("CHEWBACCA"));
        assertTrue(dropdown.getItems().contains("COUNT DOOKU"));

        frontendWithBackendPlaceholder.loadWordsWithPrefixInDropdown(dropdown, "OB");
        assertEquals(1, dropdown.getItems().size());
        assertTrue(dropdown.getItems().contains("OBI-WAN"));

        // Case 2: empty prefix, should add all characters
        frontendWithBackendPlaceholder.loadWordsWithPrefixInDropdown(dropdown, "");
        assertEquals(8, dropdown.getItems().size());
    }

    /**
     * Whitebox test to make sure the displayMessage method functions correctly in
     * several cases
     */
    @Test
    public void testDisplayMessage() {
        // Case 0: initially empty text field
        Text text = new Text();
        frontendWithBackendPlaceholder.displayMessage(2, 3, text, "OBI-WAN", "LUKE");
        assertTrue(text.getText().equals("Found 2 paths of 3 degrees of separation between OBI-WAN and LUKE."));

        // Case 1: non-empty text field, should replace previous text
        frontendWithBackendPlaceholder.displayMessage(4, 5, text, "LEAH", "SNOKE");
        assertTrue(text.getText().equals("Found 4 paths of 5 degrees of separation between LEAH and SNOKE."));

        // Case 2: only one path, should not say "paths", instead should say "path"
        frontendWithBackendPlaceholder.displayMessage(1, 2, text, "R2D2", "C3P0");
        assertTrue(text.getText().equals("Found 1 path of 2 degrees of separation between R2D2 and C3P0."));

        // Case 3: 1 degree of separation: should say "degree" not "degrees"
        frontendWithBackendPlaceholder.displayMessage(6, 1, text, "ZERO", "CAD BANE");
        assertTrue(text.getText().equals("Found 6 paths of 1 degree of separation between ZERO and CAD BANE."));

        // Case 4: 0 Paths, should say no paths found
        frontendWithBackendPlaceholder.displayMessage(0, 2, text, "CAPTAIN REX", "CAD BANE");
        assertTrue(text.getText().equals("No paths found between CAPTAIN REX and CAD BANE!"));
    }

    /**
     * Whitebox test to make sure the displayPath method functions correctly in
     * several cases
     */
    @Test
    public void testDisplayPath() {
        // Case 0: Adding a path to an empty wrapper
        FlowPane wrapper = new FlowPane();
        ArrayList<String> path = new ArrayList<String>();
        path.add("OBI-WAN");
        path.add("JAR JAR");
        path.add("SNOKE");
        frontendWithBackendPlaceholder.displayPath(path, wrapper);
        // wrapper should have a new child that is a VBOX. This will throw an Exception
        // if its not a VBox
        VBox pathVBox = (VBox) (wrapper.getChildren().get(0));
        // VBox should have 3 children that are all Text elements, in the same order as
        // above
        assertEquals(3, pathVBox.getChildren().size());
        assertEquals("OBI-WAN", ((Text) (pathVBox.getChildren().get(0))).getText());
        assertEquals("JAR JAR", ((Text) (pathVBox.getChildren().get(1))).getText());
        assertEquals("SNOKE", ((Text) (pathVBox.getChildren().get(2))).getText());

        // Case 1: Adding path to non-empty wrapper, should append path to wrapper's
        // children
        path.clear();
        path.add("LEAH");
        frontendWithBackendPlaceholder.displayPath(path, wrapper);
        pathVBox = (VBox) (wrapper.getChildren().get(1));
        // VBox should have one Text element child, with "LEAH" as its text
        assertEquals(1, pathVBox.getChildren().size());
        assertEquals("LEAH", ((Text) (pathVBox.getChildren().get(0))).getText());

    }

    /**
     * Blackbox test to make sure the frontendWithBackendPlaceholder's clearPaths
     * method functions
     * correctly
     */
    @Test
    public void testClearPaths() {
        // Case 0: non-empty, make sure all children get removed from the wrapper passed
        // as a parameter
        FlowPane wrapper = new FlowPane();
        ArrayList<VBox> pathsToAdd = new ArrayList<VBox>();
        for (int i = 0; i < 5; i++) {
            pathsToAdd.add(new VBox());
        }
        wrapper.getChildren().addAll(pathsToAdd);
        frontendWithBackendPlaceholder.clearPaths(wrapper);
        assertTrue(wrapper.getChildren().isEmpty());

        // Case 1: empty, shouldn't throw an exeption
        frontendWithBackendPlaceholder.clearPaths(wrapper);
    }

    /**
     * Tests the frontendWithBackendPlaceholders getDegreesOfSeparation method in
     * several cases
     */
    @Test
    public void testGetDegreesOfSeparation() {
        // Case 0: all same length > 2: should return the length of any path - 2
        List<List<String>> paths = new ArrayList<List<String>>();
        paths.add(Arrays.asList(new String[] { "a", "b", "c", "d" }));
        paths.add(Arrays.asList(new String[] { "a", "b", "c", "d" }));
        paths.add(Arrays.asList(new String[] { "a", "b", "c", "d" }));
        assertEquals(2, frontendWithBackendPlaceholder.getDegreesOfSeparation(paths));

        // Case 1: paths not all the same length, should return anegative number
        paths.clear();
        paths.add(Arrays.asList(new String[] { "a", "b", "c", "d" }));
        paths.add(Arrays.asList(new String[] { "a", "b", "c", "d" }));
        paths.add(Arrays.asList(new String[] { "a", "b", "c" }));
        assertTrue(frontendWithBackendPlaceholder.getDegreesOfSeparation(paths) < 0);

        // Case 2: paths are of length less than 2, should return a negative number
        paths.clear();
        paths.add(Arrays.asList(new String[] { "a" }));
        paths.add(Arrays.asList(new String[] { "a" }));
        paths.add(Arrays.asList(new String[] { "a" }));
        assertTrue(frontendWithBackendPlaceholder.getDegreesOfSeparation(paths) < 0);

        // Case 3: no paths, should return 0
        paths.clear();
        assertEquals(0, frontendWithBackendPlaceholder.getDegreesOfSeparation(paths));

    }

    // Integration Tests
    //////////////////////////////////////////////////////////////////////////////////////////////

    StarWarsSocialNetworkFrontend frontend = new StarWarsSocialNetworkFrontend();

    /**
     * Tests to make sure the correct prefixes are loaded into the dropdown in
     * several edge cases
     */
    @Test
    public void testValidPrefixIntegration() {
        ListView<String> dropdown = new ListView<String>();

        // Case 0: character name has a "-" in it: "BB-" prefix should only get back
        // "BB-8"
        frontend.loadWordsWithPrefixInDropdown(dropdown, "BB-");
        assertEquals(1, dropdown.getItems().size());
        assertEquals("BB-8", dropdown.getItems().get(0));

        // Case 1: Character with " " in their name, "JAR " should add "JAR JAR" to the
        // dropdown, and shouldn't throw any exceptions
        frontend.loadWordsWithPrefixInDropdown(dropdown, "JAR ");
        assertEquals(1, dropdown.getItems().size());
        assertEquals("JAR JAR", dropdown.getItems().get(0));

    }

    /**
     * Tests to make sure invalid prefixes display no characters in the dropdown
     */
    @Test
    public void testInvalidPrefixIntegration() {
        ListView<String> dropdown = new ListView<String>();
        // Case 0: illegal character, should throw an IllegalArgumentException, frontend
        // should not catch this since it is run on the JavaFX thread so throwing the
        // exception will just print it out to the console and allow the program to
        // continue running. Should also clear the dropdown
        boolean thrown = false;
        try {
            frontend.loadWordsWithPrefixInDropdown(dropdown, "$");
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
        assertEquals(0, dropdown.getItems().size());

        // Case 1: prefix isn't part of any names, "XYZABC" should clear the dropdown
        // and add nothing to it
        frontend.loadWordsWithPrefixInDropdown(dropdown, "XYZABC");
        assertEquals(0, dropdown.getItems().size());
    }

    // Code Review Tests
    ///////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Tests to make sure the Graph is iterable using its default iterator, i.e.
     * using an enhance for loop or forEach TEST FAILS AS OF 5/2/22 because iterator
     * returns null: FIXED,
     * TEST FAILS AS OF 5/4/22 because iterator throws StackOverflowError
     */
    @Test
    public void testGraphDefaultIterator() {
        ExtendedGraphADT<Integer> graph = new Graph<Integer>();
        // Build up a connected graph that is essentially a doubly linked list
        graph.insertVertex(1);
        for (int i = 2; i <= 10; i++) {
            graph.insertVertex(i);
            graph.insertEdge(i, i - 1, 1);
            graph.insertEdge(i - 1, i, 1);
        }
        List<Integer> list = new ArrayList<Integer>();
        graph.forEach(num -> list.add(num));
        assertEquals(10, list.size());
        for (int i = 1; i <= 10; i++) {
            assertTrue(list.contains(i));
        }
    }

    /**
     * Tests to make sure the getShortestPath algorithm of the Graph class works
     * TEST CRASHES AS OF 5/2/22, OutOfMemoryError. Likely caused by infinite loop
     * in getAllShortestPaths algorithm
     */
    @Test
    public void testGetAllShortestPaths() {
        ExtendedGraphADT<Integer> graph = new Graph<Integer>();
        // Build up a connected graph that is the following grid, where all numbers are
        // linked to the numbers adjacent to them in any cardinal direction, leading
        // zeroes wont exist in the actual vertices
        /*
         * 00 01 02 03 04 05 06 07 08 09
         * 10 11 12 13 14 15 16 17 18 19
         * 20 21 22 23 24 25 26 27 28 29
         * 30 31 32 33 34 35 36 37 38 39
         * 40 41 42 43 44 45 46 47 48 49
         * 50 51 52 53 54 55 56 57 58 59
         * 60 61 62 63 64 65 66 67 68 69
         * 70 71 72 73 74 75 76 77 78 79
         * 80 81 82 83 84 85 86 87 88 89
         * 90 91 92 93 94 95 96 97 98 99
         */
        graph.insertVertex(1);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                graph.insertVertex(i * 10 + j);
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int curr = i * 10 + j;

                int up = (i - 1) * 10 + j;
                int down = (i + 1) * 10 + j;
                int left = i * 10 + j - 1;
                int right = i * 10 + j + 1;

                try {
                    graph.insertEdge(curr, up, 1);
                    graph.insertEdge(up, curr, 1);
                } catch (IllegalArgumentException e) {
                }
                try {
                    graph.insertEdge(curr, down, 1);
                    graph.insertEdge(down, curr, 1);
                } catch (IllegalArgumentException e) {
                }
                try {
                    graph.insertEdge(curr, left, 1);
                    graph.insertEdge(left, curr, 1);
                } catch (IllegalArgumentException e) {
                }
                try {
                    graph.insertEdge(curr, right, 1);
                    graph.insertEdge(right, curr, 1);
                } catch (IllegalArgumentException e) {
                }
            }
        }

        // For 88 to 99, There should be 2 paths, [88, 89, 99] and [88, 98, 99]
        List<List<Integer>> paths = graph.getAllShortestPaths(88, 99);

        assertEquals(2, paths.size());
        paths.forEach(path -> assertEquals(3, path.size()));

        // For 31 to 43, There should be 3 paths, [31, 32, 33, 43], [31, 41, 42, 43],
        // and [31, 32, 42, 43]
        paths = graph.getAllShortestPaths(31, 43);
        assertEquals(3, paths.size());
        paths.forEach(path -> assertEquals(4, path.size()));

    }

}
