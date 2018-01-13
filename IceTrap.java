class IceTrap extends Trap{
  IceTrap(int centerX, int centerY){
    super(centerX, centerY);
  }
  public IceTrapExplosion explode(){
    return new IceTrapExplosion(getSpawnX(), getSpawnY());
  }
}