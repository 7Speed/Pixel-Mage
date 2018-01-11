import java.util.ArrayList;

class Rogue extends Player{
  static int statsPlaceholder = 100;
  Rogue(int positionX, int positionY, int health, int mana, int element){
    super(positionX, positionY, health, mana, element, 10, 7);
  }
  Rogue(int positionX, int positionY, int health, int mana, int element, ArrayList<Projectile> projectiles){
    super(positionX, positionY, health, mana, element, 10, 7);
    setProjectiles(projectiles);
  }
  public void fire(int spawnX, int spawnY, int targetX, int targetY, int element){
    if (element == 0){
      Dagger arrow = new Dagger(spawnX, spawnY, targetX, targetY);
      addProjectile(arrow);
    } else if (element == 1){
      FireDagger arrow = new FireDagger(spawnX, spawnY, targetX, targetY);
      addProjectile(arrow);
    } else if (element == 2){
      //Dash + Knockback
    } else if (element == 3){
      //Stun in range
    } else if (element == 4){
    } else if (element == 5){
      //cloak
      //DarkArrow arrow = new DarkArrow(spawnX, spawnY, targetX, targetY);
      //addProjectile(arrow);
    }
  }
}
    