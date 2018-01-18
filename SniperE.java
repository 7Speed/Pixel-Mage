public class SniperE extends ProjectileE {

  public SniperE(int health, int aggro, int x, int y, int reloadCap, int range, double speed) { 
    super(health, aggro, x, y,reloadCap, range, speed);
  }
  
  public void fire(int spawnX, int spawnY, int targetX, int targetY){
    if (!getStun()){
      EnemySniperProjectile projectile = new EnemySniperProjectile(spawnX, spawnY, targetX, targetY);
      addProjectile(projectile);
    }
  }
}
