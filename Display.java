import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Display extends JPanel implements KeyListener, MouseListener{
  
  private BufferedImage archer, paladin, rogue, enemy, projectile,fireBall, airBall, earthBall, waterBall, darkBall, background;
  public static int middleX, middleY;
  private static int backgroundX=4000;
  private static int backgroundY=4000;
  private int backgroundXCoord = -backgroundX/2;
  private int backgroundYCoord = -backgroundY/2;
  private static int TILE_SIZE=4000;
  private final Game game;
  //private final Player player;
  public int x = 0;
  public int y = 0;
  private boolean dPressed = false;
  private boolean wPressed = false;
  private boolean aPressed = false;
  private boolean sPressed = false;
  private boolean leftClick = false;
  private boolean onePressed = false;
  private boolean twoPressed = false;
  private boolean threePressed = false;
  private boolean fourPressed = false;
  private boolean fivePressed = false;
  private int classNum = 0;
  private int numEnemies;
  private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  private int numProjectiles;
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  private int numObstacles;
  private ArrayList<Wall> obstacles = new ArrayList<Wall>();
  public void setX(int x){
    this.x = x;
  }
  public void setY(int y){
    this.y = y;
  }
  public void setCoord(int [] coord){
    this.x = coord[0];
    this.y = coord[1];
  }
  public void background(Background background){
    backgroundXCoord = background.getX();
    backgroundYCoord = background.getY();
  }
  public static int getBackgroundX(){
    return backgroundX;
  }
  public static int getBackgroundY(){
    return backgroundY;
  }
  
  public void enemies(ArrayList<Enemy> enemies){
    numEnemies = enemies.size();
    this.enemies = enemies;
  }
  
  public void projectiles(ArrayList<Projectile> projectiles){
    numProjectiles = projectiles.size();
    this.projectiles = projectiles;
  }
  
  public void obstacles(ArrayList<Wall> obstacles){
    numObstacles = obstacles.size();
    this.obstacles = obstacles;
  }
  public boolean getLeftClick(){
    return leftClick;
  }
  public void fired(){
    leftClick = false;
  }
  public void mouseClicked(MouseEvent e) {}      
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1){
      leftClick = true;
    }
    if (e.getButton() == MouseEvent.BUTTON3){
      classNum++;
      if (classNum > 2){
        classNum = 0;
      }
    }
  }
  public void mouseReleased(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1){
      leftClick = false;
    }
  }
  
  public void keyTyped(KeyEvent e) {
    if(e.getKeyChar() == '1' ){
      onePressed = !onePressed;
      if ((classNum != 1)&&(classNum != 2)){
        twoPressed = false;
      }
      if (classNum != 2){
        threePressed = false;
        fourPressed = false;
        fivePressed = false;
      }
    }
    if(e.getKeyChar() == '2' ){
      twoPressed = !twoPressed;
      if ((classNum != 1)&&(classNum != 2)){
        onePressed = false;
        if (classNum != 2){
          threePressed = false;
          fourPressed = false;
          fivePressed = false;
        }
      }
    }
    if(e.getKeyChar() == '3' ){
      threePressed = !threePressed;
      if (classNum != 2){
        onePressed = false;
        if ((classNum != 1)&&(classNum != 2)){
          twoPressed = false;
        }
        fourPressed = false;
        fivePressed = false;
      }
    }
    if(e.getKeyChar() == '4' ){
      fourPressed = !fourPressed;
      if (classNum != 2){
        onePressed = false;
        if ((classNum != 1)&&(classNum != 2)){
          twoPressed = false;
        }
        if (classNum != 2){
          threePressed = false;
        }
        fivePressed = false;
      }
    }
    if(e.getKeyChar() == '5' ){
      fivePressed = !fivePressed;
      if (classNum != 2){
        onePressed = false;
        if ((classNum != 1)&&(classNum != 2)){
          twoPressed = false;
        }
        if (classNum != 2){
          threePressed = false;
        }
        fourPressed = false;
      }
    }
  }
  
  public void keyPressed(KeyEvent e) {//////////////////////////////////////////////////////////////////
    if(e.getKeyChar() == 'd' )
      dPressed = true;
    if(e.getKeyChar() == 'w' )
      wPressed = true;
    if(e.getKeyChar() == 'a' )
      aPressed = true;
    if(e.getKeyChar() == 's' )
      sPressed = true;
  }
  public void keyReleased(KeyEvent e) {
    if(e.getKeyChar() == 'd' )
      dPressed = false;
    if(e.getKeyChar() == 'w' )
      wPressed = false;
    if(e.getKeyChar() == 'a' )
      aPressed = false;
    if(e.getKeyChar() == 's' )
      sPressed = false;
  }
  
  public Display(final Game game) {
    super();// Call the constructor for normal JPanel
    this.game = game;
    //player = game.getPlayer();
    addKeyListener(this);
    addMouseListener(this);
    /*
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
     */
    
    addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent arg0) {
        middleX = getWidth() / 2 - archer.getWidth() / 2;
        middleY = getHeight() / 2 - archer.getHeight() / 2;
      }
    });
    /*
     addKeyListener(new KeyAdapter() {
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
     */
    
    
    
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
      paladin = resize(ImageIO.read(new File("Paladin.png")), Player.getSize(), Player.getSize());
      rogue = resize(ImageIO.read(new File("Rogue.png")), Player.getSize(), Player.getSize());
      // the sprite
      enemy = resize(ImageIO.read(new File("Snake1.png")),Enemy.getSize(),Enemy.getSize());
      projectile = resize(ImageIO.read(new File("projectile.png")),24,24);
      fireBall= resize(ImageIO.read(new File("fireball.png")),24,24);
      airBall=resize(ImageIO.read(new File("airball.png")),24,24);
      earthBall=resize(ImageIO.read(new File("earthball.png")),24,24);
      waterBall=resize(ImageIO.read(new File("waterball.png")),24,24);
      darkBall=resize(ImageIO.read(new File("darkball.png")),24,24);
      background=resize(ImageIO.read(new File("Background.png")),backgroundX,backgroundY);
    } catch (IOException e) {
      e.printStackTrace();// If the image does not exist this will happen
    }
    middleX = getWidth() / 2 - archer.getWidth() / 2;
    middleY = getHeight() / 2 - archer.getHeight() / 2;
  }
  
  public void paint(Graphics g) {
    g.clearRect(0, 0, getWidth(), getHeight());
    g.drawImage(background, backgroundXCoord , backgroundYCoord, this);
    /*int xOff=- player.getX()+middleX- backgroundX/2;
     int yOff= - player.getY()+middleY- backgroundY/2;
     for (int i = 0; i * TILE_SIZE <= getWidth() + TILE_SIZE; i++) {
     for (int j = 0; j * TILE_SIZE <= getHeight() + TILE_SIZE; j++) {
     g.drawImage(background, xOff + i * TILE_SIZE, yOff + j * TILE_SIZE, this);
     }
     }*/
    if (classNum == 0){
      g.drawImage(archer, x, y, this);
    } else if (classNum == 1) {
      g.drawImage(paladin, x, y, this);
    } else {
      g.drawImage(rogue, x, y, this);
    }
    
    //ArrayList<Enemy> enemies = game.enemies;
    /*for (Enemy e : enemies) {
     if (player.inRenderRange(e, middleX, middleY)) {
     int dx = e.getX() - player.getX() + middleX + Enemy.getHalfSize();
     int dy = e.getY() - player.getY() + middleY + Enemy.getHalfSize();
     g.drawImage(enemy, dx, dy, this);
     }
     }*/
    
    for (int i = 0; i < numEnemies; i++){
      if ((enemies.get(i).getX() - enemies.get(i).getSize()/2 <= x + middleX && enemies.get(i).getX() + enemies.get(i).getSize()/2 >= x - middleX) && (enemies.get(i).getY() - enemies.get(i).getSize()/2 <= y + middleY && enemies.get(i).getY() + enemies.get(i).getSize()/2 >= y - middleY)) {
        g.drawImage(enemy,(enemies.get(i)).getX(),(enemies.get(i)).getY(),this);
        if (enemies.get(i).getBurn()){
          g.setColor(Color.RED);
          g.drawRect(enemies.get(i).getX(), enemies.get(i).getY()-10,8,8);
        }
        if (enemies.get(i).getSlow()){
          g.setColor(Color.BLUE);
          g.drawRect(enemies.get(i).getX()+10, enemies.get(i).getY()-10,8,8);
        }
      }
    }
    
    /*
     for (Projectile p : player.getProjectiles()) {
     if ((projectiles.get(i).getClass().getName().equals("Arrow"))||(projectiles.get(i).getClass().getName().equals("Sword"))||(projectiles.get(i).getClass().getName().equals("Dagger"))){
     if (player.inRenderRange(p, middleX, middleY)) {
     //int dx = p.getX() - player.getX() + middleX ;
     //int dy = p.getY() - player.getY() + middleY ;
     //g.drawImage(projectile, dx, dy, this);
     g.drawImage(projectile, p
     }
     }
     }
     */
    
    
    
    for (int i = 0; i < numObstacles; i++){
      g.setColor(new Color(156, 93, 82));
      g.drawPolygon(obstacles.get(i).getHitbox());
    }
    //Graphics2D[] projectileDrawings = new Graphics2D[numProjectiles];
    //g2d.setColor(Color.WHITE);
    //Rectangle rect2 = new Rectangle(100, 100, 20, 20);
    if ((classNum == 1) && (twoPressed)){
      g.setColor(Color.BLUE);
      g.drawRect(x-100+Player.getSize()/2,y-100+Player.getSize()/2,200,200);
    }
    for (int i = 0; i < projectiles.size(); i++){
      if ((projectiles.get(i).getClass().getName().equals("Arrow"))||(projectiles.get(i).getClass().getName().equals("Sword"))||(projectiles.get(i).getClass().getName().equals("Dagger"))){
        g.drawImage(projectile,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
      } else if (projectiles.get(i) instanceof FireArrow){
        g.drawImage(fireBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
      } else if (projectiles.get(i) instanceof FireSword){
        g.drawImage(fireBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
      } else if (projectiles.get(i) instanceof FireDagger){
        g.drawImage(fireBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
      } else if (projectiles.get(i) instanceof FireExplosion){
        g.setColor(Color.RED);
        g.drawRect((projectiles.get(i)).getX()-25,(projectiles.get(i)).getY()-25,50,50);
      } else if (projectiles.get(i) instanceof IceArrow){
        g.drawImage(waterBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
      } else if (projectiles.get(i) instanceof IceExplosion){
        g.setColor(Color.BLUE);
        g.drawRect((projectiles.get(i)).getX()-25,(projectiles.get(i)).getY()-25,50,50);
      } else if (projectiles.get(i) instanceof EarthArrow){
        g.drawImage(earthBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
      } else if (projectiles.get(i) instanceof EarthStun){
        g.setColor(new Color(156, 93, 82));
        g.drawRect(x-100+Player.getSize()/2,y-100+Player.getSize()/2,200,200);
      } else if (projectiles.get(i) instanceof AirArrow){
        g.drawImage(airBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
      } else if (projectiles.get(i) instanceof AirSword){
        g.drawImage(airBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
      } else if (projectiles.get(i) instanceof DarkArrow){
        g.drawImage(darkBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
      } else if (projectiles.get(i) instanceof DarkSword){
        g.drawImage(darkBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
      } else if (projectiles.get(i) instanceof DarkDecoy){
        g.setColor(Color.BLACK);
        g.drawRect((projectiles.get(i)).getX()-10,(projectiles.get(i)).getY()-10,20,20);
      }
    }
  }
  
  public boolean getDPressed(){
    return dPressed;
  }
  public boolean getWPressed(){
    return wPressed;
  }
  public boolean getAPressed(){
    return aPressed;
  }
  public boolean getSPressed(){
    return sPressed;
  }
  public boolean getTwoPressed(){
    return twoPressed;
  }
  public void setTwoPressed(boolean twoPressed){
    this.twoPressed = twoPressed;
  }
  public boolean getThreePressed(){
    return threePressed;
  }
  public void setThreePressed(boolean threePressed){
    this.threePressed = threePressed;
  }
  public boolean getFourPressed(){
    return fourPressed;
  }
  public void setFourPressed(boolean fourPressed){
    this.fourPressed = fourPressed;
  }
  public boolean getFivePressed(){
    return fivePressed;
  }
  public void setFivePressed(boolean fivePressed){
    this.fivePressed = fivePressed;
  }
  public int getNumPressed(){
    if (!(((classNum == 1)&&(twoPressed))||((classNum == 2)&&(threePressed))||((classNum == 2)&&(fourPressed))||((classNum == 2)&&(fivePressed)))){
      if(onePressed){
        return 1;
      } else if (twoPressed){
        return 2;
      } else if (threePressed){
        return 3;
      } else if (fourPressed){
        return 4;
      } else if (fivePressed){
        return 5;
      } else {
        return 0;
      }
    } else if ((classNum == 1)&&(twoPressed)){
      if(onePressed){
        return 1;
      } else if (threePressed){
        return 3;
      } else if (fourPressed){
        return 4;
      } else if (fivePressed){
        return 5;
      } else {
        return 0;
      }
    } else if ((classNum == 2)&&(threePressed)){
      if(onePressed){
        return 1;
      } else if (twoPressed){
        return 2;
      } else if (fourPressed){
        return 4;
      } else if (fivePressed){
        return 5;
      } else {
        return 0;
      }
    } else if ((classNum == 2)&&(fourPressed)){
      if(onePressed){
        return 1;
      } else if (twoPressed){
        return 2;
      } else if (threePressed){
        return 3;
      } else if (fivePressed){
        return 5;
      } else {
        return 0;
      }
    } else {
      if(onePressed){
        return 1;
      } else if (twoPressed){
        return 2;
      } else if (threePressed){
        return 3;
      } else if (fourPressed){
        return 4;
      } else {
        return 0;
      }
    }
  }
  public int getClassNum(){
    return classNum;
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