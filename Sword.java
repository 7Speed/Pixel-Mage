class Sword extends Projectile{
  Sword(int spawnX, int spawnY, int targetX, int targetY){
    super(spawnX, spawnY, 4, targetX, targetY, 4, 24, 24);
  }
  Sword(int spawnX, int spawnY, int speed, int targetX, int targetY, int damage){
    super(spawnX, spawnY, speed, targetX, targetY, damage, 24, 24);
  }
}