/* AirSpell.java
 * Last edited January 17, 2018
 * This class represents the spell that is cast of element air
 * @author Kamron Zaidi
 */
class AirSpell extends Projectile{
  private int secondLifeTime = 0;
  AirSpell(int spawnX, int spawnY, int targetX, int targetY){
    super(spawnX, spawnY, 4, targetX, targetY, 0, 50, 50);
  }
  public int getSecondLifeTime(){
    return secondLifeTime;
  }
  public void setSecondLifeTime(int lifeTime){
    secondLifeTime = lifeTime;
  }
}