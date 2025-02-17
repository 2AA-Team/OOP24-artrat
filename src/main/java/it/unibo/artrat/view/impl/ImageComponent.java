package it.unibo.artrat.view.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageComponent extends JComponent {
    private BufferedImage image;
    private int x, y, width, height;

    /**
     * constructor to define an image component.
     * 
     * @param imagePath path for the image to draw
     * @param x         x coordinate
     * @param y         y coordinate
     * @param width     width of the image to draw
     * @param height    height of the image to draw
     * @throws IOException if image reading fails
     */
    public ImageComponent(String imagePath, int x, int y, int width, int height) throws IOException {
        this.image = ImageIO.read(new File(imagePath));
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width + x, height + y));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, x, y, width, height, this);
        }
    }
}
