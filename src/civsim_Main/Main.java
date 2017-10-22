package civsim_Main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import world_data.LoadMap;
import world_data.World;

public class Main {
	
	public static void main(String[] args) {
	        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	LoadMap NewMap = new LoadMap();
	            	NewMap.loadmap();
	                JFrame frame = new JFrame("CivSim");
	                World map = new World();
	                frame.add(map);
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.pack();
	                frame.setVisible(true);
	            }
	            
	            
	            
	        });
	}

}
