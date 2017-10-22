package creature;

public class Connection {

	public float weight = 1;
	public Node entrieNode;
	
	public Connection(Node n, float weight){
		this.weight = weight;
		this.entrieNode = n;
	}
	
	public float GetValue(){
		return weight * entrieNode.GetValue();
	}
	
}
