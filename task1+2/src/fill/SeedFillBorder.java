package fill;

import model.Point;
import model.Polygon;
import rasterize.Raster;

public class SeedFillBorder extends SeedFill implements Filler {

    private int borderColor;

    public SeedFillBorder(Raster raster) {
        super(raster);
    }

    public void setSeed(Point seed, Polygon polygon) {
        seedX = seed.getX();
        seedY = seed.getY();;
        borderColor = polygon.getColor();
        backgroundColor = raster.getPixel(seed.getX(), seed.getY());
    }

   public boolean isValidForFill(Point point){
        return point.getX() > 0 && point.getY() > 0 && point.getX() < raster.getWidth() && point.getY()
                < raster.getHeight() && borderColor != raster.getPixel(point.getX(), point.getY()) &&
                fillColor != raster.getPixel(point.getX(), point.getY()) && fillColor != borderColor;
    }
}
