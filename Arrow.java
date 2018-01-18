class Arrow extends Projectile {
 Arrow(int spawnX, int spawnY, int targetX, int targetY) {
  super(spawnX, spawnY, 50, targetX, targetY, 1, 10, 10);//only here for test
 }

 Arrow(int spawnX, int spawnY, int speed, int targetX, int targetY, int damage) {
  super(spawnX, spawnY, 8, targetX, targetY, 1, 10, 10);
 }
}