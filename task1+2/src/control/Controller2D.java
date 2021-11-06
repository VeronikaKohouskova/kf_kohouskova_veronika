package control;

import fill.SeedFill;
import rasterize.*;
import view.Panel;
import model.Polygon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller2D implements Controller {

    private final Panel panel;

    private final Raster raster;

    private LineRasterizer filledLineRasterizer;
    private SeedFill seedFiller;

    private int x,y;
    private Polygon polygon;
    private PolygonRasterizer polygonRasterizer;
    private Rasterizer rasterizer;

    public Controller2D(Panel panel) {
        this.panel = panel;
        this.raster = panel.getRaster();
        initObjects(panel.getRaster());
        initListeners(panel);
    }

    public void initObjects(Raster raster) {
        filledLineRasterizer = new FilledLineRasterizer(raster);
        polygon = new Polygon();
        polygonRasterizer = new PolygonRasterizer();

        seedFiller = new SeedFill(raster);
     }

     public void changeRasterizer(Rasterizer rasterizer) {
        this.rasterizer = rasterizer;
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
                      } else if (rasterizer instanceof PolygonRasterizer) {

                          model.Point point = new model.Point(e.getX(), e.getY());
                          polygon.addPoints(point);
                    }

                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isRightMouseButton(e)) {
                 if (rasterizer instanceof PolygonRasterizer) {
                     polygonRasterizer.setLineRasterizer(new FilledLineRasterizer(getRaster()));
                     changeRasterizer(polygonRasterizer);
                    ((PolygonRasterizer)rasterizer).rasterize(polygon);
                    polygon.clearPoints();
                }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown()) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        //TODO
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        seedFiller.setSeed(new model.Point(e.getX(),e.getY()));
                        seedFiller.setFillColor(Color.YELLOW.getRGB());
                        seedFiller.fill();

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
                        ((LineRasterizer)rasterizer).rasterize(x,y,e.getX(),e.getY(), 0xffffff);}
                   else if(rasterizer instanceof PolygonRasterizer) {
                        polygonRasterizer.setLineRasterizer(new DottedLineRasterizer(getRaster()));
                        changeRasterizer(polygonRasterizer);
                        ((PolygonRasterizer)rasterizer).rasterize(polygon);
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
                // na klávesu C vymazat plátno
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    raster.clear();
                    polygon.clearPoints();
                    update();
                }
                if (e.getKeyCode() == KeyEvent.VK_T) {
                    update();
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
//        panel.clear();
        //TODO

    }

    private void hardClear() {
        panel.clear();
    }

    public Raster getRaster() {
        return raster;
    }

}
