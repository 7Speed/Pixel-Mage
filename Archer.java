class Archer extends Player{
  static int statsPlaceholder = 100;
  Archer(int positionX, int positionY, int health, int mana, int element){
    super(positionX, positionY, health, mana, element, 10);
  }
  public void fire(int spawnX, int spawnY, int targetX, int targetY){
    Arrow arrow = new Arrow(spawnX, spawnY, targetX, targetY);
    addProjectile(arrow);
  }
}
    