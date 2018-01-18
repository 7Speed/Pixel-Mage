/* AirFist.java
 * Last edited January 17, 2018
 * This class represents the fist that is launched of element air
 * @author Kamron Zaidi
 */
class AirFist extends Fist{
  private int secondLifeTime = 0;
  AirFist(int spawnX, int spawnY, int targetX, int targetY){
    super(spawnX, spawnY, 8, targetX, targetY, 0);
  }
  public int getSecondLifeTime(){
    return secondLifeTime;
  }
  public void setSecondLifeTime(int lifeTime){
    secondLifeTime = lifeTime;
  }
}