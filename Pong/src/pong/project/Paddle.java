package pong.project;

import java.awt.Graphics;

/**
 * Contains the Paddle class that represents a paddle on the screen.
 * @author John Ho
 *
 */
public class Paddle {
	private int yPos;
	private int xPos;
	public static final int WIDTH = Pong.WIDTH / 20;
	public static final int HEIGHT = Pong.HEIGHT / 5;
	
	/**
	 * Constructs a paddle object, initializing to inputted initial x and y positions.
	 * @param x initial x position of paddle.
	 * @param y initial y position of paddle.
	 */
	public Paddle(int x, int y)
	{
		yPos = y;
		xPos = x;
	}
	
	/**
	 * Moves a paddle in the y direction on the screen.
	 * @param moveY the amount to move the paddle by in pixels.
	 */
	public void move(int moveY)
	{
		if(yPos <= 0)
		{
			if(moveY > 0)
			{
				yPos += moveY;
			}
		}
		else if(yPos + HEIGHT * 1.5 >= Pong.HEIGHT)
		{
			if(moveY < 0)
			{
				yPos += moveY;
			}
		}
		else
			yPos += moveY;
		
	}
	
	/**
	 * Getter for the paddle's x position.
	 * @return the x position of the paddle.
	 */
	public int getXPos()
	{
		return xPos;
	}
	
	/**
	 * Getter for the paddle's y position.
	 * @return the y position of the paddle.
	 */
	public int getYPos()
	{
		return yPos;
	}
	
	/**
	 * Renders the paddle on the screen.
	 * @param g the graphics context.
	 */
	public void render(Graphics g)
	{
		g.fillRect(xPos, yPos, WIDTH, HEIGHT);
	}

	
}
