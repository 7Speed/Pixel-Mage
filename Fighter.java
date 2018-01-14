import java.util.ArrayList;

class Fighter extends Player{
  static int statsPlaceholder = 100;
  Fighter(int positionX, int positionY, int health, double mana, int element){
    super(positionX, positionY, health, mana, element, 5, 7, 1);
  }
  Fighter(int positionX, int positionY, int health, double mana, int element, ArrayList<Projectile> projectiles){
    super(positionX, positionY, health, mana, element, 5, 7, 1);
    setProjectiles(projectiles);
  }
  public void fire(int spawnX, int spawnY, int targetX, int targetY, int element){
    if (element == 0){
      Fist fist = new Fist(spawnX, spawnY, targetX, targetY);
      addProjectile(fist);
    } else if (element == 2){
      IceFist fist = new IceFist(spawnX, spawnY, targetX, targetY);
      addProjectile(fist);
    } else if (element == 4){
      AirFist fist = new AirFist(spawnX, spawnY, targetX, targetY);
      addProjectile(fist);
    }
  }
  public int getManaCost(int element){
    if (element == 1){
      return 100;
    } else if (element == 2){
      return 0;
    } else if (element == 3){
      return 10;
    } else if (element == 4){
      return 0;
    } else if (element == 5){
      return 25;
    } else {
      return 0;
    }
  }
}
    