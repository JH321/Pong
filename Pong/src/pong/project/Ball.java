package pong.project;
import java.awt.Graphics;

/**
 * Holds the Ball class which represents the ball on the screen.
 * @author John Ho
 */
public class Ball {
	
	public static final int BALL_WIDTH = 25;
	public static final int BALL_HEIGHT = 25;
	private int xVel;
	private int yVel;
	private int xPos;
	private int yPos;
	
	/**
	 * Constructs a Ball object.
	 * @param xVel initial x velocity of the ball on the screen.
	 * @param yVel initial y velocity of the ball on the screen.
	 */
	public Ball(int xVel, int yVel)
	{
		this.xVel = xVel;
		this.yVel = yVel;
		
		//sets initial position of the ball to be the middle of the screen.
		xPos = Pong.WIDTH / 2; //Half of the screen width
		yPos = Pong.HEIGHT / 2; //Half of the screen height
	}
	
	/**
	 * Updates the position of the ball on the screen.
	 * @param player1 paddle of player 1.
	 * @param player2 paddle of player 2.
	 */
	public void updatePos(Paddle player1, Paddle player2)
	{
		//Collision detected, reverses ball's x velocity for bounce back
		if(isCollision(player1, true) || isCollision(player2, false))
		{
			xVel *= -1;
			
			//Ball goes faster with every paddle collision
			if(xVel > 0)
			{
				xVel += 2;
			}
			else
			{
				xVel -= 2;
			}
		}
		
		//When the ball goes through to either the right side or left side of the screen,
		//it will be re-spawned in the middle of the screen with a random velocity.
		if(xPos <= 0 || xPos + BALL_WIDTH * 2 >= Pong.WIDTH)
		{
			xPos = Pong.WIDTH / 2;
			yPos = Pong.HEIGHT / 2;
			xVel = (int) (Math.random() * 4) + 2;
			yVel = (int) (Math.random() * 4) + 2;
		}
		
		//Bounces the ball of the top and bottom of the screen.
		if(yPos <= 0 || yPos + BALL_HEIGHT * 2 >= Pong.HEIGHT)
		{
			yVel *= -1;
			
		}
		xPos += xVel;
		yPos += yVel;
	}
	
	/**
	 * Detects a collision of the ball with a paddle on the screen.
	 * @param player will check if the ball collided with this paddle.
	 * @param whichPlayer true for if the player param is player 1's paddle, 
	 * 		  false otherwise. Player 1 has the left paddle, player 2 the right.
	 * @return true if there was a collision of the this ball with the specified paddle, false
	 * 		   otherwise.
	 */
	private boolean isCollision(Paddle player, boolean whichPlayer)
	{
		//Checks collision with left paddle (player 1)
		if(whichPlayer)
		{
			if(xVel < 0)
			{
				if(player.getXPos() + Paddle.WIDTH > xPos)
				{
					if(player.getYPos() <= yPos + BALL_HEIGHT / 2 && player.getYPos() + Paddle.HEIGHT >= yPos + BALL_HEIGHT / 2)
					{
						System.out.println("COLLISION");
						return true;
						
					}
				}
			}
		}
		//Checks collision with right paddle (player 2)
		else
		{
			if(xVel > 0)
			{
				if(player.getXPos() < xPos + BALL_WIDTH)
				{
					if(player.getYPos() <= yPos + BALL_HEIGHT / 2 && player.getYPos() + Paddle.HEIGHT >= yPos + BALL_HEIGHT / 2)
					{
						System.out.println("COLLISION");
						return true;
						
					}
				}
			}
		}
		return false;
	}
	/**
	 * Renders the ball on the screen.
	 * @param g graphics context.
	 */
	public void render(Graphics g)
	{
		g.fillOval(xPos, yPos, BALL_WIDTH, BALL_HEIGHT);
	}
}
