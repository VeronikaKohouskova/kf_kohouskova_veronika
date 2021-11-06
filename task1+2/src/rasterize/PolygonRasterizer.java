package rasterize;

import model.Line;
import model.Polygon;

import java.util.List;

public class PolygonRasterizer implements Rasterizer<Polygon> {
    LineRasterizer lineRasterizer;
    Raster raster;

    @Override public void rasterize(Polygon polygon) {
        List<Line> lines = polygon.getLines();

        for (Line line : lines){
            lineRasterizer.rasterize(line);
        }
    }

    public void setRaster(Raster raster) {
        this.raster = raster;
    }

    public void setLineRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }
}
