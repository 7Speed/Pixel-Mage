import java.util.ArrayList;

class Fighter extends Player{
  static int statsPlaceholder = 100;
  Fighter(int positionX, int positionY, int health, int mana, int element){
    super(positionX, positionY, health, mana, element, 5, 7);
  }
  Fighter(int positionX, int positionY, int health, int mana, int element, ArrayList<Projectile> projectiles){
    super(positionX, positionY, health, mana, element, 5, 7);
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
}
    