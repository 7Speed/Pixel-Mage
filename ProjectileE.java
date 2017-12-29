/**
 * Auto Generated Java Class.
 */
import java.awt.Rectangle;
import java.util.ArrayList;

public class ProjectileE extends Enemy{
  int reload;
  
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  
  public ProjectileE (int health, int aggro, int x, int y) { 
    super(health, aggro, x, y);
  }
  
  public void fire(int spawnX, int spawnY, int targetX, int targetY){
    EnemyProjectile Errow = new EnemyProjectile(spawnX, spawnY, targetX, targetY);
    addProjectile(Errow);
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
  
}//class

  

