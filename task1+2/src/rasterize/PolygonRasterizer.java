package rasterize;

import model.Line;
import model.Polygon;

import java.util.List;

public class PolygonRasterizer implements Rasterizer<Polygon> {
    LineRasterizer lineRasterizer;
    LineRasterizer temporaryRasterizer;
    Raster raster;

    @Override public void rasterize(Polygon polygon) {
        List<Line> lines = polygon.getLines();
        if(lines.size() == 2) {
            temporaryRasterizer.rasterize(lines.get(0));

        } else {
            for (int i = 0; i < lines.size(); i++) {
                if (i < lines.size() - 2 || polygon.isFinished()) {
                    lineRasterizer.rasterize(lines.get(i));
                } else{
                    temporaryRasterizer.rasterize(lines.get(i));
                }
            }
        }
    }

    public void setRaster(Raster raster) {
        this.raster = raster;
    }

    public void setLineRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }

    public void setTemporaryRasterizer (LineRasterizer lineRasterizer) {
        this.temporaryRasterizer = lineRasterizer;
    }
}
