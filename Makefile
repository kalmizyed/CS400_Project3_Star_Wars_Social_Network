runTests: DataWranglerTests.class
	java -jar junit5.jar -cp . --scan-classpath -n DataWranglerTests
clean:
	rm *.class
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