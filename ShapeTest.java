import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShapeTest extends JFrame implements KeyListener, MouseListener{
  public int x = 0;
  public int y = 0;
  private boolean dPressed = false;
  private boolean wPressed = false;
  private boolean aPressed = false;
  private boolean sPressed = false;
  private boolean leftClick = false;
  private int numEnemies;
  private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  private int numProjectiles;
  private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
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

  public void paint(Graphics g){
    super.paint(g);
    g.setColor(Color.BLUE);   
    //g.fillOval(40, 40, 60, 60); // circle
    //g.setColor(Color.RED);   
    g.drawRect(x, y, Player.getSize(), Player.getSize()); // square
    // g.drawRect(200, 100, 100, 200); // Rectangle
    // g.drawArc(35, 45, 75, 95, 0, 90);
    for (int i = 0; i < numEnemies; i++){
      g.drawRect((enemies.get(i)).getX(),(enemies.get(i)).getY(),Enemy.getSize(),Enemy.getSize());
      //g.drawRect((int)enemies.get(i).getHitbox().getX(), (int)enemies.get(i).getHitbox().getY(), 40, 40);
    }
    for (int i = 0; i < numProjectiles; i++){
      g.drawRect((projectiles.get(i)).getX(),(projectiles.get(i)).getY(),10,10);
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
  
  public void keyTyped(KeyEvent e) {}
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
    leftClick = true;
  }
  public void mouseReleased(MouseEvent e) {
    leftClick = false;
  }
}