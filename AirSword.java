class AirSword extends Sword{
  private int secondLifeTime = 0;
  AirSword(int spawnX, int spawnY, int targetX, int targetY){
    super(spawnX, spawnY, 4, targetX, targetY, 4);
  }
  public int getSecondLifeTime(){
    return secondLifeTime;
  }
  public void setSecondLifeTime(int lifeTime){
    secondLifeTime = lifeTime;
  }
}