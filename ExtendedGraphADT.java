import java.util.List;

public interface ExtendedGraphADT<T> extends GraphADT<T>, Iterable<T> {
	public List<List<T>> getAllShortestPaths(T start, T end);
}
