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
    if (!getStun()){
      EnemyProjectile errow = new EnemyProjectile(spawnX, spawnY, targetX, targetY);
      addProjectile(errow);
    }
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
  
  public void displace(int direction, int speed){
    if (direction == 4){
      x += speed;
    } else if (direction == 5){
      x += Math.round(speed/Math.sqrt(2));
      y -= Math.round(speed/Math.sqrt(2));
    } else if (direction == 6){
      y -= speed;
    } else if (direction == 7){
      x -= Math.round(speed/Math.sqrt(2));
      y -= Math.round(speed/Math.sqrt(2));
    } else if (direction == 0){
      x -= speed;
    } else if (direction == 1){
      x -= Math.round(speed/Math.sqrt(2));
      y += Math.round(speed/Math.sqrt(2));
    } else if (direction == 2){
      y += speed;
    } else if (direction == 3){
      x += Math.round(speed/Math.sqrt(2));
      y += Math.round(speed/Math.sqrt(2));
    }
    for (int i = 0; i < projectiles.size(); i++){
      projectiles.get(i).displace(direction, speed);
    }
  }
  
  public void displace(int [] displaceModifier){
    x -= displaceModifier[0];
    y -= displaceModifier[1];
    for (int i = 0; i < projectiles.size(); i++){
      projectiles.get(i).displace(displaceModifier);
    }
  }
}//class