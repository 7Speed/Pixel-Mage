import java.awt.Rectangle;

abstract class Projectile {
 private int positionX;
 private int positionY;
 private int spawnX;
 private int spawnY;
 private int speed;
 private int targetX;
 private int targetY;
 private int damage;
 private Rectangle hitbox;
 private int lifeTime;
 private int deltaX;
 private int deltaY;
 private double distance;
 private double travelTime;
 private double addX;
 private double addY;
 private static int size = 50;
  private static int halfSize = size / 2;

 Projectile(int spawnX, int spawnY, int speed, int targetX, int targetY, int damage, int width, int height) {
  positionX = spawnX;
  positionY = spawnY;
  this.spawnX = spawnX;
  this.spawnY = spawnY;
  this.speed = speed;
  this.targetX = targetX;
  this.targetY = targetY;
  this.damage = damage;
  lifeTime = 0;
  hitbox = new Rectangle(positionX, positionY, width, height);
  deltaX = targetX - positionX;
  deltaY = targetY - positionY;
  distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
  addX = (deltaX * speed) / distance;
  addY = (deltaY * speed) / distance;
 }

 public int getX() {
  return positionX;
 }

 public int getY() {
  return positionY;
 }

 public int getLifeTime() {
  return lifeTime;
 }

 public Rectangle getHitbox() {
  return hitbox;
 }
 
  public static int getSize() {
  return size;
 }
 public static int getHalfSize() {
  return halfSize;
 }

 public void move() {
  lifeTime++;
  positionX = (int) Math.round(spawnX + addX * lifeTime);
  positionY = (int) Math.round(spawnY + addY * lifeTime);
  hitbox.setLocation(positionX, positionY);
 }
}