import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Display extends JPanel {

	private BufferedImage image;
	private int middleX, middleY;
	private final Game game;
	private final Player player;

	public Display(Game game) {
		super();// Call the constructor for normal JPanel
		this.game = game;
		player = game.getPlayer();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				middleX = getWidth() / 2 - image.getWidth() / 2;
				middleY = getHeight() / 2 - image.getHeight() / 2;
			}
		});
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 'd')
					game.player.setXDir(1);
				if (e.getKeyChar() == 'w')
					game.player.setYDir(1);
				if (e.getKeyChar() == 'a')
					game.player.setXDir(-1);
				if (e.getKeyChar() == 's')
					game.player.setYDir(-1);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == 'd')
					game.player.setXDir(0);
				if (e.getKeyChar() == 'w')
					game.player.setYDir(0);
				if (e.getKeyChar() == 'a')
					game.player.setXDir(0);
				if (e.getKeyChar() == 's')
					game.player.setYDir(0);
			}
		});
		setDoubleBuffered(true);// Make the panel no blink
		try {
			image = ImageIO.read(new File("Archer.png"));// Set the image for
															// the sprite
		} catch (IOException e) {
			e.printStackTrace();// If the image does not exist this will happen
		}
		middleX = getWidth() / 2 - image.getWidth() / 2;
		middleY = getHeight() / 2 - image.getHeight() / 2;
	}

	public void paint(Graphics g) {
		g.drawImage(image, middleX, middleY, this);
		ArrayList<Enemy> enemies = game.enemies;
		for (Enemy e : enemies) {
			if (player.inRenderRange(e, middleX, middleY)) {
				//g.drawImage(arg0, arg1, arg2, arg3)
			}
		}
	}

}