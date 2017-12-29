import java.awt.Rectangle;
import java.util.ArrayList;
abstract class Player{
  static int healthMax = 100;
  static int manaMax = 100;
  private int positionX;
  private int positionY;
  private int health;
  private int mana;
  private int element;
  private static int size = 20;
  private Rectangle hitbox;
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  private int reloadCap;
  Player(int positionX, int positionY, int health, int mana, int element, int reloadCap){
    this.positionX = positionX;
    this.positionY = positionY;
    this.health = health;
    this.mana = mana;
    this.element = element;
    this.reloadCap = reloadCap;
    hitbox = new Rectangle(positionX, positionY, size, size);
  }
  public int getX(){
    return positionX;
  }
  public int getY(){
    return positionY;
  }
  public static int getSize(){
    return size;
  }
  public void move(int direction, int speed){
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
    }
  }
  public void addProjectile(Projectile projectile){
    projectiles.add(projectile);
  }
  public ArrayList<Projectile> getProjectiles(){
    return projectiles;
  }
  public int getReloadCap(){
    return reloadCap;
  }
  public Rectangle getHitbox(){
    return hitbox;
  }
}