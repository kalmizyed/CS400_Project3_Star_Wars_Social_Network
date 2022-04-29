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

    StarWarsSocialNetworkFrontendWithBackendPlaceholder frontend = new StarWarsSocialNetworkFrontendWithBackendPlaceholder();

    /**
     * Run Platform.startup before any of the tests to start up JavaFX Toolkit so
     * that it's possible to call the frontend's instance methods
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
        frontend.loadWordsWithPrefixInDropdown(dropdown, "C");
        assertEquals(2, dropdown.getItems().size());
        assertTrue(dropdown.getItems().contains("CHEWBACCA"));
        assertTrue(dropdown.getItems().contains("COUNT DOOKU"));

        // Case 1: calling method on a non-empty dropdown should get rid of old words,
        // then add the ones with the give prefix
        frontend.loadWordsWithPrefixInDropdown(dropdown, "C");
        assertEquals(2, dropdown.getItems().size());
        assertTrue(dropdown.getItems().contains("CHEWBACCA"));
        assertTrue(dropdown.getItems().contains("COUNT DOOKU"));

        frontend.loadWordsWithPrefixInDropdown(dropdown, "OB");
        assertEquals(1, dropdown.getItems().size());
        assertTrue(dropdown.getItems().contains("OBI-WAN"));

        // Case 2: empty prefix, should add all characters
        frontend.loadWordsWithPrefixInDropdown(dropdown, "");
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
        frontend.displayMessage(2, 3, text, "OBI-WAN", "LUKE");
        assertTrue(text.getText().equals("Found 2 paths of 3 degrees of separation between OBI-WAN and LUKE."));

        // Case 1: non-empty text field, should replace previous text
        frontend.displayMessage(4, 5, text, "LEAH", "SNOKE");
        assertTrue(text.getText().equals("Found 4 paths of 5 degrees of separation between LEAH and SNOKE."));

        // Case 2: only one path, should not say "paths", instead should say "path"
        frontend.displayMessage(1, 2, text, "R2D2", "C3P0");
        assertTrue(text.getText().equals("Found 1 path of 2 degrees of separation between R2D2 and C3P0."));

        // Case 3: 1 degree of separation: should say "degree" not "degrees"
        frontend.displayMessage(6, 1, text, "ZERO", "CAD BANE");
        assertTrue(text.getText().equals("Found 6 paths of 1 degree of separation between ZERO and CAD BANE."));

        // Case 4: 0 Paths, should say no paths found
        frontend.displayMessage(0, 2, text, "CAPTAIN REX", "CAD BANE");
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
        frontend.displayPath(path, wrapper);
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
        frontend.displayPath(path, wrapper);
        pathVBox = (VBox) (wrapper.getChildren().get(1));
        // VBox should have one Text element child, with "LEAH" as its text
        assertEquals(1, pathVBox.getChildren().size());
        assertEquals("LEAH", ((Text) (pathVBox.getChildren().get(0))).getText());

    }

    /**
     * Blackbox test to make sure the frontend's clearPaths method functions
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
        frontend.clearPaths(wrapper);
        assertTrue(wrapper.getChildren().isEmpty());

        // Case 1: empty, shouldn't throw an exeption
        frontend.clearPaths(wrapper);
    }

    /**
     * Tests the frontends getDegreesOfSeparation method in several cases
     */
    @Test
    public void testGetDegreesOfSeparation() {
        // Case 0: all same length > 2: should return the length of any path - 2
        List<List<String>> paths = new ArrayList<List<String>>();
        paths.add(Arrays.asList(new String[] { "a", "b", "c", "d" }));
        paths.add(Arrays.asList(new String[] { "a", "b", "c", "d" }));
        paths.add(Arrays.asList(new String[] { "a", "b", "c", "d" }));
        assertEquals(2, frontend.getDegreesOfSeparation(paths));

        // Case 1: paths not all the same length, should return anegative number
        paths.clear();
        paths.add(Arrays.asList(new String[] { "a", "b", "c", "d" }));
        paths.add(Arrays.asList(new String[] { "a", "b", "c", "d" }));
        paths.add(Arrays.asList(new String[] { "a", "b", "c" }));
        assertTrue(frontend.getDegreesOfSeparation(paths) < 0);

        // Case 2: paths are of length less than 2, should return a negative number
        paths.clear();
        paths.add(Arrays.asList(new String[] { "a" }));
        paths.add(Arrays.asList(new String[] { "a" }));
        paths.add(Arrays.asList(new String[] { "a" }));
        assertTrue(frontend.getDegreesOfSeparation(paths) < 0);

        // Case 3: no paths, should return 0
        paths.clear();
        assertEquals(0, frontend.getDegreesOfSeparation(paths));

    }
}
