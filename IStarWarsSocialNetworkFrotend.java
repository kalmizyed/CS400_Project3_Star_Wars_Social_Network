// --== CS400 Project One File Header ==--
// Name: Jack Blake
// CSL Username: jblake
// Email: jhblake2@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: N/A

import java.util.List;

/**
 * Interface that, when implemented, will be a child of javafx's Application, and create a
 * window that allows for finding the degrees of separation between star wars characters.
 * @author jackblake
 */
public interface IStarWarsSocialNetworkFrontend {
	/**
	 * Sets the specified dropdown to contain a list of words with the given prefix
	 */
	public void loadWordsWithPrefixInDropdown(boolean leftDropdown, String prefix);
	
	/**
	 * Displays a message with the number of paths and length of the paths in the window.
	 */
	public void displayMessage(int numPaths, int pathLength);
	
	/**
	 * Displays the provided path
	 */
	public void displayPath(List<String> path);
	
	/**
	 * Removes all currently displayed paths from the screen
	 */
	public void clearPaths();
}

