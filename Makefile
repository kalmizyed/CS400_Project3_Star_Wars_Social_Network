runTests: AlgorithmEngineerTests.class
	java -jar junit5.jar --class-path . --scan-classpath

clean: 
	rm *.class

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java GraphADT.class ExtendedGraphADT.class Graph.class
	javac -cp .:junit5.jar AlgorithmEngineerTests.java -Xlint

GraphADT.class: GraphADT.java
	javac GraphADT.java

ExtendedGraphADT.class: ExtendedGraphADT.java
	javac ExtendedGraphADT.java

Graph.class: Graph.java
	javac Graph.java

