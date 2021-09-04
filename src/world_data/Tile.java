package world_data;

import java.awt.Color;
import java.io.Serializable;
import java.util.LinkedList;

import creature.creature;

public class Tile implements Serializable {
	
	
	
	String Terrain;
	Color TileColor;
	int Food = 10;
	int Movement;
	int upTime;
	int BuildLvlStreet;
	int BuildLvlFarm;
	int BuildLvlWall;
	
	int TerrainIndex;
	
	int MaxfoodValue = 5000;
	
	LinkedList<creature> creatures_tile = new LinkedList<creature>();
	
	Tile(String Terrain,Color TileColer, int Food){
		this.Terrain = Terrain;
		this.Food = Food;
		this.TileColor = TileColer;
		
		calculateMovement();
		
	}
	
	private void calculateMovement(){

		if(Terrain == "Ocean"){
			Movement = 1;
			TerrainIndex = 0;
		}
		else if(Terrain == "Forest"){
			Movement = 5;
			TerrainIndex = 3;
		}
		else if(Terrain == "Grassland"){
			Movement = 10;
			TerrainIndex = 2;
		}
		else if(Terrain == "Montains"){
			Movement = 2;
			TerrainIndex = 1;
		}
		
	}
	
	public void calculateFood(){
		if(Food<MaxfoodValue){
		if(Terrain == "Wastland"){
			
		}
		else if(Terrain == "Ocean"){
			
		}
		else if(Terrain == "Forest"){
			Food = Food + 2;
		}
		else if(Terrain == "Grassland"){
			Food = Food + 1;
		}
		else if(Terrain == "Montains"){
		Food = Food + 0;
		}
		}
		this.calculateColor();
		
//		TileColer = new Color(0,Food+20,0);
	}
	
	public int getMaxFoodValue(){
		return MaxfoodValue;
	}

	public String getTerrain() {
		return Terrain;
	}

	public int getFood() {
		return Food;
	}

	public void removeFood(){
		Food= Food-50;
//		System.out.println("Food:" + Food);
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
		return TileColor;
	}
	
	public void calculateColor(){
		int green = 0;
		if(Food<0){
			green =0;
		}
		else{
		green = Food/10;
		}
		if(green>= 255){
			TileColor = new Color(TileColor.getRed(),255 , TileColor.getBlue());
		}
		else{
		TileColor = new Color(TileColor.getRed(),green , TileColor.getBlue());
		}
	}
	
	public int getIndex(){
		return TerrainIndex;
	}
	
	public void addCreature(creature cre){
		creatures_tile.add(cre);
	}
	
	public void removeCreature(creature cre){
		creatures_tile.remove(cre);
	}

}
