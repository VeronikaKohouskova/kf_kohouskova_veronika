package rasterize;

public class DottedLineRasterizer extends LineRasterizer {

    public DottedLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        int a, x, y, p, xk1, xk2, dx, dy, yk1, yk2;
        dx = x2 - x1;
        dy = y2 - y1;
        a = 0;
        x = x1;
        y = y1;
        p = 2 * dy - dx;
        xk1 = 2 * dy;
        xk2 = 2 * (dy - dx);
        yk1 = 2 * dx;
        yk2 = 2 * (dx - dy);
        raster.setPixel(x, y, color);
        if (Math.abs(dy) < Math.abs(dx)) {
            if (x2 < x1) {
                int t = x1;
                x1 = x2;
                x2 = t;
                t = y1;
                y1 = y2;
                y2 = t;
            }
                while (x < x2) {
                    x = x + 1;
                    if (p < 0) {
                        p = p + xk1;
                    } else {
                        p = p + xk2;
                        y = y + 1;
                    }
                    raster.setPixel(x, y, 0xff0000);
                }

        } else {
            if (y2<y1) {
                int t = x1;
                x1 = x2;
                x2 = t;
                t = y1;
                y1 = y2;
                y2 = t;
            }
            while (y < y2) {
                y = y + 1;
                if (p <  0) {
                    p = p + yk1;
                } else {
                    p = p + yk2;
                    x = x + 1;
                }
                raster.setPixel(x, y, 0x00ff00);
            }
        }
    }

}
