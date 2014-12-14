package flg.rendering;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class HeatMap
extends JPanel {

    private static final long serialVersionUID = 1L;

    public final static int   PIXEL_SIZE       = 3;

    private float[][]         heights;

    public HeatMap(float[][] heights) {
        super();
        this.heights = heights;
    }

    @Override
    public void paint(Graphics g) {

        // draw the grid
        for (int i = 0; i < this.heights[0].length; i++) {
            for (int j = 0; j < this.heights[i].length; j++) {
                float height = this.heights[i][j];

                int x = PIXEL_SIZE * i;
                int y = PIXEL_SIZE * j;

                if (height <= -2) {
                    g.setColor(Color.decode("#06109a")); // dark blue
                    g.fill3DRect(x, y, PIXEL_SIZE, PIXEL_SIZE, false);
                } else if (height > -2 && height <= -1.5) {
                    g.setColor(Color.decode("#414df6")); // brighter blue
                    g.fill3DRect(x, y, PIXEL_SIZE, PIXEL_SIZE, false);
                } else if (height > -1.5 && height <= 0) {
                    g.setColor(Color.decode("#10b223")); // dark green
                    g.fillRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
                } else if (height > 0 && height <= 1.6) {
                    g.setColor(Color.decode("#2eea1c")); // brighter green
                    g.fillRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
                } else if (height > 1.6 && height <= 2.75) {
                    g.setColor(Color.GRAY);
                    g.fill3DRect(x, y, PIXEL_SIZE, PIXEL_SIZE, true);
                } else if (height > 2.75) {
                    g.setColor(Color.WHITE);
                    g.fill3DRect(x, y, PIXEL_SIZE, PIXEL_SIZE, true);
                }


            }
        }
    }
}
