import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<T> {
    private final boolean undirected;
    private final Map<Vertex<T>, Vertex<T>> vertices = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(T data) {
        Vertex<T> v = new Vertex<>(data);
        vertices.put(v, v);
    }

    public void addEdge(T source, T dest, double weight) {
        Vertex<T> v1 = vertices.computeIfAbsent(new Vertex<>(source), k -> new Vertex<>(source));
        Vertex<T> v2 = vertices.computeIfAbsent(new Vertex<>(dest), k -> new Vertex<>(dest));

        v1.addAdjacentVertex(v2, weight);

        if (undirected) {
            v2.addAdjacentVertex(v1, weight);
        }
    }

    public boolean hasVertex(T data) {
        return vertices.containsKey(new Vertex<>(data));
    }

    public Vertex<T> getVertex(T data) {
        return vertices.get(new Vertex<>(data));
    }

    public Map<Vertex<T>, Vertex<T>> getVertices() {
        return vertices;
    }
}
