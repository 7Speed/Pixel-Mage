public class BossE extends ProjectileE {

  public BossE(int health, int aggro, int x, int y, int reloadCap, int range, double speed) { 
    super(health, aggro, x, y,reloadCap, range, speed);
  }
  
  public void fire(int spawnX, int spawnY, int targetX, int targetY){
    if (!getStun()){
      EnemyBossProjectile projectile = new EnemyBossProjectile(spawnX, spawnY, targetX, targetY);
      addProjectile(projectile);
    }
  }
}
