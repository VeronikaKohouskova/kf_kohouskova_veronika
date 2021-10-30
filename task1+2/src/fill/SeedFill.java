package fill;

import model.Point;
import rasterize.Raster;

public class SeedFill implements Filler {

    private final Raster raster;
    private int seedX, seedY;
    private int backgroudColor;
    private int fillColor;

    public SeedFill(Raster raster) {
        this.raster = raster;
    }

    public void setSeed(Point seed) {
        seedX = seed.getX();
        seedY = seed.getY();
        backgroudColor = raster.getPixel(seedX, seedY);
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;

    }


    @Override
    public void fill() {
    seed(seedX, seedY);
    }

    private void seed(int x, int y) {
        if(backgroudColor == raster.getPixel(x,y)) {
       raster.setPixel(x, y, fillColor);
       seed(x + 1, y);
       seed(x - 1, y);
       seed(x, y + 1);
       seed(x, y - 1);
        }
    }
}
