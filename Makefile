# GLOBAL FUNCTIONS #

run: StarWarsSocialNetworkApp.class
	java StarWarsSocialNetworkApp

StarWarsSocialNetworkApp.class: StarWarsSocialNetworkApp.java StarWarsSocialNetworkBackend.class # TODO: put your class rules here so it'll run
	javac StarWarsSocialNetworkApp.java

runTests: runBackendDeveloperTests

clean:
	rm *.class

# DATA WRANGLER #
runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar -cp . --scan-classpath -n DataWranglerTests
DataWranglerTests.class: DataWranglerTests.java GraphBuilder.class ExtendedGraphADT.class
	javac -cp .:junit5.jar DataWranglerTests.java -Xlint

GraphBuilder.class: GraphBuilder.java IGraphBuilder.class ExtendedGraphADT.class GraphADTPlaceholderDW.class
	javac GraphBuilder.java

IGraphBuilder.class: IGraphBuilder.java ExtendedGraphADT.class
	javac IGraphBuilder.java

GraphADTPlaceholderDW.class: ExtendedGraphADT.class
	javac GraphADTPlaceholderDW.java

ExtendedGraphADT.class:
	javac ExtendedGraphADT.java

# ALGORITHM ENGINEER #

# BACKEND DEVELOPER #

runBackendDeveloperTests: BackendDeveloperTests.class
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

# FRONTEND DEVELOPER
