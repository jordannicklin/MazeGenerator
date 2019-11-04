package newMaze;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Render2D extends JPanel{
	private float interp;
	public static boolean drawTestPoint = false;
	public static double testPointX;
	public static double testPointY;
	public static double viewOffsetX;
	public static double viewOffsetY;
	
	public static BufferedImage dyeColor(BufferedImage image, Color color){
		int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dyed = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, 0,0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0,0,w,h);
        g.dispose();
        return dyed;
	}
	
	//get the color difference between two colors
	public static int getColorDifference(Color c1, Color c2){
		return ((c2.getRed() - c1.getRed())^2 + (c2.getGreen() - c1.getGreen())^2 + (c2.getBlue() - c1.getBlue())^2)^2;
	}
	
	public void doRepaint(float intpol){
		interp = intpol;
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g.create();
		
		//draw/repaint/clean screen
		//bullshit way to clear the screen, not sure if this is the best way but best not to change it
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Main.getWidth(), Main.getHeight());
				
		for(Block b : Main.maze.blocks)
		{
			b.draw(g2d, interp);
		}
	}
}
