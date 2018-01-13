import java.util.ArrayList;

class Wizard extends Player{
  static int statsPlaceholder = 100;
  Wizard(int positionX, int positionY, int health, int mana, int element){
    super(positionX, positionY, health, mana, element, 10, 5);
  }
  Wizard(int positionX, int positionY, int health, int mana, int element, ArrayList<Projectile> projectiles){
    super(positionX, positionY, health, mana, element, 10, 5);
    setProjectiles(projectiles);
  }
  public void fire(int spawnX, int spawnY, int targetX, int targetY, int element){
    Bolt bolt = new Bolt(spawnX, spawnY, targetX, targetY);
    addProjectile(bolt);
  }
}
