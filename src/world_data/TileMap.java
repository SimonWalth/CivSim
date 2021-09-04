package world_data;

import java.awt.Color;
import java.util.Random;

public class TileMap {

	public Tile[][] map;
	private int rows, cols;
	private Tile wasteland = new Tile("Wastland", new Color(255,255,255), 0);
	
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
    public static final Color WASTLANDCOL = new Color(255,255,255);
    

	
//	TileMap(int rows, int cols){
//		this.rows= rows;
//		this.cols= cols;
//		map = new Tile[rows][cols];
//		
//        Random r = new Random();
//        // Randomize the terrain
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                int randomTileIndex = r.nextInt(TILETYPE.length);
//                Tile randomTile = TILETYPE[randomTileIndex];
//                this.map[i][j] = randomTile;
//            }
//        }
//	}
	
	TileMap(int[][] intmap){
		
		   
		
		map = new Tile[intmap[0].length][intmap.length];
		
		rows = intmap.length;
		cols = intmap[0].length;
		
		for (int i = 0; i <= intmap.length-1; i++) {
            for (int j = 0; j <= intmap[0].length-1; j++) {
            	int TileIndex = intmap[i][j];
//                Tile mapTile = TILETYPE[TileIndex];
            	Tile mapTile = this.getNewTile(TileIndex);
                this.map[j][i] = mapTile;
            }
        }
	}
	
	private Tile getNewTile(int i){
		Tile[] tiletypes = new Tile[4];
		   tiletypes[3]= new Tile("Ocean", OCEANCOL, 0);
		   tiletypes[2]= new Tile("Montains", MOUNTAINSCOL, 1000);
		   tiletypes[1]= new Tile("Forest", FORESTCOL, 1000);
		   tiletypes[0]= new Tile("Grassland", PLAINSCOL, 1000);
		   
		   return tiletypes[i];
	}
	
	public Tile getTile(int i, int j){
		if(i>cols-1 ||i<0){
			return wasteland;
		}
		if(j>rows-1 ||j<0){
			return wasteland;
		}
		
		return map[i][j];
	}
	
	public void updateFood(){
//		System.out.println("updatefood");
		for(int i=0;i< cols; i++){
			for(int j=0;j<rows;j++){
				
				map[i][j].calculateFood();
			}
		}
//		map[0][0].calculateFood();
		
		
	}
	
}
