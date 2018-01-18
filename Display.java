import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Display extends JPanel {

 private BufferedImage archer, enemy, projectile, background;
 public static int middleX, middleY;
 private static int backgroundX=4000;
   private static int backgroundY=4000;
  private static int TILE_SIZE=4000;
 private final Game game;
 private final Player player;

 public Display(final Game game) {
  super();// Call the constructor for normal JPanel
  this.game = game;
  player = game.getPlayer();
  addMouseListener(new MouseAdapter() {
   @Override
   public void mousePressed(MouseEvent arg0) {
    //System.out.println("Down");
    player.setFiring(true);
   }

   @Override
   public void mouseReleased(MouseEvent e) {
   // System.out.println("Up");
    player.setFiring(false);
   }
  });
  addComponentListener(new ComponentAdapter() {
   @Override
   public void componentResized(ComponentEvent arg0) {
    middleX = getWidth() / 2 - archer.getWidth() / 2;
    middleY = getHeight() / 2 - archer.getHeight() / 2;
   }
  });
  addKeyListener(new KeyAdapter() {
   @Override
   public void keyPressed(KeyEvent e) {
    if (e.getKeyChar() == 'd')
     game.player.setXDir(1);
    if (e.getKeyChar() == 'w')
     game.player.setYDir(-1);
    if (e.getKeyChar() == 'a')
     game.player.setXDir(-1);
    if (e.getKeyChar() == 's')
     game.player.setYDir(1);
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
  // //------------------------------------------------
  // public boolean getLeftClick(){
  // return leftClick;
  // }
  // public void fired(){
  // leftClick = false;
  // }
  // public void mouseClicked(MouseEvent e) {}
  // public void mouseEntered(MouseEvent e) {}
  // public void mouseExited(MouseEvent e) {}
  // public void mousePressed(MouseEvent e) {
  // leftClick = true;
  // }
  // public void mouseReleased(MouseEvent e) {
  // leftClick = false;
  // }
  // //------------------------------------------------
  setDoubleBuffered(true);// Make the panel no blink
  setFocusable(true);
  try {
   archer = resize(ImageIO.read(new File("Archer.png")), Player.getSize(), Player.getSize());// Set the image for
   // the sprite
   enemy = resize(ImageIO.read(new File("Snake1.png")),Enemy.getSize(),Enemy.getSize());
   projectile = resize(ImageIO.read(new File("projectile.png")),Projectile.getSize(),Projectile.getSize());//Umm this should probably be changed
   background=resize(ImageIO.read(new File("Background.png")),backgroundX,backgroundY);
  } catch (IOException e) {
   e.printStackTrace();// If the image does not exist this will happen
  }
  middleX = getWidth() / 2 - archer.getWidth() / 2;
  middleY = getHeight() / 2 - archer.getHeight() / 2;
 }

 public void paint(Graphics g) {
  g.clearRect(0, 0, getWidth(), getHeight());
  //g.drawImage(background, - player.getX()+middleX- backgroundX/2 , - player.getY()+middleY- backgroundY/2, this);
  int xOff=- player.getX()+middleX- backgroundX/2;
    int yOff= - player.getY()+middleY- backgroundY/2;
  for (int i = 0; i * TILE_SIZE <= getWidth() + TILE_SIZE; i++) {
                for (int j = 0; j * TILE_SIZE <= getHeight() + TILE_SIZE; j++) {
                    g.drawImage(background, xOff + i * TILE_SIZE, yOff + j * TILE_SIZE, this);
                }
            }
  g.drawImage(archer, middleX, middleY, this);
  ArrayList<Enemy> enemies = game.enemies;
  for (Enemy e : enemies) {
   if (player.inRenderRange(e, middleX, middleY)) {
    int dx = e.getX() - player.getX() + middleX + Enemy.getHalfSize();
    int dy = e.getY() - player.getY() + middleY + Enemy.getHalfSize();
    g.drawImage(enemy, dx, dy, this);
   }
  }

  for (Projectile p : player.getProjectiles()) {
   if (player.inRenderRange(p, middleX, middleY)) {
    int dx = p.getX() - player.getX() + middleX ;
    int dy = p.getY() - player.getY() + middleY ;
    g.drawImage(projectile, dx, dy, this);
   }
  }
 }

 private BufferedImage resize(BufferedImage bi, int width, int height) {
  Image img = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
  BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
  Graphics2D bGr = bimage.createGraphics();
  bGr.drawImage(img, 0, 0, null);
  bGr.dispose();
  return bimage;
 }

}