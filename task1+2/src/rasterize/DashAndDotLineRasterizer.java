package rasterize;

public class DashAndDotLineRasterizer extends LineRasterizer {

    public DashAndDotLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        int err, dx, dy;
        int a = 0;
        err = 0;

        if (x1>x2) {
            int t = x1;
            x1 = x2;
            x2 = t;
            t = y1;
            y1 = y2;
            y2 = t;
        }
        dx = x2 - x1;
        dy = y2 - y1;
        if (dy<0) {
            do {
                if(a < 5 || a >= 15 && a <= 30) {
                    a++;
                    raster.setPixel(x1, y1, 0xff00ff);

                }else if (a < 15 || a < 40 && a > 30) {
                    a++;
                } else {
                    a=0;
                }
                if (err > 0) {
                    x1 = x1 + 1;
                    err = err - Math.abs(dy);
                } else {
                    y1 = y1 - 1;
                    err = err + dx;
                }
            } while (x1 <= x2 && y2 <= y1);

        } else {
            do {
                if(a < 5 || a >= 15 && a <= 30) {
                    a++;
                    raster.setPixel(x1, y1, 0xff00ff);

                }else if (a < 15 || a < 40 && a > 30) {
                    a++;
                } else {
                    a=0;
                }
                if (err > 0) {
                    x1 = x1 + 1;
                    err = err - dy;
                } else {
                    y1 = y1 + 1;
                    err = err + dx;
                }
            } while (x1 <= x2 && y1 <= y2);
        }
    }
}
