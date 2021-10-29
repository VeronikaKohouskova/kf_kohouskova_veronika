package control;

import fill.SeedFill;
import rasterize.*;
import view.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller2D implements Controller {

    private final Panel panel;

    private final Raster raster;

    private LineRasterizer trivialLineRasterizer;
    private SeedFill seedFiller;

    private int x,y;

    public Controller2D(Panel panel) {
        this.panel = panel;
        this.raster = panel.getRaster();
        initObjects(panel.getRaster());
        initListeners(panel);
    }

    public void initObjects(Raster raster) {
        trivialLineRasterizer = new FilledLineRasterizer(raster);

        Polygon polygon = new Polygon();

        seedFiller = new SeedFill(raster);
     }

     public void changeRasterizer(LineRasterizer rasterizer) {
        this.trivialLineRasterizer = rasterizer;
     }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isControlDown()) return;

                if (e.isShiftDown()) {
                    //TODO
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    x = e.getX();
                    y = e.getY();
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    //TODO
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    //TODO
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
                    raster.clear();
                    trivialLineRasterizer.rasterize(x,y,e.getX(),e.getY(), 0xffffff);
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    //TODO
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
                    //TODO
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
