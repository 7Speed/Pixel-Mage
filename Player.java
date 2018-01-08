import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

abstract class Player {
 private final double ROOT_TWO = Math.sqrt(2);

 static int healthMax = 100;
 static int manaMax = 100;
 private int positionX;
 private int positionY;
 private int health;
 private int mana;
 private int element;
 private int speed = 20;
 private static int size = 100;
 private static int half_size = size / 2;
 private Rectangle hitbox;
 private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
 private Queue<Projectile> remove;
 private int reloadCap;
 private int reloadCount = 0;
 private int xDir = 0, yDir = 0;
 private boolean isFiring = false;

 Player(int positionX, int positionY, int health, int mana, int element, int reloadCap) {
  projectiles = new ArrayList<Projectile>();
  remove = new LinkedList<Projectile>();
  this.positionX = positionX;
  this.positionY = positionY;
  this.health = health;
  this.mana = mana;
  this.element = element;
  this.reloadCap = reloadCap;
  hitbox = new Rectangle(positionX, positionY, size, size);
 }

 public int getSpeed() {
  return speed;
 }

 public int getX() {
  return positionX;
 }

 public int getY() {
  return positionY;
 }

 public static int getSize() {
  return size;
 }

 public void setXDir(int xDir) {
  this.xDir = xDir;
 }

 public void setYDir(int yDir) {
  this.yDir = yDir;
 }

 public void move() {
  if (xDir != 0 && yDir != 0) {
   positionX += (int) Math.floor(ROOT_TWO * (double) speed * (double) xDir);
   positionY += (int) Math.floor(ROOT_TWO * (double) speed * (double) yDir);
  } else {
   positionX += xDir * speed;
   positionY += yDir * speed;
  }
 }

 public ArrayList<Projectile> getProjectiles() {
  return projectiles;
 }

 public void removeProjectile(Projectile p) {
  remove.add(p);
 }

 public void updateProjectiles() {
  while (!remove.isEmpty())
   projectiles.remove(remove.remove());
 }

 public int getReloadCap() {
  return reloadCap;
 }

 public Rectangle getHitbox() {
  return hitbox;
 }

 public boolean inRenderRange(Enemy e, int xRange, int yRange) {
  boolean inXRange = e.getX() - Enemy.getHalfSize() <= getX() + xRange && e.getX() + Enemy.getHalfSize() >= getX() - xRange;
  boolean inYRange = e.getY() - Enemy.getHalfSize() <= getY() + yRange && e.getY() + Enemy.getHalfSize() >= getY() - yRange;
  return inXRange && inYRange;
 }

 public boolean inRenderRange(Projectile p, int xRange, int yRange) {
  boolean inXRange = p.getX() - Enemy.getHalfSize() <= getX() + xRange && p.getX() + Enemy.getHalfSize() >= getX() - xRange;
  boolean inYRange = p.getY() - Enemy.getHalfSize() <= getY() + yRange && p.getY() + Enemy.getHalfSize() >= getY() - yRange;
  return inXRange && inYRange;
 }

 public void setFiring(boolean b) {
  isFiring = b;
 }

 public void tryFire() {
  if (isFiring && reloadCount >= reloadCap) {
   fire(positionX + size / 2, positionY + size / 2, (int) MouseInfo.getPointerInfo().getLocation().getX() - Display.middleX + positionX, (int) MouseInfo.getPointerInfo().getLocation().getY() - Display.middleY + positionY);
   reloadCount = 0;
  } else
   reloadCount++;
 }

 abstract protected void fire(int spawnX, int spawnY, int targetX, int targetY);

}