// --== CS400 Project Three File Header ==--
// Name: Jack Blake
// CSL Username: jblake
// Email: jhblake2@wisc.edi
// Lecture #: 004 @4:00pm
// Notes to Grader:

import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

/**
 * Class controlling a JavaFX application that allows the user to interact with
 * a GUI to search for degrees of separation between Star Wars characters.
 */
public class StarWarsSocialNetworkFrontend extends Application implements IStarWarsSocialNetworkFrontend {

    // TODO: replace with actual backend class instance and create separate version
    // of this class called StarWarsSocialNetworkFrontendWithBackendPlaceholder that
    // uses the placeholder for testing purposes
    IStarWarsSocialNetworkBackend backend = new StarWarsSocialNetworkBackendPlaceholder();

    public static void main(String args) {
        Application.launch();
    }

    /**
     * Starts up the JavaFX application by creating a tree of GUI elements, then
     * setting the scene and showing the stage
     */
    @Override
    public void start(Stage stage) {

        // Create two areas where you can type text, and a list of names with that
        // prefix appears a list under it
        TextField char1TextField = new TextField();
        ListView<String> char1ListView = new ListView<String>();
        char1ListView.setMaxHeight(72);
        VBox char1Area = new VBox(char1TextField, char1ListView);

        // On start and whenever the user types in the text field, replace the words in
        // the list with the words with the prefix
        loadWordsWithPrefixInDropdown(char1ListView, "");
        char1TextField.setOnKeyTyped(
                e -> loadWordsWithPrefixInDropdown(char1ListView, char1TextField.getText().toUpperCase()));

        TextField char2TextField = new TextField();
        ListView<String> char2ListView = new ListView<String>();
        char2ListView.setMaxHeight(72);
        VBox char2Area = new VBox(char2TextField, char2ListView);

        loadWordsWithPrefixInDropdown(char2ListView, "");
        char2TextField.setOnKeyTyped(
                e -> loadWordsWithPrefixInDropdown(char2ListView, char2TextField.getText().toUpperCase()));

        // create a button that you will click to search
        Button searchBtn = new Button("Search");

        // organize the above items horizontally using an HBox
        HBox inputArea = new HBox(char1Area, char2Area, searchBtn);
        inputArea.setSpacing(20);

        // Create the area messages will be displayed to
        Text textOutput = new Text();

        // Create the area where paths will be displayed
        FlowPane pathsWrapper = new FlowPane();

        // Whenever the search button is clicked, get the paths between the two selected
        // characters from the backend, and display the realted message and display the
        // paths
        searchBtn.setOnAction(e -> {
            String char1 = char1ListView.getSelectionModel().selectedItemProperty().get();
            String char2 = char2ListView.getSelectionModel().selectedItemProperty().get();
            List<List<String>> paths = backend.getPathsBetween(char1, char2);
            int degrees = this.getDegreesOfSeparation(paths);
            if (degrees < 0) {
                textOutput.setText("Error finding degrees of separation between " + char1 + " and " + char2 + ".");
            } else {
                displayMessage(paths.size(), degrees, textOutput, char1, char2);
                clearPaths(pathsWrapper);
                for (List<String> path : paths) {
                    displayPath(path, pathsWrapper);
                }
            }
        });

        // Organize the input, text, and paths areas into a vertical orientation
        VBox content = new VBox(inputArea, textOutput, pathsWrapper);
        content.setSpacing(36);
        content.setMaxWidth(600);
        content.setLayoutX(20);
        content.setLayoutY(20);

        // give the content a white background
        Background contentBackground = new Background(
                new BackgroundFill[] { new BackgroundFill(Color.WHITE, null, null) });

        content.setBackground(contentBackground);

        // create a wrapper around all of the elements and give it a star wars
        // background
        Pane wrapper = new Pane(content);
        Background wrapperBackground = new Background(
                new BackgroundImage(new Image("./bg.jpg"), null, null, null, null));
        wrapper.setBackground(wrapperBackground);
        // make sure it is always as tall as the window so the background isn't ever cut
        // off
        wrapper.setMinHeight(480);

        // Wrap everything in a Scroll Pane so if there are lots of Paths being
        // displayed, you can scroll to see them all
        ScrollPane scrollPane = new ScrollPane(wrapper);
        scrollPane.setFitToWidth(true);

        // Add everything to a scene
        Scene scene = new Scene(scrollPane, 640, 480);

        // Set the scene and show the stage
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the specified dropdown to contain a list of words with the given prefix
     */
    @Override
    public void loadWordsWithPrefixInDropdown(ListView<String> dropdown, String prefix) {
        dropdown.getItems().clear();
        dropdown.getItems().addAll(backend.getCharacters(prefix));
    }

    /**
     * Displays a message with the number of paths and length of the paths between 2
     * characters in the given text box
     */
    @Override
    public void displayMessage(int numPaths, int degrees, Text text, String char1, String char2) {
        if (numPaths == 0) {
            text.setText("No paths found between " + char1 + " and " + char2 + "!");
            return;
        }
        String pathsPlural = "";
        if (numPaths != 1)
            pathsPlural = "s";
        String degreePlural = "";
        if (degrees != 1)
            degreePlural = "s";
        text.setText("Found " + numPaths + " path" + pathsPlural + " of " + degrees
                + " degree" + degreePlural + " of separation between " + char1 + " and " + char2 + ".");
    }

    /**
     * Displays the provided path in the give wrapper by appending a VBox of Text
     * elements of the wrapper's children
     */
    @Override
    public void displayPath(List<String> path, FlowPane pathsWrapper) {
        VBox pathBox = new VBox();
        // add to the VBox a Text element with each character
        for (String character : path) {
            pathBox.getChildren().add(new Text(character));
        }
        // add the VBox to the wrappers
        pathsWrapper.getChildren().add(pathBox);
        // give the VBox margins for better spacing
        FlowPane.setMargin(pathBox, new Insets(10, 10, 10, 10));
    }

    /**
     * Removes all currently displayed paths from the given wrapper, i.e. removing
     * all of its children
     */
    @Override
    public void clearPaths(FlowPane pathsWrapper) {
        pathsWrapper.getChildren().clear();
    }

    /**
     * return the degrees of separation for all the paths provided (amount of
     * elements between the first and last element), or a negative number if they
     * are not all the same length or are of length less than 2. returns 0 if there
     * are no paths
     */
    protected int getDegreesOfSeparation(List<List<String>> paths) {
        int currDegrees = 0;
        int prevLength = -1;
        for (List<String> path : paths) {
            // return -1 if the sizes are not the same
            if (prevLength != -1 && path.size() != prevLength)
                return -1;
            prevLength = path.size();
            currDegrees = prevLength - 2;
        }
        return currDegrees;
    }

}
