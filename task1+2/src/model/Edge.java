package model;

public class Edge extends Line{

    public Edge(Point p1, Point p2, int color) {
        super(p1, p2, color);
    }

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
        float k = (float) (p2.getX() - p1.getX()) / (float) (p2.getY() - p1.getY());
        float q = (float) p1.getX() - k * (float) p1.getY();
        return Math.round((int) (k * (float) y + q));
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
