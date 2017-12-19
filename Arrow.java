class Arrow extends Projectile{
  Arrow(int spawnX, int spawnY, int targetX, int targetY){
    super(spawnX, spawnY, 8, targetX, targetY, 1, 10, 10);
  }
  Arrow(int spawnX, int spawnY, int speed, int targetX, int targetY, int damage){
    super(spawnX, spawnY, 8, targetX, targetY, 1, 10, 10);
  }
}