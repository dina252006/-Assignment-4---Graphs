import java.util.*;

public class DijkstraSearch<T> extends Search<T> {
    private final Set<Vertex<T>> unsettledNodes;
    private final Map<Vertex<T>, Double> distances;

    public DijkstraSearch(WeightedGraph<T> graph, Vertex<T> source) {
        super(graph, source);
        unsettledNodes = new HashSet<>();
        distances = new HashMap<>();

        dijkstra();
    }

    private void dijkstra() {
        distances.put(source, 0D);
        unsettledNodes.add(source);

        while (!unsettledNodes.isEmpty()) {
            Vertex<T> currentNode = getVertexWithMinimumWeight(unsettledNodes);

            marked.add(currentNode);
            unsettledNodes.remove(currentNode);

            for (Map.Entry<Vertex<T>, Double> neighborEntry : currentNode.getAdjacentVertices().entrySet()) {
                Vertex<T> neighbor = neighborEntry.getKey();
                double weight = neighborEntry.getValue();
                double newDistance = getShortestDistance(currentNode) + weight;

                if (getShortestDistance(neighbor) > newDistance) {
                    distances.put(neighbor, newDistance);
                    edgeTo.put(neighbor, currentNode);
                    unsettledNodes.add(neighbor);
                }
            }
        }
    }

    private Vertex<T> getVertexWithMinimumWeight(Set<Vertex<T>> vertices) {
        Vertex<T> minimum = null;
        for (Vertex<T> vertex : vertices) {
            if (minimum == null || getShortestDistance(vertex) < getShortestDistance(minimum)) {
                minimum = vertex;
            }
        }
        return minimum;
    }

    private double getShortestDistance(Vertex<T> destination) {
        return distances.getOrDefault(destination, Double.MAX_VALUE);
    }
}
