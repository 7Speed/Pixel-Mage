class FireTrap extends Trap{
  FireTrap(int centerX, int centerY){
    super(centerX, centerY);
  }
  public FireTrapExplosion explode(){
    return new FireTrapExplosion(getSpawnX(), getSpawnY());
  }
}