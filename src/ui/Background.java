package ui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Background {
    public static class imgMainPanel extends JPanel {
        private Image img;

        public imgMainPanel(String imgurl) {
            this.img = new ImageIcon(getClass().getResource(imgurl)).getImage();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0,getWidth(),getHeight(), this);
            setOpaque(false);
        }
    }
}
