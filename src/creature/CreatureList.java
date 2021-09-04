package creature;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class CreatureList {
	
	LinkedList<creature> creatureList = new LinkedList<creature>();
	
	public CreatureList(){
		
	}
	
	public void addcreature(){
		int xrandomNum = ThreadLocalRandom.current().nextInt(0, 1600 + 1);
		int yrandomNum = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
		int anglerandomNum = ThreadLocalRandom.current().nextInt(0, 360 + 1);
		creature c = new creature(xrandomNum,yrandomNum,anglerandomNum);
		creatureList.add(c);
	}
	
	public LinkedList<creature> getCreatures(){
		return creatureList;
	}
	
	public int numCretures(){
		return creatureList.size();
	}
	
	public void newCreature(){
		creature c = new creature(0,0,0);
		creatureList.add(c);
	}

}
