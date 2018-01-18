import java.util.ArrayList;

class Paladin extends Player{
  static int statsPlaceholder = 100;
  Paladin(int positionX, int positionY, int health, double mana, int element){
    super(positionX, positionY, health, mana, element, 20, 3, 2, 3);
  }
  Paladin(int positionX, int positionY, int health, double mana, int element, ArrayList<Projectile> projectiles){
    super(positionX, positionY, health, mana, element, 20, 3, 2, 3);
    setProjectiles(projectiles);
  }
  public void fire(int spawnX, int spawnY, int targetX, int targetY, int element){
    if (element == 0){
      Sword sword = new Sword(spawnX, spawnY, targetX, targetY);
      addProjectile(sword);
    } else if (element == 1){
      FireSword swordCenter = new FireSword(spawnX, spawnY, targetX, targetY);
      double slopeCenter = (spawnY-targetY)*1.0/(spawnX-targetX);
      double slope1 = Math.tan(Math.atan(slopeCenter)+Math.PI/12);
      double slope2 = Math.tan(Math.atan(slopeCenter)-Math.PI/12);
      FireSword sword1 = null;
      FireSword sword2 = null;
      if (Math.abs(slopeCenter)<3){
        sword1 = new FireSword(spawnX, spawnY, targetX, (int)Math.round(slope1*targetX+spawnY-slope1*spawnX));
        sword2 = new FireSword(spawnX, spawnY, targetX, (int)Math.round(slope2*targetX+spawnY-slope2*spawnX));
      } else {
        sword1 = new FireSword(spawnX, spawnY, (int)Math.round((targetY-spawnY+slope1*spawnX)/slope1), targetY);
        sword2 = new FireSword(spawnX, spawnY, (int)Math.round((targetY-spawnY+slope2*spawnX)/slope2), targetY);
      }
      addProjectile(swordCenter);
      addProjectile(sword1);
      addProjectile(sword2);
    } else if (element == 3){
      EarthSword sword = new EarthSword(spawnX, spawnY, targetX, targetY);
      addProjectile(sword);
    } else if (element == 4){
      AirSword swordCenter = new AirSword(spawnX, spawnY, targetX, targetY);
      double slopeCenter = (spawnY-targetY)*1.0/(spawnX-targetX);
      double slope1 = Math.tan(Math.atan(slopeCenter)+Math.PI/12);
      double slope2 = Math.tan(Math.atan(slopeCenter)-Math.PI/12);
      AirSword sword1 = null;
      AirSword sword2 = null;
      if (Math.abs(slopeCenter)<3){
        sword1 = new AirSword(spawnX, spawnY, targetX, (int)Math.round(slope1*targetX+spawnY-slope1*spawnX));
        sword2 = new AirSword(spawnX, spawnY, targetX, (int)Math.round(slope2*targetX+spawnY-slope2*spawnX));
      } else {
        sword1 = new AirSword(spawnX, spawnY, (int)Math.round((targetY-spawnY+slope1*spawnX)/slope1), targetY);
        sword2 = new AirSword(spawnX, spawnY, (int)Math.round((targetY-spawnY+slope2*spawnX)/slope2), targetY);
      }
      addProjectile(swordCenter);
      addProjectile(sword1);
      addProjectile(sword2);
    } else if (element == 5){
      DarkSword sword = new DarkSword(spawnX, spawnY, targetX, targetY);
      DarkSword swordBehind = new DarkSword(spawnX, spawnY, 2*spawnX-targetX, 2*spawnY-targetY);
      addProjectile(sword);
      addProjectile(swordBehind);
    }
  }
  public int getManaCost(int element){
    if (element == 1){
      return 5;
    } else if (element == 2){
      return 2;
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
    