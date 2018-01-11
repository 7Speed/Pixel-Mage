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
    while (game == null)
      ;
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
    frame.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Display d = new Display(game);
    game.setDisplay(d);
    frame.getContentPane().add(d, BorderLayout.CENTER);
    d.setVisible(true);
    d.repaint();
  }
  
}