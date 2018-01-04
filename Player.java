import java.awt.Rectangle;
import java.util.ArrayList;

abstract class Player {
	private final double ROOT_TWO = Math.sqrt(2);

	static int healthMax = 100;
	static int manaMax = 100;
	private int positionX;
	private int positionY;
	private int health;
	private int mana;
	private int element;
	private int speed = 5;
	private static int size = 20;
	private Rectangle hitbox;
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private int reloadCap;
	private int xDir = 0, yDir = 0;

	Player(int positionX, int positionY, int health, int mana, int element, int reloadCap) {
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
		System.out.println(xDir);
		this.xDir = xDir;
	}

	public void setYDir(int yDir) {
		System.out.println(xDir);
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

	public void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
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

}