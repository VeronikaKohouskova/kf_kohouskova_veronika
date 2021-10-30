package fill;

import model.Edge;
import model.Point;
import rasterize.Raster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanLine implements Filler {

    private final Raster raster;
    private final List<Point> points;
    private final int fillColor;
    private final int borderColor;

    public ScanLine(Raster raster, List<Point> points,int fillColor, int borderColor) {
        this.raster = raster;
        this.points = points;
        this.fillColor = fillColor;
        this.borderColor = borderColor;

    }

    @Override
    public void fill() {
        List<Edge> edges = new ArrayList<>();

        int minY = points.get(0).getY();
        int maxY = minY;

        for (int y = minY; y<= maxY; y++) {
            List<Integer> intersections = new ArrayList<>();

            Collections.sort(intersections);
        }



    }
}
