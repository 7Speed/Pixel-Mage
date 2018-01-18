
/**
 * Auto Generated Java Class.
 */
import java.awt.Rectangle;

class Enemy {
 int health;
 int aggro;
 private int x;
 private int y;
 double speed = 3;
 private static int size = 100;
 private static int halfSize = size / 2;
 private Rectangle hitbox;

 public Enemy(int health, int aggro, int x, int y) {
  this.health = health;
  this.aggro = aggro;
  this.x = x;
  this.y = y;
  hitbox = new Rectangle(x, y, size, size);
 }

 public int getX() {
  return x;
 }

 public int getY() {
  return y;
 }

 public static int getSize() {
  return size;
 }

 public static int getHalfSize() {
  return halfSize;
 }

 public Rectangle getHitbox() {
  return hitbox;
 }

 public void move(int x, int y) {
  double addX;
  double addY;
  int xDiff = x - this.x;
  int yDiff = y - this.y;
  double travTime = Math.sqrt((xDiff * xDiff) + (yDiff * yDiff)) / speed;

  addX = xDiff / travTime;
  addY = yDiff / travTime;

  this.x += Math.round(addX * speed);
  this.y += Math.round(addY * speed);
  hitbox.setLocation(this.x, this.y);
 }// movemethod
}// class
