package rasterize;

import model.Triangle;

import java.awt.*;

public class TriangleRasterizer extends PolygonRasterizer{

    public void rasterize (Triangle triangle){
        super.rasterize(triangle);
        if (triangle.getLines().size()<3){
            rasterizeCircle(triangle);
        }
    }

    public void rasterizeCircle(Triangle triangle) {
        int x = 0;
        int r = (int) (Math.sqrt(Math.pow((triangle.getY1()-triangle.getX1()),2) + Math.pow((triangle.getY2()-triangle.getX2()),2))/2);
        int y = r;
        int dveY = 2*r-1;
        int dveX = 1;
        int err = 0;

        while(x<y) {
            raster.setPixel(triangle.getX1() + x, triangle.getY1() + y, triangle.getColor());
            raster.setPixel(triangle.getX1() + y, triangle.getY1() - x, triangle.getColor());
            raster.setPixel(triangle.getX1() - x, triangle.getY1() - y, triangle.getColor());
            raster.setPixel(triangle.getX1() - y, triangle.getY1() + x, triangle.getColor());
            x++;
            err += dveX;
            dveX += 2;
            if (dveY < 2 * err) {
                y--;
                err -= dveY;
                dveY -= 2;
            }
            if (x > y) break;
            raster.setPixel(triangle.getX1() + y, triangle.getY1() + x, triangle.getColor());
            raster.setPixel(triangle.getX1() + x, triangle.getY1() - y, triangle.getColor());
            raster.setPixel(triangle.getX1() - y, triangle.getY1() - x, triangle.getColor());
            raster.setPixel(triangle.getX1() - x, triangle.getY1() + y, triangle.getColor());
        }

    }
}
