import java.awt.Rectangle;
import java.util.ArrayList;
abstract class Player{
  static int healthMax = 100;
  static int manaMax = 100;
  private int positionX;
  private int positionY;
  private int health;
  private int mana;
  private int element;//0 is neutral, 1 is fire, 2 is water, 3 is earth, 4 is air, 5 is dark, 6 is light
  private int speed = 3;
  private static int size = 20;
  private Rectangle hitbox;
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  private int reloadCap;
  private double dexBuff = 1;
  private double speedBuff = 1;
  private int dexBuffCount = 0;
  private int speedBuffCount = 0;
  Player(int positionX, int positionY, int health, int mana, int element, int reloadCap){
    this.positionX = positionX;
    this.positionY = positionY;
    this.health = health;
    this.mana = mana;
    this.element = element;
    this.reloadCap = reloadCap;
    hitbox = new Rectangle(positionX, positionY, size, size);
  }
  public void setProjectiles(ArrayList<Projectile> projectiles){
    this.projectiles = projectiles;
  }
  public int getSpeed(){
    return (int)Math.round(speed*speedBuff);
  }
  public int getX(){
    return positionX;
  }
  public int getY(){
    return positionY;
  }
  public int getHealth(){
    return health;
  }
  public int getMana(){
    return mana;
  }
  public static int getSize(){
    return size;
  }
  public int getElement(){
    return element;
  }
  public void setElement(int element){
    this.element = element;
  }
  public void setSpeedBuff(double speedBuff){
    this.speedBuff = speedBuff;
    if (speedBuff!=1){
      speedBuffCount = 1;
    }
  }
  public void setDexBuff(double dexBuff){
    this.dexBuff = dexBuff;
    if (speedBuff!=1){
      dexBuffCount = 1;
    }
  }
  public void setSpeedBuffCount(int speedBuffCount){
    this.speedBuffCount = speedBuffCount;
  }
  public void setDexBuffCount(int dexBuffCount){
    this.dexBuffCount = dexBuffCount;
  }
  public int getSpeedBuffCount(){
    return speedBuffCount;
  }
  public int getDexBuffCount(){
    return dexBuffCount;
  }
  
  
  public void move(int [] displaceModifier, ArrayList<Enemy> enemies, ArrayList<Wall> obstacles){
    for (int i = 0; i < enemies.size(); i++){
      enemies.get(i).displace(displaceModifier);
    }
    for (int i = 0; i < projectiles.size(); i++){
      projectiles.get(i).displace(displaceModifier);
    }
    for (int i = 0; i < obstacles.size(); i++){
      obstacles.get(i).displace(displaceModifier);
    }
  }
  
  public void move(int direction, int speed, ArrayList<Enemy> enemies, ArrayList<Wall> obstacles){
    for (int i = 0; i < enemies.size(); i++){
      enemies.get(i).displace(direction, (int)Math.round(speed*speedBuff));
    }
    for (int i = 0; i < projectiles.size(); i++){
      projectiles.get(i).displace(direction, (int)Math.round(speed*speedBuff));
    }
    for (int i = 0; i < obstacles.size(); i++){
      obstacles.get(i).displace(direction, (int)Math.round(speed*speedBuff));
    }
  }
    /*
    if (direction == 0){
      positionX += speed;
    } else if (direction == 1){
      positionX += Math.round(speed/Math.sqrt(2));
      positionY -= Math.round(speed/Math.sqrt(2));
    } else if (direction == 2){
      positionY -= speed;
    } else if (direction == 3){
      positionX -= Math.round(speed/Math.sqrt(2));
      positionY -= Math.round(speed/Math.sqrt(2));
    } else if (direction == 4){
      positionX -= speed;
    } else if (direction == 5){
      positionX -= Math.round(speed/Math.sqrt(2));
      positionY += Math.round(speed/Math.sqrt(2));
    } else if (direction == 6){
      positionY += speed;
    } else if (direction == 7){
      positionX += Math.round(speed/Math.sqrt(2));
      positionY += Math.round(speed/Math.sqrt(2));
    }*/
  
  public void addProjectile(Projectile projectile){
    projectiles.add(projectile);
  }
  public ArrayList<Projectile> getProjectiles(){
    return projectiles;
  }
  public int getReloadCap(){
    return (int)(reloadCap/dexBuff);
  }
  public Rectangle getHitbox(){
    return hitbox;
  }
  public abstract void fire(int spawnX, int spawnY, int targetX, int targetY, int element);
}