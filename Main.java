package newMaze;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main 
{
	final public static JFrame window = new JFrame("MAZE?");
	final public static GameLoop gameLoop = new GameLoop();
	
	final public static Render2D render2D = new Render2D();
	
	public static ArrayList<Integer> keysPressed = new ArrayList<Integer>();
	
	public static Maze maze = new Maze();
	public static int disFromEnd = 0;
	
	public static void main(String[] args)
	{		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int width = 700;
		int height = 700;
		
		window.setSize(width, height);
		window.setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2);
		window.setVisible(true);
		
		window.add(render2D);
		
		window.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			
			public void keyReleased(KeyEvent e) {
				keysPressed.remove(keysPressed.indexOf(e.getKeyCode()));
				
				System.out.println("Key released: " + e.getKeyCode() + ". Total keys pressed: " + keysPressed.size());
			}
			
			public void keyPressed(KeyEvent e) {
				if(!keysPressed.contains(e.getKeyCode())){
					keysPressed.add(e.getKeyCode());
					
					System.out.println("Key pressed: " + e.getKeyCode() + ". Total keys pressed: " + keysPressed.size());
				}
			}
		});
		
		
		window.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}
		});
		
		gameLoop.startLoop(); //Initiate game logic
	}
	
	public static int getWidth(){
		return window.getContentPane().getWidth();
	}
	
	public static int getHeight(){
		return window.getContentPane().getHeight();
	}
	
	public static void moveBack()
	{
		//int times = 0;
		
		if(maze.blocks.size() - disFromEnd >= 0)
		{
			maze.lastBlock = maze.blocks.get(maze.blocks.size() - disFromEnd);
			
			maze.lastBlock._color = Color.RED;
		}
		else
		{
			disFromEnd = 0;
		}
		/*else
		{
			while(times < 10000)
			{
				times += 1;
						
				if(Main.keysPressed.contains(82))
				{
					Main.maze = new Maze();
					disFromEnd = 0;
				}
			}
			
			Main.maze = new Maze();
			disFromEnd = 0;
		}*/
	}
}
