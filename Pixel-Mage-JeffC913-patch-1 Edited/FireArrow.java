import java.util.ArrayList;
class FireArrow extends Arrow{
  FireArrow(int spawnX, int spawnY, int targetX, int targetY){
    super(spawnX, spawnY, 8, targetX, targetY, 2);
  }
  public ArrayList<Enemy> collision (ArrayList<Enemy> enemies){
    return enemies;
  }
}