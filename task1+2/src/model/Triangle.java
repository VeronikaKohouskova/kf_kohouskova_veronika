package model;

public class Triangle extends Polygon{

    private final int x1, y1, x2, y2, x3, y3;
    private final int color;

    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, int color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.color = color;
    }

  //  public Line(Point p1, Point p2, int color) {
   //     this.p1 = p1;
   //     this.p2 = p2;
   //     this.color = color;

  // }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getX3() {
        return x3;
    }

    public int getY3() {
        return y3;
    }

    public int getColor() {
        return color;
    }

}
