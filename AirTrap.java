class AirTrap extends Trap{
  AirTrap(int centerX, int centerY){
    super(centerX, centerY);
  }
  public AirTrapExplosion explode(){
    return new AirTrapExplosion(getSpawnX(), getSpawnY());
  }
}