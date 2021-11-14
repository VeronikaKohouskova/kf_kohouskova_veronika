package rasterize;

import model.Edge;
import model.Line;
import model.Point;
import model.Polygon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Clipper {
    /**
     * Ořezání obecného polygonu (polygon) jiným konvexním polygonem (clipPolygon)
     *
     * @param polygon     ořezávaný polygon
     * @param clipPolygon ořezávací polygon
     * @return seznam bodů ořezaného polygonu
     */

    public List<Point> clip(Polygon polygon, Polygon clipPolygon) {
        List<Edge> clipPolygonEdges = new ArrayList<>(convertToEdges(polygon.getLines()));
        List<Point> in = new ArrayList<>(polygon.getPoints());
        List<Point> out = new ArrayList<>();

        for (Edge edge: clipPolygonEdges) {
            out.clear();
            Point v1 = in.get(in.size()-1);
            for ( Point v2: in) {
                if(edge.isInside(v2)) {
                    if (!edge.isInside(v1)) {
                        out.add(edge.getIntersection(v1,v2));
                        out.add(v2);
                    }else {
                        if(edge.isInside(v1));
                        out.add(edge.getIntersection(v1,v2));
                    }
                    v1 = v2;
                }
            }
            in = out;
        }
        return in;
    }

    public static List<Edge> convertToEdges(List<Line> lines) {
        List<Edge> edges = new ArrayList<>();
        for (Line line : lines) {
            Edge edge = new Edge(line.getP1(), line.getP2(), line.getColor());
            edges.add(edge);
        }
        return edges;
    }
}