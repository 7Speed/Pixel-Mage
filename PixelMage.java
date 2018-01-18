/*class PixelMage{
  public static void main(String [] args){
    Game game = new Game();
    game.game();
  }
}*/

//To do
//enemies and projectiles arent centerd correctly 
//diagonal moves twice as fast
//Sprite sheets
import java.awt.BorderLayout;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.SpringLayout;

public class PixelMage {

	private JFrame frame;
	private static Game game;

	/**
	 * Launch the application.
	 * 
	 * @throws InterruptedException
	 */

	public static void main(String[] args) throws InterruptedException {

		game = new Game();
		PixelMage window = new PixelMage();
		window.frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public PixelMage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		Menu menu = new Menu(game);
		springLayout.putConstraint(SpringLayout.NORTH, menu, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, menu, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, menu, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, menu, 0, SpringLayout.EAST, frame.getContentPane());

		Display d = new Display(game);
		springLayout.putConstraint(SpringLayout.NORTH, d, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, d, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, d, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, d, 0, SpringLayout.EAST, frame.getContentPane());
		game.setDisplay(d);
		

		frame.getContentPane().add(menu);
		frame.getContentPane().add(d);
		
		d.setVisible(true);
		d.repaint();
	}

}