package creature;

import java.io.Serializable;
import java.util.Random;

public class Connection implements Serializable {

	public float weight = 1;
	public Node entrieNode;
	private int evolutionrate = 5;
	
	public Connection(Node n, float weight){
		this.weight = weight;
		this.entrieNode = n;
	}
	
	
	public float GetValue(){
		return weight * entrieNode.GetValue();
	}
	
//	public float getweight(){
//		return weight;
//	}
	
	public void changeweight(){
		float generatedFloat = new Random().nextFloat();
		generatedFloat = (2*generatedFloat -1)/evolutionrate;
		weight = weight + generatedFloat;
	}
	
}
