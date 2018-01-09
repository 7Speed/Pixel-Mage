import java.util.ArrayList;

class Hunter extends Player{
  static int statsPlaceholder = 100;
  Hunter(int positionX, int positionY, int health, int mana, int element){
    super(positionX, positionY, health, mana, element, 10);
  }
  Hunter(int positionX, int positionY, int health, int mana, int element, ArrayList<Projectile> projectiles){
    super(positionX, positionY, health, mana, element, 10);
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
}
    