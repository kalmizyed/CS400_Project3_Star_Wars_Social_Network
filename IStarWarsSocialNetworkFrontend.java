// --== CS400 Project One File Header ==--
// Name: Jack Blake
// CSL Username: jblake
// Email: jhblake2@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: N/A

import java.util.List;

import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

/**
 * Interface that, when implemented, will be a child of javafx's Application,
 * and create a
 * window that allows for finding the degrees of separation between star wars
 * characters.
 * 
 * @author jackblake
 */
public interface IStarWarsSocialNetworkFrontend {
	/**
	 * Sets the specified dropdown to contain a list of words with the given prefix
	 */
	public void loadWordsWithPrefixInDropdown(ListView<String> dropdown, String prefix);

	/**
	 * Displays a message with the number of paths and length of the paths between 2
	 * characters in the given text box.
	 */
	public void displayMessage(int numPaths, int pathLength, Text text, String char1, String char2);

	/**
	 * Displays the provided path in the give wrapper
	 */
	public void displayPath(List<String> path, FlowPane pathsWrapper);

	/**
	 * Removes all currently displayed paths from the given wrapper
	 */
	public void clearPaths(FlowPane pathsWrapper);
}
