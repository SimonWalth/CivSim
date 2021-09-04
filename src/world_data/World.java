package world_data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JPanel;

import creature.creature;

public class World extends JPanel {
    
    // size of map
    public static final int NUM_COLS = 160;
    public static final int NUM_ROWS = 100;
    
    //pixels of a tile
    public static final int PREFERRED_GRID_SIZE_PIXELS = 10;
    
    //size of map
    int preferredWidth = NUM_COLS * PREFERRED_GRID_SIZE_PIXELS;
    int preferredHeight = NUM_ROWS * PREFERRED_GRID_SIZE_PIXELS;
    
    private double zoom = 1.0;

    public static final double ZOOM_INTERVALL = 0.5;
    
    int xdiff, ydiff = 0;
    public static final int MOVE_INTERVALL = 50;
    
    private TileMap map;

    private LoadMap NewMap;
    
    private LinkedList<creature> creatureList = new LinkedList<creature>();
    
    private List<creature> deadcreatures = new ArrayList<creature>();
    private List<creature> reproducecreatures = new ArrayList<creature>();
    
    private DeepCopy dc = new DeepCopy();
    
    public World(LoadMap NewMap){
    	this.NewMap = NewMap;
    	createloadmap(NewMap.getMap());
    	
//    	this.getMap();
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
    }
    
    

    @Override
    public void paintComponent(Graphics g) {
        // Important to call super class method
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.scale(zoom, zoom);
        
        // Clear the board
        g2D.clearRect(0, 0, getWidth(), getHeight());
        // Draw the grid
        int rectWidth = getWidth() / NUM_COLS;
        int rectHeight = getHeight() / NUM_ROWS;

        for (int i = 0; i < NUM_COLS; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                // Upper left corner of this terrain rect
                int x = i * rectWidth- xdiff;
                int y = j * rectHeight - ydiff;
                Color terrainColor = map.getTile(i, j).getColer();
                g2D.setColor(terrainColor);
                g2D.fillRect(x, y, rectWidth, rectHeight);
            }
        }
        
        LinkedList<creature> clonedcreatureList = new LinkedList<creature>();
        
        if(!creatureList.isEmpty()){
        
        	clonedcreatureList = (LinkedList<creature>) creatureList.clone();
        
        	ListIterator<creature> listIterator = clonedcreatureList.listIterator();
        	int i=0;
        	while (listIterator.hasNext()) {
        		int angle = clonedcreatureList.get(i).getangle();
        		g2D.setColor(clonedcreatureList.get(i).getColor());
        		g2D.fillArc(clonedcreatureList.get(i).getx()- xdiff, clonedcreatureList.get(i).gety()- ydiff, 10, 10, angle-15 , 30);
        		listIterator.next();
        		i++;
//				System.out.println("Number of creatures:" + i);
        	}
        }
        
//        Graphics2D g2D = (Graphics2D) g;
//        g2D.scale(zoom, zoom);
//        g2D.drawImage(image, 0, 0, this);
        
    }
    
//    public void getMap(){
//    	map = new TileMap(NUM_COLS, NUM_ROWS);
//    }
    
    public void createloadmap(int[][] is){
    	map = new TileMap(is);
    }
    
	public void zoomout(){
		if(zoom!=1){
		zoom = zoom- ZOOM_INTERVALL;
		this.repaint();
		}
	}
	
	public void zoomin(){
		zoom = zoom+ ZOOM_INTERVALL;
		this.repaint();
	}
	
	public void moveUp(){
		if(ydiff!=0){
		ydiff = ydiff -MOVE_INTERVALL;
		this.repaint();
		}
	}
	
	public void moveDown(){
		if(PREFERRED_GRID_SIZE_PIXELS*NUM_ROWS>ydiff){
		ydiff = ydiff +MOVE_INTERVALL;
		this.repaint();
		}
	}
	
	public void moveRight(){
		if(PREFERRED_GRID_SIZE_PIXELS*NUM_COLS>xdiff){
		xdiff = xdiff +MOVE_INTERVALL;
		this.repaint();
		}
	}
	
	public void moveLeft(){
		if(xdiff!=0){
		xdiff = xdiff -MOVE_INTERVALL;
		this.repaint();
		}
	}
	
	public void resetPos(){
		xdiff=ydiff = 0;
		zoom = 1;
		this.repaint();
	}
	
	
	
	public void drawcreatures(LinkedList<creature> creatureList){
		this.creatureList = creatureList;
//		LinkedList<creature> creatureList = 
//		Iterator<creature> itr=al.iterator();  
//		  while(itr.hasNext()){  
//		   System.out.println(itr.next());  
//		  }
	}
	
	public void initializecreatures(){
		ListIterator<creature> listIterator = creatureList.listIterator();
		Tile pointingTile = null;
		Tile currentTile = null;
		int i=0;
		while (listIterator.hasNext()){
			
			int angle = creatureList.get(i).getangle();
			int x = creatureList.get(i).getx();
			int y = creatureList.get(i).gety();
			
			if(angle>45){
				if(angle< 135){
					pointingTile = map.getTile(x/PREFERRED_GRID_SIZE_PIXELS, y/PREFERRED_GRID_SIZE_PIXELS+1);
				}
				else if(angle<225){
					pointingTile = map.getTile(x/PREFERRED_GRID_SIZE_PIXELS+1, y/PREFERRED_GRID_SIZE_PIXELS);
				}
				else if(angle<315){
					pointingTile = map.getTile(x/PREFERRED_GRID_SIZE_PIXELS, y/PREFERRED_GRID_SIZE_PIXELS-1);
				}
				else{
					pointingTile = map.getTile(x/PREFERRED_GRID_SIZE_PIXELS-1, y/PREFERRED_GRID_SIZE_PIXELS);
				}
			}
			else{
				pointingTile = map.getTile(x/PREFERRED_GRID_SIZE_PIXELS-1, y/PREFERRED_GRID_SIZE_PIXELS);
			}
			
			currentTile = map.getTile(x/PREFERRED_GRID_SIZE_PIXELS, y/PREFERRED_GRID_SIZE_PIXELS);
			
			// tell tile creature is on it
			
			map.getTile(x/PREFERRED_GRID_SIZE_PIXELS, y/PREFERRED_GRID_SIZE_PIXELS).addCreature(creatureList.get(i));
			
			creatureList.get(i).getTileInfo(currentTile, pointingTile);
			
			i++;
			listIterator.next();
		}
	}
	
	public void creaturesupdate(){
		ListIterator<creature> listIterator = creatureList.listIterator();
		Tile pointingTile = null;
		Tile currentTile = null;
        int i=0;
        while (listIterator.hasNext()) {
//			listIterator.next();
			int angle = creatureList.get(i).getangle();
			int x = creatureList.get(i).getx();
			int y = creatureList.get(i).gety();
			
			if(angle>45){
				if(angle< 135){
					pointingTile = map.getTile(x/PREFERRED_GRID_SIZE_PIXELS, y/PREFERRED_GRID_SIZE_PIXELS+1);
				}
				else if(angle<225){
					pointingTile = map.getTile(x/PREFERRED_GRID_SIZE_PIXELS+1, y/PREFERRED_GRID_SIZE_PIXELS);
				}
				else if(angle<315){
					pointingTile = map.getTile(x/PREFERRED_GRID_SIZE_PIXELS, y/PREFERRED_GRID_SIZE_PIXELS-1);
				}
				else{
					pointingTile = map.getTile(x/PREFERRED_GRID_SIZE_PIXELS-1, y/PREFERRED_GRID_SIZE_PIXELS);
				}
			}
			else{
				pointingTile = map.getTile(x/PREFERRED_GRID_SIZE_PIXELS-1, y/PREFERRED_GRID_SIZE_PIXELS);
			}
			
			currentTile = map.getTile(x/PREFERRED_GRID_SIZE_PIXELS, y/PREFERRED_GRID_SIZE_PIXELS);
			
			// remove creature from tile
			
			map.getTile(x/PREFERRED_GRID_SIZE_PIXELS, y/PREFERRED_GRID_SIZE_PIXELS).removeCreature(creatureList.get(i));
			
			creatureList.get(i).getTileInfo(currentTile, pointingTile);
			
//			System.out.println("Creature "+i+ currentTile.getTerrain()+ "->" + pointingTile.getTerrain()+" x: " +x + " y: " +y);
			
			
			
			creatureList.get(i).updatecreatureNN();
			
			//Add creature to tile after it moved
			
			map.getTile(x/PREFERRED_GRID_SIZE_PIXELS, y/PREFERRED_GRID_SIZE_PIXELS).addCreature(creatureList.get(i));
			
			
			if(creatureList.get(i).eat()){
				map.getTile(x/PREFERRED_GRID_SIZE_PIXELS, y/PREFERRED_GRID_SIZE_PIXELS).removeFood();
//				System.out.println("remove food");
				
			}
			
			
			if(creatureList.get(i).dead()){
				deadcreatures.add(creatureList.get(i));
//				creatureList.remove(i);
//				System.out.println("creaturedied" +i);
//				break;
			}
			
			if(creatureList.get(i).reproduce()){
				reproducecreatures.add(creatureList.get(i));
			}
			
//			creatureList.get(i).move(1);
			
			
			i++;
			listIterator.next();
			
//			this.repaint();
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
		
//		for(int j = 0; j<deadcreatures.size();j++){
//			creatureList.remove(deadcreatures.get(j));
//			System.out.println("removed"+j);
//		}
        
        System.out.println("dead:" + deadcreatures.size());
        
        creatureList.removeAll(deadcreatures);
        
		
        System.out.println("alive:" + creatureList.size());
        
		deadcreatures.clear();
		
		System.out.println("reproduce:" + reproducecreatures.size());
		
		
		// problems try: saving dc for one iteration before adding it to creatures
		for(int k = 0; k<reproducecreatures.size();k++){
			//old implementation
			
//			creatureList.add((creature) dc.copy(creatureList.get(k)));
//			creatureList.peekLast().repchange();
//			creatureList.peekLast().move(10);
//			creatureList.peekLast().initialize();

		}
		
		reproducecreatures.clear();
		
		System.out.println("one iteration");
		
		map.updateFood();
		
	}
	
//	public void removeNode(creature previousNode, creature nodeToRemove) {
//		  if (previousNode != null) {
//		    previousNode.setLink(nodeToRemove.getLink());
//		  }
//		}
	
	
	

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