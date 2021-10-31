package rasterize;

public class DottedLineRasterizer extends LineRasterizer {

    public DottedLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        float k = (y2 - y1) / (float) (x2 - x1);
        float q = y1 - k * x1;
        int a = 0;

        if (k<1 && k>-1) {
            if(x2<x1) {
                int t = x1;
                x1 = x2;
                x2 = t;
                t = y1;
                y1 = y2;
                y2 = t;
            }
        for (int x = x1; x <= x2; x++) {
            float y = k * x + q;
            a++;
            if (a%5 == 0) {
                raster.setPixel(x, Math.round(y), color);
            }
            }
        } else {
            if(y2<y1) {
                int t = x1;
                x1 = x2;
                x2 = t;
                t = y1;
                y1 = y2;
                y2 = t;
            }
            for (int y = y1; y<=y2; y++) {
                float x = (y - q) / k;
                a++;
                if (a%5 == 0) {
                    raster.setPixel(Math.round(x),y, color);
                }
            }
        }
    }
}

