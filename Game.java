import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

class Game{
  private boolean dPressed = false;
  public void game(){
    Archer player = new Archer(750,400,100,100,0);
    Enemy enemy1 = new Enemy(75,40,50,60);
    ProjectileE enemy2 = new ProjectileE(75,40,50,60);
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    ArrayList<Projectile> enemyProjectiles = new ArrayList<Projectile>();
    int reloadCount = 0;
    int enemyReload = 30;
    boolean playerColl = false;
    //enemies.add(enemy1);
    //enemies.add(new Enemy(75,40,300,300));
    //ranged.add(new ProjectileE(75,40,600,300));
    ShapeTest draw = new ShapeTest();
    while(true){
      if (draw.getWPressed() && draw.getDPressed()){//keypress w and d
        player.move(1,5);
      } else if (draw.getWPressed() && draw.getAPressed()){//keypress wa
        player.move(3,5);
      } else if (draw.getAPressed() && draw.getSPressed()){//keypress as
        player.move(5,5);
      } else if (draw.getSPressed() && draw.getDPressed()){//keypress sd
        player.move(7,5);
      } else if (draw.getDPressed()){//keypress d
        player.move(0,5);
      } else if (draw.getWPressed()){//keypress w
        player.move(2,5);
      } else if (draw.getAPressed()){//keypress a
        player.move(4,5);
      } else if (draw.getSPressed()){//keypress s
        player.move(6,5);
      }
      if (reloadCount < player.getReloadCap()){
        reloadCount++;
        enemyReload++;
      }
      if (draw.getLeftClick() && (reloadCount >= player.getReloadCap())){
        player.fire(player.getX() + Player.getSize()/2, player.getY() + Player.getSize()/2, (int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY());
        reloadCount = 0;
      }
      
      int centeredX = 40/2 - Player.getSize()/2;
      int centeredY = 40/2 - Player.getSize()/2;
      
      for (int i = 0; i < enemies.size(); i++){
        enemies.get(i).move(player.getX() - centeredX, player.getY() - centeredY);
        if (enemies.get(i) instanceof ProjectileE && ((ProjectileE)(enemies.get(i))).getReload() >= enemyReload ){
          ((ProjectileE)(enemies.get(i))).fire(enemies.get(i).getX() + enemies.get(i).getSize()/2, enemies.get(i).getY() + enemies.get(i).getSize()/2, player.getX(), player.getY());
          ((ProjectileE)(enemies.get(i))).wipeReload();
        }else{
          ((ProjectileE)(enemies.get(i))).addReload(); 
        }
      }
      
      for (int i = 0; i < player.getProjectiles().size(); i++){
        boolean collision = false;
        for (int j = 0; j < enemies.size(); j++){
          if (enemies.get(j).getHitbox().intersects(player.getProjectiles().get(i).getHitbox())){
            //System.out.println(enemies.get(j).getHitbox().getLocation() + ": Enemy" + enemies.get(j).getHitbox().getHeight() + ": Enemy" + player.getProjectiles().get(i).getHitbox().getLocation() + ": Proj" + player.getProjectiles().get(i).getHitbox().getHeight()+ ": Proj");
            enemies.remove(j);
            collision = true;
          }
          //if (((ProjectileE)(enemies.get(i))).getProjectiles().get(j).getHitbox().intersects(player.getHitbox())){
           // playerColl = true;
          //}
        }
        
        player.getProjectiles().get(i).move();
        
        if (player.getProjectiles().get(i).getLifeTime() > 500){
          player.getProjectiles().remove(i);
        }
        if (collision){
          player.getProjectiles().remove(i);
        }
        
      }
      draw.repaint();
      
      draw.enemies(enemies);
      draw.projectiles(player.getProjectiles());
      for(int i=0; i<enemies.size(); i++){
        if (enemies.get(i) instanceof ProjectileE){
          for(int j=0; j< ((ProjectileE)(enemies.get(i))).getProjectiles().size(); j++){
              ((ProjectileE)(enemies.get(i))).getProjectiles().get(j).move();
            if ( ((ProjectileE)(enemies.get(i))).getProjectiles().get(j).getLifeTime() > 50){
              ((ProjectileE)(enemies.get(i))).getProjectiles().remove(j);
            }
            if (playerColl){
              ((ProjectileE)(enemies.get(i))).getProjectiles().remove(j);
              playerColl = false;
            }
            enemyProjectiles.addAll(((ProjectileE)(enemies.get(i))).getProjectiles());
          }//enemy projectiles
        }else{
        }
      }

      draw.ranged(enemyProjectiles);
      draw.setCoord(new int[] {player.getX(), player.getY()});
      if (Math.random()<0.01){
        //enemies.add(new Enemy(75,40, (int)(Math.random()*400), (int)(Math.random()*400) + 200));
        enemies.add(new ProjectileE(75,40, (int)(Math.random()*400), (int)(Math.random()*400) + 200));
      }
      try{ Thread.sleep(10); }catch(Exception e) {};//Pause for 1 second
    }
  }
  
}