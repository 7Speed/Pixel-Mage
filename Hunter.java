import java.util.ArrayList;

class Hunter extends Player{
  static int statsPlaceholder = 100;
  Hunter(int positionX, int positionY, int health, int mana, int element){
    super(positionX, positionY, health, mana, element, 10, 5);
  }
  Hunter(int positionX, int positionY, int health, int mana, int element, ArrayList<Projectile> projectiles){
    super(positionX, positionY, health, mana, element, 10, 5);
    setProjectiles(projectiles);
  }
  public void fire(int spawnX, int spawnY, int targetX, int targetY, int element){
    Arrow arrow = new Arrow(spawnX, spawnY, targetX, targetY);
    addProjectile(arrow);
  }
}