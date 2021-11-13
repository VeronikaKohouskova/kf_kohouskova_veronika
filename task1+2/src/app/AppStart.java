package app;

import control.Controller2D;
import rasterize.FilledLineRasterizer;
import view.Window;

import javax.swing.*;

public class AppStart {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           Window window = new Window();
            Controller2D controller = new Controller2D(window.getPanel());
            window.setController(controller);
            controller.changeRasterizer(new FilledLineRasterizer(controller.getRaster()));
            window.setVisible(true);
        });
        // https://www.google.com/search?q=SwingUtilities.invokeLater
        // https://www.javamex.com/tutorials/threads/invokelater.shtml
    }

}
