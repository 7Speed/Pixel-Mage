import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

class Game{
  Player player;
  //Enemy enemy1 = new Enemy(75,40,50,60);
  ArrayList<Enemy> enemies;
  ArrayList<Wall> obstacles;
  ArrayList<Projectile> enemyProjectiles;
  Background background;
  int reloadCount;
  //enemies.add(enemy1);
  //enemies.add(new Enemy(75,40,300,300));
  Display draw;
  
  public Game(){
    player = new Archer((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-player.getSize()/2,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-player.getSize()/2,100,100,0);
    enemies = new ArrayList<Enemy>();
    obstacles = new ArrayList<Wall>();
    enemyProjectiles = new ArrayList<Projectile>();
    background = new Background(-Display.getBackgroundX()/2,-Display.getBackgroundY()/2);
    reloadCount = 0;
    GameUpdate gu = new GameUpdate(this);
    Thread t = new Thread(gu);
    t.start();
  }
  public void update(){
    /*
     Player player = new Archer((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2,100,100,0);
     Enemy enemy1 = new Enemy(75,40,50,60);
     ArrayList<Enemy> enemies = new ArrayList<Enemy>();
     ArrayList<Wall> obstacles = new ArrayList<Wall>();
     int reloadCount = 0;
     enemies.add(enemy1);
     enemies.add(new Enemy(75,40,300,300));
     Display draw = new Display(this);
     */
    if(draw==null)
      return;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    if ((draw.getClassNum() == 0) && !(player instanceof Archer)){
      player = new Archer(player.getX(), player.getY(), player.getHealth(), player.getMana(), player.getElement(), player.getProjectiles());
    } else if ((draw.getClassNum() == 1) && !(player instanceof Paladin)){
      player = new Paladin(player.getX(), player.getY(), player.getHealth(), player.getMana(), player.getElement(), player.getProjectiles());
    } else if ((draw.getClassNum() == 2) && !(player instanceof Rogue)){
      player = new Rogue(player.getX(), player.getY(), player.getHealth(), player.getMana(), player.getElement(), player.getProjectiles());
    } else if ((draw.getClassNum() == 3) && !(player instanceof Fighter)){
      player = new Fighter(player.getX(), player.getY(), player.getHealth(), player.getMana(), player.getElement(), player.getProjectiles());
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    if (draw.getWPressed() && draw.getDPressed()){//keypress w and d
      player.move(1,player.getSpeed(),enemies,obstacles,background);
    } else if (draw.getWPressed() && draw.getAPressed()){//keypress wa
      player.move(3,player.getSpeed(),enemies,obstacles,background);
    } else if (draw.getAPressed() && draw.getSPressed()){//keypress as
      player.move(5,player.getSpeed(),enemies,obstacles,background);
    } else if (draw.getSPressed() && draw.getDPressed()){//keypress sd
      player.move(7,player.getSpeed(),enemies,obstacles,background);
    } else if (draw.getDPressed()){//keypress d
      player.move(0,player.getSpeed(),enemies,obstacles,background);
    } else if (draw.getWPressed()){//keypress w
      player.move(2,player.getSpeed(),enemies,obstacles,background);
    } else if (draw.getAPressed()){//keypress a
      player.move(4,player.getSpeed(),enemies,obstacles,background);
    } else if (draw.getSPressed()){//keypress s
      player.move(6,player.getSpeed(),enemies,obstacles,background);
    }
    player.setElement(draw.getNumPressed());
    if (reloadCount < player.getReloadCap()){
      reloadCount++;
    }
    if (draw.getLeftClick() && (reloadCount >= player.getReloadCap())){
      //System.out.println(player.getElement());
      player.fire(player.getX() + Player.getSize()/2, player.getY() + Player.getSize()/2, (int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY(), player.getElement());
      reloadCount = 0;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    if (player.getSpeedBuffCount() > 100){//Buff duration
      player.setSpeedBuff(1);
      player.setSpeedBuffCount(0);
    } else if (player.getSpeedBuffCount() != 0){
      player.setSpeedBuffCount(player.getSpeedBuffCount() + 1);
    }
    if (player.getDexBuffCount() > 100){//Buff duration
      player.setDexBuff(1);
      player.setDexBuffCount(0);
    } else if (player.getDexBuffCount() != 0){
      player.setDexBuffCount(player.getDexBuffCount() + 1);
    }
    IceAura paladinSlow = null;
    if ((player instanceof Paladin) && (draw.getTwoPressed())){
      paladinSlow = new IceAura(player.getX(), player.getY());
      player.getProjectiles().add(paladinSlow);
    }
    if ((player instanceof Rogue) && (draw.getTwoPressed())){
      player.getProjectiles().add(new WaterDash(player.getX(), player.getY(), (int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY()));
      draw.setTwoPressed(false);
    }
    if ((player instanceof Rogue) && (draw.getThreePressed())){
      player.getProjectiles().add(new EarthStun(player.getX(), player.getY()));
      draw.setThreePressed(false);
    }
    if ((player instanceof Rogue) && (draw.getFourPressed())){
      player.setSpeedBuff(2);
      player.setDexBuff(2);
      draw.setFourPressed(false);
    }
    if ((player instanceof Rogue) && (draw.getFivePressed())){
      player.getProjectiles().add(new DarkDecoy(player.getX(), player.getY()));
      draw.setFivePressed(false);
    }
    if ((player instanceof Fighter) && (draw.getOnePressed())){
      player.getProjectiles().add(new FireFist(player.getX() + Player.getSize()/2, player.getY() + Player.getSize()/2, (int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY()));
      draw.setOnePressed(false);
    }
    if ((player instanceof Fighter) && (draw.getThreePressed())){
      player.setSpeedBuff(0.5);
      //player.setAttBuff(2);
      draw.setThreePressed(false);
    }
    if ((player instanceof Fighter) && (draw.getFivePressed())){
      player.getProjectiles().add(new DarkDash(player.getX(), player.getY(), (int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY()));
      draw.setFivePressed(false);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    int centeredX = Enemy.getSize()/2 - Player.getSize()/2;
    int centeredY = Enemy.getSize()/2 - Player.getSize()/2;
    for (int i = 0; i < enemies.size(); i++){
      enemies.get(i).debuffs();
      if ((enemies.get(i) instanceof ProjectileE) && (((ProjectileE)(enemies.get(i))).getReload() < ((ProjectileE)(enemies.get(i))).getReloadCap())){
        ((ProjectileE)(enemies.get(i))).addReload();
      }
      if (enemies.get(i) instanceof ProjectileE && ((ProjectileE)(enemies.get(i))).getDistance(player.getX()+Player.getSize()/2, player.getY()+Player.getSize()/2) <= ((ProjectileE)(enemies.get(i))).getRange()){
        if (((ProjectileE)(enemies.get(i))).getReload() >= ((ProjectileE)(enemies.get(i))).getReloadCap()){
          ((ProjectileE)(enemies.get(i))).fire(enemies.get(i).getX() + enemies.get(i).getSize()/2, enemies.get(i).getY() + enemies.get(i).getSize()/2, player.getX()+player.getSize()/2, player.getY()+player.getSize()/2);
          ((ProjectileE)(enemies.get(i))).wipeReload();
        }
        enemies.get(i).updateHitbox();
      }else{
        enemies.get(i).move(player.getX() - centeredX, player.getY() - centeredY); 
      }
      if (enemies.get(i).getHealth() <= 0){
        enemies.remove(i);
      }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    for (int i = 0; i < obstacles.size(); i++){
      obstacles.get(i).increaseLifeTime();
      for (int j = 0; j < player.getProjectiles().size(); j++){
        if (obstacles.get(i).getHitbox().intersects(player.getProjectiles().get(j).getHitbox())){
          player.getProjectiles().remove(j);
        }
      }
      if (obstacles.get(i).getHitbox().intersects(player.getHitbox())){
        double parityModifier = (player.getX()-(player.getY()-(obstacles.get(i).getCenterY()-obstacles.get(i).getSlope()*obstacles.get(i).getCenterX()))/obstacles.get(i).getSlope())/Math.abs(player.getX()-(player.getY()-(obstacles.get(i).getCenterY()-obstacles.get(i).getSlope()*obstacles.get(i).getCenterX()))/obstacles.get(i).getSlope());
        int [] displaceModifier = {(int)Math.round(parityModifier*player.getSpeed()*Math.cos(Math.atan(-1/-obstacles.get(i).getSlope()))),(int)Math.round(player.getSpeed()*parityModifier*Math.sin(Math.atan(-1/-obstacles.get(i).getSlope())))};
        player.move(displaceModifier, enemies, obstacles, background);
      }
      for (int j = 0; j < enemies.size(); j++){
        if (enemies.get(j) instanceof ProjectileE){
          for (int k = 0; k < ((ProjectileE)(enemies.get(j))).getProjectiles().size(); k++){
            if (obstacles.get(i).getHitbox().intersects(((ProjectileE)(enemies.get(j))).getProjectiles().get(k).getHitbox())){
              ((ProjectileE)(enemies.get(j))).getProjectiles().remove(k);
            }
          }
        }
        if (obstacles.get(i).getHitbox().intersects(enemies.get(j).getHitbox())){
          double parityModifier = (enemies.get(j).getX()-(enemies.get(j).getY()-(obstacles.get(i).getCenterY()-obstacles.get(i).getSlope()*obstacles.get(i).getCenterX()))/obstacles.get(i).getSlope())/Math.abs(enemies.get(j).getX()-(enemies.get(j).getY()-(obstacles.get(i).getCenterY()-obstacles.get(i).getSlope()*obstacles.get(i).getCenterX()))/obstacles.get(i).getSlope());
          int [] displaceModifier = {(int)Math.round(3*enemies.get(j).getSpeed()*-parityModifier*Math.cos(Math.atan(-1/obstacles.get(i).getSlope()))),(int)Math.round(3*enemies.get(j).getSpeed()*-parityModifier*Math.sin(Math.atan(-1/obstacles.get(i).getSlope())))};
          enemies.get(j).displace(displaceModifier);
          //System.out.println(displaceModifier[0] + " " + displaceModifier[1]);
        }
      }
      if (obstacles.get(i).getLifeTime() > 50){
        obstacles.remove(i);
      }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    for (int i = 0; i < player.getProjectiles().size(); i++){
      boolean collision = false;
      for (int j = 0; j < enemies.size(); j++){
        if ((enemies.get(j).getHitbox().intersects(player.getProjectiles().get(i).getHitbox()))&&((player.getProjectiles().get(i) instanceof FireExplosion)||(player.getProjectiles().get(i) instanceof FireDagger))){//Projectile damages the enemy
          enemies.get(j).setBurn(true);
          collision = true;
        } else if ((enemies.get(j).getHitbox().intersects(player.getProjectiles().get(i).getHitbox()))&&(player.getProjectiles().get(i) instanceof FireFist)){
          enemies.get(j).damage(player.getProjectiles().get(i).getDamage());
        } else if ((enemies.get(j).getHitbox().intersects(player.getProjectiles().get(i).getHitbox()))&&((player.getProjectiles().get(i) instanceof IceExplosion)||(player.getProjectiles().get(i) instanceof IceArrow)||(player.getProjectiles().get(i) instanceof IceAura)||(player.getProjectiles().get(i) instanceof IceFist))){
          enemies.get(j).setSlow(true);
          collision = true;
        } else if ((enemies.get(j).getHitbox().intersects(player.getProjectiles().get(i).getHitbox()))&&(player.getProjectiles().get(i) instanceof EarthArrow)){
          collision = true;
        } else if ((enemies.get(j).getHitbox().intersects(player.getProjectiles().get(i).getHitbox()))&&(player.getProjectiles().get(i) instanceof EarthStun)){
          enemies.get(j).setStun(true);
          collision = true;
        } else if ((enemies.get(j).getHitbox().intersects(player.getProjectiles().get(i).getHitbox()))&&((player.getProjectiles().get(i) instanceof AirArrow)||(player.getProjectiles().get(i) instanceof AirSword)||(player.getProjectiles().get(i) instanceof AirFist))){
          enemies.get(j).setFreeze(true);
          collision = true;
          if (player.getProjectiles().get(i) instanceof AirArrow){
            if (((AirArrow)(player.getProjectiles().get(i))).getSecondLifeTime() == 0){
              ((AirArrow)(player.getProjectiles().get(i))).setSecondLifeTime(1);
            }
          } else if (player.getProjectiles().get(i) instanceof AirSword){
            if (((AirSword)(player.getProjectiles().get(i))).getSecondLifeTime() == 0){
              ((AirSword)(player.getProjectiles().get(i))).setSecondLifeTime(1);
            }
          } else if (player.getProjectiles().get(i) instanceof AirFist){
            if (((AirFist)(player.getProjectiles().get(i))).getSecondLifeTime() == 0){
              ((AirFist)(player.getProjectiles().get(i))).setSecondLifeTime(1);
            }
          }
          double slope = (player.getProjectiles().get(i).getY() - player.getProjectiles().get(i).getSpawnY())*1.0/(player.getProjectiles().get(i).getX() - player.getProjectiles().get(i).getSpawnX());
          if (player.getProjectiles().get(i).getX() < player.getX()){
            int [] displaceModifier = {(int)Math.round(2*player.getProjectiles().get(i).getSpeed()*Math.cos(Math.atan(slope))),(int)Math.round(2*player.getProjectiles().get(i).getSpeed()*Math.sin(Math.atan(slope)))};
            enemies.get(j).displace(displaceModifier);
          } else {
            int [] displaceModifier = {-(int)Math.round(2*player.getProjectiles().get(i).getSpeed()*Math.cos(Math.atan(slope))),-(int)Math.round(2*player.getProjectiles().get(i).getSpeed()*Math.sin(Math.atan(slope)))};
            enemies.get(j).displace(displaceModifier);
          }
        } else if ((enemies.get(j).getHitbox().intersects(player.getProjectiles().get(i).getHitbox()))&&(player.getProjectiles().get(i) instanceof DarkArrow)){
          enemies.get(j).damage(player.getProjectiles().get(i).getDamage());
          collision = true;
        } else if (player.getProjectiles().get(i) instanceof DarkDecoy){//Global
          if (!enemies.get(j).getDecoy()){
            enemies.get(j).setDecoy(player.getProjectiles().get(i).getX(),player.getProjectiles().get(i).getY());
          } else {
            enemies.get(j).setDecoyCoord(player.getProjectiles().get(i).getX(),player.getProjectiles().get(i).getY());
          }
        } else if ((enemies.get(j).getHitbox().intersects(player.getProjectiles().get(i).getHitbox()))&&(collision==false)){
          //System.out.println(enemies.get(j).getHitbox().getLocation() + ": Enemy" + enemies.get(j).getHitbox().getHeight() + ": Enemy" + player.getProjectiles().get(i).getHitbox().getLocation() + ": Proj" + player.getProjectiles().get(i).getHitbox().getHeight()+ ": Proj");
          enemies.get(j).damage(player.getProjectiles().get(i).getDamage());
          collision = true;
        }
      }
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
      player.getProjectiles().get(i).move();
      if (player.getProjectiles().get(i) instanceof AirArrow){
        if (((AirArrow)(player.getProjectiles().get(i))).getSecondLifeTime() > 0){
          ((AirArrow)(player.getProjectiles().get(i))).setSecondLifeTime(((AirArrow)(player.getProjectiles().get(i))).getSecondLifeTime() + 1);
        }
      } else if (player.getProjectiles().get(i) instanceof AirSword){
        if (((AirSword)(player.getProjectiles().get(i))).getSecondLifeTime() > 0){
          ((AirSword)(player.getProjectiles().get(i))).setSecondLifeTime(((AirSword)(player.getProjectiles().get(i))).getSecondLifeTime() + 1);
        }
      } else if (player.getProjectiles().get(i) instanceof AirFist){
        if (((AirFist)(player.getProjectiles().get(i))).getSecondLifeTime() > 0){
          ((AirFist)(player.getProjectiles().get(i))).setSecondLifeTime(((AirFist)(player.getProjectiles().get(i))).getSecondLifeTime() + 1);
        }
      } else if ((player.getProjectiles().get(i) instanceof WaterDash)||(player.getProjectiles().get(i) instanceof DarkDash)){
        int [] displaceModifier = {player.getProjectiles().get(i).getX()-player.getX(), player.getProjectiles().get(i).getY()-player.getY()};
        player.move(displaceModifier, enemies, obstacles, background);
      } else if (player.getProjectiles().get(i) instanceof EarthStun){
        player.getProjectiles().get(i).setX(player.getX());
        player.getProjectiles().get(i).setY(player.getY());
      }
      ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
      boolean removeProjectile = false;
      if ((player.getProjectiles().get(i).getLifeTime() > 50) && (player.getProjectiles().get(i) instanceof Sword)){
        removeProjectile = true;
      }
      if ((player.getProjectiles().get(i).getLifeTime() > 25) && (player.getProjectiles().get(i) instanceof Dagger)){
        removeProjectile = true;
      }
      if ((player.getProjectiles().get(i).getLifeTime() > 25) && (player.getProjectiles().get(i) instanceof Fist)){
        if ((player.getProjectiles().get(i).getLifeTime() > 50) && (player.getProjectiles().get(i) instanceof FireFist)){
          removeProjectile = true;
        } else if (!(player.getProjectiles().get(i) instanceof FireFist)){
          removeProjectile = true;
        }
      }
      if (player.getProjectiles().get(i).getLifeTime() > 100){
        if (player.getProjectiles().get(i) instanceof FireArrow){
          player.getProjectiles().add(new FireExplosion(player.getProjectiles().get(i).getX(), player.getProjectiles().get(i).getY()));
        } else if (player.getProjectiles().get(i) instanceof IceArrow){
          player.getProjectiles().add(new IceExplosion(player.getProjectiles().get(i).getX(), player.getProjectiles().get(i).getY()));
        } else if (player.getProjectiles().get(i) instanceof EarthArrow){
          obstacles.add(new Wall(player.getProjectiles().get(i).getSpawnX(), player.getProjectiles().get(i).getSpawnY(), player.getProjectiles().get(i).getX(), player.getProjectiles().get(i).getY()));
        }
        removeProjectile = true;
      }
      if (collision && ((player.getProjectiles().get(i) instanceof Arrow)||(player.getProjectiles().get(i) instanceof Sword)||(player.getProjectiles().get(i) instanceof Dagger)||(player.getProjectiles().get(i) instanceof Fist))){
        if (player.getProjectiles().get(i) instanceof FireArrow){
          player.getProjectiles().add(new FireExplosion(player.getProjectiles().get(i).getX(), player.getProjectiles().get(i).getY()));
        }
        if (player.getProjectiles().get(i) instanceof IceArrow){
          player.getProjectiles().add(new IceExplosion(player.getProjectiles().get(i).getX(), player.getProjectiles().get(i).getY()));
        }
        if (player.getProjectiles().get(i) instanceof EarthArrow){
          obstacles.add(new Wall(player.getProjectiles().get(i).getSpawnX(), player.getProjectiles().get(i).getSpawnY(), player.getProjectiles().get(i).getX(), player.getProjectiles().get(i).getY()));
        }
        if (player.getProjectiles().get(i) instanceof DarkArrow){
          int [] displaceModifier = {player.getProjectiles().get(i).getX()-player.getX(), player.getProjectiles().get(i).getY()-player.getY()};
          player.move(displaceModifier, enemies, obstacles, background);
        }
        removeProjectile = true;
        if ((player.getProjectiles().get(i) instanceof AirArrow)||(player.getProjectiles().get(i) instanceof AirSword)||(player.getProjectiles().get(i) instanceof AirFist)){
          removeProjectile = false;
        }
      }
      if ((player.getProjectiles().get(i).getLifeTime() > 50) 
            && ((player.getProjectiles().get(i) instanceof FireExplosion) 
                  || (player.getProjectiles().get(i) instanceof IceExplosion) 
                  || (player.getProjectiles().get(i) instanceof WaterDash) 
                  || (player.getProjectiles().get(i) instanceof EarthStun) 
                  || (player.getProjectiles().get(i) instanceof DarkDecoy))){//Controls how long an aoe lasts
        removeProjectile = true;
      }
      if ((player.getProjectiles().get(i).getLifeTime() > 25)&&(player.getProjectiles().get(i) instanceof DarkDash)){
        removeProjectile = true;
      }
      if (player.getProjectiles().get(i) instanceof AirArrow){
        if (((AirArrow)(player.getProjectiles().get(i))).getSecondLifeTime() > 50){
          removeProjectile = true;
        }
      } else if (player.getProjectiles().get(i) instanceof AirSword){
        if (((AirSword)(player.getProjectiles().get(i))).getSecondLifeTime() > 50){
          removeProjectile = true;
        }
      } else if (player.getProjectiles().get(i) instanceof AirFist){
        if (((AirFist)(player.getProjectiles().get(i))).getSecondLifeTime() > 50){
          removeProjectile = true;
        }
      }
      if (removeProjectile){
        player.getProjectiles().remove(i);
      }
    }
    if (player.getProjectiles().indexOf(paladinSlow) >= 0){
      player.getProjectiles().remove(paladinSlow);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    draw.enemies(enemies);
    draw.projectiles(player.getProjectiles());
    enemyProjectiles.clear();
    for(int i=0; i<enemies.size(); i++){
      if (enemies.get(i) instanceof ProjectileE){
        for(int j=0; j< ((ProjectileE)(enemies.get(i))).getProjectiles().size(); j++){
          boolean playerColl = false; 
          ((ProjectileE)(enemies.get(i))).getProjectiles().get(j).move();
          if ((((ProjectileE)(enemies.get(i))).getProjectiles().get(j).getTargetX()==((ProjectileE)(enemies.get(i))).getProjectiles().get(j).getX()) && (((ProjectileE)(enemies.get(i))).getProjectiles().get(j).getTargetY()==((ProjectileE)(enemies.get(i))).getProjectiles().get(j).getY())){
            playerColl = true;
          }
          if (((ProjectileE)(enemies.get(i))).getProjectiles().get(j).getHitbox().intersects(player.getHitbox())){
            playerColl = true;
          }
          if (((ProjectileE)(enemies.get(i))).getProjectiles().get(j).getLifeTime() > 50){
            ((ProjectileE)(enemies.get(i))).getProjectiles().remove(j);
          }
          if (playerColl){
            ((ProjectileE)(enemies.get(i))).getProjectiles().remove(j);
            //playerColl = false;
          }
          enemyProjectiles.addAll(((ProjectileE)(enemies.get(i))).getProjectiles());
        }//enemy projectiles
      }
    }
    draw.enemyProjectiles(enemyProjectiles);
    draw.obstacles(obstacles);
    draw.background(background);
    draw.setCoord(new int[] {player.getX(), player.getY()});
    if (Math.random()<0.01){
      //enemies.add(new Enemy(5,40, (int)(Math.random()*400), (int)(Math.random()*400) + 200));
      enemies.add(new ProjectileE(5,40, (int)(Math.random()*400), (int)(Math.random()*400) + 200));
    }
    if (draw != null)
      draw.repaint();
  }
  public void setDisplay(Display d) {
    draw = d;
  }
}

class GameUpdate implements Runnable {
  
  private final Game g;
  
  public GameUpdate(Game g) {
    this.g = g;
  }
  
  @Override
  public void run() {
    long time;
    while (true) {
      time = System.currentTimeMillis();
      g.update();
      while (System.currentTimeMillis() - time < 10)
        ;
      
    }
  }
  
}