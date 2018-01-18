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
import java.awt.Toolkit;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Display extends JPanel implements KeyListener, MouseListener{
  
  private BufferedImage title, instructions, archer, paladin, rogue,fighter, wizard, hunter, enemy, projectile,fireBall, airBall, earthBall, waterBall, darkBall, background, healthOrb, speedOrb, dexOrb, attOrb, sniper, boss, speedy, glassCannon;
  public static int middleX, middleY;
  private static int backgroundX=4000;
  private static int backgroundY=4000;
  private int backgroundXCoord = -backgroundX/2;
  private int backgroundYCoord = -backgroundY/2;
  private static int TILE_SIZE=4000;
  //private final Player player;
  public int x = 0;
  public int y = 0;
  public double mana = 100;
  public int health = 100;
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
  private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  private ArrayList<Projectile> enemyProjectiles = new ArrayList<Projectile>();
  private ArrayList<Wall> obstacles = new ArrayList<Wall>();
  private int pageId;
  private int score;
  public void setX(int x){
    this.x = x;
  }
  public void setY(int y){
    this.y = y;
  }
  public void setHealth(int health){
    this.health = health;
  }
  public void setMana(double mana){
    this.mana = mana;
  }
  public void setScore(int score){
    this.score = score;
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
    this.enemies = enemies;
  }
  
  public void projectiles(ArrayList<Projectile> projectiles){
    this.projectiles = projectiles;
  }
  
  public void enemyProjectiles(ArrayList<Projectile> enemyProjectiles){
    if (enemyProjectiles!=null){
      this.enemyProjectiles = enemyProjectiles;
    }
  }
  
  public void obstacles(ArrayList<Wall> obstacles){
    this.obstacles = obstacles;
  }
  public boolean getLeftClick(){
    return leftClick;
  }
  public void setId(int id){
    pageId = id;
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
      if (classNum > 5){
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
      if (classNum != 3){
        if ((classNum != 1)&&(classNum != 2)){
          twoPressed = false;
        }
        if ((classNum != 2)&&(classNum != 3)){
          threePressed = false;
          fivePressed = false;
        }
        if (classNum != 2){
          fourPressed = false;
        }
      }
    }
    if(e.getKeyChar() == '2' ){
      twoPressed = !twoPressed;
      if ((classNum != 1)&&(classNum != 2)){
        if (classNum != 3){
          onePressed = false;
        }
        if ((classNum != 2)&&(classNum != 3)){
          threePressed = false;
          fivePressed = false;
        }
        if (classNum != 2){
          fourPressed = false;
        }
      }
    }
    if(e.getKeyChar() == '3' ){
      threePressed = !threePressed;
      if ((classNum != 2)&&(classNum != 3)){
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
        if (classNum != 3){
          onePressed = false;
        }
        if ((classNum != 1)&&(classNum != 2)){
          twoPressed = false;
        }
        if ((classNum != 2) && (classNum != 3)){
          threePressed = false;
        }
        if (classNum != 3){
          fivePressed = false;
        }
      }
    }
    if(e.getKeyChar() == '5' ){
      fivePressed = !fivePressed;
      if ((classNum != 2)&&(classNum != 3)){
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
  
  public Display() {
    super();// Call the constructor for normal JPanel
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
      title = resize(ImageIO.read(new File("titletemp.png")), (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
      instructions = resize(ImageIO.read(new File("Instructions.png")), (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
      
      archer = resize(ImageIO.read(new File("Archer.png")), Player.getSize(), Player.getSize());// Set the image for
      paladin = resize(ImageIO.read(new File("Paladin.png")), Player.getSize(), Player.getSize());
      rogue = resize(ImageIO.read(new File("Rogue.png")), Player.getSize(), Player.getSize());
      fighter = resize(ImageIO.read(new File("Wizard.png")), Player.getSize(), Player.getSize());
      wizard = resize(ImageIO.read(new File("Wizard.png")), Player.getSize(), Player.getSize());
      hunter = resize(ImageIO.read(new File("Archer.png")), Player.getSize(), Player.getSize());
      // the sprite
      enemy = resize(ImageIO.read(new File("Snake1.png")),Enemy.getSize(),Enemy.getSize());
      sniper = resize(ImageIO.read(new File("Sniper.png")),Enemy.getSize(),Enemy.getSize());
      boss = resize(ImageIO.read(new File("Boss.png")),Enemy.getSize(),Enemy.getSize());
      speedy = resize(ImageIO.read(new File("Speedy.png")),Enemy.getSize(),Enemy.getSize());
      glassCannon = resize(ImageIO.read(new File("GlassCannon.png")),Enemy.getSize(),Enemy.getSize());
      
      projectile = resize(ImageIO.read(new File("projectile.png")),24,24);
      fireBall= resize(ImageIO.read(new File("fireball.png")),24,24);
      airBall=resize(ImageIO.read(new File("airball.png")),24,24);
      earthBall=resize(ImageIO.read(new File("earthball.png")),24,24);
      waterBall=resize(ImageIO.read(new File("waterball.png")),24,24);
      darkBall=resize(ImageIO.read(new File("darkball.png")),24,24);
      background=resize(ImageIO.read(new File("Background.png")),backgroundX,backgroundY);
      
      healthOrb = resize(ImageIO.read(new File("HealthOrb.png")),40,40);
      speedOrb = resize(ImageIO.read(new File("SpeedOrb.png")),40,40);
      dexOrb = resize(ImageIO.read(new File("DexOrb.png")),40,40);
      attOrb = resize(ImageIO.read(new File("AttOrb.png")),40,40);
      
    } catch (IOException e) {
      e.printStackTrace();// If the image does not exist this will happen
    }
    middleX = getWidth() / 2 - archer.getWidth() / 2;
    middleY = getHeight() / 2 - archer.getHeight() / 2;
  }
  
  public void paint(Graphics g) {
    if (pageId == 0){
      g.clearRect(0, 0, getWidth(), getHeight());
      g.drawImage(title, 0, 0, this);
    } else if (pageId == 4){
      g.clearRect(0, 0, getWidth(), getHeight());
      g.drawImage(instructions, 0, 0, this);
    } else if (pageId == 1){
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
      } else if (classNum == 2){
        g.drawImage(rogue, x, y, this);
      } else if (classNum == 3){
        g.drawImage(fighter, x, y, this);
      } else if (classNum == 4){
        g.drawImage(wizard, x, y, this);
      } else if (classNum == 5){
        g.drawImage(hunter, x, y, this);
      }
      
      g.setColor(Color.RED);
      g.fillRect(x, y - 15, (int)Math.round(health*1.0/Player.getHealthMax() * Player.getSize()), 5);
      g.setColor(Color.BLUE);
      g.fillRect(x, y - 10, (int)Math.round(mana*1.0/Player.getManaMax() * Player.getSize()), 5);
      
      //ArrayList<Enemy> enemies = game.enemies;
      /*for (Enemy e : enemies) {
       if (player.inRenderRange(e, middleX, middleY)) {
       int dx = e.getX() - player.getX() + middleX + Enemy.getHalfSize();
       int dy = e.getY() - player.getY() + middleY + Enemy.getHalfSize();
       g.drawImage(enemy, dx, dy, this);
       }
       }*/
      
      for (int i = 0; i < enemies.size(); i++){
        if ((enemies.get(i).getX() - enemies.get(i).getSize()/2 <= x + middleX && enemies.get(i).getX() + enemies.get(i).getSize()/2 >= x - middleX) && (enemies.get(i).getY() - enemies.get(i).getSize()/2 <= y + middleY && enemies.get(i).getY() + enemies.get(i).getSize()/2 >= y - middleY)) {
          if (enemies.get(i) instanceof SniperE){
            g.drawImage(sniper,(enemies.get(i)).getX(),(enemies.get(i)).getY(),this);
          } else if (enemies.get(i) instanceof BossE){
            g.drawImage(boss,(enemies.get(i)).getX(),(enemies.get(i)).getY(),this);
          } else if (enemies.get(i) instanceof SpeedyE){
            g.drawImage(speedy,(enemies.get(i)).getX(),(enemies.get(i)).getY(),this);
          } else if (enemies.get(i) instanceof GlassCannonE){
            g.drawImage(glassCannon,(enemies.get(i)).getX(),(enemies.get(i)).getY(),this);
          } else {
            g.drawImage(enemy,(enemies.get(i)).getX(),(enemies.get(i)).getY(),this);
          }
          //g.setColor(Color.RED);
          //g.fillRect((int)enemies.get(i).getHitbox().getX(), (int)enemies.get(i).getHitbox().getY(), (int)enemies.get(i).getHitbox().getWidth(), (int)enemies.get(i).getHitbox().getHeight());
          if (enemies.get(i).getBurn()){
            g.setColor(Color.RED);
            g.fillRect(enemies.get(i).getX(), enemies.get(i).getY()-10,8,8);
          }
          if (enemies.get(i).getSlow()){
            g.setColor(Color.BLUE);
            g.fillRect(enemies.get(i).getX()+10, enemies.get(i).getY()-10,8,8);
          }
          if (enemies.get(i).getStun()){
            g.setColor(new Color(156, 93, 82));
            g.fillRect(enemies.get(i).getX()+20, enemies.get(i).getY()-10,8,8);
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
      
      
      
      for (int i = 0; i < obstacles.size(); i++){
        g.setColor(new Color(156, 93, 82));
        g.fillPolygon(obstacles.get(i).getHitbox());
      }
      //Graphics2D[] projectileDrawings = new Graphics2D[numProjectiles];
      //g2d.setColor(Color.WHITE);
      //Rectangle rect2 = new Rectangle(100, 100, 20, 20);
      if ((classNum == 1) && (twoPressed)){
        g.setColor(Color.BLUE);
        g.fillRect(x-100+Player.getSize()/2,y-100+Player.getSize()/2,200,200);
      }
      for (int i = 0; i < projectiles.size(); i++){
        if ((projectiles.get(i).getClass().getName().equals("Arrow"))||(projectiles.get(i).getClass().getName().equals("Sword"))||(projectiles.get(i).getClass().getName().equals("Dagger"))||(projectiles.get(i).getClass().getName().equals("Fist"))||(projectiles.get(i).getClass().getName().equals("Bolt"))){
          g.drawImage(projectile,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof FireArrow){
          g.drawImage(fireBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof FireSword){
          g.drawImage(fireBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof FireDagger){
          g.drawImage(fireBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof FireExplosion){
          g.setColor(Color.RED);
          g.fillRect((projectiles.get(i)).getX()-25,(projectiles.get(i)).getY()-25,50,50);
        } else if (projectiles.get(i) instanceof FireFist){
          g.drawImage(fireBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof FireSpell){
          g.setColor(Color.RED);
          //g.drawRect((int)projectiles.get(i).getHitbox().getX(), (int)projectiles.get(i).getHitbox().getY(), (int)projectiles.get(i).getHitbox().getWidth(), (int)projectiles.get(i).getHitbox().getHeight());
          g.fillRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof FireTrap){
          g.setColor(Color.RED);
          g.fillRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof FireTrapExplosion){
          g.setColor(Color.RED);
          g.fillRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof IceArrow){
          g.drawImage(waterBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof IceExplosion){
          g.setColor(Color.BLUE);
          g.fillRect((projectiles.get(i)).getX()-25,(projectiles.get(i)).getY()-25,50,50);
        } else if (projectiles.get(i) instanceof IceFist){
          g.drawImage(waterBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof IceSpell){
          g.setColor(Color.BLUE);
          g.fillRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof IceTrap){
          g.setColor(Color.BLUE);
          g.fillRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof IceTrapExplosion){
          g.setColor(Color.BLUE);
          g.fillRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof EarthArrow){
          g.drawImage(earthBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof EarthStun){
          g.setColor(new Color(156, 93, 82));
          g.drawRect(x-100+Player.getSize()/2,y-100+Player.getSize()/2,200,200);
        } else if (projectiles.get(i) instanceof EarthTrap){
          g.setColor(new Color(156, 93, 82));
          g.fillRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof EarthTrapExplosion){
          g.setColor(new Color(156, 93, 82));
          g.fillRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof AirArrow){
          g.drawImage(airBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof AirSword){
          g.drawImage(airBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof AirFist){
          g.drawImage(airBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof AirSpell){
          g.setColor(Color.BLUE);
          g.fillRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof AirTrap){
          g.setColor(Color.BLUE);
          g.fillRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof AirTrapExplosion){
          g.setColor(Color.BLUE);
          g.drawRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof DarkArrow){
          g.drawImage(darkBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof DarkSword){
          g.drawImage(darkBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof DarkDecoy){
          g.setColor(Color.BLACK);
          g.fillRect((projectiles.get(i)).getX()-10,(projectiles.get(i)).getY()-10,20,20);
        } else if (projectiles.get(i) instanceof AttOrb){
          g.drawImage(attOrb,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof SpeedOrb){
          g.drawImage(speedOrb,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof DexOrb){
          g.drawImage(dexOrb,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof HealthOrb){
          g.drawImage(healthOrb,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        }
      }
      for (int i = 0; i < enemyProjectiles.size(); i++){
        g.setColor(Color.RED);
        g.fillRect((enemyProjectiles.get(i)).getX()-enemyProjectiles.get(i).getWidth()/2,(enemyProjectiles.get(i)).getY()-enemyProjectiles.get(i).getHeight()/2, enemyProjectiles.get(i).getWidth(), enemyProjectiles.get(i).getHeight());
      }
    } else if (pageId == 2){
      g.clearRect(0, 0, getWidth(), getHeight());
      g.setColor(Color.BLACK);
      g.fillRect(0,0,(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
      g.setColor(Color.WHITE);
      Font font = new Font("Serif", Font.BOLD, 50);
      g.setFont(font);
      g.drawString("You have been defeated! Your score was "+score, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-350, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
    } else if (pageId == 3){
      System.exit(0);
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
  public boolean getOnePressed(){
    return onePressed;
  }
  public void setOnePressed(boolean onePressed){
    this.onePressed = onePressed;
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
    /*if (!(((classNum == 1)&&(twoPressed))||((classNum == 2)&&(threePressed))||((classNum == 2)&&(fourPressed))||((classNum == 2)&&(fivePressed))||((classNum == 3)&&(onePressed))||((classNum == 3)&&(threePressed))||((classNum == 3)&&(fivePressed)))){
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
     } else */if ((classNum == 1)&&(twoPressed)){
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
     } else if ((classNum == 2)&&(fivePressed)){
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
     } else if ((classNum == 3)&&(onePressed)){
       if (twoPressed){
         return 2;
       } else if (threePressed){
         return 3;
       } else if (fourPressed){
         return 4;
       } else {
         return 0;
       }
     } else if ((classNum == 3)&&(threePressed)){
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
     } else if ((classNum == 3)&&(fivePressed)){
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
     } else {
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