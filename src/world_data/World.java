package world_data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class World extends JPanel {
    
    // size of map
    public static final int NUM_COLS = 640;
    public static final int NUM_ROWS = 400;
    
    //pixels of a tile
    public static final int PREFERRED_GRID_SIZE_PIXELS = 2;
    
    //size of map
    int preferredWidth = NUM_COLS * PREFERRED_GRID_SIZE_PIXELS;
    int preferredHeight = NUM_ROWS * PREFERRED_GRID_SIZE_PIXELS;
    
    private double zoom = 1.0;

    private double percentage;
    
    private TileMap map;

    
    
    public World(){
    	LoadMap NewMap = new LoadMap();
    	createloadmap(NewMap.loadmap());
    	
//    	this.getMap();
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

        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                // Upper left corner of this terrain rect
                int x = i * rectWidth;
                int y = j * rectHeight;
                Color terrainColor = map.getTile(i, j).getColer();
                g.setColor(terrainColor);
                g.fillRect(x, y, rectWidth, rectHeight);
            }
        }
        
//        Graphics2D g2D = (Graphics2D) g;
//        g2D.scale(zoom, zoom);
//        g2D.drawImage(image, 0, 0, this);
        
    }
    
    public void getMap(){
    	map = new TileMap(NUM_COLS, NUM_ROWS);
    }
    
    public void createloadmap(int[][] is){
    	map = new TileMap(is);
    }

}

/*

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

class ImagePanel extends JPanel {
  private double zoom = 1.0;

  private double percentage;

  private Image image;

  public ImagePanel(Image image, double zoomPercentage) {
    this.image = image;
    percentage = zoomPercentage / 100;
  }

  public void paintComponent(Graphics grp) {
    Graphics2D g2D = (Graphics2D) grp;
    g2D.scale(zoom, zoom);
    g2D.drawImage(image, 0, 0, this);
  }

  public void setZoomPercentage(int zoomPercentage) {
    percentage = ((double) zoomPercentage) / 100;
  }

  public void originalSize() {
    zoom = 1;
  }

  public void zoomIn() {
    zoom += percentage;
  }

  public void zoomOut() {
    zoom -= percentage;

    if (zoom < percentage) {
      if (percentage > 1.0) {
        zoom = 1.0;
      } else {
        zoomIn();
      }
    }
  }
}

*/