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