import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

class Game {

	public Archer player;
	public ArrayList<Enemy> enemies;
	private int reloadCount = 0;

	private Display test;// For testing only

	public Game() {
		player = new Archer(0, 0, 100, 100, 0);
		Enemy enemy1 = new Enemy(75, 40, 0, 0);
		enemies = new ArrayList<Enemy>();
		enemies.add(enemy1);
		enemies.add(new Enemy(75, 40, 300, 300));
		GameUpdate gu = new GameUpdate(this);
		Thread t = new Thread(gu);
		t.start();
	}

	public void update() {
		if (reloadCount < player.getReloadCap()) {
			reloadCount++;
		}
		/*
		 * if (draw.getLeftClick() && (reloadCount >= player.getReloadCap())) {
		 * player.fire(player.getX() + Player.getSize() / 2, player.getY() +
		 * Player.getSize() / 2, (int)
		 * MouseInfo.getPointerInfo().getLocation().getX(), (int)
		 * MouseInfo.getPointerInfo().getLocation().getY()); reloadCount = 0; }
		 */
		int centeredX = 40 / 2 - Player.getSize() / 2;
		int centeredY = 40 / 2 - Player.getSize() / 2;
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).move(player.getX() - centeredX, player.getY() - centeredY);
		}
		for (int i = 0; i < player.getProjectiles().size(); i++) {
			boolean collision = false;
			for (int j = 0; j < enemies.size(); j++) {
				if (enemies.get(j).getHitbox().intersects(player.getProjectiles().get(i).getHitbox())) {
					// System.out.println(enemies.get(j).getHitbox().getLocation()
					// + ": Enemy" + enemies.get(j).getHitbox().getHeight()
					// + ": Enemy" +
					// player.getProjectiles().get(i).getHitbox().getLocation()
					// + ": Proj" +
					// player.getProjectiles().get(i).getHitbox().getHeight()+
					// ": Proj");
					enemies.remove(j);
					collision = true;
				}
			}

			player.getProjectiles().get(i).move();
			if (player.getProjectiles().get(i).getLifeTime() > 500) {
				player.getProjectiles().remove(i);
			}
			if (collision) {
				player.getProjectiles().remove(i);
			}
		}
		/*
		 * draw.enemies(enemies); draw.projectiles(player.getProjectiles());
		 * draw.setCoord(new int[] { player.getX(), player.getY() });
		 * draw.repaint();
		 */
		if (Math.random() < 0.05) {
			enemies.add(new Enemy(75, 40, (int) (Math.random() * 400), (int) (Math.random() * 400) + 200));
		}
		try {
			Thread.sleep(10);
		} catch (Exception e) {
		}
		// Pause for 1 second
		if (test != null)
			test.repaint();
	}

	public void setDisplay(Display d) {
		test = d;
	}

	public Player getPlayer() {
		return player;
	}

}

class GameUpdate implements Runnable {

	private final Game g;

	public GameUpdate(Game g) {
		this.g = g;
	}

	@Override
	public void run() {
		long time;
		while (true) {
			time = System.currentTimeMillis();
			g.update();
			while (System.currentTimeMillis() - time < 100)
				;

		}
	}

}