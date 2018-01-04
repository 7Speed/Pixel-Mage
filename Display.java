import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Display extends JPanel{

    private BufferedImage image;
    
    public Display() {
        super();//Call the constructor for normal JPanel
        setDoubleBuffered(true);//Make the panel no blink
        try {
            image = ImageIO.read(new File("Archer.png"));//Set the image for the sprite
        } catch (IOException e) {
            e.printStackTrace();//If the image does not exist this will happen
        }
    }

    public void paint(Graphics g) {
        int middleX = getWidth()/2-image.getWidth()/2;
        int middleY = getHeight()/2-image.getHeight()/2;
        g.drawImage(image, middleX, middleY, this);
    }
    
}