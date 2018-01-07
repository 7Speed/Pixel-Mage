import java.awt.Polygon;
import java.lang.Math;
class Wall{
  private Polygon hitbox;
  private static double width = 10;
  private static double length = 400;
  private int lifeTime = 0;
  private int centerX;
  private int centerY;
  private double slope;
  private int[] xpoints = new int[4];
  private int[] ypoints = new int[4];
  Wall(int spawnX, int spawnY, int targetX, int targetY){
    centerX = targetX;
    centerY = targetY;
    double arrowSlope = ((spawnY-targetY)*1.0)/(spawnX-targetX);
    slope = -1/arrowSlope;
    double arrowAngle = Math.atan(arrowSlope);
    double wallAngle = Math.atan(slope);
    double xLengthModifier = length/2*Math.cos(wallAngle);
    double xWidthModifier = Math.cos(arrowAngle)*width;
    double yLengthModifier = length/2*Math.sin(wallAngle);
    double yWidthModifier = Math.sin(arrowAngle)*width;
    xpoints[0] = (int)Math.round(targetX+xLengthModifier+xWidthModifier);
    ypoints[0] = (int)Math.round(targetY+yLengthModifier+yWidthModifier);
    
    xpoints[3] = (int)Math.round(targetX+xLengthModifier-xWidthModifier);
    ypoints[3] = (int)Math.round(targetY+yLengthModifier-yWidthModifier);
    
    xpoints[1] = (int)Math.round(targetX-xLengthModifier+xWidthModifier);
    ypoints[1] = (int)Math.round(targetY-yLengthModifier+yWidthModifier);
    
    xpoints[2] = (int)Math.round(targetX-xLengthModifier-xWidthModifier);
    ypoints[2] = (int)Math.round(targetY-yLengthModifier-yWidthModifier);
    
    hitbox = new Polygon(xpoints, ypoints, 4);
  }
  public Polygon getHitbox(){
    return hitbox;
  }
  public void increaseLifeTime(){
    lifeTime++;
  }
  public int getLifeTime(){
    return lifeTime;
  }
  public int getCenterX(){
    return centerX;
  }
  public int getCenterY(){
    return centerY;
  }
  public double getSlope(){
    return slope;
  }
  public void displace(int direction, int speed){
    int xpointsSum = 0;
    int ypointsSum = 0;
    for (int i = 0; i < xpoints.length; i++){
      if (direction == 4){
        xpoints[i] += speed;
      } else if (direction == 5){
        xpoints[i] += Math.round(speed/Math.sqrt(2));
      } else if (direction == 7){
        xpoints[i] -= Math.round(speed/Math.sqrt(2));
      } else if (direction == 0){
        xpoints[i] -= speed;
      } else if (direction == 1){
        xpoints[i] -= Math.round(speed/Math.sqrt(2));
      } else if (direction == 3){
        xpoints[i] += Math.round(speed/Math.sqrt(2));
      }
      xpointsSum+=xpoints[i];
    }
    for (int i = 0; i < ypoints.length; i++){
      if (direction == 5){
        ypoints[i] -= Math.round(speed/Math.sqrt(2));
      } else if (direction == 6){
        ypoints[i] -= speed;
      } else if (direction == 7){
        ypoints[i] -= Math.round(speed/Math.sqrt(2));
      } else if (direction == 1){
        ypoints[i] += Math.round(speed/Math.sqrt(2));
      } else if (direction == 2){
        ypoints[i] += speed;
      } else if (direction == 3){
        ypoints[i] += Math.round(speed/Math.sqrt(2));
      }
      ypointsSum+=ypoints[i];
    }
    centerX = xpointsSum/xpoints.length;
    centerY = ypointsSum/ypoints.length;
    hitbox = new Polygon(xpoints, ypoints, 4);
  }
  
  public void displace(int [] displaceModifier){
    int xpointsSum = 0;
    int ypointsSum = 0;
    for (int i = 0; i < xpoints.length; i++){
      xpoints[i] -= displaceModifier[0];
      ypoints[i] -= displaceModifier[1];
      xpointsSum+=xpoints[i];
      ypointsSum+=ypoints[i];
    }
    centerX = xpointsSum/xpoints.length;
    centerY = ypointsSum/ypoints.length;
    hitbox = new Polygon(xpoints, ypoints, 4);
  }
}