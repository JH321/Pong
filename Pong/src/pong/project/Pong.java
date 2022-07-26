package pong.project;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * Contains the Pong class which, once initialized, will start the pong game.
 * @author John Ho
 *
 */
public class Pong extends JPanel implements KeyListener, ActionListener
{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 500;
	public static final int PADDLE_SPEED = 10;
	private Timer timer; 

	private Paddle player1;
	private Paddle player2;
	
	private Ball ball;
	
	/**
	 * Constructs a Pong object, initializes the JFrame, key listener, and necessary
	 * components to create a pong game on the screen.
	 */
	public Pong()
	{
		JFrame frame = new JFrame("Pong");
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(1, this);
		timer.start();
		
		player1 = new Paddle(Paddle.WIDTH / 2, HEIGHT / 2);
		player2 = new Paddle(WIDTH - Paddle.WIDTH * 2, HEIGHT / 2);
		ball = new Ball((int)(Math.random() * 4) + 2, (int)(Math.random() * 2) + 1);
		
	}
	
	public static void main(String args[])
	{
		//starts the pong game
		new Pong();
	}
	
	/**
	 * Paints the components of the pong game onto the screen, including players and ball.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		player1.render(g);
		player2.render(g);
		ball.updatePos(player1, player2);
		ball.render(g);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyChar());
	}
	
	/**
	 * Contains controls for the game. Left paddle (player 1) uses w and s to move up and down,
	 * the right paddle (player 2) uses up and down arrow keys.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_W:
			player1.move(-PADDLE_SPEED);
			break;
		case KeyEvent.VK_S:
			player1.move(PADDLE_SPEED);
			break;
		case KeyEvent.VK_UP:
			player2.move(-PADDLE_SPEED);
			break;
		case KeyEvent.VK_DOWN:
			player2.move(PADDLE_SPEED);
			break;
		default:
			System.out.println("Wrong key");
		}
		
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyChar());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == timer)
		{
			repaint();
		}
	}
}
