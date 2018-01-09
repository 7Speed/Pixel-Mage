import java.awt.Rectangle;
abstract class Projectile{
  private int positionX;
  private int positionY;
  private int spawnX;
  private int spawnY;
  private int speed;
  private int targetX;
  private int targetY;
  private int damage;
  private Rectangle hitbox;
  private int lifeTime;
  private int deltaX;
  private int deltaY;
  private double distance;
  private double travelTime;
  private double addX;
  private double addY;
  private int width;
  private int height;
  Projectile(int spawnX, int spawnY, int speed, int targetX, int targetY, int damage, int width, int height){
    positionX = spawnX;
    positionY = spawnY;
    this.spawnX = spawnX;
    this.spawnY = spawnY;
    this.speed = speed;
    this.targetX = targetX;
    this.targetY = targetY;
    this.damage = damage;
    this.width = width;
    this.height = height;
    lifeTime = 0;
    hitbox = new Rectangle(positionX-height/2, positionY-width/2, width, height);
    deltaX = targetX - positionX;
    deltaY = targetY - positionY;
    distance = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
    addX = (deltaX * speed)/distance;
    addY = (deltaY * speed)/distance;
  }
  
  public int getX(){
    return positionX;
  }
  public void setX(int positionX){
    this.positionX = positionX;
  }
  public int getY(){
    return positionY;
  }
  public void setY(int positionY){
    this.positionY = positionY;
  }
  public int getSpawnX(){
    return spawnX;
  }
  public int getSpawnY(){
    return spawnY;
  }
  public int getLifeTime(){
    return lifeTime;
  }
  public void setLifeTime(int lifeTime){
    this.lifeTime = lifeTime;
  }
  public Rectangle getHitbox(){
    return hitbox;
  }
  public int getSpeed(){
    return speed;
  }
  public int getDamage(){
    return damage;
  }
  public int getWidth(){
    return width;
  }
  public int getHeight(){
    return height;
  }
  public void displace(int direction, int speed){
    if (direction == 4){
      positionX += speed;
      spawnX += speed;
    } else if (direction == 5){
      positionX += Math.round(speed/Math.sqrt(2));
      spawnX += Math.round(speed/Math.sqrt(2));
      positionY -= Math.round(speed/Math.sqrt(2));
      spawnY -= Math.round(speed/Math.sqrt(2));
    } else if (direction == 6){
      positionY -= speed;
      spawnY -= speed;
    } else if (direction == 7){
      positionX -= Math.round(speed/Math.sqrt(2));
      spawnX -= Math.round(speed/Math.sqrt(2));
      positionY -= Math.round(speed/Math.sqrt(2));
      spawnY -= Math.round(speed/Math.sqrt(2));
    } else if (direction == 0){
      positionX -= speed;
      spawnX -= speed;
    } else if (direction == 1){
      positionX -= Math.round(speed/Math.sqrt(2));
      spawnX -= Math.round(speed/Math.sqrt(2));
      positionY += Math.round(speed/Math.sqrt(2));
      spawnY += Math.round(speed/Math.sqrt(2));
    } else if (direction == 2){
      positionY += speed;
      spawnY += speed;
    } else if (direction == 3){
      positionX += Math.round(speed/Math.sqrt(2));
      spawnX += Math.round(speed/Math.sqrt(2));
      positionY += Math.round(speed/Math.sqrt(2));
      spawnY += Math.round(speed/Math.sqrt(2));
    }
    hitbox.setLocation(positionX-(int)hitbox.getWidth()/2, positionY-(int)hitbox.getHeight()/2);
    //deltaX = targetX - spawnX;
    //deltaY = targetY - spawnY;
    //distance = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
    //addX = (deltaX * speed)/distance;
    //addY = (deltaY * speed)/distance;
  }
  
  public void displace(int [] displaceModifier){
      positionX -= displaceModifier[0];
      spawnX -= displaceModifier[0];
      positionY -= displaceModifier[1];
      spawnY -= displaceModifier[1];
      hitbox.setLocation(positionX-(int)hitbox.getWidth()/2, positionY-(int)hitbox.getHeight()/2);
    }
  
  public void move(){
    lifeTime++;
    positionX = (int)Math.round(spawnX + addX * lifeTime);
    positionY = (int)Math.round(spawnY + addY * lifeTime);
    hitbox.setLocation(positionX-(int)hitbox.getWidth()/2, positionY-(int)hitbox.getHeight()/2);
  }
}