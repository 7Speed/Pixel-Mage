class Archer extends Player {
 static int statsPlaceholder = 100;

 Archer(int positionX, int positionY, int health, int mana, int element) {
  super(positionX, positionY, health, mana, element, 1);
 }

 protected void fire(int spawnX, int spawnY, int targetX, int targetY) {
  //System.out.println(spawnX+" "+spawnY+" "+targetX+" "+targetY);
  Arrow arrow = new Arrow(spawnX, spawnY, targetX, targetY);
  super.getProjectiles().add(arrow);
 }
}
