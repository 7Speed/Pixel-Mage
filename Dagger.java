class Dagger extends Projectile{
  Dagger(int spawnX, int spawnY, int targetX, int targetY){
    super(spawnX, spawnY, 8, targetX, targetY, 2, 10, 10);
  }
  Dagger(int spawnX, int spawnY, int speed, int targetX, int targetY, int damage){
    super(spawnX, spawnY, speed, targetX, targetY, damage, 10, 10);
  }
}