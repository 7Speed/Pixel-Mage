abstract class Trap extends Projectile{
  Trap(int centerX, int centerY){
    super(centerX, centerY, 0, centerX, centerY, 0, 50, 50);
  }
  public void move(){
    setLifeTime(getLifeTime()+1);
    super.getHitbox().setLocation(super.getX()-25, super.getY()-25);
  }
  public abstract Projectile explode();
}