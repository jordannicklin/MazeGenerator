package newMaze;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Maze 
{
	private Color blocksColor = Color.GREEN;
	
	public Block lastBlock = new Block(0, 0, blocksColor);
	public ArrayList<Block> blocks = new ArrayList<Block>();
	
	public boolean attempts[] = {false, false, false, false};
	
	public Maze()
	{
		blocks.add(lastBlock);
	}

	public int getDirection()
	{
		Random rnd = new Random();
		
		return rnd.nextInt()%4;
	}
	
	public void setDown(Block b)
	{
		b._x = lastBlock._x;
		b._y = lastBlock._y + lastBlock._size;
	}
	
	public void setUp(Block b)
	{
		b._x = lastBlock._x;
		b._y = lastBlock._y - lastBlock._size;
	}
	
	public void setRight(Block b)
	{
		b._x = lastBlock._x + lastBlock._size;
		b._y = lastBlock._y;
	}
	
	public void setLeft(Block b)
	{
		b._x = lastBlock._x - lastBlock._size;
		b._y = lastBlock._y;
	}
	
	public void tick() 
	{
		Block newB = new Block(blocksColor);
		int dir = 0;
		
		boolean attemp1 = false;
		boolean attemp2 = false;
		boolean attemp3 = false;
		boolean attemp4 = false;
		
		boolean addB = true;
		
		while(newB.checkIfInvalid(dir))
		{
			if(Main.keysPressed.contains(82))
			{
				Main.maze = new Maze();
			}
			
			dir = getDirection();
			
			if(attemp1 && attemp2 && attemp3 && attemp4)
			{
				Main.disFromEnd += 1;
				Main.moveBack();
				addB = false;
			}
			
			switch(dir)
			{
			case 0:
				attemp1 = true;
				break;
			case 1:
				attemp2 = true;
				break;
			case 2:
				attemp3 = true;
				break;
			case 3:
				attemp4 = true;
				break;
			}
			
			switch(dir)
			{
			case 0:
				setDown(newB);
				break;
				
			case 1:
				setUp(newB);
				break;
				
			case 2:
				setRight(newB);
				break;
				
			case 3:
				setLeft(newB);
				break;
			}
		}

		System.out.println("dir: " + dir);
		
		if(addB)
		{
			lastBlock = newB;
			blocks.add(newB);
		}
		
	}
	
}
