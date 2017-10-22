package creature;

abstract public class Node {

	public abstract float GetValue();
	
	public static float Sigmoid(float x){
		float et =(float)Math.pow(Math.E, x);
		return et/(1+et);
	}
	
}
