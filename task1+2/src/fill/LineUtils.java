package fill;

import model.Edge;
import model.Line;

import java.util.ArrayList;
import java.util.List;

class LineUtils {

    public static List<Edge> convertToEdges(List<Line> lines) {
        List<Edge> edges = new ArrayList<>();
        for (Line line : lines) {
            Edge edge = new Edge(line.getP1(), line.getP2(), line.getColor());
            if (!edge.isHorizontal()) {
                edge.orientate();
                edges.add(edge);
            }
        }
        return edges;
    }
}