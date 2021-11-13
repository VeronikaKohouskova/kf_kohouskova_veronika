package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Triangle extends Polygon{


    public Triangle() {
        this(new ArrayList<>());
    }

    public Triangle(List<Point> points) {
        this(points, Color.MAGENTA.getRGB());
    }

    public Triangle(List<Point> points, int color) {
     super(points, color);
    }

    public Point getThirdPoint(Point point) {
        if (points.size() < 2){
            return null;
        }
        Point center = getCenter();
        double centerX = center.getX();
        double centerY = center.getY();


        double r = (Math.sqrt(Math.pow((points.get(1).getX()-points.get(0).getX()),2) + Math.pow((points.get(1).getY()-points.get(0).getY()),2))/2);
        double l = (Math.sqrt(Math.pow(point.getX()-centerX,2) + Math.pow(point.getY()-centerY,2)));
        double ratio = r/l;
        double newX = centerX+(point.getX()-centerX)*ratio;
        double newY = centerY+(point.getY()-centerY)*ratio;
        return new Point(newX,newY);

    }

    public Point getCenter() {
        double centerX = (points.get(0).getX()+points.get(1).getX())/2.0;
        double centerY = (points.get(0).getY()+points.get(1).getY())/2.0;

        return new Point(centerX,centerY);
    }

    public int getX1(){
     return points.get(0).getX();
    }
    public int getX2(){
        return points.get(1).getX();
    }
    public int getY1(){
        return points.get(0).getY();
    }

    public int getY2(){
        return points.get(1).getY();
    }

}
