class IceAura extends Projectile{
  IceAura(int centerX, int centerY){
    super(centerX, centerY, 0, centerX, centerY, 2, 200, 200);
  }
  public void move(){
    //setLifeTime(getLifeTime()+1);
    super.getHitbox().setLocation(super.getX()-100, super.getY()-100);
  }
}