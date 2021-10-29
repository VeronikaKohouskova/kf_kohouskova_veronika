package view;

import control.Controller2D;
import rasterize.DashAndDotLineRasterizer;
import rasterize.DottedLineRasterizer;
import rasterize.FilledLineRasterizer;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private final Panel panel;
    private Controller2D controller;

    public Window() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("UHK FIM PGRF : " + this.getClass().getName());


        panel = new Panel();

        JRadioButton rButton = new JRadioButton();
        rButton.setText("Trivial line");
        rButton.setBackground(Color.LIGHT_GRAY);
        rButton.addActionListener(l -> {
            controller.changeRasterizer(new FilledLineRasterizer(controller.getRaster()));
        });
        JRadioButton rButton2 = new JRadioButton();
        rButton2.setText("Dotted line");
        rButton2.setBackground(Color.LIGHT_GRAY);
        rButton2.addActionListener(l -> {
            controller.changeRasterizer(new DottedLineRasterizer(controller.getRaster()));
        });

        JRadioButton rButton3 = new JRadioButton();
        rButton3.setText("Dash-and-dot line");
        rButton3.setBackground(Color.LIGHT_GRAY);
        rButton3.addActionListener(l -> {
            controller.changeRasterizer(new DashAndDotLineRasterizer(controller.getRaster()));
        });

        JRadioButton rButton4 = new JRadioButton();
        rButton4.setText("Polygon");
        rButton4.setBackground(Color.LIGHT_GRAY);

        JButton button = new JButton();
        button.setText("Clear");

        JPanel p = new JPanel();
        p.setBackground(Color.LIGHT_GRAY);
        p.add(rButton);
        p.add(rButton2);
        p.add(rButton3);
        p.add(rButton4);
        p.add(button);
        this.add(p, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);

        setVisible(true);
        pack();

        setLocationRelativeTo(null);

        // lepší až na konci, aby to neukradla nějaká komponenta v případně složitějším UI
        panel.setFocusable(true);
        panel.grabFocus(); // důležité pro pozdější ovládání z klávesnice
    }

    public Panel getPanel() {
        return panel;
    }

    public void setController(Controller2D controller) {
        this.controller = controller;
    }
}
