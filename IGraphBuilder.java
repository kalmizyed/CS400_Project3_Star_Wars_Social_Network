public interface IGraphBuilder {
    /**
     * Reads the JSON input file and creates a graph
     * of characters from it
     * @return graph of characters
     */
    public ExtendedGraphADT<String> getGraph();

}

