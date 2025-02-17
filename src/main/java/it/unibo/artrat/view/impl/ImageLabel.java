package it.unibo.artrat.view.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel {
    private JLabel label;

    public ImageLabel(String imagePath, int x, int y, int height, int width) {
        label = new JLabel();
        try {
            BufferedImage originalImage;
            originalImage = ImageIO.read(new File(imagePath));
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(resizedImage));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        label.setBounds(x, y, width, height);
    }

    public JLabel getJLabel() {
        return this.label;
    }
}
