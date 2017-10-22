package world_data;

import java.awt.Color;
import java.util.Random;

public class TileMap {

	public Tile[][] map;
	
	//colors
	public static final Color CITYCOL = new Color(214,217,223);
    public static final Color DESERTCOL = new Color(255,204,102);
    public static final Color DIRT_ROADCOL = new Color(153,102,0);
    public static final Color FORESTCOL = new Color(0,102,0);
    public static final Color HILLSCOL = new Color(51,153,0);
    public static final Color LAKECOL = new Color(0,153,153);
    public static final Color MOUNTAINSCOL = new Color(102,102,255);
    public static final Color OCEANCOL = new Color(0,0,153);
    public static final Color PAVED_ROADCOL = new Color(51,51,0);
    public static final Color PLAINSCOL = new Color(102,153,0);
    
    Tile CITY,DESERT, DIRT_ROAD, FOREST, HILLS, LAKE, MOUNTAINS, OCEAN, PAVED_ROAD, PLAINS;
	
	   public Tile[] TILETYPE = {
		        //CITY = new Tile("Grassland", CITYCOL, 10,10),
		        //DESERT= new Tile("Grassland", DESERTCOL, 10,10),
		        //DIRT_ROAD= new Tile("Grassland", DIRT_ROADCOL, 10,10),
		        FOREST= new Tile("Grassland", FORESTCOL, 10,10),
		        //HILLS= new Tile("Grassland", HILLSCOL, 10,10),
		        //LAKE= new Tile("Grassland", LAKECOL, 10,10),
		        MOUNTAINS= new Tile("Grassland", MOUNTAINSCOL, 10,10),
		        OCEAN= new Tile("Grassland", OCEANCOL, 10,10),
		        //PAVED_ROAD= new Tile("Grassland", PAVED_ROADCOL, 10,10),
		        PLAINS= new Tile("Grassland", PLAINSCOL, 10,10)
		    };
	
	TileMap(int rows, int cols){
		map = new Tile[rows][cols];
		
        Random r = new Random();
        // Randomize the terrain
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int randomTileIndex = r.nextInt(TILETYPE.length);
                Tile randomTile = TILETYPE[randomTileIndex];
                this.map[i][j] = randomTile;
            }
        }
	}
	
	public Tile getTile(int i, int j){
		return map[i][j];
	}
	
}
