package rasterize;

public class DashAndDotLineRasterizer extends LineRasterizer {

    public DashAndDotLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        int err, dx, dy;
        dx = x2 - x1;
        dy = y2 - y1;
        err = 0;

        do {
            raster.setPixel(x1, y1, 0xff00ff);
            if (err > 0) {
                x1 = x1 + 1;
                err = err - dy;
            } else {
                y1 = y1 + 1;
                err = err + dx;
            }
        } while ((x1 <= x2) && (y1 <= y2));

    }
}
