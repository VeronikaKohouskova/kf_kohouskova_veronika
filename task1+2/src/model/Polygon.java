package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polygon {

    private final List<Point> points;
    private final int color;


    public Polygon() {
        this(new ArrayList<>());
    }

    public Polygon(List<Point> points) {
        this(points, Color.MAGENTA.getRGB());
    }

    public Polygon(List<Point> points, int color) {
        this.points = points;
        this.color = color;
    }

    public List<Line> getLines () {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i <points.size(); i++) {
            int j = i + 1;
            if (j == points.size()) {
                j = 0;
            }
            Line l = new Line(points.get(i), points.get(j), this.color);
            lines.add(l);
        }
        return lines;
    }

    public void addPoints(Point... pointsToAdd) { // vararg java
        points.addAll(Arrays.asList(pointsToAdd));
    }

    public void addPoints(List<Point> pointsToAdd) {
        points.addAll(pointsToAdd);
    }


    public void clearPoints() {
        points.clear();
    }
}
