package creature;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import world_data.Tile;

public class creature implements Serializable {
	private int x,y, angle;
	private int energy = 100;
	private int speedland, speedwater = 1;
	private int efficencyland,efficencywater = 0;
	private int attackvalue = 1;
	private int wasattacked;
	private boolean attack = false;
	private Color color = new Color(255,0,0);
	
	
	private boolean haseaten = false;
	private int eatposx,eatposy;
	
	
	private List<InputNode> InputList = new ArrayList<InputNode>();
	private List<OutputNode> OutputList = new ArrayList<OutputNode>();
	private List<HiddenNode> HiddenList = new ArrayList<HiddenNode>();
	
	private Tile currentTile;
	private Tile pointingTile;
	
	private InputNode bias = new InputNode();
	
	private InputNode curfood = new InputNode();
	private InputNode curterrain = new InputNode();
	
	private InputNode pointfood = new InputNode();
	private InputNode pointterrain = new InputNode();
	
	private InputNode othercreature = new InputNode();
	
	private int numconnections;
	
	
	public void rotate(int r){
		angle = angle + r;
		energy = energy -Math.abs(r)/10;
		while(angle>360){
			angle = angle -360;
		}
		while(angle<0){
			angle = angle+360;
		}
	}
	
	public void move(float speed){

		energy = (int) (energy - Math.abs(speed));
		if(pointingTile.getTerrain() =="Wastland"){
			speed=0;
		}
		float floatspeed;
		if(currentTile.getIndex() ==0){
			floatspeed = (float) speedwater+currentTile.getMovement();
			speed = speed* floatspeed;
			
		}
		else{
			floatspeed = (float) speedland+currentTile.getMovement();
			speed = speed* floatspeed;
//			System.out.println(floatspeed);
//			System.out.println(speed);
		}

	    double radians = Math.toRadians(angle);
	    
	    x = (int) (x-Math.round(speed*Math.cos(radians)));
//	    System.out.println(Math.cos(radians));
	    y = (int) (y+Math.round(speed*Math.sin(radians)));
//	    System.out.println(Math.sin(radians));

		
	}
	
	public void initialize(){
		energy = 100;
	}
	
	public boolean reproduce(){
		if(energy>500){
			energy = energy -400;
			return true;
		}
		return false;
	}
	
	public boolean eat(){
		if(currentTile.getFood()>50){
			energy = energy+100;
//			System.out.println("energy:" + energy);
		return true;
		}
		else{
			return false;
		}

	}
	
	public void attack(){
		
	}
	
	public void build(){
		
	}
	
	public void getTileInfo(Tile currentTile, Tile pointingTile){
		this.currentTile = currentTile;
		this.pointingTile = pointingTile;
	}
	
	public void scan(){
		
	}
	
	public void updateenergy(){
		energy = energy - efficencyland - efficencywater - speedland - speedwater -5;
	}
	
	public boolean dead(){
		return (energy<=0);
	}
	
	creature(int x, int y, int angle){
		this.x= x;
		this.y= y;
		this.angle= angle;
	
		//INPUTS
		
//		InputNode curfood = new InputNode();
//		curfood.setValue(currentTile.getFood());
//		InputNode pointfood = new InputNode();
//		pointfood.setValue(pointingTile.getFood());
		
		bias.setValue(1);
		
		InputList.add(bias);
		
		InputList.add(curfood);
		InputList.add(curterrain);
		InputList.add(pointfood);
		InputList.add(pointterrain);
		
		
		//HIDDEN
	
		for (int i=0; i<8; i++){
			HiddenList.add(new HiddenNode());
		}
		
		for (int i = 0; i < HiddenList.size(); i++) {
			HiddenNode hiddentemp = new HiddenNode();
			for (int j = 0; j < InputList.size(); j++) {
				float generatedFloat = new Random().nextFloat();
				generatedFloat = (2*generatedFloat -1)/2;
				hiddentemp.AddNeuronConnection(InputList.get(j), generatedFloat);
			}
			HiddenList.set(i, hiddentemp);
		}
		

		
//		for (HiddenNode hiddentemp : HiddenList) {
//			for (InputNode inputtemp : InputList) {
//				 float generatedFloat = new Random().nextFloat();
//				hiddentemp.AddNeuronConnection(inputtemp, generatedFloat);
//				
//				
//			}
//		}
		
		//OUTPUTS
		
		OutputNode rotate = new OutputNode();
		OutputNode move = new OutputNode();
		
		OutputList.add(rotate);
		OutputList.add(move);
		
//		for (OutputNode outputtemp : OutputList) {
//			for (HiddenNode hiddentemp : HiddenList) {
//				 float generatedFloat = new Random().nextFloat();
//				outputtemp.AddNeuronConnection(hiddentemp, generatedFloat);
//			}
//		}
		

		for (int i = 0; i < OutputList.size(); i++) {
			OutputNode outputtemp = new OutputNode();
			for (int j = 0; j < HiddenList.size(); j++) {
				float generatedFloat = new Random().nextFloat();
				generatedFloat = (2*generatedFloat -1)/2;
				outputtemp.AddNeuronConnection(HiddenList.get(j), generatedFloat);
			}
			OutputList.set(i, outputtemp);
		}	
		
		
		// Define Nummber of Connections
		numconnections = HiddenList.size() * InputList.size() + OutputList.size() * HiddenList.size();
		
	}
	
	
	public void updatecreatureNN(){
		
		//INPUTS-------------------------------------------------------------------------------------------
		
		int maxfood = currentTile.getMaxFoodValue();
		
		curfood.setValue((2*currentTile.getFood() - maxfood)/maxfood);
//		System.out.println("CURfood:"+ curfood.GetValue());
		InputList.set(InputList.indexOf(curfood), curfood);
		
		curterrain.setValue(currentTile.getIndex()-2);
		InputList.set(InputList.indexOf(curterrain), curterrain);
		
		
//		System.out.println(currentTile.getFood());
		
		pointfood.setValue((2*pointingTile.getFood() - maxfood)/maxfood);
//		System.out.println("Pointfood:"+ pointfood.GetValue());
		InputList.set(InputList.indexOf(pointfood), pointfood);
		
		pointterrain.setValue(currentTile.getIndex()-2);
		InputList.set(InputList.indexOf(pointterrain), pointterrain);
		
		//other cretures
		
		//TODO: ADD FEATURE by color
		othercreature.setValue(0);
		
//		System.out.println(pointingTile.getFood());
		
		//HIDDEN--------------------------------------------------------------------------------------------------
		
		for (HiddenNode hiddentemp : HiddenList) {
			float f = 0;
			for(Connection contemp : hiddentemp.getConnections()){
				float conval = contemp.GetValue();
				// WERTWERTNICHTSNICHTS !!!!
				f = f+ conval;
//				System.out.println("f: " +f);
//				System.out.println("contemp: " + conval);
			}
			
//			System.out.println("f: "+ f);
			
			hiddentemp.setValue(f);
		}
		
		//OUTPUT
		
		for (OutputNode outputtemp : OutputList) {
			float f = 0;
			for(Connection contemp : outputtemp.getConnections()){
				f = f+ contemp.GetValue();
			}
			
			outputtemp.setValue(f);
		}
		
		//MOVE AND ROTATE
		
		this.rotate((int) (100* OutputList.get(0).GetValue()));
//		System.out.println(360* OutputList.get(0).GetValue());
		this.move(OutputList.get(1).GetValue());
//		System.out.println(10*OutputList.get(1).GetValue());
		
		this.updateenergy();
		
		
	}
	
	public void repchange(){
		for(int i=0; i<5;i++){

			int randomNum = new Random().nextInt(numconnections+1);
			
//			System.out.println("Randomrange: "+ numconnections + " Random: "+ randomNum);
			
			if(randomNum<HiddenList.size()*InputList.size()){
				int hiddenindex = randomNum/InputList.size();
				int connectionindex = randomNum%InputList.size();
				
				
				HiddenList.get(hiddenindex).getConnections().get(connectionindex).changeweight();;
			}
			else if(randomNum<HiddenList.size()*InputList.size()+ OutputList.size()*HiddenList.size()){
				randomNum = randomNum - HiddenList.size()*InputList.size();
				int outindex = randomNum/HiddenList.size();
				int connectionindex = randomNum%HiddenList.size();
				
//				System.out.println("outindex:"+ outindex);
//				System.out.println("connectionindex:"+ connectionindex);
				
				
				OutputList.get(outindex).getConnections().get(connectionindex).changeweight();
			}
			else{
				int randomNumSpec = new Random().nextInt(4);
				switch (randomNumSpec){
				case 0: speedland = speedland+1; break;
				case 1: if(speedland>0) speedland = speedland-1; break;
				case 2: speedwater = speedwater+1; break;
				case 3: if(speedwater>0) speedwater = speedwater-1; break;
				}
				
			}
			
			
		}
	}
	
	
	public int getx(){
		return x;
	}
	
	public int gety(){
		return y;
	}
	
	public int getangle(){
		return angle;
	}
	
	public Color getColor(){
		int red, green,blue;
		if(attack){
			red = 0;
		}
		else{
			red = 255;
		}
		green = (efficencyland+speedland)*10;
		blue = (efficencywater+speedwater)*10;
		if(green >255){
			green = 255;
		}
		if(blue>255)
			blue = 255;
		color = new Color(red, green, blue);
		return color;
	}

}
