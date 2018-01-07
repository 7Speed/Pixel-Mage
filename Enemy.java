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
  private boolean burn = false;
  private boolean slow = false;
  private boolean freeze = false;
  private boolean stun = false;
  private boolean decoy = false;
  private int burnCount = 0;
  private int slowCount = 0;
  private int freezeCount = 0;
  private int stunCount = 0;
  private int decoyCount = 0;
  private int decoyX = 0;
  private int decoyY = 0;
  
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
  
  public double getSpeed(){
    return speed;
  }
  
  public int getHealth(){
    return health;
  }
  
  public void damage(int damage){
    health -= damage;
  }
  
  public void setBurn(boolean burn){
    if (this.burn != burn){
      burnCount = 1;
    }
    this.burn = burn;
  }
  
  public boolean getBurn(){
    return burn;
  }
  
  public void setSlow(boolean slow){
    if (this.slow != slow){
      slowCount = 0;
    }
    this.slow = slow;
  }
  
  public boolean getSlow(){
    return slow;
  }
  
  public void setFreeze(boolean freeze){
    if (this.freeze != freeze){
      freezeCount = 0;
    }
    this.freeze = freeze;
  }
  
  public boolean getFreeze(){
    return freeze;
  }
  
  public void setStun(boolean stun){
    if (this.stun != stun){
      stunCount = 0;
    }
    this.stun = stun;
  }
  
  public boolean getStun(){
    return stun;
  }
  
  public void setDecoy(int decoyX, int decoyY){
    decoy = true;
    decoyCount = 1;
    this.decoyX = decoyX;
    this.decoyY = decoyY;
  }
  
  public boolean getDecoy(){
    return decoy;
  }
  
  public void setDecoyCoord(int decoyX, int decoyY){
    this.decoyX = decoyX;
    this.decoyY = decoyY;
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
  
  public void displace(int [] displaceModifier){
      x -= displaceModifier[0];
      y -= displaceModifier[1];
    }
  
  public void move (int x, int y){
    if (burnCount > 100){//Burn duration
      burn = false;
      burnCount = 0;
    } else if (burnCount != 0){
      burnCount++;
    }
    if (slowCount > 100){//Slow duration
      slow = false;
      slowCount = 0;
    } else {
      slowCount++;
    }
    if (freezeCount > 5){//Freeze duration
      freeze = false;
      freezeCount = 0;
    } else {
      freezeCount++;
    }
    
    if (stunCount > 50){
      stun = false;
      stunCount = 0;
    } else {
      stunCount++;
    }
    
    if (decoyCount > 100){//Burn duration
      decoy = false;
      decoyCount = 0;
    } else if (decoyCount != 0){
      decoyCount++;
    }
    
    if ((burnCount%20 == 0)&&(burnCount!=0)){
      health -= 2;
    }
    if (slow){
      speed = speed/2.0;
    }
    if ((!freeze)&&(!stun)){
      if (decoy){
        double addX;
        double addY;
        int xDiff = decoyX - this.x;
        int yDiff = decoyY - this.y;
        double travTime = Math.sqrt((xDiff*xDiff)+(yDiff*yDiff)) / speed;
        
        addX = xDiff/travTime;
        addY = yDiff/travTime;
        
        this.x += Math.round(addX*speed);
        this.y += Math.round(addY*speed);
        hitbox.setLocation(this.x,this.y);
      } else {
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
      }
    }
    if (slow){
      speed = speed*2;
    }
  }//movemethod
}//class
