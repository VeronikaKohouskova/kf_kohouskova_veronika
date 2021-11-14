package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Polygon {

    protected final List<Point> points;
    protected final int color;


    private boolean finished = false;

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
        if (points.size() > 1) {
            for (int i = 0; i < points.size(); i++) {
                int j = i + 1;
                if (j == points.size()) {
                    j = 0;
                }
                Line l = new Line(points.get(i), points.get(j), this.color);
                lines.add(l);
            }
        }
        return lines;
    }

    public void addPoints(Point... pointsToAdd) { // vararg java
        points.addAll(Arrays.asList(pointsToAdd));
    }

    public void addPoints(List<Point> pointsToAdd) {
        points.addAll(pointsToAdd);
    }

    public void replaceAllPoints(List<Point> pointsToReplace, List<Point> replacedPoints) {
        pointsToReplace.clear();
        addPoints(replacedPoints);
    }

    public void replaceLastPoint(Point newPoint) {
        if (points.size() > 0) {
            points.set(points.size() - 1, newPoint);
        } else {
            points.add(newPoint);
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void clearPoints() {
        points.clear();
        finished = false;

    }

    public int getColor() {
        return color;
    }

    public List<Point> getPoints() {
        return points;
    }

    public int size(){
        return points.size();
    }
}
