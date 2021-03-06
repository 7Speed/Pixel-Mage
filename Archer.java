import java.util.ArrayList;

class Archer extends Player{
  static int statsPlaceholder = 100;
  Archer(int positionX, int positionY, int health, double mana, int element){
    super(positionX, positionY, health, mana, element, 10, 5, 1, 2);
  }
  Archer(int positionX, int positionY, int health, double mana, int element, ArrayList<Projectile> projectiles){
    super(positionX, positionY, health, mana, element, 10, 5, 1, 2);
    setProjectiles(projectiles);
  }
  public void fire(int spawnX, int spawnY, int targetX, int targetY, int element){
    if (element == 0){
      Arrow arrow = new Arrow(spawnX, spawnY, targetX, targetY);
      addProjectile(arrow);
    } else if (element == 1){
      FireArrow arrow = new FireArrow(spawnX, spawnY, targetX, targetY);
      addProjectile(arrow);
    } else if (element == 2){
      IceArrow arrow = new IceArrow(spawnX, spawnY, targetX, targetY);
      addProjectile(arrow);
    } else if (element == 3){
      EarthArrow arrow = new EarthArrow(spawnX, spawnY, targetX, targetY);
      addProjectile(arrow);
    } else if (element == 4){
      AirArrow arrow = new AirArrow(spawnX, spawnY, targetX, targetY);
      addProjectile(arrow);
    } else if (element == 5){
      DarkArrow arrow = new DarkArrow(spawnX, spawnY, targetX, targetY);
      addProjectile(arrow);
    }
  }
  public int getManaCost(int element){
    if (element == 1){
      return 5;
    } else if (element == 2){
      return 5;
    } else if (element == 3){
      return 5;
    } else if (element == 4){
      return 5;
    } else if (element == 5){
      return 5;
    } else {
      return 0;
    }
  }
}
    