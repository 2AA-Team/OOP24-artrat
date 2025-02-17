package it.unibo.artrat.view.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * special label that contains an icon image.
 */
public class ImageLabel {
    private final JLabel label;

    /**
     * constructor for image label.
     * 
     * @param imagePath path for the image
     * @param x         x coordinate
     * @param y         y coordinate
     * @param height    image height
     * @param width     image width
     */
    public ImageLabel(final String imagePath, final int x, final int y, final int height, final int width) {
        label = new JLabel();
        try {
            final BufferedImage originalImage;
            originalImage = ImageIO.read(new File(imagePath));
            final Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(resizedImage));
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        label.setBounds(x, y, width, height);
    }

    /**
     * get the effective label.
     * 
     * @return the jlabel
     */
    public JLabel getJLabel() {
        return Objects.requireNonNull(label);
    }
}
