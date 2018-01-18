import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShapeTest extends JFrame implements KeyListener, MouseListener{//make many changes, etc jpanel
  public int x = 0;
  public int y = 0;
  private boolean dPressed = false;
  private boolean wPressed = false;
  private boolean aPressed = false;
  private boolean sPressed = false;
  private boolean leftClick = false;
  private boolean onePressed = false;
  private boolean twoPressed = false;
  private boolean threePressed = false;
  private boolean fourPressed = false;
  private boolean fivePressed = false;
  private int classNum = 0;
  private int numEnemies;
  private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  private int numProjectiles;
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  private int numObstacles;
  private ArrayList<Wall> obstacles = new ArrayList<Wall>();
  public ShapeTest(){
    addKeyListener(this);
    addMouseListener(this);
    setSize(Toolkit.getDefaultToolkit().getScreenSize());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);
  }
  public void setX(int x){
    this.x = x;
  }
  public void setY(int y){
    this.y = x;
  }
  public void setCoord(int [] coord){
    this.x = coord[0];
    this.y = coord[1];
  }
  
  public void enemies(ArrayList<Enemy> enemies){
    numEnemies = enemies.size();
    this.enemies = enemies;
  }
  
  public void projectiles(ArrayList<Projectile> projectiles){
    numProjectiles = projectiles.size();
    this.projectiles = projectiles;
  }
  
  public void obstacles(ArrayList<Wall> obstacles){
    numObstacles = obstacles.size();
    this.obstacles = obstacles;
  }
  
  public void paint(Graphics g){
    super.paint(g);
    if (classNum == 0){
      g.setColor(Color.BLUE); 
    } else if (classNum == 1) {
      g.setColor(Color.RED);
    } else {
      g.setColor(Color.BLACK);
    }
    //g.fillOval(40, 40, 60, 60); // circle
    //g.setColor(Color.RED);   
    g.drawRect(x, y, Player.getSize(), Player.getSize()); // square
    // g.drawRect(200, 100, 100, 200); // Rectangle
    // g.drawArc(35, 45, 75, 95, 0, 90);
    for (int i = 0; i < numEnemies; i++){
      g.setColor(Color.BLUE);
      g.drawRect((enemies.get(i)).getX(),(enemies.get(i)).getY(),Enemy.getSize(),Enemy.getSize());
      if (enemies.get(i).getBurn()){
        g.setColor(Color.RED);
        g.drawRect(enemies.get(i).getX(), enemies.get(i).getY()-10,8,8);
      }
      if (enemies.get(i).getSlow()){
        g.setColor(Color.BLUE);
        g.drawRect(enemies.get(i).getX()+10, enemies.get(i).getY()-10,8,8);
      }
      //g.drawRect((int)enemies.get(i).getHitbox().getX(), (int)enemies.get(i).getHitbox().getY(), 40, 40);
    }
    for (int i = 0; i < numObstacles; i++){
      g.setColor(new Color(156, 93, 82));
      g.drawPolygon(obstacles.get(i).getHitbox());
    }
    //Graphics2D[] projectileDrawings = new Graphics2D[numProjectiles];
    //g2d.setColor(Color.WHITE);
    //Rectangle rect2 = new Rectangle(100, 100, 20, 20);
    if ((classNum == 1) && (twoPressed)){
      g.setColor(Color.BLUE);
      g.drawRect(x-100+Player.getSize()/2,y-100+Player.getSize()/2,200,200);
    }
    for (int i = 0; i < projectiles.size(); i++){
      if ((projectiles.get(i).getClass().getName().equals("Arrow"))||(projectiles.get(i).getClass().getName().equals("Sword"))||(projectiles.get(i).getClass().getName().equals("Dagger"))){
        g.setColor(Color.GREEN);
        g.drawRect((projectiles.get(i)).getX()-5,(projectiles.get(i)).getY()-5,10,10);
      } else if (projectiles.get(i) instanceof FireArrow){
        g.setColor(Color.RED);
        g.drawRect((projectiles.get(i)).getX()-5,(projectiles.get(i)).getY()-5,10,10);
      } else if (projectiles.get(i) instanceof FireSword){
        g.setColor(Color.RED);
        g.drawRect((projectiles.get(i)).getX()-5,(projectiles.get(i)).getY()-5,10,10);
      } else if (projectiles.get(i) instanceof FireDagger){
        g.setColor(Color.RED);
        g.drawRect((projectiles.get(i)).getX()-5,(projectiles.get(i)).getY()-5,10,10);
      } else if (projectiles.get(i) instanceof FireExplosion){
        g.setColor(Color.RED);
        g.drawRect((projectiles.get(i)).getX()-25,(projectiles.get(i)).getY()-25,50,50);
      } else if (projectiles.get(i) instanceof IceArrow){
        g.setColor(Color.BLUE);
        g.drawRect((projectiles.get(i)).getX()-5,(projectiles.get(i)).getY()-5,10,10);
      } else if (projectiles.get(i) instanceof IceExplosion){
        g.setColor(Color.BLUE);
        g.drawRect((projectiles.get(i)).getX()-25,(projectiles.get(i)).getY()-25,50,50);
      } else if (projectiles.get(i) instanceof EarthArrow){
        g.setColor(new Color(156, 93, 82));
        g.drawRect((projectiles.get(i)).getX()-5,(projectiles.get(i)).getY()-5,10,10);
      } else if (projectiles.get(i) instanceof EarthStun){
        g.setColor(new Color(156, 93, 82));
        g.drawRect(x-100+Player.getSize()/2,y-100+Player.getSize()/2,200,200);
      } else if (projectiles.get(i) instanceof AirArrow){
        g.setColor(new Color(100, 224, 254));
        g.drawRect((projectiles.get(i)).getX()-5,(projectiles.get(i)).getY()-5,10,10);
      } else if (projectiles.get(i) instanceof AirSword){
        g.setColor(new Color(100, 224, 254));
        g.drawRect((projectiles.get(i)).getX()-5,(projectiles.get(i)).getY()-5,10,10);
      } else if (projectiles.get(i) instanceof DarkArrow){
        g.setColor(Color.BLACK);
        g.drawRect((projectiles.get(i)).getX()-5,(projectiles.get(i)).getY()-5,10,10);
      } else if (projectiles.get(i) instanceof DarkSword){
        g.setColor(Color.BLACK);
        g.drawRect((projectiles.get(i)).getX()-5,(projectiles.get(i)).getY()-5,10,10);
      } else if (projectiles.get(i) instanceof DarkDecoy){
        g.setColor(Color.BLACK);
        g.drawRect((projectiles.get(i)).getX()-10,(projectiles.get(i)).getY()-10,20,20);
      }
    }
  }
  
  public boolean getDPressed(){
    return dPressed;
  }
  public boolean getWPressed(){
    return wPressed;
  }
  public boolean getAPressed(){
    return aPressed;
  }
  public boolean getSPressed(){
    return sPressed;
  }
  public boolean getTwoPressed(){
    return twoPressed;
  }
  public void setTwoPressed(boolean twoPressed){
    this.twoPressed = twoPressed;
  }
  public boolean getThreePressed(){
    return threePressed;
  }
  public void setThreePressed(boolean threePressed){
    this.threePressed = threePressed;
  }
  public boolean getFourPressed(){
    return fourPressed;
  }
  public void setFourPressed(boolean fourPressed){
    this.fourPressed = fourPressed;
  }
  public boolean getFivePressed(){
    return fivePressed;
  }
  public void setFivePressed(boolean fivePressed){
    this.fivePressed = fivePressed;
  }
  public int getNumPressed(){
    if (!(((classNum == 1)&&(twoPressed))||((classNum == 2)&&(threePressed))||((classNum == 2)&&(fourPressed))||((classNum == 2)&&(fivePressed)))){
      if(onePressed){
        return 1;
      } else if (twoPressed){
        return 2;
      } else if (threePressed){
        return 3;
      } else if (fourPressed){
        return 4;
      } else if (fivePressed){
        return 5;
      } else {
        return 0;
      }
    } else if ((classNum == 1)&&(twoPressed)){
      if(onePressed){
        return 1;
      } else if (threePressed){
        return 3;
      } else if (fourPressed){
        return 4;
      } else if (fivePressed){
        return 5;
      } else {
        return 0;
      }
    } else if ((classNum == 2)&&(threePressed)){
      if(onePressed){
        return 1;
      } else if (twoPressed){
        return 2;
      } else if (fourPressed){
        return 4;
      } else if (fivePressed){
        return 5;
      } else {
        return 0;
      }
    } else if ((classNum == 2)&&(fourPressed)){
      if(onePressed){
        return 1;
      } else if (twoPressed){
        return 2;
      } else if (threePressed){
        return 3;
      } else if (fivePressed){
        return 5;
      } else {
        return 0;
      }
    } else {
      if(onePressed){
        return 1;
      } else if (twoPressed){
        return 2;
      } else if (threePressed){
        return 3;
      } else if (fourPressed){
        return 4;
      } else {
        return 0;
      }
    }
  }
  public int getClassNum(){
    return classNum;
  }
  
  public void keyTyped(KeyEvent e) {
    if(e.getKeyChar() == '1' ){
      onePressed = !onePressed;
      if ((classNum != 1)&&(classNum != 2)){
        twoPressed = false;
      }
      if (classNum != 2){
        threePressed = false;
        fourPressed = false;
        fivePressed = false;
      }
    }
    if(e.getKeyChar() == '2' ){
      twoPressed = !twoPressed;
      if ((classNum != 1)&&(classNum != 2)){
        onePressed = false;
        if (classNum != 2){
          threePressed = false;
          fourPressed = false;
          fivePressed = false;
        }
      }
    }
    if(e.getKeyChar() == '3' ){
      threePressed = !threePressed;
      if (classNum != 2){
        onePressed = false;
        if ((classNum != 1)&&(classNum != 2)){
          twoPressed = false;
        }
        fourPressed = false;
        fivePressed = false;
      }
    }
    if(e.getKeyChar() == '4' ){
      fourPressed = !fourPressed;
      if (classNum != 2){
        onePressed = false;
        if ((classNum != 1)&&(classNum != 2)){
          twoPressed = false;
        }
        if (classNum != 2){
          threePressed = false;
        }
        fivePressed = false;
      }
    }
    if(e.getKeyChar() == '5' ){
      fivePressed = !fivePressed;
      if (classNum != 2){
        onePressed = false;
        if ((classNum != 1)&&(classNum != 2)){
          twoPressed = false;
        }
        if (classNum != 2){
          threePressed = false;
        }
        fourPressed = false;
      }
    }
  }
  public void keyPressed(KeyEvent e) {
    if(e.getKeyChar() == 'd' )
      dPressed = true;
    if(e.getKeyChar() == 'w' )
      wPressed = true;
    if(e.getKeyChar() == 'a' )
      aPressed = true;
    if(e.getKeyChar() == 's' )
      sPressed = true;
  }
  public void keyReleased(KeyEvent e) {
    if(e.getKeyChar() == 'd' )
      dPressed = false;
    if(e.getKeyChar() == 'w' )
      wPressed = false;
    if(e.getKeyChar() == 'a' )
      aPressed = false;
    if(e.getKeyChar() == 's' )
      sPressed = false;
  }
  
  public boolean getLeftClick(){
    return leftClick;
  }
  public void fired(){
    leftClick = false;
  }
  public void mouseClicked(MouseEvent e) {}      
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1){
      leftClick = true;
    }
    if (e.getButton() == MouseEvent.BUTTON3){
      classNum++;
      if (classNum > 2){
        classNum = 0;
      }
    }
  }
  public void mouseReleased(MouseEvent e) {
    if (e.getButton() == MouseEvent.BUTTON1){
      leftClick = false;
    }
  }
}