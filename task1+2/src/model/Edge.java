package model;

public class Edge {
    private Point p1, p2;

    public boolean isHorizontal() {
        return p1.getY() == p2.getY();
    }

    public void orientate() {
        if(p1.getY() > p2.getY()) {
            Point t = p1;
            p1 = p2;
            p2 = t;
        }
    }

    public boolean hasIntersection(int y) {
        return y < p2.getY() && y >= p1.getY();
    }

    public int getIntersection(int y) {
        return 0;
    }

    public boolean isInside(Point p) {
        Point t = new Point(p2.getX() - p1.getX(), p2.getY() - p1.getY());


        Point n = new Point(t.getY(), -t.getX());
        Point v = new Point(p.getX() - p1.getX(), p.getY() - p1.getY());

        return v.getX() * n.getX() + v.getY() * n.getY() < 0;
    }

    public Point getIntersection(Point p3, Point p4) {
        return new Point(0, 0);
    }

}
