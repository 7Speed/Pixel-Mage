//Import
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

/*
* Display.java
* Last updated Jan 18, 2018
* Draws alll the sprites and check key and mous pressed
* @Author Sean Jeong
*/

/*
 * Display.class
 * Main class for display.java
*/
public class Display extends JPanel implements KeyListener, MouseListener{
//Declare/set initial variables----------------------------------------------------------------------------------------  
//Sprites variables
  private BufferedImage title, instructions, archerSheet, paladinSheet, rogueSheet,fighterSheet, wizardSheet, hunterSheet, enemy, projectile,fireBall, airBall, earthBall, waterBall, darkBall, fireExplosion, fireSpell,iceExplosion,iceSpell, airSpell, airTrapExplosion, background, healthOrb, speedOrb, dexOrb, attOrb, sniper, boss, speedy, glassCannon;
  BufferedImage[] archer, paladin, rogue, fighter,wizard, hunter;
  //Spritesheet variables
  private int currentSprite=0;
  private int currentStep=0;
  private int sCount=-1;
  private int aCount=-1;
  private int dCount=-1; 
  private int wCount=-1;
  //Size and coordinate variables
  public static int middleX, middleY;
  private static int backgroundX=5120;
  private static int backgroundY=3840;
  private int backgroundXCoord = -backgroundX/2;
  private int backgroundYCoord = -backgroundY/2;
  public int x = 0;
  public int y = 0;
  //Hp and mp stats
  public double mana = 100;
  public int health = 100;
  //Clicking inputs
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
  //Class num to determine which class is which
  private int classNum = 0;
  //Array for enmeis,projectiles and other misc.
  private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  private ArrayList<Projectile> enemyProjectiles = new ArrayList<Projectile>();
  private ArrayList<Wall> obstacles = new ArrayList<Wall>();
  //Pages and score
  private int pageId;
  private int score;
  
  //Methods------------------------------------------------------------------------------------------------------------
  /*
 * setX
 * Sets x value of playe
 * @param x, sets the x value
*/
  public void setX(int x){
    this.x = x;
  }
  
  /*
 * setY
 * Sets y value of playe
 * @param y, sets the y value
*/
  public void setY(int y){
    this.y = y;
  }
  
  /*
 * setHealth
 * Sets the health of the player
 * @param health, health value
*/
  public void setHealth(int health){
    this.health = health;
  }
  
    /*
 * setMana
 * Sets the mana of the player
 * @param mana, mana value
*/
  public void setMana(double mana){
    this.mana = mana;
  }
  
      /*
 * setScore
 * Sets the mana of the player
 * @param score, game score value
*/
  public void setScore(int score){
    this.score = score;
  }
  
  /*
 * setCoord
 * Sets the coordinates of the player
 * @param x, player x value
 * @param y, player y value
*/
  public void setCoord(int [] coord){
    this.x = coord[0];
    this.y = coord[1];
  }
  
    /*
 * background
 * Sets the coordinates of the background
 * @param background, background class which stores x and y values
*/
  public void background(Background background){
    backgroundXCoord = background.getX();
    backgroundYCoord = background.getY();
  }
  
      /*
 * getBackgroundX
 * Sets the coordinates of the backgroundX
 * @return backgroundX, set the current x value of the background
*/
  public static int getBackgroundX(){
    return backgroundX;
  }
  
        /*
 * getBackgroundY
 * Sets the coordinates of the backgroundY
 * @return backgroundY, set the current y value of the background
*/
  public static int getBackgroundY(){
    return backgroundY;
  }
      
  /*
 * enemies
 * Gets the arraylist of the enemeis from display class
 * @param enemies, all the enemies in the game
*/
  public void enemies(ArrayList<Enemy> enemies){
    this.enemies = enemies;
  }
  
   /*
 * projectiles
 * Gets the arraylist of the projectiles from display class
 * @param projectiles, all the player's projectiles in the game
*/
  public void projectiles(ArrayList<Projectile> projectiles){
    this.projectiles = projectiles;
  }
  
     /*
 * enemyProjectiles
 * Gets the arraylist of the enemies projectiles from display class
 * @param enemyProjectiles, all the enemies projectiles in the game
*/
  public void enemyProjectiles(ArrayList<Projectile> enemyProjectiles){
    if (enemyProjectiles!=null){
      this.enemyProjectiles = enemyProjectiles;
    }
  }
  
       /*
 * obstacles
 * Gets the arraylist of the obstacles from display class
 * @param obstacles, all the obstacles in the game
*/
  public void obstacles(ArrayList<Wall> obstacles){
    this.obstacles = obstacles;
  }
  
/*
 * getLeftClick
 * Gets if the left key is clicked 
 * @return leftClick, if leftClick is pressed returns true
*/
  public boolean getLeftClick(){
    return leftClick;
    
  /*
 * setId
 * Sets the id number to know what page to display
 * @param id, int to set id as
*/  
  }
  public void setId(int id){
    pageId = id;
  }
  
  /*
 * fired
 * Sets leftClick as false if athe player shoots
*/
  public void fired(){
    leftClick = false;
  }
  
 /*
 * mouseClicked
 * Sees if mouse is clicked and then unclicks
 * @param MouseEvent e
*/
  public void mouseClicked(MouseEvent e) {}      
   /*
 * mouseEntered
 * Sees if mouse is clicked and then holds
 * @param MouseEvent e
*/
  public void mouseEntered(MouseEvent e) {}
     /*
 * mouseExited
 * Sees if mouse is unclicked and then stops
 * @param MouseEvent e
*/
  public void mouseExited(MouseEvent e) {}
  /*
 * mousePressed
 * Sets the leftClick as true if leftclick is pressed and update class if roght click is pressed
 * @param MouseEvent e
*/
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
     /*
 * mouseReleased
 * Sees if left click is unclicked and then sets leftClick as false
 * @param MouseEvent e
*/
  public void mouseReleased(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1){
      leftClick = false;
    }
  }

     /*
 * keyTyped
 * Sees what key was pressed and sets that keypressed as true
 * @param KeyEvent e
*/
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
 /*
 * keyPressed
 * Sees if keyboard is pressed and sets the Pressed values as true, also updates the currentSprite for sprite sheets
 * @param KeyEvent e
*/
  public void keyPressed(KeyEvent e) {
    if(e.getKeyChar() == 's' ){
      aCount=-1;
      dCount=-1;
      wCount=-1; 
      sCount++;
      if (sCount==0){
        currentSprite=0;
        sCount=9;
      }
      if (sCount==10){
        sCount=0;
        if (currentSprite==0){
          currentSprite=2;
        }else {
          currentSprite=0;
        }      
      }
      sPressed=true;
    }
    if(e.getKeyChar() == 'a' ){
      sCount=-1;
      dCount=-1;
      wCount=-1;
      aCount++;
      if (aCount==0){
        currentSprite=3;
        aCount=9;
      }
      if (aCount==10){
        aCount=0;
        if (currentSprite==3){
          currentSprite=5;
        }else {
          currentSprite=3;
        }      
      }
      aPressed=true;
    }
    if(e.getKeyChar() == 'd' ){
      sCount=-1;
      aCount=-1;
      wCount=-1;
      dCount++;
      if (dCount==0){
        currentSprite=6;
        dCount=9;
      }
      if (dCount==10){
        dCount=0;
        if (currentSprite==6){
          currentSprite=8;
        }else {
          currentSprite=6;
        }      
      }
      dPressed=true;
    }
    if(e.getKeyChar() == 'w' ){
      sCount=-1;
      aCount=-1;
      dCount=-1;
      wCount++;
      if (wCount==0){
        currentSprite=9;
        wCount=9;
      }
      if (wCount==10){
        wCount=0;
        if (currentSprite==9){
          currentSprite=11;
        }else {
          currentSprite=9;
        }      
      }
      wPressed=true;
    }
  }
   /* keyReleased
 * Unsets Pressed values after key is released
 * @param KeyEvent e
*/
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
  
 /* Display
 * Imports all the images 
*/
  public Display() {
    super();
    addKeyListener(this);
    addMouseListener(this);
    
    addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent arg0) {
        middleX = getWidth() / 2 - 50;
        middleY = getHeight() / 2 - 50;
      }
    });
    
    setDoubleBuffered(true);// Make the panel not blink
    setFocusable(true);
    try {
      title = resize(ImageIO.read(new File("title.png")), (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
      instructions = resize(ImageIO.read(new File("Instructions.png")), (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
      
      archerSheet = ImageIO.read(new File("ArcherSheet.png"));
      paladinSheet = ImageIO.read(new File("PaladinSheet.png"));
      rogueSheet =ImageIO.read(new File("RogueSheet.png"));
      fighterSheet = ImageIO.read(new File("FighterSheet.png"));
      wizardSheet =ImageIO.read(new File("WizardSheet.png"));
      hunterSheet= ImageIO.read(new File("HunterSheet.png"));
      
      final int width = 100;
      final int height = 100;
      final int rows = 4;
      final int cols = 3;
      archer = new BufferedImage[rows * cols];
      paladin =new BufferedImage[rows * cols];
      rogue=new BufferedImage[rows * cols];
      fighter=new BufferedImage[rows * cols];
      wizard=new BufferedImage[rows * cols];
      hunter=new BufferedImage[rows * cols];
      
      for (int j = 0; j < rows; j++){
        for (int i = 0; i < cols; i++){
          archer[(j * cols) + i] = archerSheet.getSubimage(i * width,j * height,width,height);
          paladin[(j * cols) + i] = paladinSheet.getSubimage(i * width,j * height,width,height);
          rogue[(j * cols) + i] = rogueSheet.getSubimage(i * width,j * height,width,height);
          fighter[(j * cols) + i] = fighterSheet.getSubimage(i * width,j * height,width,height);
          wizard[(j * cols) + i] = wizardSheet.getSubimage(i * width,j * height,width,height);
          hunter[(j * cols) + i] = hunterSheet.getSubimage(i * width,j * height,width,height);
        }
      }
      
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
      
      fireSpell=resize(ImageIO.read(new File("fire.png")),240,240);
      iceExplosion=resize(ImageIO.read(new File("ice.png")),50,50);
      iceSpell=resize(ImageIO.read(new File("ice.png")),240,240);
      airSpell=resize(ImageIO.read(new File("tornado.png")),240,240);
      airTrapExplosion=resize(ImageIO.read(new File("tornado.png")),500,500);
    } catch (IOException e) {
      e.printStackTrace();// If the image does not exist this will happen
    }
    middleX = getWidth() / 2 - 50;
    middleY = getHeight() / 2 - 50;
  }
  
/* paint
 * Draws all the sprites and shapes
 * @param Graphics g
*/
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
      
      if (classNum == 0){
        g.drawImage(archer[currentSprite], x, y, this);
      } else if (classNum == 1) {
        g.drawImage(paladin[currentSprite], x, y, this);
      } else if (classNum == 2){
        g.drawImage(rogue[currentSprite], x, y, this);
      } else if (classNum == 3){
        g.drawImage(fighter[currentSprite], x, y, this);
      } else if (classNum == 4){
        g.drawImage(wizard[currentSprite], x, y, this);
      } else if (classNum == 5){
        g.drawImage(hunter[currentSprite], x, y, this);
      }
      
      g.setColor(Color.RED);
      g.fillRect(x, y - 15, (int)Math.round(health*1.0/Player.getHealthMax() * Player.getSize()), 5);
      g.setColor(Color.BLUE);
      g.fillRect(x, y - 10, (int)Math.round(mana*1.0/Player.getManaMax() * Player.getSize()), 5);
      
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
      
      for (int i = 0; i < obstacles.size(); i++){
        g.setColor(new Color(156, 93, 82));
        g.fillPolygon(obstacles.get(i).getHitbox());
      }
      if ((classNum == 1) && (twoPressed)){
        g.setColor(Color.BLUE);
        g.drawRect(x-100+Player.getSize()/2,y-100+Player.getSize()/2,200,200);
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
          g.drawImage(fireExplosion, (projectiles.get(i)).getX()-25,(projectiles.get(i)).getY()-25,this);
        } else if (projectiles.get(i) instanceof FireFist){
          g.drawImage(fireBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof FireSpell){
          g.drawImage(fireSpell, (projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,this);
        } else if (projectiles.get(i) instanceof FireTrap){
          g.setColor(Color.RED);
          g.fillRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof FireTrapExplosion){
          g.setColor(Color.RED);
          g.drawImage(fireSpell, (projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,this);
        } else if (projectiles.get(i) instanceof IceArrow){
          g.drawImage(waterBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof IceExplosion){
          g.drawImage(iceExplosion, (projectiles.get(i)).getX()-25,(projectiles.get(i)).getY()-25,this);
        } else if (projectiles.get(i) instanceof IceFist){
          g.drawImage(waterBall,(projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2, this);
        } else if (projectiles.get(i) instanceof IceSpell){
          g.drawImage(iceSpell, (projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,this);
        } else if (projectiles.get(i) instanceof IceTrap){
          g.setColor(Color.BLUE);
          g.fillRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof IceTrapExplosion){
          g.setColor(Color.BLUE);
          g.drawImage(iceSpell, (projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,this);
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
          g.drawImage(airSpell, (projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,this);
        } else if (projectiles.get(i) instanceof AirTrap){
          g.setColor(Color.BLUE);
          g.fillRect((projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,projectiles.get(i).getWidth(),projectiles.get(i).getHeight());
        } else if (projectiles.get(i) instanceof AirTrapExplosion){
          g.setColor(Color.BLUE);
          g.drawImage(airTrapExplosion, (projectiles.get(i)).getX()-projectiles.get(i).getWidth()/2,(projectiles.get(i)).getY()-projectiles.get(i).getHeight()/2,this);
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
  
  /* getDPressed
 * Draws all the sprites and shapes
 * @param Graphics g
*/
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
    if ((classNum == 1)&&(twoPressed)){
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