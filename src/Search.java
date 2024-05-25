import java.util.*;

public abstract class Search<T> {
    protected Set<Vertex<T>> marked;
    protected Map<Vertex<T>, Vertex<T>> edgeTo;
    protected final Vertex<T> source;
    protected final WeightedGraph<T> graph;

    public Search(WeightedGraph<T> graph, Vertex<T> source) {
        this.graph = graph;
        this.source = source;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(Vertex<T> v) {
        return marked.contains(v);
    }

    public Iterable<Vertex<T>> pathTo(Vertex<T> v) {
        if (!hasPathTo(v)) return null;

        LinkedList<Vertex<T>> path = new LinkedList<>();
        for (Vertex<T> x = v; x != source; x = edgeTo.get(x)) {
            path.push(x);
        }
        path.push(source);

        return path;
    }

    public WeightedGraph<T> getGraph() {
        return graph;
    }
}
