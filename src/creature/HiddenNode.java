package creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HiddenNode extends Node {

	float value;
	List<Connection> connections = new ArrayList<Connection>();
	
//	public HiddenNode(){
//		Random rand = new Random();
//		value = rand.nextFloat();
//	}
	
	public void setValue(float f){
		value = f;
	}
	
	public void AddNeuronConnection(Node n, float weight){
		//ADDNeuronConnection()
		connections.add(new Connection(n,weight));
	}
	
	public List<Connection> getConnections(){
		return connections;
	}

	@Override
	public float GetValue() {
		return Sigmoid(value);
	}
	
	
}
