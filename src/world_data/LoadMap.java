package world_data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class LoadMap {

	private File file;
	private int width;
	private int hight;
	
	private int[][] intmap;
	
	public LoadMap(File file, int width, int hight){
		this.file = file;
		this.width= width;
		this.hight = hight;
		
		intmap = new int[hight][width];
		
		loadmap();
	}
	

public int[][] loadmap(){

	
	FileReader fr = null;
	BufferedReader br = null;
	
	String[] lines = new String[hight];
	
	

	System.out.println("dsfk");
	
	try
	{
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		System.out.println("Loaded");

//	  for ( int c; ( c = fr.read() ) != -1; ) // -1 for end of stream is reached
//	    System.out.print( (char) c );
		
		for (int i=0; i<lines.length-1 ; i++){
			lines[i] = br.readLine();
			System.out.println(lines[i]);
		}
		
	}
	catch ( IOException e ) {
	  System.err.println( "Fehler beim Lesen der Datei!" );
	}
	finally {
	  try { fr.close(); br.close(); } catch ( Exception e ) { }
	  System.out.println("Finished Reading");
	}
	
	for(int i=0;i< lines.length-1; i++){
	    for (int j = 0; j < lines[i].length(); j++){
	    	intmap[i][j] = lines[i].charAt(j) - '0';
	    }
	}
	
	return intmap;
}

public int[][] getMap(){
	return intmap;
}

}