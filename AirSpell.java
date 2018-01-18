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