package model;

public class Line {

    private final Point p1, p2;
    private final int color;

    public Line(int x1, int y1, int x2, int y2, int color) {
        this.p1 = new Point(x1,y1);
        this.p2 = new Point(x2,y2);
        this.color = color;
    }

    public Line(Point p1, Point p2, int color) {
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;

  }

    public int getX1() {
        return p1.getX();
    }

    public int getY1() {
        return p1.getY();
    }

    public int getX2() {
        return p2.getX();
    }

    public int getY2() {
        return p2.getY();
    }

    public int getColor() {
        return color;
    }

}
