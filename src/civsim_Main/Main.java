package civsim_Main;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import creature.CreatureList;
import world_data.LoadMap;
import world_data.World;

public class Main {
	
	public static void main(String[] args) {
	        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html

	                
	            	File file = new File("Map_Small.txt");
	            	int width = 160;
	            	int hight = 100;
	            	LoadMap NewMap = new LoadMap(file, width, hight);
	            	
	            	
	            	World map = new World(NewMap);
	                GUI nml = new GUI(map);
	                nml.setVisible(true);
	                
	                CreatureList creatures = new CreatureList();
	                while (creatures.numCretures() <1000){
	                	creatures.addcreature();
	                }
//	                creatures.newCreature();
	                map.drawcreatures(creatures.getCreatures());
	                map.initializecreatures();
	                
	                
	                for(int i=0; i<500000; i++){
	                	System.out.println("Iteration: " +i);
//	                	while (creatures.numCretures() <100){
//		                	creatures.addcreature();
//		                	map.initializecreatures();
//		                }
	                	map.creaturesupdate();
	                	
	        	        SwingUtilities.invokeLater(new Runnable() {
	        	            public void run() {
	                	
	                	map.repaint();
	        	            }
	        	            });
	                	
	                	try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	
	                }
	                
	                
	                
	            
	            
	            
	            
	        
	}

}


