public class GlassCannonE extends ProjectileE {

  public GlassCannonE(int health, int aggro, int x, int y, int reloadCap, int range, double speed) { 
    super(health, aggro, x, y,reloadCap, range, speed);
  }
  
  public void fire(int spawnX, int spawnY, int targetX, int targetY){
    if (!getStun()){
      EnemyGlassCannonProjectile projectile = new EnemyGlassCannonProjectile(spawnX, spawnY, targetX, targetY);
      addProjectile(projectile);
    }
  }
}
