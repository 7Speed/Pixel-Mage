class DarkDash extends Projectile{
  DarkDash(int spawnX, int spawnY, int targetX, int targetY){
    super(spawnX, spawnY, 15, targetX, targetY, 0, Player.getSize()+2, Player.getSize()+2);
  }
}