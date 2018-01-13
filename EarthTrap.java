class EarthTrap extends Trap{
  EarthTrap(int centerX, int centerY){
    super(centerX, centerY);
  }
  public EarthTrapExplosion explode(){
    return new EarthTrapExplosion(getSpawnX(), getSpawnY());
  }
}