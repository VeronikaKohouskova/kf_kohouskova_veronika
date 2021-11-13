package fill;

import model.Point;
import rasterize.Raster;

import java.util.ArrayDeque;
import java.util.Queue;

public class SeedFill implements Filler {
    protected final Raster raster;
    protected int seedX, seedY;
    protected int backgroundColor;
    protected int fillColor;

    public SeedFill(Raster raster) {
        this.raster = raster;
    }

    public void setSeed(Point seed) {
        seedX = seed.getX();
        seedY = seed.getY();
        backgroundColor = raster.getPixel(seedX, seedY);
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;

    }

    public int getFillColor() {
        return fillColor;
    }

    public boolean isValidForFill(Point point){
        return point.getX() > 0 && point.getY() > 0 && point.getX() < raster.getWidth() && point.getY()
                < raster.getHeight() && backgroundColor == raster.getPixel(point.getX(), point.getY());
    }

    @Override
    public void fill() {
       Queue<Point> queue  = new ArrayDeque<>();
        queue.add(new Point(seedX, seedY));
        seed(queue);
    }

    public void seed(Queue<Point> queue) {
        while (! queue.isEmpty()){
            Point point = queue.poll();
            if(isValidForFill(point)) {
                raster.setPixel(point.getX(), point.getY(), fillColor);
                queue.add(new Point(point.getX() + 1, point.getY()));
                queue.add(new Point(point.getX() - 1, point.getY()));
                queue.add(new Point(point.getX(), point.getY() + 1));
                queue.add(new Point(point.getX(), point.getY() - 1));
            }
        }
    }
}
