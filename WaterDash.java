class WaterDash extends Projectile{
  WaterDash(int spawnX, int spawnY, int targetX, int targetY){
    super(spawnX, spawnY, 20, targetX, targetY, 0, Player.getSize()+2, Player.getSize()+2);
  }
}