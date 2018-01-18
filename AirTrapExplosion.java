class AirTrapExplosion extends Projectile{
  AirTrapExplosion(int centerX, int centerY){
    super(centerX, centerY, 0, centerX, centerY, 0, 500, 500);
  }
  public void move(){
    setLifeTime(getLifeTime()+1);
    super.getHitbox().setLocation(super.getX()-250, super.getY()-250);
  }
}