package world_data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class World extends JPanel {
    
    // size of map
    public static final int NUM_ROWS = 100;
    public static final int NUM_COLS = 100;
    
    //pixels of a tile
    public static final int PREFERRED_GRID_SIZE_PIXELS = 10;
    
    //size of map
    int preferredWidth = NUM_COLS * PREFERRED_GRID_SIZE_PIXELS;
    int preferredHeight = NUM_ROWS * PREFERRED_GRID_SIZE_PIXELS;
    
    private TileMap map;

    
    
    public World(){
    	getMap();
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
    }
    
    

    @Override
    public void paintComponent(Graphics g) {
        // Important to call super class method
        super.paintComponent(g);
        // Clear the board
        g.clearRect(0, 0, getWidth(), getHeight());
        // Draw the grid
        int rectWidth = getWidth() / NUM_COLS;
        int rectHeight = getHeight() / NUM_ROWS;

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                // Upper left corner of this terrain rect
                int x = i * rectWidth;
                int y = j * rectHeight;
                Color terrainColor = map.getTile(i, j).getColer();
                g.setColor(terrainColor);
                g.fillRect(x, y, rectWidth, rectHeight);
            }
        }
    }
    
    public void getMap(){
    	map = new TileMap(NUM_ROWS, NUM_COLS);
    }

}
