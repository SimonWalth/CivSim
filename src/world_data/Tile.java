package world_data;

import java.awt.Color;

public class Tile {
	
	String Terrain;
	Color TileColer;
	int Food;
	int Movement;
	int upTime;
	int BuildLvlStreet;
	int BuildLvlFarm;
	int BuildLvlWall;
	
	Tile(String Terrain,Color TileColer, int Food){
		this.Terrain = Terrain;
		this.Food = Food;
		this.TileColer = TileColer;
		
		calculateMovement();
		
	}
	
	private void calculateMovement(){
		//if(Terrain == "Grasland"){
			Movement = 1;
		//}
	}
	
	private void calculateFood(){
		Food = Food + 1;
	}

	public String getTerrain() {
		return Terrain;
	}

	public int getFood() {
		return Food;
	}


	public int getMovement() {
		return Movement;
	}

	public int getUpTime() {
		return upTime;
	}

	public void setUpTime(int upTime) {
		this.upTime = upTime;
	}

	public int getBuildLvlStr() {
		return BuildLvlStreet;
	}

	public void setBuildLvlStr(int buildLvl) {
		BuildLvlStreet = buildLvl;
	}
	
	public int getBuildLvlFarm() {
		return BuildLvlFarm;
	}

	public void setBuildLvlFarm(int buildLvl) {
		BuildLvlFarm = buildLvl;
	}
	
	public int getBuildLvlWall() {
		return BuildLvlWall;
	}

	public void setBuildLvlWall(int buildLvl) {
		BuildLvlWall = buildLvl;
	}

	public Color getColer() {
		return TileColer;
	}
	
	

}
