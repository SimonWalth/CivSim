package creature;

import java.io.Serializable;

abstract public class Node implements Serializable {

	public abstract float GetValue();
	
	public static float Sigmoid(float x){
//		System.out.println("x" + x);
		float et =(float)Math.pow(Math.E, x);
//		System.out.println("Sigmoid:"+ et );
		return (((et/(1+et))));
	}
	
}
