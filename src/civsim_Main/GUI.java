package civsim_Main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import world_data.World;

public class GUI extends JFrame {
	
	World map;
	
	 // Menüleiste 
    JMenuBar menuBar;
 
    // Menü "Datei"
    JMenu fileMenu;
    JMenu zoomMenu;
    JMenu moveMenu;
 
    // Menüpunkt "Öffnen"
    JMenuItem openItem;
 
    // Menüpunkt "Schließen"
    JMenuItem closeItem;
    
    JMenuItem zoomOut;
    JMenuItem zoomIn;
    JMenuItem resetPos;
    
    JMenuItem moveUp;
    JMenuItem moveDown;
    JMenuItem moveRight;
    JMenuItem moveLeft;
    
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    
    private static final String MOVE_UP = "move up";
    private static final String MOVE_DOWN = "move down";
    private static final String MOVE_RIGHT = "move right";
    private static final String MOVE_LEFT = "move left";
    //private static final String FIRE = "move fire";
    private static final String ZOOM_OUT = "zoom out";
    private static final String ZOOM_IN = "zoom in";
    
    private static final String RESET = "reset";
 
    public GUI(World map ) {
    	this.map=map;
    	
        this.setTitle("CIVSim");
        //this.setSize(400, 300);
 
        // Menüleiste wird erzeugt
        menuBar = new JMenuBar();
 
        // Menü "Datei" wird erzeugt
        fileMenu = new JMenu("Datei");
        
        zoomMenu = new JMenu("Zoom");
        moveMenu = new JMenu("Move");
 
        // Menüpunkte werden erzeugt
        openItem = new JMenuItem("Öffnen");
        closeItem = new JMenuItem("Schließen");
        
        zoomIn = new JMenuItem("ZoomIn");
        zoomOut = new JMenuItem("ZoomOut");
        resetPos = new JMenuItem("Reset");
        
        moveUp = new JMenuItem("Hoch");
        moveDown = new JMenuItem("Runter");
        moveRight = new JMenuItem("Rechts");
        moveLeft = new JMenuItem("Links");
 
        // Menüpunkte werden dem Datei-Menü hinzugefügt
        fileMenu.add(openItem);
        fileMenu.add(closeItem);
        
        zoomMenu.add(zoomIn);
        zoomMenu.add(zoomOut);
        zoomMenu.add(resetPos);
        
        moveMenu.add(moveUp);
        moveMenu.add(moveDown);
        moveMenu.add(moveRight);
        moveMenu.add(moveLeft);
 
        //Datei-Menü wird der Menüleiste hinzugefügt
        menuBar.add(fileMenu);
        menuBar.add(zoomMenu);
        menuBar.add(moveMenu);
 
        //Menüleiste wird dem JFrame hinzugefügt
        this.add(menuBar, BorderLayout.NORTH);
        this.add(map, BorderLayout.CENTER);
        this.pack();
 
        // ActionListener wird als anonyme Klasse eingebunden
        openItem.addActionListener(new java.awt.event.ActionListener() {
            // Beim Drücken des Menüpunktes wird actionPerformed aufgerufen
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Dateiauswahldialog wird erzeugt...
                JFileChooser fc = new JFileChooser();
                // ... und angezeigt
                fc.showOpenDialog(null);
            }
        });
 
        // ActionListener wird als anonyme Klasse eingebunden
        closeItem.addActionListener(new java.awt.event.ActionListener() {
            // Beim Drücken des Menüpunktes wird actionPerformed aufgerufen
            public void actionPerformed(java.awt.event.ActionEvent e) {
                //Programm schließen
                System.exit(0);
            }
        });
        
        zoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                map.zoomin();
                
                
            }
        });
        

        zoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                map.zoomout();
                
                
            }
        });
        
        resetPos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                map.resetPos();
                
                
            }
        });
        
        moveUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                map.moveUp();;
                
                
            }
        });
        

        moveDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                map.moveDown();
                
                
            }
        });
        
        moveRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                map.moveRight();;
                
                
            }
        });
        

        moveLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {

                map.moveLeft();
                
                
            }
        });
        

        
        map.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
        map.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);
        map.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), MOVE_LEFT);
        map.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), MOVE_RIGHT);
        
        map.getInputMap(IFW).put(KeyStroke.getKeyStroke('+'), ZOOM_IN);
        map.getInputMap(IFW).put(KeyStroke.getKeyStroke('-'), ZOOM_OUT);

        map.getActionMap().put(MOVE_UP, new MoveAction(0));
        map.getActionMap().put(MOVE_DOWN, new MoveAction(1));
        map.getActionMap().put(MOVE_LEFT, new MoveAction(2));
        map.getActionMap().put(MOVE_RIGHT, new MoveAction(3));
        
        map.getActionMap().put(ZOOM_IN, new MoveAction(4));
        map.getActionMap().put(ZOOM_OUT, new MoveAction(5));
        
        
    }
 


    private class MoveAction extends AbstractAction {

    	int moveID;
    	
        MoveAction(int moveID) {

        	this.moveID= moveID;
        	
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        	
        	if(moveID == 0){
        		map.moveUp();
        	}
        	if(moveID == 1){
        		map.moveDown();
        	}
        	if(moveID == 2){
        		map.moveLeft();
        	}
        	if(moveID == 3){
        		map.moveRight();
        	}
        	
        	if(moveID == 4){
        		map.zoomin();;
        	}
        	if(moveID == 5){
        		map.zoomout();
        	}
            // Same as the move method in the question code.
            // Player can be detected by e.getSource() instead and call its own move method.
        }
    }
    
}


/*

public class MyGame extends JFrame {

    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String MOVE_UP = "move up";
    private static final String MOVE_DOWN = "move down";
    private static final String FIRE = "move fire";

    static JLabel obj1 = new JLabel();
    static JLabel obj2 = new JLabel();

    public MyGame() {

//      Do all the layout management and what not...

        obj1.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
        obj1.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);
//      ...
        obj1.getInputMap(IFW).put(KeyStroke.getKeyStroke("control CONTROL"), FIRE);
        obj2.getInputMap(IFW).put(KeyStroke.getKeyStroke("W"), MOVE_UP);
        obj2.getInputMap(IFW).put(KeyStroke.getKeyStroke("S"), MOVE_DOWN);
//      ...
        obj2.getInputMap(IFW).put(KeyStroke.getKeyStroke("T"), FIRE);

        obj1.getActionMap().put(MOVE_UP, new MoveAction(1, 1));
        obj1.getActionMap().put(MOVE_DOWN, new MoveAction(2, 1));
//      ...
        obj1.getActionMap().put(FIRE, new FireAction(1));
        obj2.getActionMap().put(MOVE_UP, new MoveAction(1, 2));
        obj2.getActionMap().put(MOVE_DOWN, new MoveAction(2, 2));
//      ...
        obj2.getActionMap().put(FIRE, new FireAction(2));

//      In practice you would probably create your own objects instead of the JLabels.
//      Then you can create a convenience method obj.inputMapPut(String ks, String a)
//      equivalent to obj.getInputMap(IFW).put(KeyStroke.getKeyStroke(ks), a);
//      and something similar for the action map.

        add(obj1);
        add(obj2);
//      Do other GUI things...
    }

    static void rebindKey(KeyEvent ke, String oldKey) {

//      Depends on your GUI implementation.
//      Detecting the new key by a KeyListener is the way to go this time.
        obj1.getInputMap(IFW).remove(KeyStroke.getKeyStroke(oldKey));
//      Removing can also be done by assigning the action name "none".
        obj1.getInputMap(IFW).put(KeyStroke.getKeyStrokeForEvent(ke),
                 obj1.getInputMap(IFW).get(KeyStroke.getKeyStroke(oldKey)));
//      You can drop the remove action if you want a secondary key for the action.
    }

    public static void main(String[] args) {

        new MyGame();
    }

    private class MoveAction extends AbstractAction {

        int direction;
        int player;

        MoveAction(int direction, int player) {

            this.direction = direction;
            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            // Same as the move method in the question code.
            // Player can be detected by e.getSource() instead and call its own move method.
        }
    }

    private class FireAction extends AbstractAction {

        int player;

        FireAction(int player) {

            this.player = player;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            // Same as the fire method in the question code.
            // Player can be detected by e.getSource() instead, and call its own fire method.
            // If so then remove the constructor.
        }
    }
}

*/