package rasterize;

public class DottedLineRasterizer extends LineRasterizer {

    public DottedLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        int a, x, y, p, k1, k2, dx, dy;
        dx = x2 - x1;
        dy = y2 - y1;
        a = 0;
        x = x1;
        y = y1;
        p = 2 * dy - dx;
        k1 = 2 * dy;
        k2 = 2 * (dy - dx);
        raster.setPixel(x, y, color);
        while (x < x2) {
            x = x + 1;
            if(p<0) {
                p = p + k1;
            } else {
                p = p + k2;
                y = y +1;
            }
                raster.setPixel(x, y, 0xff0000);
        }
    }
}
