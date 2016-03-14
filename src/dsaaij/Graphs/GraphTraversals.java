package dsaaij.Graphs;

import net.datastructures.*;

import java.util.Set;

/**
 * Created by warn on 14/3/2016.
 */
public class GraphTraversals {
    public static <V, E> void DFS(Graph<V, E> g, Vertex<V> u, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
        known.add(u);
        for (Edge<E> e : g.outgoingEdges(u)) {
            Vertex<V> v = g.opposite(u, e);
            if (!known.contains(v)) {
                forest.put(v, e);
                DFS(g, v, known, forest);
            }
        }
    }

    public static <V, E> PositionalList<Edge<E>> constructPath(Graph<V, E> g, Vertex<V> u, Vertex<V> v,
                                                               Map<Vertex<V>, Edge<E>> forest) {
        PositionalList<Edge<E>> path = new LinkedPositionalList<>();
        if (forest.get(v) != null) {
            Vertex<V> walk = v;
            while (walk != u) {
                Edge<E> edge = forest.get(walk);
                path.addFirst(edge);
                walk = g.opposite(walk, edge);
            }
        }
        return path;
    }

    public static <V, E> void BFS(Graph<V, E> g, Vertex<V> s, Set<Vertex<V>> known, Map<Vertex<V>, Edge<E>> forest) {
        known.add(s);
        PositionalList<Vertex<V>> level = new LinkedPositionalList<>();
        level.addFirst(s);
        while (!level.isEmpty()) {
            PositionalList<Vertex<V>> nextLevel = new LinkedPositionalList<>();
            for (Vertex<V> u : level) {
                for (Edge<E> e : g.outgoingEdges(u)) {
                    Vertex<V> v = g.opposite(u, e);
                    if (!known.contains(v)) {
                        known.add(v);
                        forest.put(v, e);
                        nextLevel.addLast(v);
                    }
                }
            }
            level = nextLevel;
        }
    }
}
