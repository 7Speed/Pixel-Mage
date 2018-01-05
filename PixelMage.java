
//class PixelMage{
//  public static void main(String [] args){
//    Game game = new Game();
//    game.game();
//   Window window = new Window();
//       window.frame.setVisible(true);
//  }
//}
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class PixelMage {

	private JFrame frame;
	private static Game game;

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		game = new Game();
		while(game==null);
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Display d = new Display(game);
		game.setDisplay(d);
		frame.getContentPane().add(d, BorderLayout.CENTER);
		d.setVisible(true);
		d.repaint();
	}

}