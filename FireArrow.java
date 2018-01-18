/* AirArrow.java
 * Last edited January 17, 2018
 * This class represents the arrow that is launched of element air
 * @author Kamron Zaidi
 */
class FireArrow extends Arrow{
  FireArrow(int spawnX, int spawnY, int targetX, int targetY){
    super(spawnX, spawnY, 8, targetX, targetY, 2);
  }
}