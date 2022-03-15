import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jgrapht.EdgeFactory;
import org.jgrapht.experimental.dag.DirectedAcyclicGraph;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jinpeng.fan
 * @date 2022/3/14 5:50 PM
 * description
 */
public class DAGTest {

    @Test
    public void test() throws DirectedAcyclicGraph.CycleFoundException {
        DirectedAcyclicGraph<Dot, Edge<Dot>> dag = new DirectedAcyclicGraph<>(new TestEdgeFactory());
        Dot dot1 = new Dot(1, "first");
        Dot dot2 = new Dot(2, "second");
        Dot dot3 = new Dot(3, "third");
        Dot dot4 = new Dot(4, "fourth");
        dag.addVertex(dot1);
        dag.addVertex(dot2);
        dag.addVertex(dot3);
        dag.addVertex(dot4);
        dag.addDagEdge(dot1, dot2);
        dag.addDagEdge(dot3, dot4);
//        dag.addDagEdge(dot3, dot2);
        System.out.println(dag);
        testDag(dag);
    }

    public void testDag(DirectedAcyclicGraph<Dot, Edge<Dot>> dag) {
        int size = dag.vertexSet().size();
        Map<Dot, Integer> vertexesMark = Maps.newHashMapWithExpectedSize(size);
        int mark = 0;
        for (Dot dot : dag.vertexSet()) {
            vertexesMark.put(dot, mark++);
        }
        boolean[][] matrix = new boolean[size][size];
        boolean[] arrived = new boolean[size];
        Arrays.fill(arrived, false);
        for (Edge<Dot> edge : dag.edgeSet()) {
            matrix[vertexesMark.get(edge.getSource())][vertexesMark.get(edge.getTarget())] = true;
            matrix[vertexesMark.get(edge.getTarget())][vertexesMark.get(edge.getSource())] = true;
        }
        List<Dot> noInDots = dag.vertexSet().stream().filter(element -> dag.inDegreeOf(element) == 0).collect(Collectors.toList());
        dfs(vertexesMark.get(noInDots.get(0)), matrix, arrived);
        System.out.println(Arrays.toString(arrived));
    }

    public void dfs(int node, boolean[][] graph, boolean[] arrived) {
        arrived[node] = true;
        for (int i = 0; i < graph[node].length; ++i) {
            if (!arrived[i] && i != node && graph[node][i]) {
                dfs(i, graph, arrived);
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class Dot {
        private Integer identify;

        private String value;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    private static class Edge<T> {
        private T source;

        private T target;
    }

    private static class TestEdgeFactory implements EdgeFactory<Dot, Edge<Dot>> {

        public TestEdgeFactory() {
        }

        @Override
        public Edge<Dot> createEdge(Dot sourceVertex, Dot targetVertex) {
            return new Edge<>(sourceVertex, targetVertex);
        }
    }
}
