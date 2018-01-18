public class SpeedyE extends ProjectileE {

  public SpeedyE(int health, int aggro, int x, int y, int reloadCap, int range, double speed) { 
    super(health, aggro, x, y,reloadCap, range, speed);
  }
  
  public void fire(int spawnX, int spawnY, int targetX, int targetY){
    if (!getStun()){
      EnemySpeedyProjectile projectile = new EnemySpeedyProjectile(spawnX, spawnY, targetX, targetY);
      addProjectile(projectile);
    }
  }
}
