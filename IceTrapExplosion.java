class IceTrapExplosion extends Projectile{
  IceTrapExplosion(int centerX, int centerY){
    super(centerX, centerY, 0, centerX, centerY, 2, 240, 240);
  }
  public void move(){
    setLifeTime(getLifeTime()+1);
    super.getHitbox().setLocation(super.getX()-120, super.getY()-120);
  }
}