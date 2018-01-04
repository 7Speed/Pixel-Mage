//class PixelMage{
//  public static void main(String [] args){
//    Game game = new Game();
//    game.game();
 //   Window window = new Window();
 //       window.frame.setVisible(true);
//  }
//}
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class PixelMage {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
      PixelMage window = new PixelMage();
        window.frame.setVisible(true);
        Game game = new Game();
    game.game(); 
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

        Display d = new Display();
        frame.getContentPane().add(d, BorderLayout.CENTER);
        d.setVisible(true);
        d.repaint();
    }

}