runTests: BackendDeveloperTests.class
	java -jar junit5.jar --class-path . --select-class BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java StarWarsSocialNetworkBackend.class Trie.class ExtendedGraphPlaceholderBD.class
	javac -cp junit5.jar:. BackendDeveloperTests.java

StarWarsSocialNetworkBackend.class: StarWarsSocialNetworkBackend.java
	javac StarWarsSocialNetworkBackend.java

Trie.class: Trie.java TrieNode.class
	javac Trie.java

TrieNode.class: TrieNode.java
	javac TrieNode.java

ExtendedGraphPlaceholderBD.class: ExtendedGraphPlaceholderBD.java
	javac ExtendedGraphPlaceholderBD.java

clean:
	rm *.class
