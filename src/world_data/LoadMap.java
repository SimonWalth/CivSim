package world_data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class LoadMap {


public void loadmap(){
	
	FileReader fr = null;
	BufferedReader br = null;
	
	String[] lines = new String[400];
	
	File file = new File("Map1.txt");	

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
	
	
}

}