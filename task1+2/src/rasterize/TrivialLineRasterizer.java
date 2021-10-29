package rasterize;

public class TrivialLineRasterizer extends LineRasterizer {

    public TrivialLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        float k = (y2 - y1) / (float) (x2 - x1);
        float q = y1 - k * x1;

        if (k<1 && k>-1) {
        for (int x = x1; x <= x2; x++) {
            float y = k * x + q;
            raster.setPixel(x, Math.round(y), color);
            }
        } else {
            for (int y = y1; y<=y2; y++) {
                float x = (y - q) / k;
                raster.setPixel(Math.round(x), y, color);
            }
        }
    }
}

