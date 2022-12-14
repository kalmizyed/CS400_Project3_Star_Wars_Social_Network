# GLOBAL FUNCTIONS #
run: StarWarsSocialNetworkFrontend.class
	java --module-path ./javafx --add-modules javafx.controls StarWarsSocialNetworkFrontend

runTests: runBackendDeveloperTests runFrontendDeveloperTests runDataWranglerTests

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

# ALGORITHM ENGINEER #

runAlgorithmTests: AlgorithmEngineerTests.class
	java -jar junit5.jar --class-path . --scan-classpath

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java GraphADT.class ExtendedGraphADT.class Graph.class
	javac -cp .:junit5.jar AlgorithmEngineerTests.java -Xlint

GraphADT.class: GraphADT.java
	javac GraphADT.java

ExtendedGraphADT.class: ExtendedGraphADT.java
	javac ExtendedGraphADT.java

Graph.class: Graph.java
	javac Graph.java

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

runWithBackendPlaceholder: StarWarsSocialNetworkFrontendWithBackendPlaceholder.class
	java --module-path ./javafx --add-modules javafx.controls StarWarsSocialNetworkFrontendWithBackendPlaceholder

runFrontendDeveloperTests: FrontendDeveloperTests.class
	java --module-path ./javafx --add-modules javafx.controls -jar junit5.jar -cp . --scan-classpath -n FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java StarWarsSocialNetworkFrontendWithBackendPlaceholder.class StarWarsSocialNetworkFrontend.class
	javac --module-path ./javafx --add-modules javafx.controls  -cp .:./junit5.jar FrontendDeveloperTests.java

StarWarsSocialNetworkFrontend.class: StarWarsSocialNetworkFrontend.java StarWarsSocialNetworkBackend.class
	javac --module-path ./javafx --add-modules javafx.controls StarWarsSocialNetworkFrontend.java

StarWarsSocialNetworkFrontendWithBackendPlaceholder.class: 
	javac --module-path ./javafx --add-modules javafx.controls StarWarsSocialNetworkFrontendWithBackendPlaceholder.java

StarWarsSocialNetworkBackendPlaceholder.class: StarWarsSocialNetworkBackendPlaceholder.java
	javac StarWarsSocialNetworkBackendPlaceholder.java

