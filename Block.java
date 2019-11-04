package newMaze;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block
{
	public int _x;
	public int _y;
	
	public int _size = 6;
	public Color _color = Color.WHITE;
	
	public boolean _has_stoped = false;
	
	public Block(int x, int y, Color color)
	{
		_x = x;
		_y = y;
		_color = color;
	}
	
	public Block(Color color) {
		_x = -1;
		_y = -1;
		_color = color;
	}

	public void draw(Graphics2D g2d, float interp) 
	{	
		g2d.setColor(_color);
		g2d.fillRect(_x, _y, _size, _size);
	}
	
	public boolean hitOtherBlock(Block other)
	{
		int xDistance = Math.abs(other._x - this._x);
		int yDistance = Math.abs(other._y - this._y);
		
		boolean xInvalid = xDistance < _size;
		boolean yInvalid = yDistance < _size;
		
		boolean inDiagnol = xDistance == 2 * _size && yDistance == 2 * _size;
		
		//System.out.println(xDistance + " " + yDistance);
		//System.out.println(xInvalid + " " + yInvalid);
		//System.out.println();
		
		if(inDiagnol)
		{
			return false;
		}
		
		return xInvalid || yInvalid; 
	}
	
	public boolean isThereBlock()
	{
		int invalidPointx1 = _x + _size;
		int invalidPointy1 = _y + _size;
		
		int invalidPointx2 = _x + _size;
		int invalidPointy2 = _y - _size;
		
		int invalidPointx3 = _x - _size;
		int invalidPointy3 = _y + _size;
		
		int invalidPointx4 = _x - _size;
		int invalidPointy4 = _y - _size;
		
		/*int invalidPointx5 = _x + _size;
		int invalidPointy5 = _y + 0;
		
		int invalidPointx6 = _x + 0;
		int invalidPointy6 = _y - _size;
		
		int invalidPointx7 = _x - 0;
		int invalidPointy7 = _y + _size;
		
		int invalidPointx8 = _x - _size;
		int invalidPointy8 = _y - 0;*/
		
		int numInvalidBlocks = 0;
		
		for(Block b : Main.maze.blocks)
		{
			if(b._x == invalidPointx1 && b._y == invalidPointy1)
			{
				numInvalidBlocks += 1;
			}
			if(b._x == invalidPointx2 && b._y == invalidPointy2)
			{
				numInvalidBlocks += 1;
			}
			if(b._x == invalidPointx3 && b._y == invalidPointy3)
			{
				numInvalidBlocks += 1;
			}
			if(b._x == invalidPointx4 && b._y == invalidPointy4)
			{
				numInvalidBlocks += 1;
			}
			
			/*if(b._x == invalidPointx5 && b._y == invalidPointy5)
			{
				numInvalidBlocks += 1;
			}
			if(b._x == invalidPointx6 && b._y == invalidPointy6)
			{
				numInvalidBlocks += 1;
			}
			if(b._x == invalidPointx7 && b._y == invalidPointy7)
			{
				numInvalidBlocks += 1;
			}
			if(b._x == invalidPointx8 && b._y == invalidPointy8)
			{
				numInvalidBlocks += 1;
			}*/
			
			if(b._x == this._x && b._y == this._y)
			{
				return true;
			}
			
			if(numInvalidBlocks == 2)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean onScreen()
	{
		boolean xOK = (_x >= 0 && _x <= Main.getWidth());
		boolean yOK = (_y >= 0 && _y <= Main.getHeight());
		
		return xOK && yOK;
	}
	
	public boolean hitAnyBlock()
	{
		boolean hasHit = false;
		int numHit = 0;
		
		for(Block b : Main.maze.blocks)
		{
			if(hitOtherBlock(b))
			{
				numHit += 1;
			}
			if(numHit == 2)
			{
				//System.out.println("to cloe");
				
				hasHit = true;
				break;
			}
		}
		
		return hasHit;
	}
	
	public boolean checkIfInvalid()
	{
		return !onScreen() || isThereBlock();
		
		/*int bottomY = _y + _size;
		boolean metBottom = bottomY >= Main.getHeight() ? true : false;
		
		System.out.println("b y: " + bottomY);
		System.out.println("m y: " + Main.getHeight());
				
		boolean hitBlock = false;
		
		for(Block b : Main.entites)
		{
			hitBlock = this.hitOtherBlock(b);
			
			if(hitBlock)
			{
				break;
			}
		}
		
		if(metBottom || hitBlock)
		{
			//_x = _lastX;
			//_y = _lastY;
			
			_lastX = _x;
			_lastY = _y;
			
			_has_stoped = true;
		}*/
	}
}
