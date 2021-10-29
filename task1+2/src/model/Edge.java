package model;

public class Edge {
    private int x1, y1, x2, y2;

    public boolean isHorizontal() {
        return false;
    }

    public void orientate() {
    }

    public boolean hasIntersection(int y) {
        return false;
    }

    public int getIntersection(int y) {
        return 0;
    }

    public boolean isInside(Point p) {
        Point t = new Point(x2 - x1, y2 - y1);


        Point n = new Point(t.y, -t.x);
        Point v = new Point(p.x - x1, p.y - y1);

        return v.x * n.x + v.y * n.y < 0;
    }

    public Point getIntersection(Point p3, Point p4) {
        return new Point(0, 0);
    }

}
