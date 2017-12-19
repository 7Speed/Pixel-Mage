/**
 * Auto Generated Java Class.
 */
import java.awt.Rectangle;
class Enemy {
  int health;
  int aggro;
  int x;
  int y;
  double speed = 2;
  private static int size = 40;
  private Rectangle hitbox;
  
  public Enemy (int health, int aggro, int x, int y) { 
    this.health = health;
    this.aggro = aggro;
    this.x = x;
    this.y = y;
    hitbox = new Rectangle(x, y, size, size);
  }
  
  public int getX(){
    return x;
  }
  
  public int getY(){
    return y;
  }
  
  public static int getSize(){
    return size;
  }
  
  public Rectangle getHitbox(){
    return hitbox;
  }
  
  public void displace(int direction, int speed){
    if (direction == 4){
      x += speed;
    } else if (direction == 5){
      x += Math.round(speed/Math.sqrt(2));
      y -= Math.round(speed/Math.sqrt(2));
    } else if (direction == 6){
      y -= speed;
    } else if (direction == 7){
      x -= Math.round(speed/Math.sqrt(2));
      y -= Math.round(speed/Math.sqrt(2));
    } else if (direction == 0){
      x -= speed;
    } else if (direction == 1){
      x -= Math.round(speed/Math.sqrt(2));
      y += Math.round(speed/Math.sqrt(2));
    } else if (direction == 2){
      y += speed;
    } else if (direction == 3){
      x += Math.round(speed/Math.sqrt(2));
      y += Math.round(speed/Math.sqrt(2));
    }
  }
  
  public void move (int x, int y){
    double addX;
    double addY;
    int xDiff = x - this.x;
    int yDiff = y - this.y;
    double travTime = Math.sqrt((xDiff*xDiff)+(yDiff*yDiff)) / speed;
      
    addX = xDiff/travTime;
    addY = yDiff/travTime;
    
    this.x += Math.round(addX*speed);
    this.y += Math.round(addY*speed);
    hitbox.setLocation(this.x,this.y);
  }//movemethod
}//class
