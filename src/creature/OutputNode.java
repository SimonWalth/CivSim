package creature;

import java.util.ArrayList;
import java.util.List;

public class OutputNode extends Node {

	private float value;
	List<Connection> connections = new ArrayList<Connection>();
	
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
		return value;
	}

}
