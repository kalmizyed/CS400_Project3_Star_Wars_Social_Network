run: StarWarsSocialNetworkFrontend.class
	java --module-path ./lib --add-modules javafx.controls StarWarsSocialNetworkFrontend

StarWarsSocialNetworkFrontend.class: StarWarsSocialNetworkFrontend.java StarWarsSocialNetworkBackendPlaceholder.class
	javac --module-path ./lib --add-modules javafx.controls StarWarsSocialNetworkFrontend.java

StarWarsSocialNetworkBackendPlaceholder.class: StarWarsSocialNetworkBackendPlaceholder.java
	javac StarWarsSocialNetworkBackendPlaceholder.java

runTests: FrontendDeveloperTests.class
	java --module-path ./lib --add-modules javafx.controls -jar ./lib/junit5.jar -cp . --scan-classpath -n FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java StarWarsSocialNetworkFrontend.class
	javac --module-path ./lib --add-modules javafx.controls  -cp .:./lib/junit5.jar FrontendDeveloperTests.java

clean:
	rm *.class