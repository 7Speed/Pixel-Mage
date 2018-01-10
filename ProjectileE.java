/**
 * Auto Generated Java Class.
 */
import java.awt.Rectangle;
import java.util.ArrayList;

public class ProjectileE extends Enemy{
  int reload;
  int reloadCap = 100;//Moved to enemy class for easier customization of different enemy classes
  int range = 200;//Moved to enemy class for easier customization of different enemy classes
  
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  
  public ProjectileE (int health, int aggro, int x, int y) { 
    super(health, aggro, x, y);
  }
  
  public void fire(int spawnX, int spawnY, int targetX, int targetY){
    EnemyProjectile errow = new EnemyProjectile(spawnX, spawnY, targetX, targetY);
    addProjectile(errow);
  }
  
  public void addProjectile(Projectile projectile){
    projectiles.add(projectile);
  }
  
  public ArrayList<Projectile> getProjectiles(){
    return projectiles;
  }
  
  public int getReload(){
    return reload; 
  }
  
  public void addReload(){
    this.reload = this.reload + 1; 
  }
  
  public void wipeReload(){
    this.reload = 0; 
  }
  
  public int getRange(){
    return range;//Returns the range of the enemy (the distance before it starts shooting. This is in enemy class now to make it easier to add a sniper enemy and other classes
  }
  
  public int getReloadCap(){
    return reloadCap;
  }
  
  public int getDistance(int playerX, int playerY){
    int a;
    int b;
    
    a = (super.getX()+ super.getSize()/2) - playerX;
    b = (super.getY()+ super.getSize()/2) - playerY;
    
    return (int)(Math.sqrt(a*a + b*b));//Changed so that it doesn't change range anymore, instead directly outputs the distance
  }
}//class