class DarkDecoy extends Projectile{
  DarkDecoy(int centerX, int centerY){
    super(centerX, centerY, 0, centerX, centerY, 2, 20, 20);
  }
  public void move(){
    setLifeTime(getLifeTime()+1);
    super.getHitbox().setLocation(super.getX()-10, super.getY()-10);
  }
}