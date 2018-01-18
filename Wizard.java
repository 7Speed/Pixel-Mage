import java.util.ArrayList;

class Wizard extends Player{
  static int statsPlaceholder = 100;
  Wizard(int positionX, int positionY, int health, double mana, int element){
    super(positionX, positionY, health, mana, element, 20, 5, 2, 1);
  }
  Wizard(int positionX, int positionY, int health, double mana, int element, ArrayList<Projectile> projectiles){
    super(positionX, positionY, health, mana, element, 20, 5, 2, 1);
    setProjectiles(projectiles);
  }
  public void fire(int spawnX, int spawnY, int targetX, int targetY, int element){
    Bolt bolt = new Bolt(spawnX, spawnY, targetX, targetY);
    addProjectile(bolt);
  }
  public int getManaCost(int element){
    if (element == 1){
      return 50;
    } else if (element == 2){
      return 25;
    } else if (element == 3){
      return 25;
    } else if (element == 4){
      return 25;
    } else if (element == 5){
      return 50;
    } else {
      return 0;
    }
  }
}
