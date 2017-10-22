package world_data;

import java.awt.Color;

public class Tile {
	
	String Terrain;
	Color TileColer;
	int Food;
	int Water;
	int Movement;
	int upTime;
	int BuildLvl;
	
	Tile(String Terrain,Color TileColer, int Food, int Water){
		this.Terrain = Terrain;
		this.Food = Food;
		this.Water = Water;
		this.TileColer = TileColer;
		
		calculateMovement();
		
	}
	
	private void calculateMovement(){
		//if(Terrain == "Grasland"){
			Movement = 0;
		//}
	}

	public String getTerrain() {
		return Terrain;
	}

	public int getFood() {
		return Food;
	}

	public int getWater() {
		return Water;
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

	public int getBuildLvl() {
		return BuildLvl;
	}

	public void setBuildLvl(int buildLvl) {
		BuildLvl = buildLvl;
	}

	public Color getColer() {
		return TileColer;
	}
	
	

}
