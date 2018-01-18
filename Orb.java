class Orb extends Projectile{
  Orb(int centerX, int centerY){
    super(centerX, centerY, 0, centerX, centerY, 0, 40, 40);
  }
  public void move(){
    setLifeTime(getLifeTime()+1);
    super.getHitbox().setLocation(super.getX()-10, super.getY()-10);
  }
}