import java.util.ArrayList;

class Hunter extends Player{
  static int statsPlaceholder = 100;
  Hunter(int positionX, int positionY, int health, double mana, int element){
    super(positionX, positionY, health, mana, element, 10, 5, 1);
  }
  Hunter(int positionX, int positionY, int health, double mana, int element, ArrayList<Projectile> projectiles){
    super(positionX, positionY, health, mana, element, 10, 5, 1);
    setProjectiles(projectiles);
  }
  public void fire(int spawnX, int spawnY, int targetX, int targetY, int element){
    Arrow arrow = new Arrow(spawnX, spawnY, targetX, targetY);
    addProjectile(arrow);
  }
  public int getManaCost(int element){
    if (element == 1){
      return 20;
    } else if (element == 2){
      return 20;
    } else if (element == 3){
      return 20;
    } else if (element == 4){
      return 20;
    } else if (element == 5){
      return 0;
    } else {
      return 0;
    }
  }
}