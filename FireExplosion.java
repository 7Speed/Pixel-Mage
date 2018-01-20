//import javafx.scene.shape.Circle;
class FireExplosion extends Projectile{
  //Circle hitbox;
  FireExplosion(int centerX, int centerY){
    super(centerX, centerY, 0, centerX, centerY, 2, 50, 50);
    //hitbox = new Circle(centerX, centerY, 25);
  }
  public void move(){
    setLifeTime(getLifeTime()+1);
    super.getHitbox().setLocation(super.getX()-25, super.getY()-25);
  }
}