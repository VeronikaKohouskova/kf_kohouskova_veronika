package control;

import fill.ScanLine;
import fill.SeedFill;
import fill.SeedFillBorder;
import model.Triangle;
import rasterize.*;
import view.Panel;
import model.Polygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Controller2D implements Controller {

    private final Panel panel;

    private final RasterBufferedImage raster;

    private LineRasterizer filledLineRasterizer;
    private SeedFill seedFiller;
    private SeedFillBorder seedFillBorder;
    private ScanLine scanLine;

    private int x, y;
    private Polygon polygon;
    private List<Polygon> polygons;
    private Clipper clipper;
    private Triangle triangle;
    private PolygonRasterizer polygonRasterizer;
    private TriangleRasterizer triangleRasterizer;
    private Rasterizer rasterizer;

    public Controller2D(Panel panel) {
        this.panel = panel;
        this.raster = (RasterBufferedImage) panel.getRaster();
        initObjects(panel.getRaster());
        initListeners(panel);
    }

    public void initObjects(Raster raster) {
        filledLineRasterizer = new FilledLineRasterizer(raster);
        polygon = new Polygon();
        triangle = new Triangle();
        polygons = new ArrayList<>();
        clipper = new Clipper();
        polygonRasterizer = new PolygonRasterizer();
        triangleRasterizer = new TriangleRasterizer();
        triangleRasterizer.setLineRasterizer(new FilledLineRasterizer(raster));
        triangleRasterizer.setRaster(raster);
        triangleRasterizer.setTemporaryRasterizer(new DashAndDotLineRasterizer(raster));
        seedFillBorder = new SeedFillBorder(raster);
        seedFiller = new SeedFill(raster);
    }

    public void changeRasterizer(Rasterizer rasterizer) {
        this.rasterizer = rasterizer;
        raster.clear();
        if(polygons!= null) {
          polygon.clearPoints();
            polygons.clear();
        }
        if (triangle != null) {
            triangle.clearPoints();
        }
        panel.grabFocus();
    }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    if (rasterizer instanceof LineRasterizer) {
                        x = e.getX();
                        y = e.getY();
                    } else if (rasterizer instanceof TriangleRasterizer) {
                        if (triangle.getPoints().size() < 3) {
                            if (triangle.getPoints().size() == 0) {
                                triangle.addPoints(new model.Point(e.getX(), e.getY()));
                            }
                            model.Point point = new model.Point(e.getX(), e.getY());
                            triangle.addPoints(point);
                        }

                    } else if (rasterizer instanceof PolygonRasterizer) {
                       if (polygon.getPoints().size() == 0) {
                           polygon.addPoints(new model.Point(e.getX(), e.getY()));
                        }
                        model.Point point = new model.Point(e.getX(), e.getY());
                        polygon.addPoints(point);

                    }

                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    if (rasterizer instanceof PolygonRasterizer) {
                    polygon.setFinished(true);
                    polygons.add(polygon);

                        if(polygons.size()>=2) {

                        polygons.get(polygons.size() - 2).replaceAllPoints(polygons.get(polygons.size() - 2).getPoints(), clipper.clip(polygons.get(polygons.size() - 2), polygons.get(polygons.size() - 1)));
                        }
                        for(Polygon polygon : polygons){
                            ((PolygonRasterizer) rasterizer).rasterize(polygon);
                        }
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    if (rasterizer instanceof PolygonRasterizer) {
                        polygon.setFinished(true);
                        polygons.add(polygon);
                        for(Polygon polygon : polygons){
                            ((PolygonRasterizer) rasterizer).rasterize(polygon);
                        }

                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (rasterizer instanceof TriangleRasterizer) {
                        if (triangle.getPoints().size() == 3) {
                            panel.clear();
                            triangle.setFinished(true);
                            ((TriangleRasterizer) rasterizer).rasterize(triangle);
                            triangle.clearPoints();
                        }
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        scanLine = new ScanLine(raster,Color.PINK.getRGB(),polygon);
                        ((PolygonRasterizer) rasterizer).rasterize(polygon);
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        seedFiller.setSeed(new model.Point(e.getX(), e.getY()));
                        seedFiller.setFillColor(Color.BLUE.getRGB());
                        seedFiller.fill();
                    } else if (SwingUtilities.isMiddleMouseButton(e)) {
                        Polygon object;
                        if (rasterizer instanceof TriangleRasterizer) {
                            object = triangle;
                        } else if (rasterizer instanceof PolygonRasterizer) {
                            object = polygon;
                        } else {
                            return;
                        }
                        seedFillBorder.setSeed(new model.Point(e.getX(), e.getY()), object);
                        seedFillBorder.setFillColor(Color.CYAN.getRGB());
                        seedFillBorder.fill();
                    }
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    if (rasterizer instanceof LineRasterizer) {
                        raster.clear();
                        ((LineRasterizer) rasterizer).rasterize(x, y, e.getX(), e.getY(), 0xffffff);
                    } else if (rasterizer instanceof TriangleRasterizer) {
                        if (triangle.getPoints().size()>2) {
                            model.Point eventPoint = new model.Point(e.getX(), e.getY());
                            model.Point adjustedPoint = triangle.getThirdPoint(eventPoint);
                            triangle.replaceLastPoint(adjustedPoint);
                        }
                        else {
                            triangle.replaceLastPoint(new model.Point(e.getX(),e.getY()));
                        }
                        raster.clear();

                        ((TriangleRasterizer) rasterizer).rasterize(triangle);
                    } else if (rasterizer instanceof PolygonRasterizer) {
                        polygon.replaceLastPoint(new model.Point(e.getX(), e.getY()));
                        raster.clear();
                        ((PolygonRasterizer) rasterizer).rasterize(polygon);
                        if(polygon.isFinished()) {
                            polygon.clearPoints();
                        }
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {

                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                }
                update();
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    raster.clear();
                    if(polygons!= null) {
                        polygon.clearPoints();
                        polygons.clear();
                    }
                    if (triangle != null) {
                        triangle.clearPoints();
                    }
                    update();
                }
                if (e.getKeyCode() == KeyEvent.VK_T) {
                    changeRasterizer(triangleRasterizer);
                }

            }
        });

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
            }
        });
    }

    private void update() {
        if (panel.getGraphics() != null)
            panel.repaint();
    }


    private void hardClear() {
        panel.clear();
    }

    public Raster getRaster() {
        return raster;
    }

}
