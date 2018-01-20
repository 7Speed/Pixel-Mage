class Background{
  private int positionX;
  private int positionY;
  private static int width = 5120;
  private static int height = 3840;
  Background(int positionX, int positionY){
    this.positionX = positionX;
    this.positionY = positionY;
  }
  public int getX(){
    return positionX;
  }
  public int getY(){
    return positionY;
  }
  public static int getWidth(){
    return width;
  }
  public static int getHeight(){
    return height;
  }
  public void displace(int direction, int speed){
    if (direction == 4){
      positionX += speed;
    } else if (direction == 5){
      positionX += Math.round(speed/Math.sqrt(2));
      positionY -= Math.round(speed/Math.sqrt(2));
    } else if (direction == 6){
      positionY -= speed;
    } else if (direction == 7){
      positionX -= Math.round(speed/Math.sqrt(2));
      positionY -= Math.round(speed/Math.sqrt(2));
    } else if (direction == 0){
      positionX -= speed;
    } else if (direction == 1){
      positionX -= Math.round(speed/Math.sqrt(2));
      positionY += Math.round(speed/Math.sqrt(2));
    } else if (direction == 2){
      positionY += speed;
    } else if (direction == 3){
      positionX += Math.round(speed/Math.sqrt(2));
      positionY += Math.round(speed/Math.sqrt(2));
    }
  }
  
  public void displace(int [] displaceModifier){
    positionX -= displaceModifier[0];
    positionY -= displaceModifier[1];
  }
}