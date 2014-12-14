package flg.rendering;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class HeatMap
extends JPanel {

    private static final long serialVersionUID = 1L;

    public final static int   PIXEL_SIZE       = 5;

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

                if (height <= -2) {
                    g.setColor(Color.BLUE);
                } else if (height > -2 && height < 2) {
                    g.setColor(Color.GREEN);
                } else if (height >= 2) {
                    g.setColor(Color.WHITE);
                }


                int x = PIXEL_SIZE * i;
                int y = PIXEL_SIZE * j;

                g.fillRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
            }
        }
    }
}
