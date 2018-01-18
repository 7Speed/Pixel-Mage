/* AirArrow.java
 * Last edited January 17, 2018
 * This class represents the arrow that is launched of element air
 * @author Kamron Zaidi
 */
class AirArrow extends Arrow{
  private int secondLifeTime = 0;
  /** AirArrow
    * Constructor for AirArrow class
    * @param spawnX An int storing the X location at which the arrow spawned
    * @param spawnY An int storing the Y location at which the arrow spawned
    * @param targetX An int storing the X coordinate where the arrow is heading towards
    * @param targetY An int storing the Y coordinate where the arrow is heading towards
    */
  AirArrow(int spawnX, int spawnY, int targetX, int targetY){
    super(spawnX, spawnY, 8, targetX, targetY, 4);
  }
  public int getSecondLifeTime(){
    return secondLifeTime;
  }
  public void setSecondLifeTime(int lifeTime){
    secondLifeTime = lifeTime;
  }
}