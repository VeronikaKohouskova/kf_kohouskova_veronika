package fill;

import model.Edge;
import model.Point;
import model.Polygon;
import rasterize.Raster;

import java.util.ArrayList;
import java.util.List;

public class ScanLine implements Filler {

    private final Raster raster;
    private List<Point> points;
    private List<Edge> edges;
    private int fillColor;
    private int borderColor;


    public ScanLine(Raster raster, int fillColor, Polygon polygon) {
        this.raster = raster;
        this.points = polygon.getPoints();
        this.edges = LineUtils.convertToEdges(polygon.getLines());
        this.fillColor = fillColor;
        this.borderColor = polygon.getColor();
        fill();
    }

    @Override
    public void fill() {
        int minY = points.get(0).getY();
        int maxY = minY;

        for (int i = 1; i <= points.size() - 1; i++) {
            if(minY > points.get(i).getY()) {
                minY = points.get(i).getY();
            }
            if(maxY < points.get(i).getY()) {
                maxY = points.get(i).getY();
            }
        }
        List<Integer> intersections = new ArrayList<>();

        for (int y = minY; y < maxY; y++) {
            intersections.clear();
            for (Edge edge : edges) {
                if (edge.hasIntersection(y)) {
                    int x = edge.getIntersection(y);
                    intersections.add(x);
                }
            }
            
            for (int s = 0; s < intersections.size() - 1; s++) {
                for (int t = 0; t < intersections.size() - s - 1; t++) {
                    if (intersections.get(t) > intersections.get(t + 1)) {
                        int c = intersections.get(t);
                        intersections.set(t, intersections.get(t + 1));
                        intersections.set(t + 1, c);
                    }
                }
            }

            for (int i = 0; i < intersections.size() - 1; i += 2) {
                if (intersections.size() > 1) {
                    int p1 = intersections.get(i);
                    int p2 = intersections.get(i + 1);

                    for (int x = p1 + 1; x <= p2; x++) {
                        raster.setPixel(x, y, fillColor);
                    }
                }
            }
        }
    }
}
